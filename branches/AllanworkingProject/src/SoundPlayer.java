/*
SoundPlayer.java
Creates a JButton that is loaded with the specified .wav file
Team Triple Threat
Log:
02/14/2008 Allan Lei Completed working module
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JButton;
import sun.audio.AudioStream;
import sun.audio.AudioPlayer;

public class SoundPlayer extends JButton implements ActionListener {
    AudioStream song;
    AudioStream backup;
    String fName;
    Boolean play = false;

    public SoundPlayer(String filename) {
        super("Play");
        fName = filename;
        try {
            song = new AudioStream(new FileInputStream(new File(fName)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setActionCommand(filename);
        addActionListener(this);
        validate();
    }
    
    public SoundPlayer(AudioStream as) {
        super("Play");
        try {
            song = as;
            backup = as;
            fName = as.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setActionCommand(fName);
        addActionListener(this);
        validate();
    }

    public void play() {
        try {
            AudioPlayer.player.start(song);
            if(fName.equals(song.toString())){
                song = new AudioStream(backup);
                
            }else{
                song = new AudioStream(new FileInputStream(new File(fName)));
            }
        } catch (Exception ex) {
        }
    }

    public void stop() {
        AudioPlayer.player.stop(song);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(fName)) {
            try {
                play();
            } catch (Exception ex) {
            }
        }
    }
}
