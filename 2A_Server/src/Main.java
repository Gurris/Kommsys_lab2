import java.rmi.*;

//http://www.ejbtutorial.com/programming/java-rmi-example-simple-chat-program-between-server-and-client
public class Main {
    public static void main(String[] args) {
        try {
            Server b=new Server();
            Naming.rebind("chat", b);
            System.out.println("[System] Chat Server is ready.");
        }catch (Exception e) {
            System.out.println("Chat Server failed: " + e);
        }
    }
}