package stateMachine;

/**
 * Created by Gurris on 2016-10-17.
 */
public class OutgoingState extends ClientState{

    @Override
    public ClientState reciveTRO() {
        return this.sendACK();
    }

    @Override
    public ClientState sendACK() {
        return new StreamingState();
    }

    @Override
    public String currentState() {
        return "OUTGOING";
    }

    @Override
    public boolean validEvent(events e) {
        if(e.equals(events.RECIVE_TRO) || e.equals(events.SEND_ACK))
            return true;
        return false;
    }
}
