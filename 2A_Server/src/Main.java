import java.rmi.*;

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