/*
 * Word.java
 * 
 * Team Triple Threat
 * Log:
 * 03/16/2008 Mark Lauman Created + implemented class
 */

package game;

import java.awt.Point;
import java.util.ArrayList;

/**
 * This class represents a word stored in a <code>WordGrid</code> object. It
 * allows for better control over individual words
 * @author Team Triple Threat
 */
public class Word {
    /** A constant representing no orientation */
    public static final int NONE  = 0x00;//represents 00000000
    /** A constant representing orientation downward */
    public static final int DOWN  = 0x02;//represents 00000010
    /** A constant representing orientation upward */
    public static final int UP    = 0x03;//represents 00000011
    /** A constant representing orientation to the left */
    public static final int LEFT  = 0x08;//represents 00001000
    /** A constant representing orientation to the right */
    public static final int RIGHT = 0x0C;//represents 00001100
    
    /** The actual word in here */
    private String string;
    /** The starting location for this word */
    public Point startPoint;
    /** The direction in which this word is headed */
    private int direction;
    
    /**
     * Makes an empty <code>Word</code> using s as the basic word. This word has
     * no direction or <code>startPoint</code> assigned to it by default
     * @param s The string that will be the base for this word
     */
    public Word(String s) {
        string = s;
        startPoint = null;
        direction = NONE;
    }
    
    /**
     * Convert any direction into a string with this method
     * @param direction The direction to be converted
     * @return A <code>String</code> version of the direction.
     */
    public static String directionToString(int direction) {
        String result = "";
        switch(direction & 0x0C) {
            case LEFT : result += "Left"; break;
            case RIGHT: result += "Right"; break;
        }
        switch(direction & 0x03) {
            case UP  : result += "Up"; break;
            case DOWN: result += "Down"; break;
        }
        return result;
    }
    
    public Word(final Word w) {
        string = w.toString();
        direction = w.getDirection();
        if(w.isPlaced()) {
            startPoint = new Point();
            startPoint.x = w.startPoint.x;
            startPoint.y = w.startPoint.y;
        } else {
            startPoint = null;
        }
    }
    
    /** Gets the full direction of the word, which includes X and Y direction.
      * @return The full direction of the word
      */
    public int getDirection() {
        return direction;
    }
    
    /** Gets the X direction of the word
      * @return The X direction of the word
      */
    public int getDirectionX() {
        /* 0x0D = 00001100
         * 00001100 & 0000xx00 = 0000xx00
         * The bits we are concered about are marked "x" to show that we want 
         * their values
         */
        return direction & 0x0C;
    }
    
    /** Gets the Y direction of the word
      * @return The Y direction of the word
      */
    public int getDirectionY() {
        /* 0x03 = 00000011
         * 00000011 & 000000xx = 000000xx
         * The bits we are concered about are marked "x" to show that we want 
         * their values
         */
        return direction & 0x03;
    }
    
    /**
     * Retrieve the ending point for this word if it proceeds in its current
     * direction.
     * @return The end point of the word
     */
    public Point getEndPoint() {
        if (direction == NONE);
        try {
            int x_offset = 0;
            int y_offset = 0;
            
            int dir = getDirectionX();
            if(dir == LEFT) {
                x_offset -= string.length() - 1;
            }
            else if (dir == RIGHT) {
                x_offset += string.length() - 1;
            }
            
            dir = getDirectionY();
            if(dir == UP) {
                y_offset -= string.length() - 1;
            }
            else if (dir == DOWN) {
                y_offset += string.length() - 1;
            }
            
            return new Point(startPoint.x + x_offset, startPoint.y + y_offset);
        } catch (Exception e) { }
        return null;
    }
    
    /**
     * Get what x values are valid for the starting point on a grid thata allows
     * a maximum X coordinate of <code>maxX</code>
     * @param maxX The largest index allowed in your grid
     * @return     A range of permissible X values. The first element in the
     *             <code>ArrayList</code> is the minimum X coordinate allowed.
     *             The second element is the maximum X coordinate allowed
     */
    public ArrayList<Integer> getStartXRange(int maxX) {
        if(direction == NONE)
            return null;
        
        ArrayList<Integer> result = new ArrayList<Integer>(2);
        int dirX = getDirectionX();
        
        if(dirX == NONE) {
            result.add(0);
            result.add(maxX);
        }
        else if (dirX == LEFT) {
            result.add(0 + string.length() - 1);
            result.add(maxX);
        }
        else if (dirX == RIGHT) {
            result.add(0);
            result.add(maxX - string.length() + 1);
        }
        return result;
    }
    
    /**
     * Get what y values are valid for the starting point on a grid thata allows
     * a maximum Y coordinate of <code>maxY</code>
     * @param maxY The largest index allowed in your grid
     * @return     A range of permissible Y values. The first element in the
     *             <code>ArrayList</code> is the minimum Y coordinate allowed.
     *             The second element is the maximum Y coordinate allowed
     */
    public ArrayList<Integer> getStartYRange(int maxY) {
        if(direction == NONE)
            return null;
        
        ArrayList<Integer> result = new ArrayList<Integer>(2);
        int dirY = getDirectionY();
        
        if(dirY == NONE) {
            result.add(0);
            result.add(maxY);
        }
        else if (dirY == UP) {
            result.add(0 + string.length() - 1);
            result.add(maxY);
        }
        else if (dirY == DOWN) {
            result.add(0);
            result.add(maxY - string.length() + 1);
        }
        return result;
    }
    
    /**
     * Returns true if this <code>Word</code> has been placed in a grid
     * @return True if this <code>Word</code> has been placed in a grid
     */
    public boolean isPlaced() {
        return startPoint != null;
    }
    
    /**
     * Get the length of the base string of this <code>Word</code>
     * @return The length of the base string of this <code>Word</code>
     */
    public int length() {
        return string.length();
    }
    
    /** Sets the direction of this object to equal the passed one. You can set
      *  direction by combining two of the direction constants together with a
      *  bitwise or<p>
      * <b>eg.</b> <code>setDirection(Word.LEFT | Word.UP)</code> would set the
      * direction to go up-left
      */
    public void setDirection(int dir) {
        direction = dir;
    }
    
    /**
     * Returns a string representation of the object. In general, the 
     * <code>toString</code> method returns a string that 
     * "textually represents" this object. The result should 
     * be a concise but informative representation that is easy for a 
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The <code>toString</code> method for class <code>Object</code> 
     * returns a string consisting of the name of the class of which the 
     * object is an instance, the at-sign character `<code>@</code>', and 
     * the unsigned hexadecimal representation of the hash code of the 
     * object. In other words, this method returns a string equal to the 
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return  a string representation of the object.
     */
    @Override
    public String toString() {
        return string.toString();
    }
}
