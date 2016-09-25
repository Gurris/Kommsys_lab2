import java.rmi.*;
import java.rmi.server.*;

public class StartServer {
    public static void main(String[] args) {
        try {
            ChatServer b=new ChatServer();
            Naming.rebind("chat", b);
            System.out.println("[System] Chat Server is ready.");
        }catch (Exception e) {
            System.out.println("Chat Server failed: " + e);
        }
    }
}