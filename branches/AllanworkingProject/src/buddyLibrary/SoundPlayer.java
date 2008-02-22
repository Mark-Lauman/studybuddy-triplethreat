/*
SoundPlayer.java
Creates a JButton that is loaded with the specified .wav file
Team Triple Threat
Log:
02/20/2008 Allan Lei Updated Audiostream playing
02/14/2008 Allan Lei Completed working module
 */

package buddyLibrary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JButton;
import sun.audio.AudioData;
import sun.audio.AudioStream;
import sun.audio.AudioPlayer;
import sun.audio.ContinuousAudioDataStream;

public class SoundPlayer extends JButton implements ActionListener {

    AudioStream song;
    ContinuousAudioDataStream cas;
    AudioData data;
    String fName;
    Boolean audiostream = false;

    public SoundPlayer(String filename) {
        /* Constructor for SoundPlayer.  This constructor accepts a file location of a .wav file.  
         Also sets all the action commands */
        super("Play");
        fName = filename;
        try {
            AudioStream as = new AudioStream(new FileInputStream(new File(fName)));
            data = as.getData();
            cas = new ContinuousAudioDataStream(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setActionCommand(filename);
        addActionListener(this);
        validate();
    }

    public SoundPlayer(AudioStream as) {
        /* Constructor for SoundPlayer.  This constructor accepts an AudioStream.  
         Also sets all the action commands */
        super("Play");
        try {
            data = as.getData();
            cas = new ContinuousAudioDataStream(data);

            audiostream = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        setActionCommand(cas.toString());
        addActionListener(this);
        validate();
    }

    
    public void play() {
        /* Play the stored audio
         Input: None
         Output: None - Plays the stored AudioStream */
        AudioPlayer.player.start(cas);
    }

    
    public void stop() {
        /* Stop the stored audio
         Input: None
         Output: None - Stops the currently playing audio*/
        AudioPlayer.player.stop(song);
    }

    public void actionPerformed(ActionEvent e) {
        /* ActionListener that listens to the stop and play commands */
        if (e.getActionCommand().equals(cas.toString())) {
            try {
                play();
            } catch (Exception ex) {
            }
        }
    }
}
