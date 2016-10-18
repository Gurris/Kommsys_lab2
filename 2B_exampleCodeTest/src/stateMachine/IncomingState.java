package stateMachine;

/**
 * Created by Gurris on 2016-10-17.
 */
public class IncomingState extends ClientState{


    @Override
    public ClientState reciveACK() {

        return new StreamingState();
    }

    @Override
    public String currentState() {
        return "INCOMING";
    }

    @Override
    public boolean validEvent(events e) {
        if(e.equals(events.RECIVE_ACK)){
            return true;
        }
        return false;
    }
}
