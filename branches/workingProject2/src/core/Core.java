package core;

/*
 * PhotoAlbum.java
 *
 * Created on January 22, 2008, 1:43 PM
 *
 * @author Allan Lei
 * ala16
 * 301028107
 */
//import Core.TestBuddy;
import coreScreens.*;
import buddyLibrary.*;
import Data.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class Core extends JFrame implements ActionListener {
    private String user;
    Container content;

    public static void main(String[] args) {
        new Core();
    }

    public Core() {
        setTitle("Core");
        content = getContentPane();
        content.setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(1200, 700));
        setLayout(new BorderLayout());
        setLocation(60, 50);

        Buddy b = loadBuddy("TestBudddy1");
        //content.add(b, BorderLayout.CENTER);
        BuddySelection c = new BuddySelection(300, 400);
        content.add(c, BorderLayout.CENTER);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    public String getUser() {
        return user;
    }

    public Buddy loadBuddy(String classN) {
            try {
                Class c = Class.forName("Data." + classN);
                Buddy b = (Buddy) c.newInstance();
                return b;
            } catch (Exception ex) {
                return null;
            }
    }
    
    public void actionPerformed(ActionEvent e) {
    }
}
