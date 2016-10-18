package stateMachine;

/**
 * Created by Gurris on 2016-10-17.
 */
public class ClientStateHandler {


    private ClientState currentState = null;

    public ClientStateHandler(){
        currentState = new IdleState();
    }

    public void processNextEvent(events event, String variable){
        switch (event){
            case SEND_INVITE:
                if(currentState.validEvent(event)){
                    //Valid event
                    currentState = currentState.sendInvite(variable);
                    System.out.println(currentState.currentState());
                }
                break;

            case RECIVE_INVITE:
                if(currentState.validEvent(event)){
                    //Valid event
                    currentState = currentState.reciveInvite(variable);
                    System.out.println(currentState.currentState());
            }

            case SEND_TRO:
                if(currentState.validEvent(event)){
                    //Valid event
                    currentState = currentState.sendTRO();
                    System.out.println(currentState.currentState());
                }
                break;
            case RECIVE_TRO:
                if(currentState.validEvent(event)){
                    //Valid event
                    currentState = currentState.reciveTRO();
                    System.out.println(currentState.currentState());
                }
                break;
            case SEND_ACK:
                if(currentState.validEvent(event)){
                    //Valid event
                    currentState = currentState.sendACK();
                    System.out.println(currentState.currentState());
                }
                break;
            case RECIVE_ACK:
                if(currentState.validEvent(event)){
                    //Valid event
                    currentState = currentState.reciveACK();
                    System.out.println(currentState.currentState());
                }
                break;
            case SEND_BYE:
                if(currentState.validEvent(event)){
                    //Valid event
                    currentState = currentState.sendBye();
                    System.out.println(currentState.currentState());
                }
                break;
            case RECIVE_BYE:
                if(currentState.validEvent(event)){
                    //Valid event
                    currentState = currentState.reciveBye();
                    System.out.println(currentState.currentState());
                }
                break;
            case OK:
                if(currentState.validEvent(event)){
                    //Valid event
                    currentState = currentState.reciveOk();
                    System.out.println(currentState.currentState());
                }
                break;
            default:
                System.out.println("NOT IMPLEMENTED!");

        }
    }

}
