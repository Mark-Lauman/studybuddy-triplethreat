/*
 * SoundPlayer.java
 * 
 * Team Triple Threat
 * Log:
 * 02/24/2008 Mark Lauman Re-did SoundPlayer to use up-to-date API's
 * 02/24/2008 Mark Lauman Updated comments so they line up with new standards
 * 02/20/2008 Allan Lei   Updated Audiostream playing
 * 02/14/2008 Allan Lei   Completed working module
 */

package buddyLibrary;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;

/**
 * This is a <code>JButton</code> object, that plays an audio clip when clicked.
 * The audio clip must be supported by the
 * <a href="http://java.sun.com/products/java-media/sound/techReference/javasoundfaq.html#formats">
 * Java Sound API</a>.
 * @author Team Triple Threat
 * @see <a href="http://java.sun.com/javase/6/docs/api/java/applet/AudioClip.html">
 *      java.applet.AudioClip</a>
 * @see <a href="http://java.sun.com/javase/6/docs/api/java/awt/event/ActionListener.html">
 *      java.awt.event.ActionListener</a>
 * @see <a href="http://java.sun.com/javase/6/docs/api/java/io/IOException.html">
 *      java.io.IOException</a>
 * @see <a href="http://java.sun.com/javase/6/docs/api/javax/swing/JButton.html">
 *      javax.swing.JButton</a>
 */
public class SoundPlayer extends JButton implements ActionListener{
    
    private AudioClip sound;
    
    /**
     * Constructs a <code>SoundPlayer</code> that points to the file located at
     * <code>filename</code>
     * @param filename The filename of the sound you wish to play
     * @throws java.io.IOException If <code>filename</code> is invalid
     */
    public SoundPlayer(String filename) throws IOException {
        super("Play Sound"); // Make this a button with play sound written on it
        File f = new File(filename); // The File object provides additional
                                     // features, used below.
        sound = Applet.newAudioClip(f.toURI().toURL());
            // lets make our sound object point to filename
        if(!f.exists()) {
            //If the file doesn't exist at all
            throw new IOException("File does not exist");
        }
        if(sound.toString().equals("sun.applet.AppletAudioClip@c40c80")) {
            //If the file is not supported, then sound always equals the above
            //string. So throw an exception!
            throw new IOException("File is not a supported sound");
        }
//        System.out.println(sound);
        this.addActionListener(this);
    }

    /**
     * If you have already opened the sound, you may create a new
     * <code>SoundPlayer</code> linked to your sound by passing it to this
     * function
     * @param audio An opened sound file.
     */
    public SoundPlayer(AudioClip audio) {
        super("Play Sound");
        sound = audio;
        this.addActionListener(this);
    }

    /**
     * Play the audio file linked to this button
     */
    public void play() {
        sound.play();
    }
    
    /**
     * If the audio file linked to this button is playing, stop it
     */
    public void stop() {
        sound.stop();
    }

    /**
     * When this button is clicked, this function is called and the sound is
     * played.
     * @param e An <code>ActionEvent</code> relating to this button
     */
    public void actionPerformed(ActionEvent e) {
        play();
    }
}