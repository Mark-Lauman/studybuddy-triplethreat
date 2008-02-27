/*
 * UserSelection.java
 * 
 * Team Triple Threat
 * Log:
 * 02/21/2008 Vic completed the class
 * 02/11/2008 Mark Lauman Created Template
 */

package buddyLibrary;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
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
   public final int subButtonwidth = 50 ;
   /** the string name for subbutton "New Player" */
   public final String newPlayerText = "New Player";
   /** the string name for subbutton "Delete Player" */
   public final String deletePlayerText = "Delete Player";
   /** the string name for subbutton "Login" */
   public final String loginUserText = "Login";
   /** the directory to store user's data */
   public final String userDIR = "./data/user";
   /** an array list to store usernames */
   public ArrayList<String> userList = new ArrayList<String>();
   
   
   /**
    * Constructs a <code>UserSelection</code> menu with specified width 
    * and height and creates a directory of the user's name to store data.
    * The names are stored in a <code>DefaultListModel</code> and an array list 
    * for checking if a user exists already.
    * Also, it creates sub buttons and main button.
    */
   public UserSelection (int width, int height) {
       super(width, height);
       setBorder(BorderFactory.createTitledBorder("Study Buddies"));
       
       File f = new File(userDIR);  //add all users to the list and create the directory      
       String [] fileList = f.list();       
       for(int i = 0; i < fileList.length; i++) {
           list.addElement(fileList[i]); //add the names to list
           userList.add(fileList[i].toUpperCase());
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
        String str = (String)JOptionPane.showInputDialog(null,
                    "Please enter the name of the new player:",
                    "New Player",
                    JOptionPane.PLAIN_MESSAGE, null, null, null );
        //if a name is entered, then create a folder of the same name
        //if the name is alrady on the list, then show the warning message
        if(str != null) {
            if (userList.contains(str.toUpperCase())) {
                JOptionPane.showMessageDialog(null,
                   "The user is already on the list!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
                return;
                }
            userList.add(str.toUpperCase());
            //creates the directory of the user
            File f = new File(userDIR + "/" + str);
            f.mkdir();
            //and puts on the list
            addChoice(str); 
        } 
   }

  /**
    * Deletes the user from the list and also deletes the folder
    */
   private void deleteUser(){
       String userTobeDeleted = getSelection();
       
       //if nothing is selected on the list, then show the warning message
       if (userTobeDeleted == null ) {
           JOptionPane.showMessageDialog(null,
                   "Please select one user from the list!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
           return;
       }
       //the warning message
       int n = JOptionPane.showConfirmDialog(null,
                         "Once you delete a player, all of their" 
                        + "study buddy scores are erased.\n" 
                        + "There is no way to get this data back.\n" 
                        + "Do you still want to delete this person?",
                            "Delete Player", JOptionPane.YES_NO_OPTION);
       
       // if the user decides to delete the user, n = 0
       // then remove the user from the list and delete the directory as well
       if(n == 0) {
            list.removeElement(userTobeDeleted);
            userList.remove(userTobeDeleted.toUpperCase());
         File f = new File(userDIR + "/" + userTobeDeleted);
            f.delete();
        }       
   }


  /**
    * Events for each button
    */
   public void actionPerformed(ActionEvent e) { 
       //if the command is new player
       if(e.getActionCommand().compareTo(newPlayerText) == 0) {
           addUser();
       }//esle if the command is delete player
       else if(e.getActionCommand().compareTo(deletePlayerText) == 0){
           deleteUser();
       }
       else if(e.getActionCommand().compareTo(loginUserText) == 0) {
      //if the comand is login
       //call function to handl user choice
       }
   }
    
   
   
}
