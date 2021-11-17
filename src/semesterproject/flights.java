package semesterproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class flights extends JFrame implements ActionListener{
    JTable table;
    JScrollPane scrollPane;
    JButton button;
    JLabel flight;
    
    Connection con;
    Statement stmt;
    ResultSet rs;
    
    
    flights(){
        this.setTitle("Flight Details");
        this.setLayout(null);
        this.setSize(800,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(200, 0, 50));
        
        flight = new JLabel("Scheduled Flight Details");
        flight.setBounds(190, 30, 400, 50);
        flight.setFont(new Font("Sans Serif", Font.BOLD, 35));
        flight.setForeground(Color.yellow);
        
        table = new JTable();
        
        table.setBounds(10, 110, 775, 250);
        table.setFont(new Font("Sans Serif", Font.PLAIN, 17));
        
        
        scrollPane = new JScrollPane();
        scrollPane.add(table);
        
        button = new JButton("Book a Flight");
        button.setFont(new Font("Sans Serif", Font.BOLD, 17));
        button.setBounds(300, 390, 150, 40);
        button.setForeground(Color.yellow);
        button.setBackground(new Color(200, 0, 50));
        button.setBorder(BorderFactory.createLineBorder(Color.white,3));
        
        button.addActionListener(this);
        
        
        this.add(button);
        this.add(scrollPane);
        this.add(table);
        this.add(flight);
        
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
                
                String query = "select * from ticketbooking";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch(SQLException ax){
                
            }
        
        
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false);
        TicketBooking ticketB = new TicketBooking();
    }
    
}
