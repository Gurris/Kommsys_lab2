/**
 * Created by Gurris on 2016-10-13.
 */
public class Main {
    public static void main(String[] args){
        //GUI C = new GUI();
        try{
            Client c = new Client();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }

    }
}
