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
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This tests the SoundPlayer class, to prove that it is working properly.
 * @author Team Triple Threat
 * @see <a href="../buddyLibrary/SoundPlayer.html">buddyLibrary.SoundPlayer</a>
 * @see <a href="http://java.sun.com/javase/6/docs/api/java/io/IOException.html">
 *      java.io.IOException</a>
 * @see <a href="http://java.sun.com/javase/6/docs/api/javax/swing/JFrame.html">
 *      javax.swing.JFrame</a>
 * @see <a href="http://java.sun.com/javase/6/docs/api/javax/swing/JPanel.html">
 *      javax.swing.JPanel</a>
 */
public class TestSoundPlayer {
    
    /**
     * Run a test on SoundPlayer. The test run is determined by the second line
     * in the code. Change the number there to change the test.
     * @param args Arguments passed on command line. These are ignored.
     */
    public static void main(String[] args) {
        try{
            JFrame test = makeTest1();
            test.setVisible(true);
            System.out.println("No errors!");
        }
        catch(IOException e) {
            System.out.println("ERROR - IOException: "
                                 + e.getMessage());
        }
    }
    
    /**
     * The first test: what happens if you pass an invalid file to
     * <code>SoundPlayer</code>'s constructor?
     * @return A <code>JFrame</code> containing the first test, ready to be set
     *         visible
     * @throws java.io.IOException If the file name did not work
     */
    public static JFrame makeTest1() throws IOException {
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
     * The second test: what happens if you pass a valid file to
     * <code>SoundPlayer</code>'s constructor? You can also check that clicking
     * on the button does play the sound.
     * @return A <code>JFrame</code> containing the second test, ready to be set
     *         visible
     * @throws java.io.IOException If the file name did not work
     */
    public static JFrame makeTest2() throws IOException {
        JFrame frame = new JFrame("Test 2: Valid Constructor");
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 500));
        String musicLocation = System.getProperty("user.dir")
                                + "\\src\\testers\\test.wav";
        panel.add(new SoundPlayer(musicLocation));
        frame.getContentPane().add(panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
    
    /**
     * The third test: what happens if you pass an invalid file to
     * <code>SoundPlayer</code>'s constructor? (But the file exists)
     * @return A <code>JFrame</code> containing the third test, ready to be set
     *         visible
     * @throws java.io.IOException If the file name did not work
     */
    public static JFrame makeTest3() throws IOException {
        JFrame frame = new JFrame("Test 3: Invalid file - not sound");
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 500));
        String musicLocation = System.getProperty("user.dir")
                                + "\\src\\testers\\CodingStyleExample.java";
        panel.add(new SoundPlayer(musicLocation));
        frame.getContentPane().add(panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
    
    /**
     * The fourth test: what happens if you use too short a file extension?
     * @return A <code>JFrame</code> containing the fourth test, ready to be set
     *         visible
     * @throws java.io.IOException If the file name did not work
     */
    public static JFrame makeTest4() throws IOException {
        JFrame frame = new JFrame("Test 3: Valid file, short path");
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 500));
        String musicLocation = "\\src\\testers\\test.wav";
        panel.add(new SoundPlayer(musicLocation));
        frame.getContentPane().add(panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
}
