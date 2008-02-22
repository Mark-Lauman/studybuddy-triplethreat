package core;

/*
BarGraph.java
This class displays the data inside it as a bar graph

Team Triple Threat
Log:
02/22/2008 Mark Lauman Moved components
01/22/2008 Allan Lei Created
*/

import testers.TestBuddy;
import buddyLibrary.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class Core extends JFrame implements ActionListener{
    private String user = "testUser";
    
    
    public static void main(String[] args) {
        new Core();
    }
    
    public Core() {
        setTitle("Core");
        Container content = getContentPane();
        content.setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(1200, 700));
        setLayout(new BorderLayout());
        setLocation(60, 50);
        
        TestBuddy t = new TestBuddy(this);


        content.add(t, BorderLayout.CENTER);
        
        
        
        
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
    }
    
    public String getUser(){
        return user;
    }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
