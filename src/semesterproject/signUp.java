package semesterproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class signUp extends JFrame implements ActionListener{
    
    JPanel panel;
    
    JTextField email;
    JTextField Fname;
    JTextField Lname;
    JPasswordField pass;
    
    JLabel emailLabel;
    JLabel firstNameLabel;
    JLabel secondNameLabel;
    JLabel passLabel;
    
    JButton create;
    JButton back;
    
    ImageIcon image;
    JLabel text;
    JLabel text1;
    JLabel icon;
    
    Connection con;
    
    
    
    
    signUp(){
        createDatabase();
        this.setSize(600,900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("SignUp Page");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(200, 0, 50));
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(200,0,50));
        panel.setSize(578,845);
        
        //Labels
        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(100, 300, 60, 30);
        emailLabel.setForeground(Color.yellow);
        
        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(60, 380, 160, 30);
        firstNameLabel.setForeground(Color.yellow);
        secondNameLabel = new JLabel("Second Name:");
        secondNameLabel.setBounds(60, 460, 160, 30);
        secondNameLabel.setForeground(Color.yellow);
        passLabel = new JLabel("Password:");
        passLabel.setBounds(60, 540, 120, 30);
        passLabel.setForeground(Color.yellow);
        
        emailLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
        firstNameLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
        secondNameLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
        passLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
        
        
        //TextFields
        email = new JTextField();
        email.setBounds(250, 300, 200, 30);
        Fname = new JTextField();
        Fname.setBounds(250, 380, 200, 30);
        Lname = new JTextField();
        Lname.setBounds(250, 460, 200, 30);
        pass = new JPasswordField();
        pass.setBounds(250, 540, 200, 30);
        
        //Buttons
        create = new JButton("Create");
        create.setBounds(120, 660, 100, 40);
        create.setForeground(Color.yellow);
        create.setBackground(new Color(200, 0, 50));
        create.setBorder(BorderFactory.createLineBorder(Color.white,3));
        
        back = new JButton("Back");
        back.setBounds(290, 660, 100, 40);
        back.setForeground(Color.yellow);
        back.setBackground(new Color(200, 0, 50));
        back.setBorder(BorderFactory.createLineBorder(Color.white,3));
        
        create.setFont(new Font("Sans Serif", Font.BOLD, 16));
        back.setFont(new Font("Sans Serif", Font.BOLD, 16));
        
        image = new ImageIcon("dude1.png");
        icon = new JLabel();
        icon.setBounds(20,25,252,247);
        icon.setIcon(image);
        
        text = new JLabel("Create your ");
        text.setBounds(300,100,400,50);
        text.setForeground(Color.yellow);
        text.setFont(new Font("Sans Serif",Font.BOLD,40));

        text1 = new JLabel("User Account!");
        text1.setBounds(280,170,440,40);
        text1.setForeground(Color.yellow);
        text1.setFont(new Font("Sans Serif",Font.BOLD,40));
        
        
        
        create.addActionListener(this);
        back.addActionListener(this);
        
        this.add(panel);
        
        panel.add(emailLabel);
        panel.add(firstNameLabel);
        panel.add(secondNameLabel);
        panel.add(passLabel);
        
        panel.add(email);
        panel.add(Fname);
        panel.add(Lname);
        panel.add(pass);
        
        panel.add(icon);
        panel.add(text);
        panel.add(text1);
        
        panel.add(create);
        panel.add(back);
        
        
        
        this.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String fEmail = email.getText();
        if(ae.getSource()== create){
            if(email.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter your email!");
            }
            else if(!fEmail.contains("@gmail.com"))
             JOptionPane.showMessageDialog(null, "Please enter correct email!");
            else if(Lname.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter your Name please!");
            }
            else if(pass.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter your Name please!");
            }
            else{
                try {
                    try (Statement stmt = con.createStatement()) {
                        String fName = Fname.getText();
                        String LName = Lname.getText();
                        String password = pass.getText();
                        String dbop = "INSERT INTO userdata VALUES('" + fEmail +"','"+ fName +"','"+ LName +"','"+ password +"')";
                        stmt.execute(dbop);
                    }
                    this.setVisible(false);
                    logIn login = new logIn();
                } catch (SQLException ex) {
                    Logger.getLogger(signUp.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(this, "Account has been created");
                email.setText("");
                Fname.setText("");
                Lname.setText("");
                pass.setText("");
            }
            
        }
        if(ae.getSource()== back){
            this.setVisible(false);
            logIn login = new logIn();
        }
    }
    
    private void createDatabase(){
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
}
