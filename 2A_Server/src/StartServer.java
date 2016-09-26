import java.rmi.*;
import java.rmi.server.*;
//http://www.ejbtutorial.com/programming/java-rmi-example-simple-chat-program-between-server-and-client
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