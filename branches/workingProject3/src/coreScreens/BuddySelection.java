  /*
 * BuddySelection.java
 * 
 * Team Triple Threat
 * Log:
 * 02/28/2008 Allan Lei added file checking to add buddy
 * 02/22/2008 Vic Kao complete deleteBuddy and exportBuddy
 * 02/21/2008 Vic Kao complete addBuddy  
 */
package coreScreens;

import buddyLibrary.JarResource;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.channels.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.util.*;

/**
 * This class handle all the requests related to buddies on the <code>Buddy</code>
 * screen.
 * @author Vic Kao Team Triple Threat
 * @see <a href="http://java.sun.com/javase/6/docs/api/javax/swing/JPanel.html">
 *      javax.swing.JPanel</a>
 */
public class BuddySelection extends buddyLibrary.SelectionMenu {

    /** the map for buddy name on screen to the file choose of it */
    HashMap<String, String> exportmap = new HashMap<String, String>();
    /** the space between buttons */
    private final int subButtonwidth = 25;
    /** the sub button - Add Buddy */
    private final String addBuddytext = "Add Buddy";
    /** the sub button - Export Buddy */
    private final String exportBuddytext = "Export Buddy";
    /** the sub button - Delete Buddy */
    private final String deleteBuddytext = "Delete Buddy";
    /** the main button - Start */
    private final String startuddytext = "Start";
    /** the directory to store user's data */
    private final String buddydir = "./Buddies";
    private ArrayList<String> undo = new ArrayList<String>();

    /**
     * Constructs a <code>BuddySelection</code> menu with specified width 
     * and height and creates a directory of the buddy's name to store data.
     * The names are stored in a <code>DefaultListModel</code> and an array list 
     * for checking if a user exists already.
     * Also, it creates sub buttons and main button.
     */
    public BuddySelection(int width, int height, ActionListener c) {
        super("Buddy Selection", width, height, c);

        File f = new File(buddydir);  //add all buddies to the list and create a directory    

        String[] fileList = f.list();
        String classFile = ".class";
        for (int i = 0; i < fileList.length; i++) {
            //if the file doesn't have "."
            if (fileList[i].indexOf(".") < 0 && fileList[i].indexOf(classFile) > 0) {
                list.addElement(fileList[i]); //add the names to list
                exportmap.put(fileList[i], buddydir + "/" + fileList[i]);
            } else {
                if (fileList[i].indexOf(classFile) > 0) {
                    list.addElement(fileList[i].substring(0, fileList[i].indexOf("."))); //add the names to list
                    exportmap.put(fileList[i].substring(0, fileList[i].indexOf(".")), buddydir + "/" + fileList[i]);
                }
            }
        }

        //add sub button - Add Buddy
        addSubButton(addBuddytext, subButtonwidth);
        //add sub button - Export Buddy
        addSubButton(exportBuddytext, subButtonwidth);
        //add sub button - Delete Buddy 
        addSubButton(deleteBuddytext, subButtonwidth);
        //add the main button - Start to play
        setMainButton("Start");
    }

    /**
     * Adds the name of the jar file chosen to the list, and make a copy 
     * of the jar file itself to the buddy folder
     */
    private void addBuddy() {
        //open a file choose to locate the .jar file
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {

            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".jar") || f.isDirectory();
            }

