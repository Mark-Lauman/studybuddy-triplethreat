  /*
   * 
   * BreakBuddy.java
   * 
   * Team Triple Threat
   * Log:
   * 03/29/2008 Vic Kao implemented and completed the Breakbuddy
   */

package Buddies;

import subBreakBuddy.*;
import buddyLibrary.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.*;
import javax.swing.*;
import java.util.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

        
/**
 * This Breakbuddy will generates a random integer number between 0 and 99.
 * The user will need to guess the number correcly within 10 times.
 * @author Team Triple Threat
 * @see <a href="http://java.sun.com/javase/6/docs/api/javax/swing/JPanel.html">
 *      javax.swing.JPanel</a>
 */
public class BreakBuddy extends Buddy implements ActionListener{
    //create a startPanel for the welcome screen
    //create a gameScreenPanel for the playing screen
    startPanel startScreenPanel;
    BreakBuddyJFrame gameScreenPanel;
    private String buddyPath = System.getProperty("user.dir") + "/Buddies/BreakBuddy/";
    /**
     * Constructs a <code>BreakBuddy</code> buddy, and creates instances of 
     * startScreenPanel and gameScreenPanel
     */
    public BreakBuddy() {
        //set the layout to BorderLayout
        this.setLayout(new BorderLayout());
        startScreenPanel = new startPanel(this);
        gameScreenPanel = new BreakBuddyJFrame();
        
        add(startScreenPanel);     
        
        
        
        File f2 = new File(System.getProperty("user.dir") + "/subBreakBuddy/");
        f2.mkdir();
        try{
        copyFile(new File(buddyPath + "/subBreakBuddy/BreakBuddyJFrame.class"), 
                new File(System.getProperty("user.dir") + "/subBreakBuddy/BreakBuddyJFrame.class"));
        copyFile(new File(buddyPath + "/subBreakBuddy/BreakBuddyJFrame.class"), 
                new File(System.getProperty("user.dir") + "/subBreakBuddy/BreakBuddyJFrame$1.class"));
        copyFile(new File(buddyPath + "/subBreakBuddy/BreakBuddyJFrame.class"), 
                new File(System.getProperty("user.dir") + "/subBreakBuddy/BreakBuddyJFrame$2.class"));
        copyFile(new File(buddyPath + "/subBreakBuddy/BreakBuddyJFrame.class"), 
                new File(System.getProperty("user.dir") + "/subBreakBuddy/BreakBuddyJFrame$3.class"));
        copyFile(new File(buddyPath + "/subBreakBuddy/BreakBuddyJFrame.class"), 
                new File(System.getProperty("user.dir") + "/subBreakBuddy/startPanel.class"));
        copyFile(new File(buddyPath + "/subBreakBuddy/BreakBuddyJFrame.class"), 
                new File(System.getProperty("user.dir") + "/subBreakBuddy/startPanel$1.class"));
        
        }catch(Exception ex){
            
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
    
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("closed")){
            startScreenPanel.stopsound();
        }
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
