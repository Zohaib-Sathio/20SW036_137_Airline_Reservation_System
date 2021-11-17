package semesterproject;

import java.awt.*;
import  javax.swing.*;

public class LoadPage{

    JFrame fm;
    JProgressBar pb;
    JLabel lb;
    JLabel bl;
    JLabel bl1;
    ImageIcon image;

    LoadPage() {

        image = new ImageIcon("dude1.png");
        lb = new JLabel();
        lb.setBounds(160,180,252,247);
        lb.setIcon(image);

        bl = new JLabel("Welcome To ");
        bl.setBounds(180,50,400,40);
        bl.setForeground(Color.yellow);
        bl.setFont(new Font("Sans Serif",Font.BOLD,30));

        bl1 = new JLabel("INTERNATIONAL AIRLINES ");
        bl1.setBounds(80,90,440,40);
        bl1.setForeground(Color.yellow);
        bl1.setFont(new Font("Sans Serif",Font.BOLD,30));

        fm = new JFrame();
        pb = new JProgressBar();
        pb.setFont(new Font("Sans Serif",Font.BOLD,20));

        fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fm.setSize(600, 600);
        fm.setLayout(null);
        fm.setLocationRelativeTo(null);
        fm.setVisible(true);
        fm.setResizable(false);
        fm.getContentPane().setBackground(new Color(200, 0, 50));

        pb.setValue(0);
        pb.setBounds(0, 525, 600, 40);
        pb.setStringPainted(true);
        fm.add(pb);
        fm.add(lb);
        fm.add(bl);
        fm.add(bl1);

        fill();
    }

    private void fill() {
        int counter = 0;
        while (counter <= 100) {
            pb.setValue(counter);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                
            }

            counter += 10;
        }
        if(counter >100){
            pb.setString("Logging In");
            logIn login = new logIn();
            fm.setVisible(false);
        }

    }
}
