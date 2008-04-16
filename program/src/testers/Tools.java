/*
 * TestWordGrid.java
 * 
 * Team Triple Threat
 * Log:
 * 03/17/2008 Mark Lauman Created and implemented class
 */

package testers;

import game.Const;
import game.SoundFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

/**
 * These are some common functions used by the tester classes. It makes more
 * sense to put them here, where everyone can acess them easily in one location
 * @author Team Triple Threat
 */
public abstract class Tools {
    
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
}
