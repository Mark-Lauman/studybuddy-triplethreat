  /*
   * BuddySelection.java
   * 
   * Team Triple Threat
   * Log:
   * 02/22/2008 Vic Kao complete deleteBuddy and exportBuddy
   * 02/21/2008 Vic Kao complete addBuddy  
  */

package buddyLibrary;

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
<<<<<<< .mine

    /** the map for buddy name on screen to the file choose of it */
    HashMap<String, String> exportmap = new HashMap<String, String>();
    /** the space between buttons */
    public final int subButtonwidth = 25;
    /** the string names for subbuttons and the main button */
    public final String addBuddytext = "Add Buddy";
    public final String exportBuddytext = "Export Buddy";
    public final String deleteBuddytext = "Delete Buddy";
    public final String startBuddytext = "Start";
    /** the directory to store user's data */
    //public final String buddydir="./Data";
    public final String buddydir = "./build/classes/Buddies";

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
        for (int i = 0; i < fileList.length; i++) {
            //if the file doesn't have "."
            if (fileList[i].indexOf(".") < 0) {

=======
   /** the map for buddy name on screen to the file choose of it */ 
   HashMap<String,String> exportmap= new HashMap<String,String>();
   /** the space between buttons */     
   public final int subButtonwidth = 25 ;
   /** the string name for subbuttons "Add Buddy" */
   public final String addBuddytext = "Add Buddy"; 
   /** the string name for subbuttons "Export Buddy" */
   public final String exportBuddytext = "Export Buddy";
   /** the string name for subbuttons "Delete Buddy" */
   public final String deleteBuddytext = "Delete Buddy";
   /** the string name for the main button "Start" */
   public final String startuddytext = "Start";
   /** the directory to store user's data */
   public final String buddydir="./test";
   
   /**
    * Constructs a <code>BuddySelection</code> menu with specified width 
    * and height and creates a directory of the buddy's name to store data.
    * The names are stored in a <code>DefaultListModel</code> and an array list 
    * for checking if a user exists already.
    * Also, it creates sub buttons and main button.
    */   
   public BuddySelection (int width, int height) {
       super(width, height);
       
       File f = new File(buddydir);  //add all buddies to the list and create a directory    
       
       String [] fileList = f.list();       
       for(int i = 0; i < fileList.length; i++) {
           //if the file doesn't have "."
           if(fileList[i].indexOf(".")<0) {
>>>>>>> .r370
                list.addElement(fileList[i]); //add the names to list
                exportmap.put(fileList[i], buddydir + "/" + fileList[i]);
           }
           else {
               list.addElement(fileList[i].substring(0, fileList[i].indexOf("."))); //add the names to list
               exportmap.put(fileList[i].substring(0, fileList[i].indexOf(".")), buddydir + "/" + fileList[i]);
           }           
       }
       
       //add sub button - Add Buddy
       addSubButton(addBuddytext, subButtonwidth);
       //add sub button - Export Buddy
       addSubButton(exportBuddytext, subButtonwidth);
       //add sub button - Delete Buddy 
       addSubButton(deleteBuddytext, subButtonwidth);
       //add the main button - Start
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
       return f.getName().toLowerCase().endsWith(".jar")
           || f.isDirectory();
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
           addChoice(name); //add the name to the list

<<<<<<< .mine
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
            addChoice(name); //add the name to the list

            try {
                copyFile(chooser.getSelectedFile(), new File(buddydir + "/" +
                        chooser.getSelectedFile().getName()));
                exportmap.put(name, buddydir + "/" + chooser.getSelectedFile().getName());

                JarResource j = new JarResource(chooser.getSelectedFile().getPath());

                j.extract(name + ".class", buddydir + "/");

            } catch (IOException e) {
                
            }
=======
       try{
           copyFile(chooser.getSelectedFile(), new File(buddydir + "/" + 
                   chooser.getSelectedFile().getName()));
           exportmap.put(name, buddydir + "/" + chooser.getSelectedFile().getName());
       }catch( IOException e){
          ;}
>>>>>>> .r370
        }
  }
       
   /**
    * Removes the buddy's name and buddy.jar from the list and the buddy folder 
    */  
   private void deleteBuddy() {
       
       String buddyTobeDeleted = getSelection();
       
       //if nothing is chosen on the list, then show the warning message
       if (buddyTobeDeleted == null ) {
           JOptionPane.showMessageDialog(null,
                   "Please select one buddy from the list!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
           return;
       }
       //warning message when about to delete the buddy
       int n = JOptionPane.showConfirmDialog(null,
                         "Once you delete a buddy, all player data for that" 
                        + "buddy is erased.\n" 
                        + "There is no way to get this data back.\n" 
                        + "Do you still want to delete this buddy?",
                            "Delete Player",
                        JOptionPane.YES_NO_OPTION);
       
       // if the user decides to delete the user, n = 0
       // and remove the user from the list and delete the directory as well
        if(n == 0) {
            list.removeElement(buddyTobeDeleted);
            
         File f = new File(exportmap.get(buddyTobeDeleted));
            f.delete();            
            exportmap.remove(buddyTobeDeleted);            
        }              
   }
   
   /**
    * Exports the selected buddy and saves to the specified location
    */   
   private void exportBuddy() {
       
       String tempExport = getSelection();
       
       //if nothing is chosen on the list, then pop up the warning message
       if(tempExport == null) {
           JOptionPane.showMessageDialog(null,
                   "Please select one buddy from the list!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
           return;
       }
       
       //If a buddy is selected on the list, then commit the export 
       File orginalFile= new File(exportmap.get(tempExport));
       
       JFileChooser chooser = new JFileChooser();   

       chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
       public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith(".jar")
            || f.isDirectory();
      }

      //shows ".jar" in the file type field
      public String getDescription() {
        return "*.jar";
      }
    });
    
      int returnVal = chooser.showSaveDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
          String tofilename="";
          //adds ".jar" for the file
          if(chooser.getSelectedFile().getName().indexOf(".") < 0)
              tofilename=chooser.getSelectedFile().getPath() + ".jar";
           else
              tofilename=chooser.getSelectedFile().getPath();

       File file = new File(tofilename);
           try {
               copyFile(orginalFile, file);}
           catch(IOException e){
               System.out.println("Error!");
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
        } 
        catch (IOException e) {
            throw e;
        }
        finally {
            if (inChannel != null) inChannel.close();
            if (outChannel != null) outChannel.close();
        }
    }
   
    public void actionPerformed(ActionEvent e) {
       //if the command is Add buddy
       if(e.getActionCommand().compareTo(addBuddytext) == 0) {
           addBuddy();
       }//esle if the command is Delete buddy
       else if(e.getActionCommand().compareTo(deleteBuddytext) == 0){
           deleteBuddy();
       }
       else if(e.getActionCommand().compareTo(exportBuddytext) == 0){
            exportBuddy();
       }
       else if(e.getActionCommand().compareTo(startuddytext) == 0){
           
       }
    } 
}
