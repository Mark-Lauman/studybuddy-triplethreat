/*
 * TestSoundSearch.java
 * 
 * Team Triple Threat
 * Log:
 * 03/15/2008 Mark Lauman Created + implemented class
 */

package testers;

import Buddies.SoundSearch;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Team Triple Threat
 */
public class TestSoundSearch {
    public static void main(String args[]) {
        System.out.println(runGetLevelTest());
        System.out.println();
        System.out.println(runGetWordListTest());
        runTest1().setVisible(true);
    }
    
    public static String runGetLevelTest() {
        String result = "";
        ArrayList<ArrayList<Integer>> lst = SoundSearch.getLevelList();
        for(int a = 0; a < lst.size(); a++) {
            result += "Level:" + (a + 1) + "\n";
            for(int b = 0; b < lst.get(a).size(); b++) {
                result += "\t" + lst.get(a).get(b) + "\n";
            }
        }
        return result;
    }
    
    public static JFrame runTest1() {
        JFrame result = new JFrame();
        SoundSearch s = new SoundSearch();
        result.setPreferredSize(new Dimension(800, 600));
        result.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        result.getContentPane().add(s);
        result.pack();
        return result;
    }
    
    public static String runGetWordListTest() {
        String result = "";
        ArrayList<ArrayList<ArrayList<String>>> lst;
        lst = SoundSearch.getWordList();
        for(int a = 0; a < lst.size(); a++) {
            result += "Level:" + (a + 1) + "\n";
            for(int b = 0; b < lst.get(a).size(); b++) {
                result += "\t" + lst.get(a).get(b).get(0) + "\n";
                result += "\t\t" + lst.get(a).get(b).get(1) + "\n";
            }
        }
        return result;
    }
}
