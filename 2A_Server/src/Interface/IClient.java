package Interface;

import java.rmi.*;

public interface IClient extends Remote{
    public void writeTo (String name)throws RemoteException ;
}