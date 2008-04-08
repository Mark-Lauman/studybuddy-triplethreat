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
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
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
    private JPanel screen;
    private ImageIcon icon = new ImageIcon("bg.jpg");

    public static void main(String[] args) {
        new Core();
    }

    public Core() {
        File buddyf = new File(System.getProperty("user.dir") + "/Buddies/");
        buddyf.mkdir();
        File dataf = new File(System.getProperty("user.dir") + "/Data/");
        dataf.mkdir();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("core/bg.jpg");
        if(is != null){
            try{
            Image i = ImageIO.read(is);
            icon = new ImageIcon(i);
            is.close();
            }catch(Exception ex){
            }
        }
        InputStream is2 = this.getClass().getClassLoader().getResourceAsStream("core/ico.jpg");
        if(is != null){
            try{
            Image i = ImageIO.read(is2);
            this.setIconImage(i);
            is2.close();
            }catch(Exception ex){
            }
        }
        
        setTitle("SnowBird V3");
        content = getContentPane();
        content.setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        setLocation(60, 50);
        makeMenuBar();
        content.add(mb, BorderLayout.NORTH);

        screen = new JPanel() {

            protected void paintComponent(Graphics g) {
                //  Dispaly image at at full size
                //g.drawImage(icon.getImage(), 0, 0, null);

                //  Scale image to size of component
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);

            //super.paintComponent(g);
            }
        };
        screen.setLayout(null);
        screen.setOpaque(false);
        content.add(screen, BorderLayout.CENTER);

        
        setVisible(true);
        us = new UserSelection(400, 400, this);
        setCenter(us);
        screen.add(us);
        currentscreen = "userselection";

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {
                exit();
            }
        });
        screen.addComponentListener(new ComponentListener() {

            public void componentResized(ComponentEvent e) {
                if(!currentscreen.equals("buddy")){
                    setCenter(screen.getComponents()[0]);
                }
                
            }
            public void componentHidden(ComponentEvent e) {
            }
            public void componentMoved(ComponentEvent e) {
            }
            public void componentShown(ComponentEvent e) {
            }
        });
        

        File userFolder = new File("./Data/~temp/");
        userFolder.mkdir();
        File buddyFolder = new File("./Buddies/~temp/");
        buddyFolder.mkdir();

        pack();
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

            ClassLoader cl = new URLClassLoader(urls);
            Class cls = cl.loadClass("Buddies." + classN);
            Buddy b = (Buddy) cls.newInstance();
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    private void loadResources(File buddyFolder, String bname) {
        for (int i = 0; i < buddyFolder.listFiles().length; i++) {
            if (buddyFolder.listFiles()[i].toString().contains(".class")) {
                String s = buddyFolder.listFiles()[i].toString().replace(System.getProperty("user.dir") + "\\Buddies\\" + bname + "\\", "");
                //System.out.println(s);
                try {
                    File newF = new File(s);
                    copyFile(buddyFolder.listFiles()[i], newF);
                    //tempFiles.add(newF);
                    newF.deleteOnExit();
                } catch (Exception ex) {

                }
            }
            if (buddyFolder.listFiles()[i].isDirectory() && containsClass(buddyFolder.listFiles()[i], bname)) {
                String s = buddyFolder.listFiles()[i].toString().replace(System.getProperty("user.dir") + "\\Buddies\\" + bname + "\\", "");
                //System.out.println(s);
                File f = new File(s);
                f.mkdir();
                //tempFiles.add(f);
                f.deleteOnExit();
                loadResources(buddyFolder.listFiles()[i], bname);
            }
        }
    }

    private boolean containsClass(File buddyFolder, String bname) {
        for (int i = 0; i < buddyFolder.listFiles().length; i++) {
            if (buddyFolder.listFiles()[i].toString().contains(".class")) {
                String s = buddyFolder.listFiles()[i].toString().replace(System.getProperty("user.dir") + "\\Buddies\\" + "\\", "");
                //System.out.println(s);
                return true;
            }
            if (buddyFolder.listFiles()[i].isDirectory()) {
                String s = buddyFolder.listFiles()[i].toString().replace(System.getProperty("user.dir") + "\\Buddies\\" + bname + "\\", "");
                //System.out.println(s);
                containsClass(buddyFolder.listFiles()[i], bname);
            }
        }
        return false;
    }

    /**
     * This function copyFile is copied from http://www.rgagnon.com/javadetails/java-0064.html
     * It performs the copy-file function
     * @param in A File object to be copied
     * @param out A File object to be saved as
     */
    public static void copyFile(File in, File out) throws IOException {
        FileChannel inChannel = new FileInputStream(in).getChannel();
        FileChannel outChannel = new FileOutputStream(out).getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            throw e;
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
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

    private void removeFiles(ArrayList<File> f) {
        for (int i = 0; i < f.size(); i++) {
            f.get(i).deleteOnExit();
        }
    }

    private void exit() {
        File userFolder = new File("./Data/~temp/");
        deleteDir(userFolder);
        File buddyFolder = new File("./Buddies/~temp/");
        deleteDir(buddyFolder);
        System.exit(0);
    }

    /**
     *  Sets the position of any component 
     *
     * @param c The Component to be moved
     * @param x X coordinate that the component will be moved to
     * @param y Y coordinate that the component will be moved to
     * @return Set the position of the component
     */
    public void setPosition(Component c, int x, int y) {
        Insets insets = screen.getInsets();
        c.setBounds(x + insets.left, y + insets.top, c.getPreferredSize().width, c.getPreferredSize().height);
        validate();
    }

    public void setCenter(Component c) {
        setPosition(c, getSize().width / 2 - c.getPreferredSize().width / 2, getSize().height / 2 - c.getPreferredSize().height / 2);
        validate();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            if (us.getSelection() != null) {
                setUser(us.getSelection());
                setTitle("SnowBird V3 - " + user);
                us.setVisible(false);
                screen.remove(us);
                uc = new UserChoice(this);
                uc.setPreferredSize(new Dimension(300,400));
                screen.add(uc);
                setCenter(uc);
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
            setTitle("SnowBird V3");
            uc.setVisible(false);
            screen.remove(uc);
            us = new UserSelection(300, 400, this);
            screen.add(us);
            setCenter(us);
            currentscreen = "userselection";
            back.setActionCommand("None");
            back.setVisible(false);
            validate();
        } else if (e.getActionCommand().equals("Statistics")) {
            uc.setVisible(false);
            screen.remove(uc);
            stats = new Stats(getUser());
            stats.setReference(this);
            screen.add(stats);
            setCenter(stats);
            currentscreen = "stats";
            back.setActionCommand("BackToUCFromStats");
            back.setText("Back");
            validate();
        } else if (e.getActionCommand().equals("BackToUCFromStats")) {
            stats.setVisible(false);
            screen.remove(stats);
            uc = new UserChoice(this);
            screen.add(uc);
            setCenter(uc);
            currentscreen = "userchoice";
            back.setActionCommand("BackToUserSelection");
            back.setText("Logoff");
            validate();
        } else if (e.getActionCommand().equals("Study Buddy")) {
            uc.setVisible(false);
            screen.remove(uc);
            bs = new BuddySelection(300, 400, this);
            screen.add(bs);
            setCenter(bs);
            currentscreen = "buddyselection";
            back.setActionCommand("BackToUCFromBS");
            back.setText("Back");
            validate();
        } else if (e.getActionCommand().equals("BackToUCFromBS")) {
            bs.setVisible(false);
            screen.remove(bs);
            uc = new UserChoice(this);
            screen.add(uc);
            setCenter(uc);
            currentscreen = "userchoice";
            back.setActionCommand("BackToUserSelection");
            back.setText("Logoff");
            validate();
        } else if (e.getActionCommand().equals("Start")) {
            if (bs.getSelection() != null) {
                bs.setVisible(false);
                screen.remove(bs);
                loadResources(new File(System.getProperty("user.dir") + "/Buddies/" + bs.getSelection()), bs.getSelection());
                b = loadBuddy(bs.getSelection());
                b.setReference(this);
                screen.setLayout(new BorderLayout());
                
                screen.add(b, BorderLayout.CENTER);
                setSize(b.getPreferredSize());
                currentscreen = "buddy";
                back.setActionCommand("BackToBS");
                validate();
                //setResizable(false);
            }
        } else if (e.getActionCommand().equals("BackToBS")) {
            b.actionPerformed(new ActionEvent(this, 0, "closed"));
            setResizable(true);
            b.setVisible(false);
            screen.remove(b);
            screen.setLayout(null);
            bs = new BuddySelection(300, 400, this);
            screen.add(bs);
            setCenter(bs);
            this.setPreferredSize(bs.getPreferredSize());
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