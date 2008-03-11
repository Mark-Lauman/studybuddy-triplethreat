 /*
 * Core.java
 * 
 * Team Triple Threat
 * Log:
 * 
 * 02/25/2008 Allan Lei Core, added screen switiching
 */
package core;

import Buddies.*;
import coreScreens.*;
import buddyLibrary.*;
import Buddies.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.*;

public class Core extends JFrame implements ActionListener {

    private String user = "asd";
    private Container content;
    private JMenuBar mb;
    private UserSelection us;
    private UserChoice uc;
    private BuddySelection bs;
    private Stats stats;
    private Buddy b;
    private JButton back;

    public static void main(String[] args) {
        new Core();
    }

    public Core() {
        setTitle("Buddy App V1");
        content = getContentPane();
        content.setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(800, 600));
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
    private void makeMenuBar() {
        mb = new JMenuBar();
        mb.setLayout(new BorderLayout());
        //JMenu m = new JMenu("File");
        //mb.add(m, BorderLayout.LINE_START);
        back = new JButton("Back");
        back.setMargin(new Insets(0, 0, 0, 0));
        back.setPreferredSize(new Dimension(60, 20));
        back.setVisible(false);
        back.setActionCommand("None");
        back.addActionListener(this);
        mb.add(back, BorderLayout.LINE_END);
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
    private void setUser(String userName) {
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

            Buddy b = (Buddy) c.newInstance();

            b.setReference(this);
            return b;
        } catch (Exception ex) {
            return null;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            if (us.getSelection() != null) {
                setUser(us.getSelection());
                setTitle("Buddy App V1 - " + user);
                us.setVisible(false);
                content.remove(us);
                uc = new UserChoice(this);
                content.add(uc, BorderLayout.WEST);
                back.setActionCommand("BackToUserSelection");
                back.setText("Logoff");
                back.setVisible(true);
                invalidate();
                validate();
                pack();
            }
        } else if (e.getActionCommand().equals("BackToUserSelection")) {
            setUser(null);
            setTitle("Buddy App V1");
            uc.setVisible(false);
            content.remove(uc);
            us = new UserSelection(300, 400, this);
            content.add(us, BorderLayout.WEST);
            back.setActionCommand("None");
            back.setVisible(false);
            validate();
        } else if (e.getActionCommand().equals("Statistics")) {
            uc.setVisible(false);
            content.remove(uc);
            stats = new Stats();
            stats.setReference(this);
            content.add(stats, BorderLayout.CENTER);
            back.setActionCommand("BackToUCFromStats");
            back.setText("Back");
            validate();
        } else if (e.getActionCommand().equals("BackToUCFromStats")) {
            stats.setVisible(false);
            content.remove(stats);
            uc = new UserChoice(this);
            content.add(uc, BorderLayout.WEST);
            back.setActionCommand("BackToUserSelection");
            back.setText("Logoff");
            validate();
        } else if (e.getActionCommand().equals("Study Buddy")) {
            uc.setVisible(false);
            content.remove(uc);
            bs = new BuddySelection(300, 400, this);
            content.add(bs, BorderLayout.WEST);
            back.setActionCommand("BackToUCFromBS");
            back.setText("Back");
            validate();
        } else if (e.getActionCommand().equals("BackToUCFromBS")) {
            bs.setVisible(false);
            content.remove(bs);
            uc = new UserChoice(this);
            content.add(uc, BorderLayout.WEST);
            back.setActionCommand("BackToUserSelection");
            back.setText("Logoff");
            validate();
        } else if (e.getActionCommand().equals("Start")) {
            if (bs.getSelection() != null) {
                bs.setVisible(false);
                content.remove(bs);
                b = loadBuddy(bs.getSelection());
                b.setReference(this);
                content.add(b, BorderLayout.CENTER);
                back.setActionCommand("BackToBS");
                validate();
            }
        } else if (e.getActionCommand().equals("BackToBS")) {
            b.setVisible(false);
            content.remove(b);
            bs = new BuddySelection(300, 400, this);
            content.add(bs, BorderLayout.WEST);
            back.setActionCommand("BackToUCFromBS");
            validate();
        }
    }
}
