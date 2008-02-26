

/*
 * PhotoAlbum.java
 *
 * Created on January 22, 2008, 1:43 PM
 *
 * @author Allan Lei
 * ala16
 * 301028107
 */


 /*
 * BuddySelection.java
 * 
 * Team Triple Threat
 * Log:
 * 
 * 02/25/2008 Allan Lei Core, added screen switiching
 */
package core;

import coreScreens.*;
import buddyLibrary.*;
import Buddies.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class Core extends JFrame implements ActionListener{
    public String user = "asd";
    public Container content;
    private JMenuBar mb;
    private UserSelection us;
    private UserChoice uc;
    private BuddySelection bs;
    private Buddy b;

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
        makeMenuBar();
        content.add(mb, BorderLayout.NORTH);
        
        us = new UserSelection(300, 400, this);
        content.add(us, BorderLayout.WEST);
        
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void makeMenuBar(){
        mb = new JMenuBar();
        JMenu m = new JMenu("File");
        mb.add(m);
    }
    
    public String getUser() {
        return user;
    }
    
    public void setUser(String userName){
        user = userName;
    }

    private Buddy loadBuddy(String classN) {
            try {
                Class c = Class.forName("Buddies." + classN);
                Buddy b = (Buddy)c.newInstance();
                return b;
            } catch (Exception ex) {
                return null;
            }
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Login")){
            if(us.getSelection() != null){
            user = us.getSelection();
            us.setVisible(false);
            content.remove(us);
            uc = new UserChoice(this);
            content.add(uc, BorderLayout.WEST);
            invalidate();
            validate();
            pack();
            }
        }else if(e.getActionCommand().equals("Statistics")){
            //System.out.println("Stats clicked");
        }else if(e.getActionCommand().equals("Study Buddy")){
            uc.setVisible(false);
            content.remove(uc);
            bs = new BuddySelection(300, 400, this);
            content.add(bs, BorderLayout.CENTER);
            validate();
        }else if(e.getActionCommand().equals("Start")){
            if(bs.getSelection() != null){
                bs.setVisible(false);
                content.remove(bs);
                b = loadBuddy(bs.getSelection());
                content.add(b);
                validate();
            }
            
        }
    }
}