            //shows ".jar" in the file type field
            public String getDescription() {
                return "*.jar";
            }
        });

        int r = chooser.showOpenDialog(new JFrame());
        if (r == JFileChooser.APPROVE_OPTION) {
            String name = chooser.getSelectedFile().getName();
            name = name.substring(0, name.lastIndexOf(".")); //get the name before the extension type
            try {
                JarResource jr = new JarResource(chooser.getSelectedFile().getAbsolutePath());

                if (jr.contains(name + ".class")) {
                    ArrayList<String> files = jr.getFilename();
                    File f = new File(buddydir + "/" + name);
                    f.mkdir();
                    for (int i = 0; i < files.size(); i++) {
                        if (files.get(i).endsWith("/")) {
                            f = new File(buddydir + "/" + name + "/" + files.get(i));
                            f.mkdir();
                        } else {
                            jr.extract(files.get(i), buddydir + "/" + name + "/");
                            if (files.get(i).endsWith(".class")) {
                                jr.extract(files.get(i), buddydir + "/");
                            }
                        }
                    }
                    copyFile(chooser.getSelectedFile(), new File(buddydir + "/" + chooser.getSelectedFile().getName()));
                    exportmap.put(name, buddydir + "/" + chooser.getSelectedFile().getName());
                    addChoice(name); //add the name to the list
                    undo.add("Add - " + name);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "The chosen file is incompatible with our system! Please choose another", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {

            }
        }
    }

    private void addBuddy(String name) {
        try {
            JarResource jr = new JarResource(buddydir + "/~temp/" + name + ".jar");

            if (jr.contains(name + ".class")) {
                ArrayList<String> files = jr.getFilename();
                File f = new File(buddydir + "/" + name);
                f.mkdir();
                for (int i = 0; i < files.size(); i++) {
                    if (files.get(i).endsWith("/")) {
                        f = new File(buddydir + "/" + name + "/" + files.get(i));
                        f.mkdir();
                    } else {
                        jr.extract(files.get(i), buddydir + "/" + name + "/");
                        if (files.get(i).endsWith(".class")) {
                            jr.extract(files.get(i), buddydir + "/");
                        }
                    }
                }
                copyFile(new File(buddydir + "/~temp/" + name + ".jar"), new File(buddydir + "/" + name + ".jar"));
                addChoice(name); //add the name to the list
            } else {
                JOptionPane.showMessageDialog(null,
                        "The chosen file is incompatible with our system! Please choose another", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {

        }
    }

    /**
     * Removes the buddy's name and buddy.jar from the list and the buddy folder 
     */
    private void deleteBuddy() {
        String buddyTobeDeleted = getSelection();

        //if nothing is chosen on the list, then show the warning message
        if (buddyTobeDeleted == null) {
            JOptionPane.showMessageDialog(null,
                    "Please select one buddy from the list!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        //warning message when about to delete the buddy
        int n = JOptionPane.showConfirmDialog(null,
                "Once you delete a buddy, all player data for that" + "buddy is erased.\n" + "There is no way to get this data back.\n" + "Do you still want to delete this buddy?",
                "Delete Player",
                JOptionPane.YES_NO_OPTION);

        // if the user decides to delete the user, n = 0
        // and remove the user from the list and delete the directory as well
        if (n == 0) {
            list.removeElement(buddyTobeDeleted);

            try {
                copyFile(new File(buddydir + "/" + buddyTobeDeleted + ".jar"), new File(buddydir + "/~temp/" + buddyTobeDeleted + ".jar"));
            } catch (Exception ex) {

            }
            File classFile = new File(buddydir + "/" + buddyTobeDeleted + ".class");
            classFile.delete();
            File jarFile = new File(buddydir + "/" + buddyTobeDeleted + ".jar");
            jarFile.delete();
            File dirFile = new File(buddydir + "/" + buddyTobeDeleted + "/");
            deleteDir(dirFile);
            exportmap.remove(buddyTobeDeleted);
            undo.add("Delete - " + buddyTobeDeleted);
        }
    }

    /**
     * Removes the buddy's name and buddy.jar from the list and the buddy folder 
     */
    private void deleteBuddy(String s) {
        list.removeElement(s);
        File classFile = new File(buddydir + "/" + s + ".class");
        classFile.delete();
        File jarFile = new File(buddydir + "/" + s + ".jar");
        jarFile.delete();
        File dirFile = new File(buddydir + "/" + s + "/");
        deleteDir(dirFile);
        exportmap.remove(s);
    }

    /**
     * Exports the selected buddy and saves to the specified location
     */
    private void exportBuddy() {
        String tempExport = getSelection();
        //if nothing is chosen on the list, then pop up the warning message
        if (tempExport == null) {
            JOptionPane.showMessageDialog(null,
                    "Please select one buddy from the list!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        //If a buddy is selected on the list, then commit the export 
        File orginalFile = new File(System.getProperty("user.dir") + "/Buddies/" + getSelection() + ".jar");

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {

            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith("/") || f.isDirectory();
            }

            //shows ".jar" in the file type field
            public String getDescription() {
                return "Folders";
            }
        });

        // Open chooser dialog
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {

            File target = new File(chooser.getSelectedFile().getAbsolutePath() + "/" + tempExport + ".jar");
            try {
                copyFile(orginalFile, target);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Sorry, we cannot find that specific path.  Please choose another", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
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

    public void actionPerformed(ActionEvent e) {
        //if the command is Add buddy
        if (e.getActionCommand().compareTo(addBuddytext) == 0) {
            addBuddy();
        } else if (e.getActionCommand().compareTo(deleteBuddytext) == 0) {
            deleteBuddy();
        } else if (e.getActionCommand().compareTo(exportBuddytext) == 0) {
            exportBuddy();
        } else if (e.getActionCommand().compareTo(startuddytext) == 0) {

        } else if (e.getActionCommand().equals("Undo") && undo.size() > 0) {
            String cmd = undo.get(undo.size() - 1);
            if (cmd.contains("Add - ")) {
                deleteBuddy(cmd.substring(6, cmd.length()));
                undo.remove(undo.size() - 1);
            } else if (cmd.contains("Delete - ")) {
                addBuddy(cmd.substring(9, cmd.length()));
                undo.remove(undo.size() - 1);
            }
        }
    }
}
