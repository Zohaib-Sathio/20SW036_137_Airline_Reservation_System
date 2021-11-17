package semesterproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class logIn implements ActionListener {
    
    JFrame f;
    JLabel l;
    JTextField t1;
    JPasswordField t2;
    JLabel l1;
    JLabel l2;
    JLabel welcome;
    JLabel name;
    JLabel icon;
    JButton b1;
    JButton b2;
    ImageIcon image;
    
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    


    logIn(){
        //icon
        ImageIcon image = new ImageIcon("dude1.png");
        icon = new JLabel();
        icon.setBounds(80,65,252,247);
        icon.setIcon(image);
        
        welcome = new JLabel(" INTERNATIONAL ");
        welcome.setBounds(40,330,400,40);
        welcome.setForeground(Color.yellow);
        welcome.setFont(new Font("Sans Serif",Font.BOLD,40));

        name = new JLabel(" AIRLINES ");
        name.setBounds(110,400,440,40);
        name.setForeground(Color.yellow);
        name.setFont(new Font("Sans Serif",Font.BOLD,40));
        
        //Frame
        createDatabase();
        f = new JFrame("LOGIN PAGE");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setSize(900,650);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.getContentPane().setBackground(new Color(200, 0, 50));

        //TextField
        t1 = new JTextField();
        t1.setBounds(600,150,200,35);
        f.add(t1);
        t2 = new JPasswordField();
        t2.setBounds(600,260,200,35);
        f.add(t2);
        
        f.add(welcome);
        f.add(name);
        f.add(icon);

        //Label
        l1 = new JLabel("Email : ");
        l1.setBounds(460,150,180,50);
        l1.setFont(new Font("Sans Serif", Font.BOLD, 20));
        l1.setForeground(Color.yellow);
        f.add(l1);
        l2 = new JLabel("Password : ");
        l2.setBounds(460,260,180,50);
        l2.setFont(new Font("Sans Sarif", Font.BOLD, 20));
        l2.setForeground(Color.yellow);
        f.add(l2);

        //Buttons
        b1 = new JButton("Login");
        b1.setBounds(520,400,100,40);
        b1.setFont(new Font("Sans Sarif", Font.BOLD, 16));
        b1.setForeground(Color.yellow);
        b1.setBackground(new Color(200, 0, 50));
        b1.setBorder(BorderFactory.createLineBorder(Color.white,3));
        f.add(b1);
        
        b2 = new JButton("SignUp");
        b2.setBounds(680,400,100,40);
        b2.setFont(new Font("Sans Sarif", Font.BOLD, 16));
        b2.setBorder(BorderFactory.createLineBorder(Color.white,3));
        b2.setForeground(Color.yellow);
        b2.setBackground(new Color(200, 0, 50));
        f.add(b2);
        b2.addActionListener(this);
        b1.addActionListener(this);


        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    void createDatabase(){
                try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightdetails","root","password");
            
              System.out.println("Database Connection Successfull");

        } 
        catch(  ClassNotFoundException | SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==b1){
            f.setVisible(false);
            welcomeClass welcome = new welcomeClass();
//            int n =0;
//            try { 
//                String userInformation;
//	            	 userInformation =  t1.getText();
//                String selectQuery = "select * from usercredentials WHERE email = ?";
//		    	stmt = con.prepareStatement(selectQuery); 
//		    	stmt.setString(1, userInformation);
//		    	ResultSet result = stmt.executeQuery();
                
//                String user[] = new String[4];
//                    String emailS = t1.getText();
//                    Statement stmt = con.createStatement();
//                    String dbop = "select * from usersdata.usercredentials where email = '" + emailS + "'";
//                    rs = stmt.executeQuery(dbop);
//                    user[0] = rs.getString(1);
//                    user[1] = rs.getString(2);
//                    user[2] = rs.getString(3);
//                    user[3] = rs.getString(4);
//                    for(int i =0 ; i<user.length ; i++ ){
//                        System.out.println(user[i]);
//                    }
//                    welcomeClass welcomeclass = new welcomeClass();
//                    stmt.close();
//                    System.out.println(result.getString(1));
//                    JOptionPane.showMessageDialog(null, "LogIn Successful.");
                    
                    
//                } 
//            catch (SQLException ex) {
//                    JOptionPane.showMessageDialog(null, "LogIn Error!");
//                }
        }
        if(ae.getSource()==b2){
            f.setVisible(false);
        signUp signup = new signUp();
        }
        
    }
    public void userLogIn(String email, String password){
        //System.out.println(email + " " + password);
        String selectQuery = "'select * from usercredentials where email = ?";
        try {
            stmt = con.prepareStatement(selectQuery);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            System.out.println(stmt.toString());
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
        } catch (SQLException ex) {
            Logger.getLogger(logIn.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        //System.out.println(selectQuery);
    }


}
