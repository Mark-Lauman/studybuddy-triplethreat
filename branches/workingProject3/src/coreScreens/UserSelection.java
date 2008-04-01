/*
 * UserSelection.java
 * 
 * Team Triple Threat
 * Log:
 * 02/21/2008 Vic completed the class
 * 02/11/2008 Mark Lauman Created Template
 */
package coreScreens;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.channels.FileChannel;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.util.ArrayList;

/**
 * This class handles all the requests related to users on the User Selection Screen.
 * It scans the folders' name of each user and display it on the selection menu.
 * @author Team Triple Threat
 * @see <a href="http://java.sun.com/javase/6/docs/api/javax/swing/JPanel.html">
 *      javax.swing.JPanel</a>
 */
public class UserSelection extends buddyLibrary.SelectionMenu {

    /** the space between buttons */
    public final int subButtonwidth = 50;
    /** the sub button - New Player */
    public final String newPlayerText = "New Player";
    /** the sub button - Delete Player */
    public final String deletePlayerText = "Delete Player";
    /** the sub button - Login */
    public final String loginUserText = "Login";
    /** the directory to store user's data */
    public final String userDIR = "./Data";
    /** an array list to store usernames */
    public ArrayList<String> userList = new ArrayList<String>();
    private ArrayList<String> undo = new ArrayList<String>();

    /**
     * Constructs a <code>UserSelection</code> menu with specified width 
     * and height and creates a directory of the user's name to store data.
     * The names are stored in a <code>DefaultListModel</code> and an array list 
     * for checking if a user exists already.
     * Also, it creates sub buttons and main button.
     */
    public UserSelection(int width, int height, ActionListener c) {
        super("User Selection", width, height, c);
        File f = new File(userDIR);  //add all users to the list and create the directory   
        String[] fileList = f.list();

        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].indexOf(".") < 0 && !fileList[i].equals("~temp")) {
                list.addElement(fileList[i]); //add the names to list
                userList.add(fileList[i].toUpperCase());
            }
        }


        //add sub button - New Player
        addSubButton(newPlayerText, subButtonwidth);
        //add sub button - Delete Player
        addSubButton(deletePlayerText, subButtonwidth);
        //add the main button - Login
        setMainButton("Login");
    }

    /**
     * Adds a user to the list and creates a folder of user's name
     */
    private void addUser() {
        String str = (String) JOptionPane.showInputDialog(null,
                "Please enter the name of the new player:",
                "New Player",
                JOptionPane.PLAIN_MESSAGE, null, null, null);
        //if a name is entered, then create a folder of the same name
        //if the name is alrady on the list, then show the warning message
        if (str != null) {
            if (userList.contains(str.toUpperCase())) {
                JOptionPane.showMessageDialog(null,
                        "The user is already on the list!", "Warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (str.equals("~temp")) {
                JOptionPane.showMessageDialog(null,
                        "That username is reserved! \n Please choose another", "Warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            //trim the str
            str = str.trim();

            //if str is empty
            if (str.length() == 0) {
                JOptionPane.showMessageDialog(null,
                        "Invalid username! Please enter again!", "Warning",
                        JOptionPane.WARNING_MESSAGE);
                //prompt the user to enter again
                addUser();

                return;
            }


            userList.add(str.toUpperCase());

            //creates the directory of the user
            File f = new File(userDIR + "/" + str);
            f.mkdir();
            //and puts on the list
            addChoice(str);
            undo.add("Add - " + str);
        }
    }

    private void addUser(String name) {
        try {
            userList.add(name.toUpperCase());
            //creates the directory of the user
            File f = new File(userDIR + "/" + name);
            f.mkdir();
            //and puts on the list
            addChoice(name);
            File sourceFolder = new File(userDIR + "/~temp/" + name + "/");

            for (int i = 0; i < sourceFolder.list().length; i++) {
                copyFile(new File(userDIR + "/~temp/" + name + "/" + sourceFolder.list()[i]), new File(userDIR + "/" + name + "/" + sourceFolder.list()[i]));
            }
            deleteDir(sourceFolder);
        } catch (Exception ex) {
        }
    }

    /**
     * Deletes the user from the list and also deletes the folder
     */
    private void deleteUser() {
        String userTobeDeleted = getSelection();

        //if nothing is chosen on the list, then show the warning message
        if (userTobeDeleted == null) {
            JOptionPane.showMessageDialog(null,
                    "Please select one user from the list!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        //the warning message
        int n = JOptionPane.showConfirmDialog(null,
                "Once you delete a player, all of their" + "study buddy scores are erased.\n" + "There is no way to get this data back.\n" + "Do you still want to delete this person?",
                "Delete Player", JOptionPane.YES_NO_OPTION);

        // if the user decides to delete the user, n = 0
        // and remove the user from the list and delete the directory as well
        if (n == 0) {
            list.removeElement(userTobeDeleted);
            userList.remove(userTobeDeleted.toUpperCase());
            File f = new File(userDIR + "/" + userTobeDeleted);
            try {
                File copyFolder = new File(userDIR + "/~temp/" + userTobeDeleted + "/");
                copyFolder.mkdir();
                for (int i = 0; i < f.list().length; i++) {
                    copyFile(new File(userDIR + "/" + userTobeDeleted + "/" + f.list()[i]), new File(userDIR + "/~temp/" + userTobeDeleted + "/" + f.list()[i]));
                }
            } catch (Exception ex) {
            }
            deleteDir(f);
            undo.add("Delete - " + userTobeDeleted);
        }
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

    /**
     * Deletes the user from the list and also deletes the folder
     */
    private void deleteUser(String name) {
        list.removeElement(name);
        userList.remove(name.toUpperCase());
        File f = new File(userDIR + "/" + name);
        deleteDir(f);
    }

    public static boolean deleteDir(File dir) {
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

    /**
     * Events for each button
     */
    public void actionPerformed(ActionEvent e) {
        //if the command is new player
        if (e.getActionCommand().compareTo(newPlayerText) == 0) {
            addUser();
        } else if (e.getActionCommand().compareTo(deletePlayerText) == 0) {
            deleteUser();
        } else if (e.getActionCommand().compareTo(loginUserText) == 0) {

        } else if (e.getActionCommand().equals("Undo") && undo.size() > 0) {
            String cmd = undo.get(undo.size() - 1);
            if (cmd.contains("Add - ")) {
                deleteUser(cmd.substring(6, cmd.length()));
                undo.remove(undo.size() - 1);
            } else if (cmd.contains("Delete - ")) {
                addUser(cmd.substring(9, cmd.length()));
                undo.remove(undo.size() - 1);
            }
        }
    }
}
