  /*
   * 
   * BreakBuddy.java
   * 
   * Team Triple Threat
   * Log:
   * 03/29/2008 Vic Kao implemented and completed the Breakbuddy
   */

package Buddies;


import subBreakBuddy.startPanel;
import subBreakBuddy.BreakBuddyJFrame;
import buddyLibrary.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

        
/**
 * This Breakbuddy will generates a random integer number between 0 and 99.
 * The user will need to guess the number correcly within 10 times.
 * @author Team Triple Threat
 * @see <a href="http://java.sun.com/javase/6/docs/api/javax/swing/JPanel.html">
 *      javax.swing.JPanel</a>
 */
public class BreakBuddy extends Buddy {
    //create a startPanel for the welcome screen
    //create a gameScreenPanel for the playing screen
    startPanel startScreenPanel;
    BreakBuddyJFrame gameScreenPanel;
   
    
    /**
     * Constructs a <code>BreakBuddy</code> buddy, and creates instances of 
     * startScreenPanel and gameScreenPanel
     */
    public BreakBuddy() {
        //set the layout to BorderLayout
        this.setLayout(new BorderLayout());
        startScreenPanel = new startPanel(this);
        gameScreenPanel = new BreakBuddyJFrame(this);
        add(startScreenPanel);     
    }
    
    /**
     * This function handles the events when the Start button is clicked on
     * the welcome screen
     */
    public void startButtonClicked() {
        remove(startScreenPanel);
        add(gameScreenPanel);
        
        validate();
    }
}
    
 
