import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ChatServer extends UnicastRemoteObject implements ChatServerInt{

    private Vector v=new Vector();
    private ArrayList<ClientEntity> clients = new ArrayList<>();

    public ChatServer() throws RemoteException{}

    public boolean login(ChatClientInt a) throws RemoteException{
        System.out.println(a.getName() + "  got connected....");
        ClientEntity ce = new ClientEntity(a);
        clients.add(ce); // add client to list
        ce.writeTo("Welcome to the chat room!");
        serverMessage("new user has joined the chat!");
        return true;
    }

    public void publish(String s, ChatClientInt ci){
        System.out.println(s);
        for(int i=0; i<clients.size(); i++){
            if(!clients.get(i).getInterface().equals(ci)){
                clients.get(i).writeTo(s);
            }
        }
    }

    private void serverMessage(String message){
        for(int i=0; i<clients.size(); i++){
            clients.get(i).writeTo("[System]: " + message);
        }
    }

    public Vector getConnected() throws RemoteException{
        return v;
    }

    public void broadcast(String message, ChatClientInt ci) throws RemoteException{
        System.out.println("HELLO: " + message);
        if(message.equals(null))
            return;

        if(String.valueOf(message.charAt(0)).equals("/")){
            System.out.println("USER SENT A COMMAND!");
        }

    }


}