/*
Buddy.java
Brief File Description
Team Triple Threat
Log:
02/14/2008 Allan Lei   Total revision, implementation of all methods 
02/11/2008 Mark Lauman Created Template
 */

package buddyLibrary;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import Core.*;
import sun.audio.AudioStream;


public class Buddy extends JPanel {
    public Core frame;

    public Buddy() {
        /* Constructor for Buddy */
        setLayout(null);
    }

    
    public void addAudioPlayer(String filename, int x, int y) {
        /* Create an audioplayer with a specific filename and location
         Input: String filename - file location of the .wav file to be stored
                int x - X coordinate where the audioplayer will be located
                int y - Y coordinate where the audioplayer will be located
         Output: None - Creates a audiplayer with the specified sound file and moved to specified location*/
        SoundPlayer player = new SoundPlayer(filename);
        add(player);
        setPosition(player, x, y);
        validate();
    }
    
    public void addAudioPlayer(AudioStream as, int x, int y) {
        /* Create an audioplayer with a Audiostream at a specific location
         Input: AudioStream as - file location of a AudioStream to be stored
                int x - X coordinate where the audioplayer will be located
                int y - Y coordinate where the audioplayer will be located
         Output: None - Creates a audiplayer with the specified AudioStream and moved to specified location*/
        SoundPlayer player = new SoundPlayer(as);
        add(player);
        setPosition(player, x, y);
        validate();
    }

    
    /**
 *  Retrieve the statistics for a certain buddy
 *
 * @param  buddyName  Name of the study buddy to get the scores from
 * @return  stats Returns the values of the specified study buddy in a float array
 */
    public float[] getStatistics(String buddyName) {
        float[] stats = null;
        ArrayList<Float> temp = new ArrayList<Float>();
        try {
            BufferedReader read = new BufferedReader(new FileReader(new File(buddyName)));

            String line = null;
            while ((line = read.readLine()) != null) {
                temp.add(Float.parseFloat(line));
            }
            read.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        stats = new float[temp.size()];
        for (int i =
                0; i < temp.size(); i++) {
            stats[i] = temp.get(i);
        }
        return stats;
    }
    
    public float[] getStatistics(BufferedReader br) {
        /* Retrieves statistics from a BufferedReader and returns as a float array
        Input: BufferedReader br - Buffered reader that contains an data stream to be read from
        Output: float[] stats - Returns the values of the specified Buffered Reader in a float array*/
        float[] stats = null;
        ArrayList<Float> temp = new ArrayList<Float>();
        try {
            BufferedReader read = br;

            String line = null;
            while ((line = read.readLine()) != null) {
                temp.add(Float.parseFloat(line));
            }
            read.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        stats = new float[temp.size()];
        for (int i =
                0; i < temp.size(); i++) {
            stats[i] = temp.get(i);
        }
        return stats;
    }

    public String getUser() {
        /* Retrieve the user name from Core parent
         Input: None
         Output: String user - Retrieves the username from the reference frame (Core)*/
        return frame.getUser();
    }
        
    public void loadImage(String filename, int x, int y) {
        /* Loads and displays an image from a file location and specify the location
         Input: String filename - file location of an image file
                int x - X coordinate for the image to be placed at
                int y - Y coordinate for the image to be placed at
         Output: None - Places the accessed image at the specified location*/
        ImageIcon image = new ImageIcon(filename);
        JLabel pic = new JLabel(image);
        add(pic);
        setPosition(pic, x, y);
        validate();
    }

    public void loadImage(Image i, int x, int y) {
        /* Loads and displays an image from a file location and specify the location
         Input: Image i - Image stream to be read from
                int x - X coordinate for the image to be placed at
                int y - Y coordinate for the image to be placed at
         Output: None - Places the accessed image at the specified location*/
        ImageIcon image = new ImageIcon(i);
        JLabel pic = new JLabel(image);
        add(pic);
        setPosition(pic, x, y);
        validate();
    }
    
    public SoundPlayer loadSound(String filename) {
        /* Load a sound file from a specific location
         Input: String filename - File location of a .wav file
         Output: SoundPlayer player - Returns a object that has the .wav file stored inside*/
        SoundPlayer player = new SoundPlayer(filename);
        return player;
    }

    public void setPosition(Component c, int x, int y) {
        /* Sets the position of any component 
        Input: Component c - The Component to be moved
               int x - X coordinate that the component will be moved to
               int y - Y coordinate that the component will be moved to
         Output: None*/
        Insets insets = getInsets();
        c.setBounds(x + insets.left, y + insets.top, c.getPreferredSize().width, c.getPreferredSize().height);
        validate();
    }

    public void setReference(Core f) {
        /* sets the parent to f
         Input: Core f - A Core object
         Output: None - saves the reference to the Core object*/
        this.frame = f;
    }

    public void setTitle(String title) {
        /* Sets the title on the parent Core window
         Input: String title - Name that the Core object's title will be changed to
         Output: None - Sets the new title in Core object*/
        this.frame.setTitle(title);
    }
}
