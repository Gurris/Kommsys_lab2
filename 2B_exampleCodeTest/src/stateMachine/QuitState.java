package stateMachine;

/**
 * Created by Gurris on 2016-10-17.
 */
public class QuitState extends ClientState{

    @Override
    public ClientState reciveOk() {
        return new IdleState();
    }

    @Override
    public String currentState() {
        return "QUIT";
    }

    @Override
    public boolean validEvent(events e) {
        if(e.equals(events.OK))
            return true;
        return false;
    }
}
