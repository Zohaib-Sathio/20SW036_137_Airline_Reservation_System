package semesterproject;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;


public class TicketBooking extends JFrame implements ActionListener{
    
    JLabel ID;
    JLabel flightN;
    JLabel departure;
    JLabel destination;
    JLabel date;
    JLabel arrivalT;
    JLabel departureT;
    JLabel price;
    JLabel welcome;
    
    JTable table;
    JScrollPane scrollPane;
    
    JTextField IDf;
    JTextField flightNf;
    JTextField departuref;
    JTextField destinationf;
    JTextField datef;
    JTextField arrivalTf;
    JTextField departureTf;
    JTextField pricef;
    
    JButton select;
    JButton check;
    
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    
    String fValues[]= new String[8];
    int number = 0;
    JLabel icon;

    
    
    
    TicketBooking(){
        this.setSize(800,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ticket Booking");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(200, 0, 50));
        
        welcome = new JLabel("Book your Ticket here!");
        welcome.setBounds(290,120,600,50);
        welcome.setForeground(Color.yellow);
        welcome.setFont(new Font("Sans Serif",Font.BOLD,40));
        
        
        ImageIcon image = new ImageIcon("dude1.png");
        icon = new JLabel();
        icon.setBounds(10,15,252,247);
        icon.setIcon(image);
        
        
        //Labels
        departure = new JLabel("Departure:");
        departure.setBounds(150, 270, 120, 30);
        departure.setForeground(Color.yellow);
        destination = new JLabel("Flight Name:");
        destination.setBounds(50, 690, 120, 30);
        destination.setForeground(Color.yellow);
        ID = new JLabel("Flight ID:");
        ID.setBounds(50, 750, 120, 30);
        ID.setForeground(Color.yellow);
        flightN = new JLabel("Date:");
        flightN.setBounds(50, 810, 120, 30);
        flightN.setForeground(Color.yellow);
        date = new JLabel("Destination:");
        date.setBounds(50, 630, 120, 30);
        date.setForeground(Color.yellow);
        arrivalT = new JLabel("Arrival Time:");
        arrivalT.setBounds(400, 690, 120, 30);
        arrivalT.setForeground(Color.yellow);
        departureT = new JLabel("Departure Time:");
        departureT.setBounds(400, 750, 200, 30);
        departureT.setForeground(Color.yellow);
        price = new JLabel("Flight Price:");
        price.setBounds(400, 810, 280, 30);
        price.setForeground(Color.yellow);
        
        //Label font change
        ID.setFont(new Font("Sans Serif", Font.BOLD, 17));
        flightN.setFont(new Font("Sans Serif", Font.BOLD, 17));
        departure.setFont(new Font("Sans Serif", Font.BOLD, 17));
        destination.setFont(new Font("Sans Serif", Font.BOLD, 17));
        date.setFont(new Font("Sans Serif", Font.BOLD, 17));
        arrivalT.setFont(new Font("Sans Serif", Font.BOLD, 17));
        departureT.setFont(new Font("Sans Serif", Font.BOLD, 17));
        price.setFont(new Font("Sans Serif", Font.BOLD, 17));
        
        //adding a Table
        table = new JTable();
        
        table.setFillsViewportHeight(true);
        table.setBounds(40, 350, 700, 250);
        table.setFont(new Font("Sans Serif", Font.BOLD, 30));
        
        scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        
        table.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        
        //TextFields
        departuref = new JTextField();
        departuref.setBounds(270, 270, 150, 35);
        flightNf = new JTextField();
        flightNf.setBounds(170, 690, 150, 35);
        IDf = new JTextField();
        IDf.setBounds(170, 750, 150, 35);
        datef = new JTextField();
        datef.setBounds(170, 810, 150, 35);
        destinationf = new JTextField();
        destinationf.setBounds(170, 630, 150, 35);
        arrivalTf = new JTextField();
        arrivalTf.setBounds(550, 690, 150, 35);
        departureTf = new JTextField();
        departureTf.setBounds(550, 750, 150, 35);
        pricef = new JTextField();
        pricef.setBounds(550, 810, 150, 35);
        
        //textfields font change
        IDf.setFont(new Font("Sans Serif", Font.BOLD, 15));
        flightNf.setFont(new Font("Sans Serif", Font.BOLD, 15));
        departuref.setFont(new Font("Sans Serif", Font.BOLD, 15));
        destinationf.setFont(new Font("Sans Serif", Font.BOLD, 15));
        datef.setFont(new Font("Sans Serif", Font.BOLD, 15));
        arrivalTf.setFont(new Font("Sans Serif", Font.BOLD, 15));
        departureTf.setFont(new Font("Sans Serif", Font.BOLD, 15));
        pricef.setFont(new Font("Sans Serif", Font.BOLD, 15));
        
        select = new JButton("Select");
        select.setBounds(300, 890, 100, 35);
        select.setForeground(Color.yellow);
        select.setBackground(new Color(200, 0, 50));
        select.setBorder(BorderFactory.createLineBorder(Color.white,3));
        
        check = new JButton("Check");
        check.setBounds(450, 270, 100, 35);
        check.setForeground(Color.yellow);
        check.setBackground(new Color(200, 0, 50));
        check.setBorder(BorderFactory.createLineBorder(Color.white,3));
        
//        showFlights = new JButton("Show Flights");
//        showFlights.setBounds(520, 200, 120, 35);
//        showFlights.setVisible(false);
        
       
        check.addActionListener(this);
        select.addActionListener(this);
        
        this.add(ID);
        this.add(flightN);
        this.add(departure);
        this.add(destination);
        this.add(date);
        this.add(arrivalT);
        this.add(departureT);
        this.add(price);
        
        this.add(IDf);
        this.add(flightNf);
        this.add(departuref);
        this.add(destinationf);
        this.add(datef);
        this.add(arrivalTf);
        this.add(departureTf);
        this.add(pricef);
        
        this.add(table);
        this.add(select);
        this.add(check);
        this.add(icon);
        this.add(welcome);
        
        
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
                rs = stmt.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch(SQLException ax){
                
            }
        
        
        
        this.setVisible(true);
    
}
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()== check){
            
            try{
                String dept = departuref.getText(); 
                String query = "select * from ticketbooking where Departure = '"+ dept + "'";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
//                showFlights.setVisible(true);
                
                
            }
            catch(SQLException ax){
                
            }
        }
//        if(ae.getSource()==showFlights){
//            try{
//                
//                String query = "select * from ticketbooking";
//                stmt = con.prepareStatement(query);
//                rs = stmt.executeQuery();
//                table.setModel(DbUtils.resultSetToTableModel(rs));
//            }
//            catch(SQLException ax){
//                
//            }
//        }
        if(ae.getSource()== select){
            if(number==1){
                boom();
            }
            if(number==0){
                int selectedRow = table.getSelectedRow();
                
               for(int i =0; i<8; i++){
                   
               String value = table.getModel().getValueAt(selectedRow, i).toString();
               fValues[i] = value;
               }
//               for(int j=0; j<fValues.length; j++){
//                   System.out.print(fValues[j] + ", ");
//                
//               }
               IDf.setText(fValues[0]);
               flightNf.setText(fValues[1]);
               departuref.setText(fValues[2]);
               destinationf.setText(fValues[3]);
               datef.setText(fValues[4]);
               arrivalTf.setText(fValues[5]);
               departureTf.setText(fValues[6]);
               pricef.setText(fValues[7]);
               select.setText("Confirm");
               number++;
               System.out.println();
            }
            
        }
           
    }
    public void boom(){
        this.setVisible(false);
        JFrame fe;
    JLabel lab1;
    JLabel lab2;
    JLabel lab3;
    JLabel lab4;
    JLabel lab5;
    JLabel lab6;
    JLabel lab7;
    JLabel lab8;
    JLabel lab9;
    JLabel lab10;
    JLabel lab11;
    JLabel lab12;
    JLabel lab13;
    JLabel lab16;
    JLabel lab17;
    JLabel lab18;
    JLabel lab19;
    JLabel lab21;
    JLabel lab22;
    JLabel lab23;
    JLabel lab24;
//    JButton credits;
        
        fe = new JFrame();
        fe.setSize(800, 600);
        fe.setLayout(null);
        fe.setLocationRelativeTo(null);
        fe.setResizable(false);
        fe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fe.getContentPane().setBackground(new Color(200, 0, 50));

        lab1 = new JLabel("RESERVED TICKET DETAILS");
        lab1.setBounds(40,40,600,40);
        lab1.setForeground(new Color(77,166,255));
        lab1.setFont(new Font("Sans Serif",Font.BOLD, 40));

        lab2 = new JLabel("  Route ");
        lab2.setBounds(40,60,200,90);
        lab2.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 20));
        lab2.setForeground(Color.yellow);

        lab3 = new JLabel("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        lab3.setBounds(40,60,670,170);
        lab3.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 18));
        lab3.setForeground(Color.yellow);

        lab4 = new JLabel("From ");
        lab4.setBounds(40,120,520,140);
        lab4.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 18));
        lab4.setForeground(Color.yellow);

        lab5 = new JLabel("AirLine");
        lab5.setBounds(160,120,520,140);
        lab5.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 18));
        lab5.setForeground(Color.yellow);

        lab6 = new JLabel("Departure Time");
        lab6.setBounds(290,120,520,140);
        lab6.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 18));
        lab6.setForeground(Color.yellow);

        lab7 = new JLabel("Arrival Time");
        lab7.setBounds(460,120,520,140);
        lab7.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 18));
        lab7.setForeground(Color.yellow);

        lab8 = new JLabel("                     - - - - - - - - - - - - - - - - - - - - - - - - -");
        lab8.setBounds(40,200,520,140);
        lab8.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 18));
        lab8.setForeground(Color.yellow);

        lab9 = new JLabel("To");
        lab9.setBounds(40,280,200,90);
        lab9.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 18));
        lab9.setForeground(Color.yellow);

        lab10 = new JLabel("Flight ID");
        lab10.setBounds(160,280,200,90);
        lab10.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 18));
        lab10.setForeground(Color.yellow);

        lab11 = new JLabel("Date");
        lab11.setBounds(290,280,200,90);
        lab11.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 18));
        lab11.setForeground(Color.yellow);

        lab12 = new JLabel("Price");
        lab12.setBounds(460,280,200,90);
        lab12.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 18));
        lab12.setForeground(Color.yellow);

        lab13 = new JLabel("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        lab13.setBounds(40,360,670,140);
        lab13.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 18));
        lab13.setForeground(Color.yellow);
        
        lab16 = new JLabel(fValues[2]);
        lab16.setBounds(40,170,520,140);
        lab16.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 20));
        lab16.setForeground(Color.white);

        lab17 = new JLabel(fValues[1]);
        lab17.setBounds(120,170,520,140);
        lab17.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 20));
        lab17.setForeground(Color.white);

        lab18 = new JLabel(fValues[5]);
        lab18.setBounds(330,170,520,140);
        lab18.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 20));
        lab18.setForeground(Color.white);

        lab19 = new JLabel(fValues[6]);
        lab19.setBounds(460,170,520,140);
        lab19.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 20));
        lab19.setForeground(Color.white);

        lab21 = new JLabel(fValues[3]);
        lab21.setBounds(40,330,200,90);
        lab21.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 20));
        lab21.setForeground(Color.white);

        lab22 = new JLabel(fValues[0]);
        lab22.setBounds(160,330,200,90);
        lab22.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 20));
        lab22.setForeground(Color.white);

        lab23 = new JLabel(fValues[4]);
        lab23.setBounds(290,330,200,90);
        lab23.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 20));
        lab23.setForeground(Color.white);

        lab24 = new JLabel(fValues[7]);
        lab24.setBounds(460,330,200,90);
        lab24.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 20));
        lab24.setForeground(Color.white);
        
