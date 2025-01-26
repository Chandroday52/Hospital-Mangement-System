package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class update_patient_details extends JFrame {

    update_patient_details(){

        JPanel panel=new JPanel();
        panel.setBounds(5,5,940,490);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image=imageIcon.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1=new ImageIcon(image);
        JLabel label= new JLabel(imageIcon1);
        label.setBounds(500,60,300,300);
        panel.add(label);

        JLabel label1=new JLabel("Update Patient Details");
        label1.setBounds(124,11,260,25);
        label1.setFont(new Font("tahoma",Font.BOLD,20));
        panel.add(label1);

        JLabel label2 =new JLabel("Name: ");
        label2.setBounds(25,88,60,25);
        label2.setFont(new Font("tahoma",Font.PLAIN,14));
        panel.add(label2);

        Choice choice=new Choice();
        choice.setBounds(248,85,140,25);
        panel.add(choice);
        try{
            conn c=new conn();
            ResultSet resultSet=c.statement.executeQuery("select * from Patient_Info");
            while (resultSet.next()){
                choice.add(resultSet.getString("Name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel label3 =new JLabel("Room Number: ");
        label3.setBounds(25,129,100,25);
        label3.setFont(new Font("tahoma",Font.PLAIN,14));
        panel.add(label3);

        JTextField textField=new JTextField();
        textField.setBounds(248,129,140,20);
        panel.add(textField);

        JLabel label4 =new JLabel("In-Time: ");
        label4.setBounds(25,174,100,25);
        label4.setFont(new Font("tahoma",Font.PLAIN,14));
        panel.add(label4);

        JTextField textFieldINTime =new JTextField();
        textFieldINTime.setBounds(248,174,140,20);
        panel.add(textFieldINTime);


        JLabel label5 =new JLabel("Amount Paid (Rs) : ");
        label5.setBounds(25,216,130,25);
        label5.setFont(new Font("tahoma",Font.PLAIN,14));
        panel.add(label5);

        JTextField textFieldAmount =new JTextField();
        textFieldAmount.setBounds(248,216,140,20);
        panel.add(textFieldAmount);

        JLabel label6 =new JLabel("Pending Amount(Rs) : ");
        label6.setBounds(25,261,150,25);
        label6.setFont(new Font("tahoma",Font.PLAIN,14));
        panel.add(label6);

        JTextField textFieldPending =new JTextField();
        textFieldPending.setBounds(248,261,140,20);
        panel.add(textFieldPending);

        JButton check=new JButton("Check");
        check.setBounds(170,350,120,30);
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id=choice.getSelectedItem();
                String  q="select * from Patient_Info where Name = '"+id+"'";
                try {
                    conn c=new conn();
                    ResultSet resultSet=c.statement.executeQuery(q);
                    while (resultSet.next()){
                        textField.setText(resultSet.getString("Room_Number"));
                        textFieldINTime.setText(resultSet.getString("Time"));
                        textFieldAmount.setText(resultSet.getString("Deposite"));
                    }

                    ResultSet resultSet1=c.statement.executeQuery("select * from room where room_no = '"+textField.getText()+"'");
                    while (resultSet1.next()){
                        String price=resultSet1.getString("Price");
                        int amountPaid =Integer.parseInt(price) - Integer.parseInt(textFieldAmount.getText());
                        textFieldPending.setText(""+amountPaid);
                    }

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton update=new JButton("Update");
        update.setBounds(36,350,120,30);
        update.setForeground(Color.white);
        update.setBackground(Color.BLACK);
        panel.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c=new conn();
                    String q=choice.getSelectedItem();
                    String room=textField.getText();
                    String time=textFieldINTime.getText();
                    String amount=textFieldAmount.getText();
                    c.statement.executeUpdate("update Patient_Info set Room_Number = '"+room+"', Time ='"+time+"',Deposite = '"+amount+"' where name= '"+q+"'");
                    JOptionPane.showMessageDialog(null,"Update Succesfully");

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton back=new JButton("BACK");
        back.setBounds(300,350,120,
                30);
        back.setForeground(Color.white);
        back.setBackground(Color.BLACK);
        panel.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(950,500);
        setLayout(null);
        setLocation(400,250);
        setVisible(true);
    }

    public static  void main(String [] args){
        new update_patient_details();
    }
}
