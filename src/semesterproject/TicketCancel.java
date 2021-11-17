package semesterproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;


public class TicketCancel implements ActionListener{
    JFrame f;
    
    JLabel lb2;
    JLabel lb4;
    JLabel lb5;
    JLabel lb6;
    
    JTextField tf2;
    JTextField tf4;
    JTextField tf5;
    JTextField tf6;
    
    JButton tb1;
    JButton tb2;
    JTable table;
    JLabel tickets;
    
    int n = 0;
    String[] ticket = new String[4];
    
   Connection con;
   Statement stmt;
   ResultSet rs;

    TicketCancel(){
        //Frame
        f = new JFrame("Cancel Ticket");
        f.setLayout(null);
        f.setSize(600,600);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.getContentPane().setBackground(new Color(200, 0, 50));
        
        table = new JTable();
        table.setBounds(0,100,600,150);
        
        tickets = new JLabel("Booked Tickets");
        tickets.setBounds(150,20,400,40);
        tickets.setFont(new Font("Sans Serif", Font.BOLD, 35));
        tickets.setForeground(Color.yellow);
        

        //Label
        lb2 = new JLabel(" Flight ID: ");
        lb2.setBounds(270,300,120,30 );
        lb2.setForeground(Color.yellow);
        lb2.setFont(new Font("Sans Serif", Font.BOLD, 17));

        lb4 = new JLabel("Booking Date: ");
        lb4.setBounds(15,300,120,30);
        lb4.setForeground(Color.yellow);
        lb4.setFont(new Font("Sans Serif", Font.BOLD, 17));

        lb5 = new JLabel("Departure: ");
        lb5.setBounds(15,360,120,30);
        lb5.setForeground(Color.yellow);
        lb5.setFont(new Font("Sans Serif", Font.BOLD, 17));

        lb6 = new JLabel("Destination: ");
        lb6.setBounds(270,360,120,30);
        lb6.setForeground(Color.yellow);
        lb6.setFont(new Font("Sans Serif", Font.BOLD, 17));

        //TextField
        tf2 = new JTextField();
        tf2.setBounds(380,300,120,30);
        
        
        tf4 = new JTextField();
        tf4.setBounds(130,300,120,30);

        tf5 = new JTextField();
        tf5.setBounds(130,360,120,30);

        tf6 = new JTextField();
        tf6.setBounds(380,360,120,30);
        
        tf2.setFont(new Font("Sans Serif", Font.BOLD, 15));
        tf4.setFont(new Font("Sans Serif", Font.BOLD, 15));
        tf5.setFont(new Font("Sans Serif", Font.BOLD, 15));
        tf6.setFont(new Font("Sans Serif", Font.BOLD, 15));
        
        //BUTTONS
        tb1 = new JButton("Cancel Ticket");
        tb1.setFocusable(false);
        tb1.setBounds(130,460,150,30);
        tb1.setFont(new Font("Sans Serif", Font.BOLD, 17));
        tb1.setForeground(Color.yellow);
        tb1.setBackground(new Color(200, 0, 50));
        tb1.setBorder(BorderFactory.createLineBorder(Color.white,4));

        tb2 = new JButton("Home");
        tb2.setBounds(300,460,150,30);
        tb2.setFocusable(false);
        tb2.setFont(new Font("Sans Serif", Font.BOLD, 17));
        tb2.setForeground(Color.yellow);
        tb2.setBackground(new Color(200, 0, 50));
        tb2.setBorder(BorderFactory.createLineBorder(Color.white,3));

        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightdetails","root","password");
            
              System.out.println("Database Connection Successfull");

        } 
        catch(  ClassNotFoundException | SQLException e)
        {
            System.out.println(e);
        }
        try{
                
                String query = "select * from bookedtickets";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch(SQLException ax){
                
            }
        

        f.add(tf2);
        f.add(tf4);
        f.add(tf5);
        f.add(tf6);
        f.add(table);
        f.add(tickets);

        
        f.add(lb2);
        f.add(lb4);
        f.add(lb5);
        f.add(lb6);

        f.add(tb1);
        f.add(tb2);

        f.setVisible(true);

        tb1.addActionListener(this);
        tb2.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==tb1){
            if(n==1){
                
                try {
                    try (Statement stmt = con.createStatement()) {
                        String id = tf2.getText();
                        String dbop = "DELETE FROM `flightdetails`.`bookedtickets` WHERE (`FlightID` = '" + id + "')";
                        stmt.execute(dbop);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(signUp.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                JOptionPane.showMessageDialog(null,"Ticket has been cancelled succefully!");
                welcomeClass welcome = new welcomeClass();
                f.setVisible(false);
            }
            if(n==0){
                int selectedRow = table.getSelectedRow();
                
               for(int i =0; i<4; i++){
                   
               String value = table.getModel().getValueAt(selectedRow, i).toString();
               ticket[i] = value;
               }
               
               tf2.setText(ticket[0]);
               tf4.setText(ticket[1]);
               tf5.setText(ticket[2]);
               tf6.setText(ticket[3]);
                tb1.setText("Confirm!");
                n++;
            }
        }
        if(ae.getSource()==tb2){
            f.setVisible(false);
            welcomeClass welcome = new welcomeClass();
        }
    }

    
}
