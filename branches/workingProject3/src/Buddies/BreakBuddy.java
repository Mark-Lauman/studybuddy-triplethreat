/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Chen-Wei Kao
 */
public class BreakBuddy extends Buddy {
    
    
    
    startPanel startScreenPanel;
    BreakBuddyJFrame gameScreenPanel;
    
    
    public BreakBuddy() {
        this.setLayout(new BorderLayout());
        startScreenPanel = new startPanel(this);
        gameScreenPanel = new BreakBuddyJFrame(this);
        add(startScreenPanel);     
    }
    
  
    public void startButtonClicked() {
        remove(startScreenPanel);
        add(gameScreenPanel);
        
        validate();
    }
}
    
 
