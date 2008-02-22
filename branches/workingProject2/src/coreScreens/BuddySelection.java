  /*
   * BuddySelection.java
   * This class handle all the requests related to user
   * Team Triple Threat
   * Log:
   * 02/21/2008 Vic Kao complete the working module  
  */

package buddyLibrary;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.util.ArrayList;

public class BuddySelection extends buddyLibrary.SelectionMenu1 {
    
   public final int subButtonwidth = 25 ;   //set the space between buttons
   public final String addBuddytext = "Add Buddy";  //set the tag for convention
   public final String exportBuddytext = "Export Buddy";  //set the tag for convention
   public final String deleteBuddytext = "Delete Buddy";  //set the tag for convention
   public final String startuddytext = "Start";  //set the tag for convention
       
   public BuddySelection (int width, int height) {
       super(width, height);
       
       addSubButton(addBuddytext, subButtonwidth); //add sub button - Add Buddy
       addSubButton(exportBuddytext, subButtonwidth);   //add sub button - Export Buddy
       addSubButton(deleteBuddytext, subButtonwidth);   //add sub button - Delete Buddy       
       setMainButton("Start");  //add the main button - Start to play
    }
   
   
   private void addBuddy() {
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File("."));

    chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
      public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith(".jar")
            || f.isDirectory();
      }

      public String getDescription() {
        return "*.jar";
      }
    });

    int r = chooser.showOpenDialog(new JFrame());
    if (r == JFileChooser.APPROVE_OPTION) {
      String name = chooser.getSelectedFile().getName();
      System.out.println(name);
    }
  }
       
   
   
   private void deleteBuddy() {
       
   }
   
   private void exportBuddy() {
       
   }
   
    public void actionPerformed(ActionEvent e) {
       //if the command is Add buddy
       if(e.getActionCommand().compareTo(addBuddytext)== 0) {
           addBuddy();
       }//esle if the command is Delete buddy
       else if(e.getActionCommand().compareTo(deleteBuddytext)== 0){
           deleteBuddy();
       }
       else if(e.getActionCommand().compareTo(exportBuddytext)== 0) {
            exportBuddy();
       }
       else if(e.getActionCommand().compareTo(startuddytext) == 0) {
           
       }
    } 
}
