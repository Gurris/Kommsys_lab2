import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ChatServer extends UnicastRemoteObject implements ChatServerInt{

    private Vector v=new Vector();
    private ArrayList<ClientEntity> clients = new ArrayList<>();

    public ChatServer() throws RemoteException{
        heartbeat hb = new heartbeat(this);
        hb.start();
    }

    public boolean login(ChatClientInt a) throws RemoteException{
        System.out.println(a.getName() + "  got connected....");
        ClientEntity ce = new ClientEntity(a);
        clients.add(ce); // add client to list
        ce.writeTo("Welcome to the chat room!");
        serverMessage("new user has joined the chat!");
        return true;
    }

    private void serverMessage(String message){
        for(int i=0; i<clients.size(); i++){
            clients.get(i).writeTo("[System]: " + message);
        }
    }


    public ArrayList<ClientEntity> getClients() throws RemoteException{
        return clients;
    }

    public void disconnect(ChatClientInt ci){   // put method in interface!
        for(int i=0; i<clients.size(); i++){
            if(clients.get(i).getInterface().equals(ci)){
                System.out.println("Removing client: " + clients.get(i).getNickname());
                clients.remove(i);
            }
        }
    }

    public void broadcast(String message, ChatClientInt ci) throws RemoteException{

        if(message.equals(null))
            return;

        if(String.valueOf(message.charAt(0)).equals("/")){
            commandHandler(message, ci);
        }else{
            message = messageFormat(message, ci);
            for(int i=0; i<clients.size(); i++){
                if(!clients.get(i).getInterface().equals(ci)){
                    clients.get(i).writeTo(message);
                }

            }
        }

    }
    private String messageFormat(String message, ChatClientInt ci){
        String formatMessage = "";
        ClientEntity tmp = getClient(ci);
        if(tmp != null){
            formatMessage = "[" + tmp.getNickname() + "]: " + message;
            return formatMessage;
        }
        return null;
    }

    private void commandHandler(String commandString, ChatClientInt ci){
        ClientEntity tmp = getClient(ci);
        if(tmp.equals(null))
            return;

        String[] command = commandString.split(" ");
        switch (command[0].toLowerCase()){

            case "/alive":
                tmp.setAlive(true);
                break;
            case "/quit":
                disconnect(ci);
                break;
            case "/who":
                String users = "";
                for(int i=0; i<clients.size(); i++){
                    users += clients.get(i).getNickname() + ", ";
                }
                tmp.writeTo("[System]: " + users);
                break;
            case "/nick":
                for(int i=0; i<clients.size(); i++){
                    if(command[1].toLowerCase().equals(clients.get(i).getNickname().toLowerCase()) || command[1].toLowerCase().contains("system") || command[1].toLowerCase().contains("you")){
                        tmp.writeTo("[System]: Invalid nickname!");
                        return;
                    }
                }
                tmp.setNickname(command[1]);
                break;
            case "/help":
                tmp.writeTo("[System]: " + "/quit - leave, /who - list of all users, /nick <nickname> - change your nickname, /help - list of available commands");
                break;
            default:
                System.out.println("Client sent invalid command!");
                break;
        }

    }

    private ClientEntity getClient(ChatClientInt ci){
        for(int i=0; i<clients.size(); i++){ // find correct client
            if(clients.get(i).getInterface().equals(ci)){
                return clients.get(i);
            }
        }
        return null;
    }

}





class heartbeat extends Thread{

    private ChatServer server = null;
    private ArrayList<ClientEntity> clients = null;

    public heartbeat(ChatServer server){
        this.server = server;
    }

    public void run(){
        while(true){
            try{
                clients = server.getClients();
                if(clients.size() != 0){
                    setFalse(clients);
                    for(int i=0; i<clients.size(); i++){
                        clients.get(i).writeTo("/alive");
                    }
                    Thread.sleep(10000); // will sleep for 10 seconds. If clients have not responded in time they will be removed
                    for(int i=0; i<clients.size(); i++){
                        if(!clients.get(i).isAlive()){
                            server.disconnect(clients.get(i).getInterface());
                        }
                    }
                }else {
                    Thread.sleep(10000);
                }

            }catch (Exception e){
                System.out.println("Exception in heartbeat thread: " + e);
            }
        }

    }

    private void setFalse(ArrayList<ClientEntity> cl){
        for(int i=0; i<cl.size(); i++){
            cl.get(i).setAlive(false);
        }
    }

}