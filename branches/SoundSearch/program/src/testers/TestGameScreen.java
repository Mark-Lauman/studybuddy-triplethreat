/*
 * TestGameScreen.java
 * 
 * Team Triple Threat
 * Log:
 * 03/16/2008 Mark Lauman Implemented & created class
 */

package testers;

import game.Const;
import game.SoundFilter;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JFrame;
import screens.GameScreen;

/**
 * This class is designed solely to run tests on GameScreen.
 * @author deam Triple Threat
 */
public class TestGameScreen {
    public static void main(String args[]) {
        System.out.println(arrListToString(getWordList()));
        makeTest1().setVisible(true);
    }
    
    public static JFrame makeTest1() {
        JFrame frame = new JFrame("Test 1: Making the window");
        
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        GameScreen g;
        g = new GameScreen(new ButtonListener(), 1, getWordList());
        frame.getContentPane().add(g);
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        return frame;
    }
    
    /**
     * This function is a simplified version of the getWordList() function from
     * SoundSearch.java. That one is for general cases, but this one just assumes
     * the directory is there
     * @return An ArrayList representing all available sounds inside one
     *         particular level (level 1)
     */
    public static ArrayList<ArrayList<String>> getWordList() {
        String folderPath; //The location of the current folder
        File soundNames[]; //An array of all the sound files in the folder
        int sound; //the index of the next sound, so it can be added properly
        String soundName; //the name of the sound without its path or extension
        ArrayList<ArrayList<String>> result; //our final ArrayList
        result = new ArrayList<ArrayList<String>>();
                
        //we construct the path of this folder, so we can create
        //a new sound list using this path as the base
        folderPath = System.getProperty("user.dir");
        folderPath += Const.FILE_PATH + "1";
        //get a list of all the sound files in the directory
        soundNames = (new File(folderPath)).listFiles(new SoundFilter());
        //set the sound equal to the next element that we will add
        sound = 0;
        
        //now we begin adding sounds
        for(int sFileIndex = 0; sFileIndex < soundNames.length; sFileIndex++) {
            //make a new ArrayList representing this sound pair
            result.add(new ArrayList<String>());
            //get the name of the sound file
            soundName = soundNames[sFileIndex].getName();
            soundName = soundName.toUpperCase(Locale.ENGLISH);
            soundName = soundName.replace(".WAV", "");
            //store the name of the sound file
            result.get(sound).add(soundName);
            //get the location of the sound file
            result.get(sound).add(soundNames[sFileIndex].getPath());
            sound++;
        }
        return result;
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
