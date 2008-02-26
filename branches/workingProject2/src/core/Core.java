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
        
        JMenu m = new JMenu("asd");
        
        
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
            user = us.getSelection();
            us.setVisible(false);
            content.remove(us);
            uc = new UserChoice(this);
            content.add(uc, BorderLayout.WEST);
            invalidate();
            validate();
            pack();
        }else if(e.getActionCommand().equals("Statistics")){
            System.out.println("thn");
        }
    }
}
