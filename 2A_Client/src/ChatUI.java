import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.ExportException;
import java.util.*;

public class ChatUI{
    private ChatClient client;
    private ChatServerInt server;
    public void doConnect(){
        if (connect.getText().equals("Connect")){
            if (IP.getText().isEmpty()){JOptionPane.showMessageDialog(frame, "You need to type a IP address."); return;}
            try{
                client=new ChatClient(IP.getText());
                client.setGUI(this);
                server=(ChatServerInt)Naming.lookup("rmi://"+ IP.getText() +"/chat");
                server.login(client);
                connect.setText("Disconnect");
            }catch(Exception e){JOptionPane.showMessageDialog(frame, "ERROR, we wouldn't connect....");}
        }else{
            try{
                connect.setText("Connect");
                server.disconnect(client);
            }catch (Exception e){
                System.out.println("Could not disconnect");
                System.exit(-1);
            }
        }
    }

    public void sendText(){
        if (connect.getText().equals("Connect")){
            JOptionPane.showMessageDialog(frame, "You need to connect first."); return;
        }
        String st=tf.getText();
        tf.setText("");
        try{
            tx.append("\n"+ "[YOU]: " +st);
            server.broadcast(st, client);
            if(st.toLowerCase().equals("/quit")){
                System.exit(1);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void writeMsg(String st){
        String s = Character.toString(st.charAt(0));
        if(s.equals("/")){
            commands(st);
        }else{
            tx.setText(tx.getText()+"\n"+st);
        }
    }

    private void commands(String commandString){
        String[] command = commandString.split(" ");
        switch (command[0].toLowerCase()){

            case "/alive":
                try{
                    server.broadcast("/alive", client);
                }catch (RemoteException e){
                    System.out.println("Remote exception");
                }
                break;
            default:
                System.out.println("not valid command from server");
                break;
        }
    }

    public static void main(String [] args){
        ChatUI c=new ChatUI();
    }

    //User Interface code.
    public ChatUI(){
        frame=new JFrame("Group Chat");
        JPanel main =new JPanel();
        JPanel top =new JPanel();
        JPanel cn =new JPanel();
        JPanel bottom =new JPanel();
        tf=new JTextField();
        IP = new JTextField();
        tx=new JTextArea();
        connect=new JButton("Connect");
        JButton bt=new JButton("Send");
        lst=new JList();
        main.setLayout(new BorderLayout(5,5));
        top.setLayout(new GridLayout(1,0,5,5));
        cn.setLayout(new BorderLayout(5,5));
        bottom.setLayout(new BorderLayout(5,5));
        top.add(new JLabel("IP address: "));top.add(IP);

        top.add(connect);
        cn.add(new JScrollPane(tx), BorderLayout.CENTER);
        cn.add(lst, BorderLayout.EAST);
        bottom.add(tf, BorderLayout.CENTER);
        bottom.add(bt, BorderLayout.EAST);
        main.add(top, BorderLayout.NORTH);
        main.add(cn, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.SOUTH);
        main.setBorder(new EmptyBorder(10, 10, 10, 10) );
        //Events
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try{
                    if(client != null){
                        server.disconnect(client);
                    }
                }catch (RemoteException ee){
                    System.out.println(ee);
                }finally {
                    System.exit(1);
                }

            }
        });
        connect.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ doConnect();   }  });
        bt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ sendText();   }  });
        tf.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ sendText();   }  });

        frame.setContentPane(main);
        frame.setSize(600,600);
        frame.setVisible(true);
    }
    JTextArea tx;
    JTextField tf, IP;
    JButton connect;
    JList lst;
    JFrame frame;
}