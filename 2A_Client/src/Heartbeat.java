/**
 * Created by Gurris on 2016-10-14.
 */
public class Heartbeat extends Thread {

    private Client c;

    public Heartbeat(Client c){
        this.c = c;
        c.setServerStatusIsUp(false);
    }

    public void run(){

        while(true){
            try{

                if(c.getServerStatusIsUp()){

                    c.setGotAlive(false);
                    Thread.sleep(15000);
                    if(!c.gotAlive()){ // har inte fått /alive status från server innom 15 sec. Betyder att något är fel och server är offline.(Troligtvis)
                        c.setServerStatusIsUp(false);
                        c.serverCrash();
                    }

                }else{
                    Thread.sleep(15000);
                }

            }catch (Exception e){
                System.out.println("Heartbeat encountered a error");
            }

        }

    }

}
