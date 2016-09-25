import java.rmi.RemoteException;

/**
 * Created by Gurris on 2016-09-24.
 */
public class ClientEntity {

    private ChatClientInt a = null;
    private String nickname = null;

    public ClientEntity(ChatClientInt a){
        this.a = a;
        this.nickname = "Guest";
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

    public ChatClientInt getInterface(){
        return a;
    }

}
