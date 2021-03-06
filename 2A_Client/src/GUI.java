import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

//Simple GUI from:
//http://www.ejbtutorial.com/programming/java-rmi-example-simple-chat-program-between-server-and-client


public class GUI {

    private Client client;

    JTextArea textArea;
    JTextField message, IP;
    JButton connect;
    JFrame frame;
    JLabel serverStatus;

    public GUI(Client c){
        this.client = c;

        //creating frames and panels
        frame=new JFrame("Group Chat");
        JPanel wrapper =new JPanel();
        JPanel top =new JPanel();
        JPanel center =new JPanel();
        JPanel bottom =new JPanel();

        //creating buttons and text fields
        message=new JTextField();
        IP = new JTextField(); // IP textfield
        textArea=new JTextArea();
        connect=new JButton("Connect");
        JButton bt=new JButton("Send");
        serverStatus = new JLabel("status");
        serverStatus.setForeground(Color.RED);

        //setting layouts for the panels
        wrapper.setLayout(new BorderLayout(5,5));
        top.setLayout(new GridLayout(1,0,5,5));
        center.setLayout(new BorderLayout(5,5));
        bottom.setLayout(new BorderLayout(5,5));

        //Top part of GUI
        top.add(new JLabel("IP address: "));
        top.add(IP);
        top.add(connect);

        //center part of GUI. Text area
        center.add(new JScrollPane(textArea), BorderLayout.CENTER);

        //bottom part of GUI
        bottom.add(message, BorderLayout.CENTER);
        bottom.add(bt, BorderLayout.EAST);

        //connecting all panels to one
        wrapper.add(top, BorderLayout.NORTH);
        wrapper.add(center, BorderLayout.CENTER);
        wrapper.add(bottom, BorderLayout.SOUTH);
        wrapper.setBorder(new EmptyBorder(10, 10, 10, 10) );

        //Event handling
        textArea.setEditable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if(client != null){
                    client.disconnectFromServer();
                }
                System.exit(1);
            }
        });


        connect.addActionListener(e -> connectToServer());

        bt.addActionListener(e -> sendText());

        message.addActionListener(e -> sendText());

        frame.setContentPane(wrapper);
        frame.setSize(600,600);
        frame.setVisible(true);
    }

    private void connectToServer(){
        if (connect.getText().equals("Connect")){
            if (IP.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Enter a address!");
                return;
            }
            if(client.connectToServer(IP.getText())){
                connect.setText("Disconnect");
                System.out.println("Connected to server successfully");
            }else{
                JOptionPane.showMessageDialog(frame, "Failed to connect to server");
            }

        }else{
            connect.setText("Connect");
            client.disconnectFromServer();
        }
    }

    public void disconnectedFromServer(){
        JOptionPane.showMessageDialog(frame, "Disconnected from server!");
        connect.setText("Connect");
    }

    public void sendText(){
        if (connect.getText().equals("Connect")){
            JOptionPane.showMessageDialog(frame, "You need to connect first.");
            return;
        }

        String st=message.getText();
        message.setText("");
        if(st.equals(null) || st.equals(""))
            return;

        textArea.append("\n"+ "[YOU]: " +st);
        client.broadcast(st);
    }

    public void writeMsg(String st){
        textArea.setText(textArea.getText()+"\n"+st);
    }

}