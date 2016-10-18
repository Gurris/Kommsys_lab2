package stateMachine;

/**
 * Created by Gurris on 2016-10-17.
 */
public abstract class ClientState {

    public ClientState sendInvite(String invite_to_someone){
        return null;
    }

    public ClientState reciveInvite(String invite_from_someone){
        return null;
    }

    public ClientState sendTRO(){
        return null;
    }

    public ClientState reciveTRO(){
        return null;
    }

    public ClientState sendACK(){
        return null;
    }

    public ClientState reciveACK(){
        return null;
    }

    public ClientState sendBye(){
        return null;
    }

    public ClientState reciveBye(){
        return null;
    }

    public ClientState reciveOk(){
        return null;
    }

    public String currentState(){
        return null;
    }

    public boolean validEvent(events e){
        return false;
    }



}
