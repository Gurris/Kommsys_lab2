import Interface.*;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Server extends UnicastRemoteObject implements Interface.IServer {

    private ArrayList<ClientEntity> clients = new ArrayList<>();

    public Server() throws RemoteException{
        Heartbeat hb = new Heartbeat(this);
        hb.start();
    }

    private void serverMessage(String message){
        broadcast("[System]: " + message);
    }

    public boolean login(IClient a) throws RemoteException{
        ClientEntity ce = new ClientEntity(a);
        clients.add(ce); // add client to list
        ce.writeTo("Welcome to the chat room!");
        serverMessage("new user has joined the chat!");
        return true;
    }

    public ArrayList<ClientEntity> getClients() throws RemoteException{
        return clients;
    }

    public synchronized void disconnect(IClient ci){
        for(int i=0; i<clients.size(); i++){
            if(clients.get(i).getInterface().equals(ci)){
                System.out.println("Removing client: " + clients.get(i).getNickname());
                clients.remove(i);
                break;
            }
        }
    }

    public void broadcast(String message, IClient ci){ // TO ALL EXCEPT SENDER
        if(message.equals(null))
            return;

        if(String.valueOf(message.charAt(0)).equals("/")){
            commandHandler(message, ci);
        }else{
            message = messageFormat(message, ci);
            for(int i=0; i<clients.size(); i++){
                if(!clients.get(i).getInterface().equals(ci)){
                    try{
                        clients.get(i).writeTo(message);
                    }catch (Exception e){
                        System.out.println("Could not write to client: " + clients.get(i).getNickname()+". --Removing!");
                        disconnect(clients.get(i).getInterface());
                    }

                }

            }
        }
    }

    public void broadcast(String message){ // TO ALL
        if(message.equals(null))
            return;

        for(int i=0; i<clients.size(); i++){
            try{
                clients.get(i).writeTo(message);
            }catch (Exception e){
                System.out.println("Could not write to client: " + clients.get(i).getNickname()+". --Removing!");
                disconnect(clients.get(i).getInterface());
            }
        }
    }

    public void singleClientMsg(String message, ClientEntity client){
        try{
            client.writeTo(message);
        }catch (Exception e){
            System.out.println("Could not write to client! Removing");
            disconnect(client.getInterface());
        }
    }

    private String messageFormat(String message, IClient ci){
        String formatMessage = "";
        ClientEntity tmp = getClient(ci);
        if(tmp != null){
            formatMessage = "[" + tmp.getNickname() + "]: " + message;
            return formatMessage;
        }
        return null;
    }

    private synchronized void commandHandler(String commandString, IClient ci){
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
                singleClientMsg("[System]: " + users, tmp);
                break;
            case "/nick":
                for(int i=0; i<clients.size(); i++){
                    if(command[1].toLowerCase().equals(clients.get(i).getNickname().toLowerCase()) || command[1].toLowerCase().contains("system") || command[1].toLowerCase().contains("you")){
                        singleClientMsg("[System]: Invalid nickname!", tmp);
                        return;
                    }
                }
                tmp.setNickname(command[1]);
                break;
            case "/help":
                singleClientMsg("[System]: " + "/quit - leave, /who - list of all users, /nick <nickname> - change your nickname, /help - list of available commands", tmp);
                break;
            default:
                System.out.println("Client sent invalid command!");
                break;
        }

    }

    private ClientEntity getClient(IClient ci){
        for(int i=0; i<clients.size(); i++){ // find correct client
            if(clients.get(i).getInterface().equals(ci)){
                return clients.get(i);
            }
        }
        return null;
    }

}