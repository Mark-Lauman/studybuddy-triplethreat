/*
 * SoundSearch.java
 * 
 * Team Triple Threat
 * Log:
 * 03/16/2008 Mark Lauman Integrated GameScreen into the program
 * 03/15/2008 Mark Lauman Rewrote class to use new architecture
 * 03/12/2008 Mark Lauman Wrote initial class
 */

package Buddies;

import buddyLibrary.Buddy;
import game.Const;
import game.SoundFilter;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import javax.swing.JButton;
import screens.GameScreen;
import screens.MessageScreen;

public class SoundSearch extends Buddy implements ActionListener {
    private ArrayList<ArrayList<ArrayList<String>>> wordList;
    private MessageScreen ms;
    private GameScreen game;
    
    public SoundSearch() {
        super();
        wordList = getWordList();
        this.setLayout(new GridLayout(0,1));
        this.setPreferredSize(new Dimension(800, 600));
        
        ms = new MessageScreen(this);
        this.add(ms);
    }
    
    public static ArrayList<ArrayList<Integer>> getLevelList() {
        //opening file...
        ArrayList<ArrayList<Integer>> result;
        try {
            String path = System.getProperty("user.dir");
            path += Const.FILE_PATH + "levels.txt";
            FileReader f = new FileReader(path);
            BufferedReader reader = new BufferedReader(f);
            
            String line = reader.readLine();
            ArrayList<String> fileData = new ArrayList<String>();
            while(line != null) {
                fileData.add(line);
                line = reader.readLine();
            }
            
            String arr[];
            int i = 0;
            result = new ArrayList<ArrayList<Integer>>();
            for(String str: fileData) {
                result.add(new ArrayList<Integer>());
                arr = str.split(" ");
                for(String s: arr) {
                    try {
                        result.get(i).add(Integer.parseInt(s));
                    } catch(Exception e) {
                        
                    }
                }
                i++;
            }
        } catch(Exception e) {
            result = new ArrayList<ArrayList<Integer>>();
        }
        return result;
    }
    
    /**
     * Gets a full list of all the sound files stored for this buddy's use
     * The sounds can be accessed in the following fashion from the returned
     * <code>ArrayList</code>:<p>
     * <code>getWordList().get(level).get(wordNum).get(0)</code> gets the name
     * of the word.<p>
     * <code>getWordList().get(level).get(wordNum).get(1)</code> gets the
     * location of the word's sound file
     * @return An ArrayList representing all available sounds and their level
     *         structure
     */
    public static ArrayList<ArrayList<ArrayList<String>>> getWordList() {
        /* Inside this function, we must take care to not make 2 copies of any
         * sound file - that would be wasteful. If the sound is used in 2 levels
         * then we make a shallow copy of the sound, so it takes up less space
         */
        String folderPath; //The location of the current folder
        int folderName; //The name of the current folder
        File soundNames[]; //An array of all the sound files in the folder
        int sound; //the index of the next sound, so it can be added properly
        String soundName; //the name of the sound without its path or extension
        HashSet<Integer> addedFolders = new HashSet<Integer>();
            //All folders already added to the list. This allows for easy
            //retrieval later
        ArrayList<ArrayList<Integer>> levels = getLevelList();
            //a list of the levels and their connected folders
        ArrayList<ArrayList<ArrayList<String>>> result; //our final ArrayList
        result = new ArrayList<ArrayList<ArrayList<String>>>();
        
        //Start the loop
        for(int level = 0; level < levels.size(); level++) {
            //we begin at the tier of the levels
            
            result.add(new ArrayList<ArrayList<String>>());
                //Add a new arrayList representing this level
            
            //now at folder level
            for(int folder = 0; folder < levels.get(level).size(); folder++) {
                //we begin by getting the name of the containing folder
                folderName = levels.get(level).get(folder);
                
                if(addedFolders.contains(folderName)) {
                    //if this folder is in the added folders then it has been
                    //added already. Due to convention, it would also have been
                    //the entire ArrayList at the level matching its name
                    //Therefore, we merely tag all the values in that ArrayList
                    //so they are not duplicated in memory.
                    result.get(level).addAll(result.get(folderName - 1));
                }
                else {
                    //we construct the path of this folder, so we can create
                    //a new sound list using this path as the base
                    folderPath = System.getProperty("user.dir");
                    folderPath += Const.FILE_PATH + folderName;
                    //get a list of all the sound files in the directory
                    soundNames = (new File(folderPath)).listFiles(new SoundFilter());
                    //set the sound equal to the next element that we will add
                    sound = result.get(level).size();
                    
                    //now we begin adding sounds
                    for(int sFileIndex = 0; sFileIndex < soundNames.length; sFileIndex++) {
                        //make a new ArrayList representing this sound pair
                        result.get(level).add(new ArrayList<String>());
                        //get the name of the sound file
                        soundName = soundNames[sFileIndex].getName();
                        soundName = soundName.toUpperCase(Locale.ENGLISH);
                        soundName = soundName.replace(".WAV", "");
                        //store the name of the sound file
                        result.get(level).get(sound).add(soundName);
                        //get the location of the sound file
                        result.get(level).get(sound).add(soundNames[sFileIndex].getPath());
                        sound++;
                    }
                    //This folder has been fully added, so add it to the added
                    //folders
                    addedFolders.add(folderName);
                }
            }
        }
        return result;
    }
    
    /**
     * Retrieves the user's level from the data file. If the file is empty,
     * returns 1 (for level 1)
     * @return The level of this player
     */
    public int getLevel() {
        try {
            return (Integer)this.getDataContent().get(0);
        } catch(Exception e) {
            return 1;
        }
    }
    
    private void startGame() {
        this.remove(ms);
        int level = getLevel();
        game = new GameScreen(this, level, wordList.get(level));
    }
    
    public void actionPerformed(ActionEvent e) {
        String source = "";
        if(e.getSource().getClass().toString().equals("class javax.swing.JButton")) {
            source = ((JButton)e.getSource()).getText();
        }
        
        if(source.equals("Start A Puzzle!") || source.equals("One More Round...")) {
            startGame();
        }
    }
}
