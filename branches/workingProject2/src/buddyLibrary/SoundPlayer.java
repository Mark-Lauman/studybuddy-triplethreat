/*
 * SoundPlayer.java
 * 
 * Team Triple Threat
 * Log:
 * 02/24/2008 Mark Lauman Updated comments so they line up with new standards
 * 02/20/2008 Allan Lei   Updated Audiostream playing
 * 02/14/2008 Allan Lei   Completed working module
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

/**
 * This is a <code>JButton</code> object, that plays an audio clip when clicked.
 * The audio clip must be in .wav format.
 * @author Team Triple Threat
 */
public class SoundPlayer extends JButton implements ActionListener {

    private AudioStream song;
    private ContinuousAudioDataStream cas;
    private AudioData data;
    private String fName;
    private Boolean audiostream = false;

    /**
     * Constructs a <code>SoundPlayer</code> that points to the file located at
     * <code>filename</code>. If the file is not a .wav file,
     * @param filename The filename of the sound you wish to play
     */
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

    /**
     * If you have already opened the sound, you may create a new
     * <code>SoundPlayer</code> linked to your sound by passing it to this
     * function.
     * @param as An opened sound file.
     */
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

    /**
     * Play the audio file linked to this button
     */
    public void play() {
        AudioPlayer.player.start(cas);
    }
    
    /**
     * If the audio file linked to this button is playing, stop it.
     */
    public void stop() {
        AudioPlayer.player.stop(song);
    }

    /**
     * Plays the sound. When this button is clicked, this function is called.
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(cas.toString())) {
            try {
                play();
            } catch (Exception ex) {
            }
        }
    }
}
