/*
 * TestSoundSearch.java
 * 
 * Team Triple Threat
 * Log:
 * 04/01/2008 Mark Lauman Wrote getLastLevel test
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
        System.out.println(runGetWordListTest1());
        System.out.println(runGetWordListTest2());
        System.out.println(runGetWordListTest3());
        System.out.println();
        System.out.println(runGetLastLevelTest());
        System.out.println("-----------------------");
        System.out.println("    Begin Execution");
        System.out.println("-----------------------");
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
    
    public static String runGetLastLevelTest() {
        int level = SoundSearch.getLastLevel();
        return "Last Level = " + level;
    }
    
    public static String runGetWordListTest1() {
        ArrayList<ArrayList<String>> lst;
        lst = SoundSearch.getWordList(1);
        String result = "Level:" + 1 + "\n";
        for(int word = 0; word < lst.size(); word++) {
            result += "\t" + lst.get(word).get(0) + "\n";
            result += "\t\t" + lst.get(word).get(1) + "\n";
        }
        return result;
    }
    
    public static String runGetWordListTest2() {
        ArrayList<ArrayList<String>> lst;
        lst = SoundSearch.getWordList(29);
        String result = "Level:" + 29 + "\n";
        for(int word = 0; word < lst.size(); word++) {
            result += "\t" + lst.get(word).get(0) + "\n";
            result += "\t\t" + lst.get(word).get(1) + "\n";
        }
        return result;
    }
    
    public static String runGetWordListTest3() {
        try {
            ArrayList<ArrayList<String>> lst;
            lst = SoundSearch.getWordList(30);
            return "GetWordListTest3 FAILED";
        } catch(Exception e) {
            return "GetWordListTest3 PASSED";
        }
    }
}
