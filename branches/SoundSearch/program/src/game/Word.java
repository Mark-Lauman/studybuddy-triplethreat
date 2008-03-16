/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Point;

/**
 *
 * @author Mark Lauman
 */
public class Word {
    public String string;
    public Point location;
    private int direction;
    
    public Word() {
        string = "";
        location = new Point();
        direction = -1;
    }
    
    public int getDirection() {
        return direction;
    }
    
    public void setDirection(int dir) {
        
    }
}
