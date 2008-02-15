/*
Buddy.java
Brief File Description
Team Triple Threat
Log:
02/14/2008 Allan Lei   Total revision, implementation of all methods 
02/11/2008 Mark Lauman Created Template
 */

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import javax.imageio.ImageIO;
import javax.swing.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Buddy extends JPanel {

    public Core frame;

    public Buddy() {
        setLayout(null);
    }

    public void addAudioPlayer(String filename, int x, int y) {
        SoundPlayer player = new SoundPlayer(filename);
        add(player);
        setPosition(player, x, y);
        validate();
    }
    
    public void addAudioPlayer(AudioStream as, int x, int y) {
        SoundPlayer player = new SoundPlayer(as);
        add(player);
        setPosition(player, x, y);
        validate();
    }

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
        return frame.getUser();
    }
    
    

    public void loadImage(String filename, int x, int y) {
        ImageIcon image = new ImageIcon(filename);
        JLabel pic = new JLabel(image);
        add(pic);
        setPosition(pic, x, y);
        validate();
    }

    public void loadImage(Image i, int x, int y) {
        ImageIcon image = new ImageIcon(i);
        JLabel pic = new JLabel(image);
        add(pic);
        setPosition(pic, x, y);
        validate();
    }

    public SoundPlayer loadSound(String filename) {
        SoundPlayer player = new SoundPlayer(filename);
        return player;
    }

    public void setPosition(Component c, int x, int y) {
        Insets insets = getInsets();
        c.setBounds(x + insets.left, y + insets.top, c.getPreferredSize().width, c.getPreferredSize().height);
        validate();
    }

    public void setReference(Core f) {
        this.frame = f;
    }

    public void setTitle(String title) {
        this.frame.setTitle(title);
    }
}
