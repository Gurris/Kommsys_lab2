import java.rmi.RemoteException;

/**
 * Created by Gurris on 2016-09-24.
 */
public class ClientEntity {

    private ChatClientInt a = null;
    private String nickname = null;
    private boolean alive = false;

    public ClientEntity(ChatClientInt a){
        this.a = a;
        this.nickname = "Guest";
    }

    public void setAlive(boolean status){
        alive = status;
    }

    public boolean isAlive(){
        return alive;
    }

    public void writeTo(String message){
        try{
            a.tell(message);
        }catch (Exception e){

        }
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nick){
        nickname = nick;
    }

    public ChatClientInt getInterface(){
        return a;
    }

}
