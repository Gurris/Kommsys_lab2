import Interface.IServer;

import javax.swing.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements Interface.IClient {

    private GUI ui;
    private IServer server;
    private boolean serverStatusIsUp;
    private boolean recivedAlive;

    public Client() throws RemoteException {
        ui = new GUI(this);
        Heartbeat hb = new Heartbeat(this);
        hb.start();
    }

    public void writeTo(String st) throws RemoteException{
        String s = Character.toString(st.charAt(0));
        System.out.println("Received: " + st);
        if(s.equals("/")){
            commands(st);
        }else{
            ui.writeMsg(st);
        }
    }

    private void commands(String commandString){
        String[] command = commandString.split(" ");
        switch (command[0].toLowerCase()){

            case "/alive":
                setGotAlive(true);
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
            setServerStatusIsUp(true);
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public void disconnectFromServer(){
        try{
            server.disconnect(this);
            setServerStatusIsUp(false);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void broadcast(String message){
        try{
            server.broadcast(message, this);
        }catch (Exception e){
            System.out.println("Could not send message to server. Did it crash?");
            disconnectFromServer();
            serverCrash();
        }
    }

    public void serverCrash(){
        ui.disconnectedFromServer();
    }

    public synchronized void setServerStatusIsUp(boolean status){
        serverStatusIsUp = status;
    }

    public synchronized boolean getServerStatusIsUp(){
        return serverStatusIsUp;
    }

    public synchronized boolean gotAlive(){
        return recivedAlive;
    }

    public synchronized void setGotAlive(boolean status){
        recivedAlive = status;
    }

}