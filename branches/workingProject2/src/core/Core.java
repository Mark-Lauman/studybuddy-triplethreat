 /*
 * Core.java
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
import javax.swing.*;

public class Core extends JFrame implements ActionListener{
    private String user = "asd";
    private Container content;
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
        
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

 /**
 *  Creates the menu bar
 *
 * @return  Makes the menu bar
 */
    private void makeMenuBar(){
        mb = new JMenuBar();
        JMenu m = new JMenu("File");
        mb.add(m);
    }
    
 /**
 *  Retrieves the user name in Core
 *
 * @return user Returns the username stored in Core
 */
    public String getUser() {
        return user;
    }
    
    
 /**
 *  Sets the user name in Core
 *
 * @param  userName Name to change current user to
 */
    public void setUser(String userName){
        user = userName;
    }

    
 /**
 *  Loads a .class file in the Buddies folder and converts it to a Buddy Class
 *
 * @param  classN Name of the .class file to be loaded
 * @return  b The loaded .class file returned as a Buddy instance
 */
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
            content.add(bs, BorderLayout.WEST);
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
