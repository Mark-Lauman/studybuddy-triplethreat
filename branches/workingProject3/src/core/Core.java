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
import java.net.URL;
import java.net.URLClassLoader;
import javax.swing.*;

public class Core extends JFrame implements ActionListener {

    private String user = "";
    private Container content;
    private JMenuBar mb;
    private UserSelection us;
    private UserChoice uc;
    private BuddySelection bs;
    private Stats stats;
    private Buddy b;
    private JButton back;
    private String currentscreen;

    public static void main(String[] args) {
        new Core();
    }

    public Core() {
        setTitle("Buddy App V3");
        content = getContentPane();
        content.setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        setLocation(60, 50);
        makeMenuBar();
        content.add(mb, BorderLayout.NORTH);

        us = new UserSelection(300, 400, this);
        content.add(us, BorderLayout.WEST);
        currentscreen = "userselection";

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                exit();
            }
        });
        pack();
        setVisible(true);

        File userFolder = new File("./Data/~temp/");
        userFolder.mkdir();
        File buddyFolder = new File("./Buddies/~temp/");
        buddyFolder.mkdir();
    }

    /**
     *  Creates the menu bar
     *
     * @return  Makes the menu bar
     */
    private void makeMenuBar() {
        mb = new JMenuBar();
        mb.setLayout(new BorderLayout());
        JMenu m = new JMenu("File");
        mb.add(m, BorderLayout.LINE_START);

        JMenuItem undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undo.setActionCommand("Undo");
        undo.addActionListener(this);
        m.add(undo);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        exit.setActionCommand("Exit");
        exit.addActionListener(this);
        m.add(exit);

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
        File file = new File(System.getProperty("user.dir"));

        try {
            // Convert File to a URL
            
            URL url = file.toURI().toURL();          // file:/c:/myclasses/
            URL[] urls = new URL[]{url};

            // Create a new class loader with the directory
            ClassLoader cl = new URLClassLoader(urls);

            // Load in the class; MyClass.class should be located in
            // the directory file:/c:/myclasses/com/mycompany
            Class cls = cl.loadClass("Buddies." + classN);
            Buddy b = (Buddy)cls.newInstance();
            return b;
        } catch (Exception e) {
            return null;
        }

    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    private void exit() {
        File userFolder = new File("./Data/~temp/");
        deleteDir(userFolder);
        File buddyFolder = new File("./Buddies/~temp/");
        deleteDir(buddyFolder);
        System.exit(0);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            if (us.getSelection() != null) {
                setUser(us.getSelection());
                setTitle("Buddy App V3 - " + user);
                us.setVisible(false);
                content.remove(us);
                uc = new UserChoice(this);
                content.add(uc, BorderLayout.WEST);
                currentscreen = "userchoice";
                back.setActionCommand("BackToUserSelection");
                back.setText("Logoff");
                back.setVisible(true);
                invalidate();
                validate();
                pack();
            }
        } else if (e.getActionCommand().equals("BackToUserSelection")) {
            setUser(null);
            setTitle("Buddy App V3");
            uc.setVisible(false);
            content.remove(uc);
            us = new UserSelection(300, 400, this);
            content.add(us, BorderLayout.WEST);
            currentscreen = "userselection";
            back.setActionCommand("None");
            back.setVisible(false);
            validate();
        } else if (e.getActionCommand().equals("Statistics")) {
            uc.setVisible(false);
            content.remove(uc);
            stats = new Stats(getUser());
            stats.setReference(this);
            content.add(stats, BorderLayout.CENTER);
            currentscreen = "stats";
            back.setActionCommand("BackToUCFromStats");
            back.setText("Back");
            validate();
        } else if (e.getActionCommand().equals("BackToUCFromStats")) {
            stats.setVisible(false);
            content.remove(stats);
            uc = new UserChoice(this);
            content.add(uc, BorderLayout.WEST);
            currentscreen = "userchoice";
            back.setActionCommand("BackToUserSelection");
            back.setText("Logoff");
            validate();
        } else if (e.getActionCommand().equals("Study Buddy")) {
            uc.setVisible(false);
            content.remove(uc);
            bs = new BuddySelection(300, 400, this);
            content.add(bs, BorderLayout.WEST);
            currentscreen = "buddyselection";
            back.setActionCommand("BackToUCFromBS");
            back.setText("Back");
            validate();
        } else if (e.getActionCommand().equals("BackToUCFromBS")) {
            bs.setVisible(false);
            content.remove(bs);
            uc = new UserChoice(this);
            content.add(uc, BorderLayout.WEST);
            currentscreen = "userchoice";
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
                currentscreen = "buddy";
                back.setActionCommand("BackToBS");
                validate();
            }
        } else if (e.getActionCommand().equals("BackToBS")) {
            b.actionPerformed(new ActionEvent(this, 0, "closed"));
            b.setVisible(false);
            content.remove(b);
            bs = new BuddySelection(300, 400, this);
            content.add(bs, BorderLayout.WEST);
            currentscreen = "buddyselection";
            back.setActionCommand("BackToUCFromBS");
            validate();
        } else if (e.getActionCommand().equals("Exit")) {
            exit();
        } else if (e.getActionCommand().equals("Undo")) {
            if (currentscreen.equals("buddyselection")) {
                bs.actionPerformed(new ActionEvent(this, 0, "Undo"));
            } else if (currentscreen.equals("userselection")) {
                us.actionPerformed(new ActionEvent(this, 0, "Undo"));
            }
        }

    }
}
