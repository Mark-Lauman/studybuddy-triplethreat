/*
 * SoundSearch.java
 * 
 * Team Triple Threat
 * Log:
 * 03/15/2008 Mark Lauman Rewrote class to use new architecture
 * 03/12/2008 Mark Lauman Wrote initial class
 */

package Buddies;

import buddyLibrary.Buddy;
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
import screens.MessageScreen;

public class SoundSearch extends Buddy implements ActionListener {
    private static final String FILE_PATH = "\\src\\game\\sounds\\";
    private ArrayList<ArrayList<ArrayList<String>>> wordList;
    private MessageScreen ms;
    
    public SoundSearch() {
        super();
        wordList = getWordList();
        
        this.setTitle("SoundSearch");
        this.setLayout(new GridLayout(0,1));
        this.setPreferredSize(new Dimension(800, 600));
        
        ms = new MessageScreen(this);
    }
    
    public static ArrayList<ArrayList<ArrayList<String>>> getWordList() {
        ArrayList<ArrayList<ArrayList<String>>> result;
        String path;
        int folderName;
        File soundNames[];
        int sound;
        String soundName;
        HashSet<Integer> usedFolders = new HashSet<Integer>();
        ArrayList<ArrayList<Integer>> levels = getLevelList();
        
        result = new ArrayList<ArrayList<ArrayList<String>>>();
        for(int level = 0; level < levels.size(); level++) {
            result.add(new ArrayList<ArrayList<String>>());
            
            for(int folder = 0; folder < levels.get(level).size(); folder++) {
                folderName = levels.get(level).get(folder);
                
                if(usedFolders.contains(folderName)) {
                    result.get(level).addAll(result.get(folderName));
                }
                else {
                    path = System.getProperty("user.dir");
                    path += FILE_PATH + folderName;
                    soundNames = (new File(path)).listFiles();
                    sound = result.get(level).size();
                    
                    for(int sFileIndex = 0; sFileIndex < soundNames.length; sFileIndex++) {
                        result.get(level).add(new ArrayList<String>());
                        soundName = soundNames[sFileIndex].getName();
                        soundName = soundName.toUpperCase(Locale.ENGLISH);
                        soundName = soundName.replace(".WAV", "");
                        result.get(level).get(sound).add(soundName);
                        result.get(level).get(sound).add(soundNames[sFileIndex].getPath());
                    }
                    usedFolders.add(folderName);
                }
            }
        }
        return result;
    }
    
    public static ArrayList<ArrayList<Integer>> getLevelList() {
        //opening file...
        ArrayList<ArrayList<Integer>> result;
        try {
            String path = System.getProperty("user.dir");
            path += FILE_PATH + "levels.txt";
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
    
    public void actionPerformed(ActionEvent e) {
        
    }
}
