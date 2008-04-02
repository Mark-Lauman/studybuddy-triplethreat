/*
 * SoundSearch.java
 * 
 * Team Triple Threat
 * Log:
 * 03/19/2008 Mark Lauman Added endGame function
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
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import javax.swing.JButton;
import screens.GameScreen;
import screens.MessageScreen;

public class SoundSearch extends Buddy implements ActionListener {
    private MessageScreen ms;
    private GameScreen game;
    private static final int LAST_LEVEL = getLastLevel();
    
    public SoundSearch() {
        super();
        this.setLayout(new GridLayout(0,1));
        this.setPreferredSize(new Dimension(800, 600));
        
        ms = new MessageScreen(this);
        this.add(ms);
    }
    
    /**
     * When the user finishes a round of the SoundSearch, win or lose, this is
     * called.
     */
    private void endGame() {
        if(game.getWinState()) {
            //If you won the game
            storeGameData(game.getTime());
            ms.setTimeVisible(true);
            ms.setTime(game.getTime());
            ms.setMessage("Level Cleared!");
        }
        else {
            //If you lost the match or gave it up
            storeGameData(15*60);
            ms.setTimeVisible(false);
            if(game.getTime() >= 60*15) {
                ms.setMessage("Time's Up!");
            }
            else {
                ms.setMessage("Try Again?");
            }
        }
        
        this.remove(game);
        game = null;
        this.add(ms);
        this.revalidate();
        this.repaint();
        
    }
    
    public static final int getLastLevel() {
        int result = 0;
        
        try {
            String path = System.getProperty("user.dir");
            path += Const.FILE_PATH + "levels.txt";
            FileReader f = new FileReader(path);
            BufferedReader reader = new BufferedReader(f);
            
            String line = reader.readLine();
            while(line != null) {
                result++;
                line = reader.readLine();
            }
            reader.close();
        } catch(Exception e) {
            result = 0;
        }
        
        return result;
    }
    
    /**
     * Gets a list of all the folders inside each level. The outer ArrayList
     * represents the levels, while the inner one contains the folder names for
     * each level.
     * @return An ArrayList that can be used to access folder names.
     */
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
            reader.close();
            
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
     * Gets a full list of all the sound files stored for the specified level.
     * @param level The level that you wish to retrieve
     * @return An ArrayList representing all available sounds and their level
     *         structure
     */
    public static ArrayList<ArrayList<String>> getWordList(int level) {
        level--;
        String folderPath; //The location of the current folder
        int folderName; //The name of the current folder
        File soundNames[]; //An array of all the sound files in the folder
        int sound; //the index of the next sound, so it can be added properly
        String soundName; //the name of the sound without its path or extension
        HashSet<Integer> addedFolders = new HashSet<Integer>();
            //All folders already added to the list. (Error Checking)
        ArrayList<ArrayList<Integer>> levels = getLevelList();
            //a list of the levels and their connected folders
        
        ArrayList<ArrayList<String>> result; //our final ArrayList
        result = new ArrayList<ArrayList<String>>();
        
        for(int folder = 0; folder < levels.get(level).size(); folder++) {
            //now at folder level
            //we begin by getting the name of the containing folder
            folderName = levels.get(level).get(folder);
            
            if(!addedFolders.contains(folderName)) {
                //We use the if statement so we don't add duplicates of a folder
                
                //Construct the path of this folder, so we can create a new
                //sound list using this path as the base
                folderPath = System.getProperty("user.dir");
                folderPath += Const.FILE_PATH + folderName;
                //get a list of all the sound files in the directory
                soundNames = (new File(folderPath)).listFiles(new SoundFilter());
                //set the sound equal to the next element that we will add
                sound = result.size();
                
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
                //This folder has been fully added, so add it to the added
                //folders
                addedFolders.add(folderName);
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
    
    private int changeLevel(ArrayList<Integer> last3Rounds) {
        int level = this.getLevel();
        boolean change = true;
        
        //See if we need to increase the level
        for(int i : last3Rounds) {
            change &= i < 2.5*60;
        }
        if(change) {
            if (level < LAST_LEVEL) {
                return level+1;
            }
            else if(level > LAST_LEVEL) {
                return LAST_LEVEL;
            }
        }
        
        change = true;
        for(int i : last3Rounds) {
            change &= i == 15*60;
        }
        if(change && level > 1) {
            return level-1;
        }
        
        return level;
    }
    
    private void startGame() {
        this.remove(ms);
        int level = getLevel();
        game = new GameScreen(this, level, getWordList(level));
        this.add(game);
        this.revalidate();
    }
    
    public void storeGameData(int time) {
        if(time > 0) {
            this.setStatType(Buddy.TIME_STATS);
            this.writeStats(time);
        }
        else {
            this.setStatType(Buddy.TIME_STATS);
            try {
                //Try to write to existing binary data
                ArrayList<Integer> dat =
                        (ArrayList<Integer>)this.getDataContent().get(1);
                dat.set(2, dat.get(1));
                dat.set(1, dat.get(0));
                dat.set(0, time);
            }
            catch (Exception e) {
                //The binary data does not exist yet, so we must make it
                //ourselves
                
            }
        }
        
    }
    
    public void actionPerformed(ActionEvent e) {
        String source = "";
        if(e.getSource().getClass().toString().equals("class javax.swing.JButton")) {
            source = ((JButton)e.getSource()).getText();
        }
        
        if(source.equals("Start A Puzzle!") || source.equals("One More Round...")) {
            startGame();
        }
        else if(source.equals("Give Up")) {
            endGame();
        }
    }
}
