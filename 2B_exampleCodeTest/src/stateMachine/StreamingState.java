package stateMachine;

/**
 * Created by Gurris on 2016-10-17.
 */
public class StreamingState extends ClientState{

    @Override
    public ClientState sendBye() {
        return new QuitState();
    }

    @Override
    public ClientState reciveBye() {
        return new IdleState();
    }

    @Override
    public String currentState() {
        return "STREAMING";
    }

    @Override
    public boolean validEvent(events e) {
        if(e.equals(events.RECIVE_BYE) || e.equals(events.SEND_BYE))
            return true;
        return false;
    }
}
