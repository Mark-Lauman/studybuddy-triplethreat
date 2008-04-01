/*
 * Const.java
 * 
 * Team Triple Threat
 * Log:
 * 03/30/2008 Mark Lauman Added SELECT_COLOR
 * 03/26/2008 Mark Lauman Added SELECT_DIFF and SELECT_WIDTH
 * 03/16/2008 Mark Lauman Created Class
 */

package game;

/**
 * This class stores all the constants used in the program, so they can be
 * changed at a central location
 * @author Team Triple Threat
 */
public abstract class Const {
    
    /** The file path of the sound files */
    public static final String FILE_PATH = "\\src\\game\\sounds\\";
//    public static final String FILE_PATH = "\\SoundSearch\\game\\sounds\\";
    
    /** The width of a letter on the grid */
    public static final int LETTER_WIDTH = 20;
    /**
     * This is the color used for selections on the <code>WordGrid</code>
     */
    public static final int[] SELECT_COLOR = {255, 255, 0, 100};
    /**
     * The width of the buffer space between the edge of a letter and an
     * internal selection
     */
    public static final int SELECT_DIFF = 3;
    /** The width of selections on the grid */
    public static final int SELECT_WIDTH = LETTER_WIDTH - 2 * SELECT_DIFF;
    
}