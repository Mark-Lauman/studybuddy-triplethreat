  /*
   * UserSelection.java
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



public class UserSelection extends buddyLibrary.SelectionMenu1 {
    
   public final String userDIR = "./data/user"; //set a constant directory
   public final int subButtonwidth = 50 ;   //set the space between buttons
   public final String newPlayertext = "New Player";  //set the tag for convention
   public final String deletePlayertext ="Delete Player";    //set the tag for convention
   public final String loginUsertext = "Login";  //set the tag for convention
   public ArrayList<String> userList = new ArrayList<String>(); //store usernames to userList
   
   public UserSelection (int width, int height) {
       super(width, height);
       
       File f = new File(userDIR);  //add all users to the list and create a directory      
       String [] fileList = f.list();       
       for(int i = 0; i < fileList.length; i++) {
           list.addElement(fileList[i]); //add the names to list
           userList.add(fileList[i].toUpperCase());
       }
       
       addSubButton(newPlayertext, subButtonwidth); //add sub button - New Player
       addSubButton(deletePlayertext, subButtonwidth);  //add sub button - Delete Player
       setMainButton("Login");  //add the main button - Login
   }
   

   private void addUser() {
       /* this function adds a uer to the list */
        String str = (String)JOptionPane.showInputDialog(null,
                    "Please enter the name of the new player:",
                    "New Player",
                    JOptionPane.PLAIN_MESSAGE,
                    null, null, null );
        
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
            File f = new File(userDIR + "/" + str);
            f.mkdir();
            addChoice(str); 
        } 
   }
  
   private void deleteUser(){
       /*  delete the user from the list and also delete the folder */
       String userTobeDeleted = getSelection();
       
       //if nothing is chosen on the list, then show the warning message
       if (userTobeDeleted == null ) {
           JOptionPane.showMessageDialog(null,
                   "Please select one user from the list!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
           return;
       }
       
       int n = JOptionPane.showConfirmDialog(null,
                         "Once you delete a player, all of their" 
                        + "study buddy scores are erased.\n" 
                        + "There is no way to get this data back.\n" 
                        + "Do you still want to delete this person?",
                            "Delete Player",
                        JOptionPane.YES_NO_OPTION);
       
       // if the user decides to delete the user, n = 0
       // and remove the user from the list and delete the directory as well
        if(n == 0) {
            list.removeElement(userTobeDeleted);
            userList.remove(userTobeDeleted.toUpperCase());
         File f = new File(userDIR + "/" + userTobeDeleted);
            f.delete();
        }
       
   }
   public void actionPerformed(ActionEvent e) { 
       //if the command is new player
       if(e.getActionCommand().compareTo(newPlayertext)== 0) {
           addUser();
       }//esle if the command is delete player
       else if(e.getActionCommand().compareTo(deletePlayertext)== 0){
           deleteUser();
       }
       else if(e.getActionCommand().compareTo(loginUsertext)== 0) {
      //if the comand is login
       //call function to handl user choice
       }
   }
    
}
