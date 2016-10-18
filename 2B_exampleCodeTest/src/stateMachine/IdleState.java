package stateMachine;

import java.util.Scanner;

/**
 * Created by Gurris on 2016-10-17.
 */
public class IdleState extends ClientState{

    public IdleState(){
        System.out.println("CURRENT STATE IS IDLE!");
    }

    @Override
    public ClientState sendInvite(String invite_to_someone) {
        return new OutgoingState();
    }

    @Override
    public ClientState reciveInvite(String invite_form_someone) {
        System.out.println("INCOMMMING CALL!");
        System.out.println("ACCEPT - y");
        System.out.println("DECLINE - n");
        Scanner s = new Scanner(System.in);
        if(s.nextLine().equals("y")){
            return new IncomingState();
        }else{
            return new IdleState();
        }
    }

    @Override
    public String currentState() {
        return "IDLE";
    }

    @Override
    public boolean validEvent(events e) {
        if(e.equals(events.SEND_INVITE) || e.equals(events.RECIVE_INVITE) || e.equals(events.SEND_TRO)){ // accepts INVITE and TRO
            return true;
        }
        return false;
    }
}
