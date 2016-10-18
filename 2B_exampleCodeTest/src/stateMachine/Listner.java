package stateMachine;

import java.io.BufferedReader;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Gurris on 2016-10-18.
 */
public class Listner extends Thread{

    private ServerSocket sock = null;
    private BufferedReader in = null;
    private boolean alive = true;
    private static int PORT = 10017;
    private Main server = null; //-------------CHANGE TO ANOTHER CLASS!

    public Listner(Main server){
        this.server = server;
        try{
            sock = new ServerSocket(PORT);
        }catch (Exception e){
            System.out.println("Could not create socket");
        }
    }

    public void run() {
        System.out.println("Listner started.");

        try{
            Socket s = sock.accept();
            if(!server.isBusy()){
                server.setBusy(true);


            }


        }catch (Exception e){

        }









    }


}
