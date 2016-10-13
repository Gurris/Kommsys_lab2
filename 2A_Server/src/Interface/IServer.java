package Interface;

import java.rmi.*;

public interface IServer extends Remote{
    public boolean login (IClient a)throws RemoteException ;
    public void  disconnect(IClient ci) throws RemoteException;
    public void broadcast(String message, IClient ci) throws  RemoteException;
}