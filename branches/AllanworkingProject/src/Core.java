/*
 * PhotoAlbum.java
 *
 * Created on January 22, 2008, 1:43 PM
 *
 * @author Allan Lei
 * ala16
 * 301028107
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class Core extends JFrame{
    public String user = "testUser";
    
    
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
}
