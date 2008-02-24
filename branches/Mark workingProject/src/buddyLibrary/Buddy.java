package buddyLibrary;

/*
Buddy.java
Brief File Description
Team Triple Threat
Log:
02/24/2008 Mark Lauman Modified constructor
02/22/2008 Mark Lauman Moved elements
02/14/2008 Allan Lei   Total revision, implementation of all methods 
02/11/2008 Mark Lauman Created Template
 */

import core.Core;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import Core.*;
import sun.audio.AudioStream;


public class Buddy extends JPanel {
    public Core frame;

    public Buddy(Core core) {
        frame = core;
        setLayout(null);
    }

    //Create an audioplayer with a specific filename and location
    public void addAudioPlayer(String filename, int x, int y) {
        SoundPlayer player = new SoundPlayer(filename);
        add(player);
        setPosition(player, x, y);
        validate();
    }
    
    //Create an audioplayer with a Audiostream at a specific location
    public void addAudioPlayer(AudioStream as, int x, int y) {
        SoundPlayer player = new SoundPlayer(as);
        add(player);
        setPosition(player, x, y);
        validate();
    }

    //Retrieve the statistics for a certain buddy
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
    
    //Retrieves statistics from a BufferedReader
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

    //Retrieve the user name from Core parent
    public String getUser() {
        return frame.getUser();
    }
    
    
    //Loads and displays an image from a file location and specify the location
    public void loadImage(String filename, int x, int y) {
        ImageIcon image = new ImageIcon(filename);
        JLabel pic = new JLabel(image);
        add(pic);
        setPosition(pic, x, y);
        validate();
    }

    //Displays image at a specific location
    public void loadImage(Image i, int x, int y) {
        ImageIcon image = new ImageIcon(i);
        JLabel pic = new JLabel(image);
        add(pic);
        setPosition(pic, x, y);
        validate();
    }

    //Load a sound file from a specific location
    public SoundPlayer loadSound(String filename) {
        SoundPlayer player = new SoundPlayer(filename);
        return player;
    }

    //Sets the position of any component
    public void setPosition(Component c, int x, int y) {
        Insets insets = getInsets();
        c.setBounds(x + insets.left, y + insets.top, c.getPreferredSize().width, c.getPreferredSize().height);
        validate();
    }

    //sets the parent to f
    public void setReference(Core f) {
        this.frame = f;
    }

    
    //Sets the title on the parent Core window
    public void setTitle(String title) {
        this.frame.setTitle(title);
    }
}
