import java.rmi.*;
import java.util.*;

public interface ChatServerInt extends Remote{
    public boolean login (ChatClientInt a)throws RemoteException ;
    public void publish (String s, ChatClientInt ci)throws RemoteException ;
    public Vector getConnected() throws RemoteException ;
    public void broadcast(String message, ChatClientInt ci) throws  RemoteException;
}