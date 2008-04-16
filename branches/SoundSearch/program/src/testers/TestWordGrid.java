/*
 * TestWordGrid.java
 * 
 * Team Triple Threat
 * Log:
 * 03/30/2008 Mark Lauman WordGrid constructor changed, so updated this program
 * 03/17/2008 Mark Lauman Created class
 */

package testers;

import game.WordGrid;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class is designed to test <code>WordGrid</code> for errors and bring
 * them forward. It serves no functional purpose in the buddy.
 * @author Team Triple Threat
 */
public abstract class TestWordGrid {
    public static void main(String args[]) {
        ArrayList<ArrayList<String>> inLst = Tools.getWordList();
        ArrayList<String> lst = new ArrayList<String>(inLst.size());
        for(int i = 0; i < 5; i++) {
            lst.add(inLst.get(i).get(0));
        }
        
        for(String s : lst) {
            System.out.println(s);
        }
        
        System.out.println("--------------------");
        System.out.println("  End Passed List  ");
        System.out.println("--------------------");
        
        runTest1(lst).setVisible(true);
    }
    
    /**
     * Runs test one - it merely creates the panel in a window to see if it will
     * work.
     * @param words The words to be passed to <code>WordGrid</code>'s
     *              constructor.
     * @return A <code>JFrame</code> containing test 1, all ready to be displayed
     */
    public static JFrame runTest1(ArrayList<String> words) {
        JFrame frame = new JFrame("Test 1: Create Window");
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 600));
        
        WordGrid w = new WordGrid(words, new EmptyScreen());
        panel.add(w);
        frame.getContentPane().add(panel);
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        return frame;
    }
}