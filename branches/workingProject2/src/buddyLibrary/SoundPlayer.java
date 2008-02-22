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

    //Constructor for Soundplayer that accepts a file location
    public SoundPlayer(String filename) {
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

    //Constructor for Soundplayer that accepts a AudioStream
    public SoundPlayer(AudioStream as) {
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

    //Play the stored audio
    public void play() {
        AudioPlayer.player.start(cas);
    }

    //Stop the stored audio
    public void stop() {
        AudioPlayer.player.stop(song);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(cas.toString())) {
            try {
                play();
            } catch (Exception ex) {
            }
        }
    }
}
