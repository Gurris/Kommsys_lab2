package stateMachine;

import java.net.DatagramSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Gurris on 2016-10-17.
 */
public class Main {

    private boolean busy = false;

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String event = "";
        String variable = "";
        ClientStateHandler handler = new ClientStateHandler();

        //--------------
        //Listner listner = new Listner(this);

        //--------------


        while (true) {
            System.out.println("-----------------");
            System.out.println("EVENT!");
            event = s.nextLine();

            switch (event){
                case "SEND_INVITE":
                    System.out.print("INVITE TO: ");
                    s.nextLine(); // will not effect first command
                    handler.processNextEvent(events.SEND_INVITE, variable);
                    break;
                case "RECIVE_INVITE":
                    variable = "this is a invite from someone";
                    handler.processNextEvent(events.RECIVE_INVITE, variable);
                    break;

                case "SEND_TRO":
                    variable = "";
                    handler.processNextEvent(events.SEND_TRO, variable);
                    break;

                case "RECIVE_TRO":
                    variable = "";
                    handler.processNextEvent(events.RECIVE_TRO, variable);
                    break;

                case "SEND_ACK":
                    variable = "";
                    handler.processNextEvent(events.SEND_ACK, variable);
                    break;

                case "RECIVE_ACK":
                    variable = "";
                    handler.processNextEvent(events.RECIVE_ACK, variable);
                    break;

                case "SEND_BYE":
                    variable = "";
                    handler.processNextEvent(events.SEND_BYE, variable);
                    break;

                case "RECIVE_BYE":
                    variable = "";
                    handler.processNextEvent(events.RECIVE_BYE, variable);
                    break;
                case "RECIVE_OK":
                    variable = "";
                    handler.processNextEvent(events.OK, variable);
                default:
                    System.out.println("INVALID COMMAND");
            }
        }

    }
/*
    public void ExternaleventPosting(events e, String variable){
        System.out.println("-----------------");
        System.out.println("EVENT!");

        switch (e){
            case SEND_INVITE:
                System.out.print("INVITE TO: ");
                handler.processNextEvent(events.SEND_INVITE, variable);
                break;
            case RECIVE_INVITE:
                variable = "this is a invite from someone";
                handler.processNextEvent(events.RECIVE_INVITE, variable);
                break;

            case SEND_TRO:
                variable = "";
                handler.processNextEvent(events.SEND_TRO, variable);
                break;

            case RECIVE_TRO:
                variable = "";
                handler.processNextEvent(events.RECIVE_TRO, variable);
                break;

            case SEND_ACK:
                variable = "";
                handler.processNextEvent(events.SEND_ACK, variable);
                break;

            case RECIVE_ACK:
                variable = "";
                handler.processNextEvent(events.RECIVE_ACK, variable);
                break;

            case SEND_BYE:
                variable = "";
                handler.processNextEvent(events.SEND_BYE, variable);
                break;

            case RECIVE_BYE:
                variable = "";
                handler.processNextEvent(events.RECIVE_BYE, variable);
                break;
            case OK:
                variable = "";
                handler.processNextEvent(events.OK, variable);
            default:
                System.out.println("INVALID COMMAND");
        }
    }
    */

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
