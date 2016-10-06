import java.rmi.*;
import java.util.*;

public interface ChatServerInt extends Remote{
    public boolean login (ChatClientInt a)throws RemoteException ;
    public void  disconnect(ChatClientInt ci) throws RemoteException;
    public void broadcast(String message, ChatClientInt ci) throws  RemoteException;
}