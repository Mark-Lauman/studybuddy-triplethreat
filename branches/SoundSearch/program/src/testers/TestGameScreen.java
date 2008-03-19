/*
 * TestGameScreen.java
 * 
 * Team Triple Threat
 * Log:
 * 03/16/2008 Mark Lauman Implemented & created class
 */

package testers;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import screens.GameScreen;

/**
 * This class is designed solely to run tests on GameScreen.
 * @author Team Triple Threat
 */
public abstract class TestGameScreen {
    public static void main(String args[]) {
//        System.out.println(arrListToString(Tools.getWordList()));
        makeTest1().setVisible(true);
    }
    
    public static JFrame makeTest1() {
        JFrame frame = new JFrame("Test 1: Making the window");
        
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        GameScreen g;
        g = new GameScreen(new ButtonListener(), 1, Tools.getWordList());
        frame.getContentPane().add(g);
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        return frame;
    }
    
    public static String arrListToString(ArrayList<ArrayList<String>> arr) {
        String result = "Level 1:";
        for(int a = 0; a < arr.size(); a++) {
            result += "\n\t" + arr.get(a).get(0);
            result += "\n\t\t" + arr.get(a).get(1);
        }
        return result;
    }
}
