/*
 * TestWord.java
 * 
 * Team Triple Threat
 * Log:
 * 03/16/2008 Mark Lauman Created + implemented class
 */

package testers;

import game.Word;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Team Triple Threat
 */
public class TestWord {
    public static void main(String[] args) {
        Word w = new Word("HELLO");
        assert w.toString().equals("HELLO") : "A1 Failed";
        assert w.length() == 5 : "A2 Failed";
        assert w.getDirection() == Word.NONE : "A3 Failed";
        assert w.getDirectionX() == Word.NONE : "A4 Failed";
        assert w.getDirectionY() == Word.NONE : "A5 Failed";
        w.setDirection(Word.RIGHT | Word.DOWN);
        assert w.getDirection() == (Word.RIGHT | Word.DOWN) : "A6 Failed";
        assert w.getDirectionX() == Word.RIGHT : "A7 Failed";
        assert w.getDirectionY() == Word.DOWN : "A8 Failed";
        Point p = w.getEndPoint();
        assert p == null : "A9 Failed";
        w.startPoint = new Point(0, 0);
        p = w.getEndPoint();
        assert p != null : "A10 Failed";
        assert p.x == 4 : "A11 Failed";
        assert p.y == 4 : "A12 Failed";
        
        w.setDirection(Word.LEFT | Word.UP);
        assert w.getDirection() == (Word.LEFT | Word.UP) : "A13 Failed";
        assert w.getDirectionX() == Word.LEFT : "A14 Failed";
        assert w.getDirectionY() == Word.UP : "A15 Failed";
        w.startPoint = new Point(5, 5);
        p = w.getEndPoint();
        assert p != null : "A16 Failed";
        assert p.x == 1 : "A17 Failed";
        assert p.y == 1 : "A18 Failed";
        
        assert w.isPlaced() : "A19 Failed";
        w.startPoint = null;
        assert !w.isPlaced() : "A20 Failed";
        
        ArrayList<Integer> arr = w.getStartXRange(10);
        assert arr.get(0) == 4 : "A21 Failed";
        assert arr.get(1) == 10 : "A22 Failed";
        arr = w.getStartYRange(10);
        assert arr.get(0) == 4 : "A23 Failed";
        assert arr.get(1) == 10 : "A24 Failed";
        
        w.setDirection(Word.LEFT);
        arr = w.getStartXRange(10);
        assert arr.get(0) == 4 : "A25 Failed";
        assert arr.get(1) == 10 : "A26 Failed";
        arr = w.getStartYRange(10);
        assert arr.get(0) == 0 : "A27 Failed";
        assert arr.get(1) == 10 : "A28 Failed";
        
        w.setDirection(Word.RIGHT | Word.DOWN);
        arr = w.getStartXRange(10);
        assert arr.get(0) == 0 : "A29 Failed";
        assert arr.get(1) == 6 : "A30 Failed";
        arr = w.getStartYRange(10);
        assert arr.get(0) == 0 : "A31 Failed";
        assert arr.get(1) == 6 : "A32 Failed";
        
        w.setDirection(Word.DOWN);
        arr = w.getStartXRange(10);
        assert arr.get(0) == 0 : "A33 Failed";
        assert arr.get(1) == 10 : "A34 Failed";
        arr = w.getStartYRange(10);
        assert arr.get(0) == 0 : "A35 Failed";
        assert arr.get(1) == 6 : "A36 Failed";
    }
}
