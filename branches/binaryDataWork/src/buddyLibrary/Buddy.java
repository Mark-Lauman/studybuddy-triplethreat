package buddyLibrary;

/*
 * Buddy.java
 * Brief File Description
 * Team Triple Threat
 * Log:
 * 03/10/2008 Mark Lauman Created templates for binary data functions
 * 02/22/2008 Mark Lauman Moved elements
 * 02/14/2008 Allan Lei   Total revision, implementation of all methods 
 * 02/11/2008 Mark Lauman Created Template
 */

import core.Core;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import core.*;
import java.applet.AudioClip;
import sun.audio.AudioStream;


public class Buddy extends JPanel {
    public Core frame;

    public Buddy() {
        setLayout(null);
    }
    
 /**
 *  Create an audioplayer with a Audiostream at a specific location
 *
 * @param filename File location of the .wav file to be stored
 * @param x X coordinate where the audioplayer will be located
 * @param y Y coordinate where the audioplayer will be located
 * @return Creates a audiplayer with the specified AudioStream and moved to specified location
 */
    public void addAudioPlayer(String filename, int x, int y) {
        try{
        SoundPlayer player = new SoundPlayer(filename);
        add(player);
        setPosition(player, x, y);
        validate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
 /**
 *  Create an audioplayer with a Audiostream at a specific location
 *
 * @param filename File location of a AudioClip to be stored
 * @param x X coordinate where the audioplayer will be located
 * @param y Y coordinate where the audioplayer will be located
 * @return   Creates a audiplayer with the specified AudioClip and moved to specified location
 */
    public void addAudioPlayer(AudioClip ac, int x, int y) {
        SoundPlayer player = new SoundPlayer(ac);
        add(player);
        setPosition(player, x, y);
        validate();
    }
    
    /**
     * Get all abjects stored in this buddy's binary data file and return them
     * as an ArrayList
     * 
     * @param  buddyName The name of this buddy for file access
     * @return An ArrayList containing the objects stored in this buddy's binary
     *         data file. These objects are of type Object, and must be cast to
     *         their correct types by the calling buddy.<br />
     *         If the file is empty or does not exist then this function returns
     *         an empty ArrayList.
     */
    public ArrayList<Object> getDataContent(String buddyName) {
        return null;
    }
    
    /**
     * Get an ObjectInputStream that points to this buddy's binary data file.
     * This allows for more control over reading than getDataContent()
     * 
     * @param buddyName The name of this buddy for file access
     * @return An ObjectInputStream that points to this buddy's binary data file.
     *         If  file does not exist then it returns null
     */
    public ObjectInputStream getDataReader(String buddyName) {
        return null;
    }
    
    /**
     * Get an ObjectOutputStream that points to this buddy's binary data file.
     * This allows the buddy to write to the binary file.
     * 
     * @param buddyName The name of this buddy for file access
     * @return An ObjectOutputStream that points to this buddy's binary data
     *         file. If  file does not exist then it creates it
     */
    public ObjectOutputStream getDataWriter(String buddyName) {
        return null;
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
    
 /**
 *  Retrieve the statistics for a certain buddy
 *
 * @param br Buffered reader that contains an data stream to be read from
 * @return stats Returns the values of the specified Buffered Reader in a float array
 */ 
    public float[] getStatistics(BufferedReader br) {
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

 /**
 *  Retrieve the user name from Core parent
 *
 * @return   Retrieves the username from the reference frame (Core)
 */ 
    public String getUser() {
        System.out.println(frame.getUser());
        return frame.getUser();
       
    }
    
 /**
 *  Loads and displays an image from a file location and specify the location
 *
 * @param filename File location of an image file
 * @param x X coordinate for the image to be placed at
 * @param y Y coordinate for the image to be placed at
 * @return   Places the accessed image at the specified location
 */ 
    public void loadImage(String filename, int x, int y) {
        ImageIcon image = new ImageIcon(filename);
        JLabel pic = new JLabel(image);
        add(pic);
        setPosition(pic, x, y);
        validate();
    }
    
 /**
 *  Loads and displays an image from a file location and specify the location
 *
 * @param i Image stream to be read from
 * @param x X coordinate for the image to be placed at
 * @param y Y coordinate for the image to be placed at
 * @return   Places the accessed image at the specified location
 */ 
    public void loadImage(Image i, int x, int y) {
        ImageIcon image = new ImageIcon(i);
        JLabel pic = new JLabel(image);
        add(pic);
        setPosition(pic, x, y);
        validate();
    }

 /**
 *  Load a sound file from a specific location
 *
 * @param filename - File location of a .wav file
 * @return player Returns a object that has the .wav file stored inside
 */ 
    public SoundPlayer loadSound(String filename) {
        try{
        SoundPlayer player = new SoundPlayer(filename);
        return player;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
 /**
 *  Sets the position of any component 
 *
 * @param c The Component to be moved
 * @param x X coordinate that the component will be moved to
 * @param y Y coordinate that the component will be moved to
 * @return Set the position of the component
 */ 
    public void setPosition(Component c, int x, int y) {
        Insets insets = getInsets();
        c.setBounds(x + insets.left, y + insets.top, c.getPreferredSize().width, c.getPreferredSize().height);
        validate();
    }
    
 /**
 *  Sets the parent to f
 *
 * @param f A Core object
 * @return   saves the reference to the Core object
 */ 
    public void setReference(Core f) {
        frame = f;
    }
    
 /**
 *  Sets the title on the parent Core window
 *
 * @param title Name that the Core object's title will be changed to
 * @return   Sets the new title in Core object
 */ 
    public void setTitle(String title) {
        frame.setTitle(title);
    }
}