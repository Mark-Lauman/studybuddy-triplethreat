/*
 * GameScreen.java
 * 
 * Team Triple Threat
 * Log:
 * 03/16/2008 Mark Lauman Implemented class
 * 03/15/2008 Mark Lauman Created class
 */

package screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * 
 * @author Mark Lauman
 */
public class GameScreen extends JPanel {
    private WordGrid grid;
    
    public GameScreen(ActionListener a, int level, ArrayList<ArrayList<String>> wordList) {
        ArrayList<ArrayList<String>> words = pickWords(wordList);
        grid = new WordGrid(words.get(0));
        this.add(grid);
    }
    
    /**
     * Returns the chosen words in a different format. The first element in the
     * returned list points to all the word names, while the second element
     * points to all their locations.
     * 
     * @param wordList A list of words to choose from. The list should be
     *                 formatted so first tier refers to the word, and the
     *                 second tier refers to the name or pathname.
     * @return The words chosen in another format (see above)
     */
    public ArrayList<ArrayList<String>> pickWords(ArrayList<ArrayList<String>> wordList) {
        ArrayList<ArrayList<String>> result;
        result = new ArrayList<ArrayList<String>>(0);
        result.add(new ArrayList<String>(0));
        result.add(new ArrayList<String>(0));
        
        Random r = new Random();
        HashSet<Integer> chosen = new HashSet<Integer>(2);
        int next = 0;
        
        while(5 > result.get(0).size()) {
            next = r.nextInt(wordList.size());
            if(!chosen.contains(next)) {
                result.get(0).add(wordList.get(next).get(0));
                result.get(1).add(wordList.get(next).get(1));
                chosen.add(next);
            }
        }
        return result;
    }
    
    /**
     * This class is responsible for drawing the letter grid on the game screen
     * @author Team Triple Threat
     */
    public class WordGrid extends JPanel {
        public WordGrid(ArrayList<String> words) {
            super();
            this.setDimensions(words);
            this.setBackground(Color.WHITE);
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));   
        }
        
        private void setDimensions(ArrayList<String> words) {
            int size = getBiggestWord(words).leghth() + 3;
                //If the size of the grid == the size of all the words, then it
                //is impossible to add in 5 words. Adding 3 makes word addition
                //always possible.
            size *= 20;
            this.setPreferredSize(new Dimension(size, size));
        }
        
        /**
         * Gets data from the passed ArrayList.
         * @param words The words that you want to analyze
         * @return Data relating to the string. The first index is the longest
         *         String's length, and the second is 1 if the array is all
         *         equal length
         */
        private ArrayList<Integer> getWordData(ArrayList<String> words) {
            ArrayList<Integer> result = new ArrayList<Integer>(0);
            result.add(words.get(0).length());
            result.add(1);
            
            for(int i = 1; i < words.size(); i++) {
                if(result.get(0) != words.get(i).length()) {
                    result.set(1, 0);
                }
                if(result.get(0) < words.get(i).length()) {
                    result.set(0, words.get(i).length());
                }
            }
            return result;
        }
    }
}
