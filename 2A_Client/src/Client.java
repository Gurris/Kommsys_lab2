import Interface.IServer;

import javax.swing.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements Interface.IClient {

    private GUI ui;
    private IServer server;

    public Client() throws RemoteException {
        ui = new GUI(this);
    }

    public void writeTo(String st) throws RemoteException{
        String s = Character.toString(st.charAt(0));
        System.out.println("Received: " + st);
        if(s.equals("/")){
            System.out.println("COMMAND!");
            commands(st);
        }else{
            ui.writeMsg(st);
        }
    }

    private void commands(String commandString){
        String[] command = commandString.split(" ");
        switch (command[0].toLowerCase()){

            case "/alive":
                broadcast("/alive");
                break;
            default:
                System.out.println("Not valid command from server");
                break;
        }
    }

    public boolean connectToServer(String ip){
        try{
            server=(IServer)Naming.lookup("rmi://"+ ip +"/chat");
            server.login(this);
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public void disconnectFromServer(){
        try{
            server.disconnect(this);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void broadcast(String message){
        try{
            server.broadcast(message, this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}