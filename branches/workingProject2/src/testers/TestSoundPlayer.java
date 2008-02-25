/*
 * TestSoundPlayer.java
 * 
 * Team Triple Threat
 * Log:
 * 02/24/2008 Mark Lauman Implemented
 */

package testers;

import buddyLibrary.SoundPlayer;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This tests the SoundPlayer class, to prove that it is working properly.
 * @author Team Triple Threat
 * @see <a href="../buddyLibrary/SoundPlayer.html">buddyLibrary.SoundPlayer</a>
 * @see <a href="http://java.sun.com/javase/6/docs/api/javax/swing/JFrame.html">
 *      javax.swing.JFrame</a>
 * @see <a href="http://java.sun.com/javase/6/docs/api/javax/swing/JPanel.html">
 *      javax.swing.JPanel</a>
 */
public class TestSoundPlayer {
    
    /**
     * Run a test on SoundPlayer. The test run is determined by the second line
     * in the code. Change the number there to change the test.
     * @param args arguments passed on command line. These are ignored.
     */
    public static void main(String[] args) {
        JFrame test = makeTest2();
        test.setVisible(true);
    }
    
    /**
     * The first test, what happens if you pass an invalid file to SoundPlayer's
     * constructor.
     * @return A <code>JFrame</code> containing the first test, ready to be set
     *         visible
     */
    public static JFrame makeTest1() {
        JFrame frame = new JFrame("Test 1: Invalid Constructor");
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 500));
        panel.add(new SoundPlayer(""));
        frame.getContentPane().add(panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
    
    /**
     * The second test, what happens if you pass a valid file to SoundPlayer's
     * constructor. You can also check that clicking on the button does play the
     * sound.
     * @return A <code>JFrame</code> containing the second test, ready to be set
     *         visible
     */
    public static JFrame makeTest2() {
        JFrame frame = new JFrame("Test 2: Valid Constructor");
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 500));
        panel.add(new SoundPlayer("src/testers/test.wav"));
        frame.getContentPane().add(panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
}