//        credits = new JButton("Credits");
//        credits.setBounds(320,490,120,50);
//        credits.setFocusable(false);
//        credits.setForeground(Color.yellow);
//        credits.setBackground(new Color(200, 0, 50));
//        credits.setBorder(BorderFactory.createLineBorder(Color.white,4));
//        credits.setFont(new Font("Sans Serif", Font.BOLD, 20));
//        credits.addActionListener(this);
        
        try {
                    try (Statement stmt = con.createStatement()) {
                        String departureD = fValues[2];
                        String destinationD = fValues[3];
                        String idD = fValues[0];
                        String dateD = fValues[4];
                        String dbop = "INSERT INTO `flightdetails`.`bookedtickets` VALUES ('"+ idD +"', '"+ departureD +"', '"+ destinationD +"', '"+ dateD + "')";
                        stmt.execute(dbop);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(signUp.class.getName()).log(Level.SEVERE, null, ex);
                }
        

        fe.add(lab1);
        fe.add(lab2);
        fe.add(lab3);
        fe.add(lab4);
        fe.add(lab5);
        fe.add(lab6);
        fe.add(lab7);
        fe.add(lab8);
        fe.add(lab9);
        fe.add(lab10);
        fe.add(lab11);
        fe.add(lab12);
        fe.add(lab13);
        fe.add(lab16);
        fe.add(lab17);
        fe.add(lab18);
        fe.add(lab19);
        fe.add(lab21);
        fe.add(lab22);
        fe.add(lab23);
        fe.add(lab24);
//        fe.add(credits);
        
        fe.setVisible(true);
    }
    
    
    }