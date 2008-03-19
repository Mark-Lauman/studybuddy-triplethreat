/*
 * GameScreen.java
 * 
 * Team Triple Threat
 * Log:
 * 03/18/2008 Mark Lauman Added InfoPanel into the screen
 * 03/16/2008 Mark Lauman Implemented class
 * 03/15/2008 Mark Lauman Created class
 */

package screens;

import game.InfoPanel;
import game.WordGrid;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * 
 * @author Mark Lauman
 */
public class GameScreen extends JPanel {
    private WordGrid grid;
    private InfoPanel info;
    
    public GameScreen(ActionListener a, int level,
                             ArrayList<ArrayList<String>> wordList) {
        this.setLayout(new GridBagLayout());
        
        //This is the GridBagConstraints object that specifies specifics for
        //object layout
        GridBagConstraints c = new GridBagConstraints();
	c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        
        //make the buffer space around the letter grid
        JPanel gridBuffer = new JPanel();
        SpringLayout buffLayout = new SpringLayout();
        gridBuffer.setLayout(buffLayout);
        c.gridx = 0;
        c.weightx = 2f/3f;
        
        //make the letter grid and add it to its buffer
        ArrayList<ArrayList<String>> words = pickWords(wordList);
        grid = new WordGrid(words.get(0));
        gridBuffer.add(grid);
        
        //centre the wordlist and place the grid buffer onto this JPanel
        buffLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, grid, 0,
                                 SpringLayout.HORIZONTAL_CENTER, gridBuffer);
        buffLayout.putConstraint(SpringLayout.VERTICAL_CENTER, grid, 0,
                                 SpringLayout.VERTICAL_CENTER, gridBuffer);
        this.add(gridBuffer, c);
        
        //get the filenames for all of the placed words
        ArrayList<String> placedWords = grid.getPlaced();
        ArrayList<String> filenames = new ArrayList<String>(placedWords.size());
        int i = 0;
        for(int j = 0; j < words.get(0).size() && i < placedWords.size(); j++) {
            if(words.get(0).get(j).equals(placedWords.get(i))) {
                filenames.add(words.get(1).get(j));
                i++;
            }
        }
        
        //create the information panel and add it to the stage
        info = new InfoPanel(level, filenames, a);
        c.gridx = 2;
        c.weightx = 1f/3f;
        this.add(info, c);
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
     * If the user passed this level, this function returns <code>true</code>.
     * Othewise, this returns <code>false</code>
     * @return A <code>representing the state of the users game. If the user
     *         passed this level, this function returns <code>true</code>.
     *         Otherwise, this returns <code>false</code>
     */
    public boolean getPassed() {
        return info.getPassed();
    }
    
    /**
     * Returns the time that the the user has played in seconds.
     * @return An <code>int</code> representing the time that the the user has
     *         played in seconds.
     */
    public int getTime() {
        return info.getTime();
    }
}
