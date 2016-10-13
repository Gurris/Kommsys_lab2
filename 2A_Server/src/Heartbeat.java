import java.util.ArrayList;

/**
 * Created by Gurris on 2016-10-13.
 */
public class Heartbeat extends  Thread{
    private Server server = null;
    private ArrayList<ClientEntity> clients = null;

    public Heartbeat(Server server){
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
