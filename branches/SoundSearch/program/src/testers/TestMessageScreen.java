/*
 * Test MessageScreen.java
 * 
 * Team Triple Threat
 * Log:
 * 03/15/2008 Mark Lauman Wrote tests 2 and 3
 * 03/14/2008 Mark Lauman Created class and wrote test 1
 */

package testers;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import screens.MessageScreen;

/**
 *
 * @author Team Triple Threat
 */
public class TestMessageScreen {
    
    
    public static void main(String[] args) {
        makeTest1().setVisible(true);
        makeTest2().setVisible(true);
        makeTest3().setVisible(true);
    }
    
    public static JFrame makeTest1() {
        JFrame frame = new JFrame("Test 1: Making the window");
        
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        frame.setLayout(new GridLayout(0, 1));
        frame.getContentPane().add(new MessageScreen(new ButtonListener()));
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        return frame;
    }
    
    public static JFrame makeTest2() {
        JFrame frame = new JFrame("Test 2: Changing labels inside the window");
        
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        frame.setLayout(new GridLayout(0, 1));
        
        MessageScreen scr = new MessageScreen(new ButtonListener());
        scr.setMessage("Hello World!");
        scr.setTime(80f);
        frame.getContentPane().add(scr);
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        return frame;
    }
    
    public static JFrame makeTest3() {
        JFrame frame = new JFrame("Test 3: Hiding the time");
        
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        frame.setLayout(new GridLayout(0, 1));
        
        MessageScreen scr = new MessageScreen(new ButtonListener());
        scr.setMessage("Hello World!");
        scr.setTime(80f);
        scr.setTimeVisible(false);
        frame.getContentPane().add(scr);
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        return frame;
    }
}
