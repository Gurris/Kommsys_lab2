import Interface.*;

import java.rmi.RemoteException;


/**
 * Created by Gurris on 2016-09-24.
 */
public class ClientEntity {

    private IClient a = null;
    private String nickname = null;
    private boolean alive = true;
    private Object writeLock = new Object();

    public ClientEntity(IClient a){
        this.a = a;
        this.nickname = "Guest";
    }

    public void setAlive(boolean status){
        alive = status;
    }

    public boolean isAlive(){
        return alive;
    }

    public void writeTo(String message)throws RemoteException{
        synchronized (writeLock){
            a.writeTo(message);
        }

    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nick){
        nickname = nick;
    }

    public IClient getInterface(){
        return a;
    }

}
