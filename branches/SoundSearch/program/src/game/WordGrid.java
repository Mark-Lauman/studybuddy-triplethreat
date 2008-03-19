/*
 * WordGrid.java
 * 
 * Team Triple Threat
 * Log:
 * 03/19/2008 Mark Lauman Modified getPlaced, it now returns the words as strings
 * 03/18/2008 Mark Lauman Modified placeWords, so words not placed are removed
 *                        from the wordList
 * 03/17/2008 Mark Lauman Created class
 */

package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * <code>WordGrid</code> is responsible for drawing the letter grid on the game
 * screen. 
 * @author Team Triple Threat
 */
public class WordGrid extends JPanel {
    /**
     * This image is used as a buffer before the grid is displayed to the
     * screen
     */
    private BufferedImage disp;
    /** The character grid itself */
    private ArrayList<ArrayList<Character>> grid;
    /** The <code>ArrayList</code> containing all the words in the grid */
    private ArrayList<Word> wordList;
    
    /**
     * Constructs a new <code>WordGrid</code> and places as many of the passed
     * <code>words</code> as possible into it. To determine what items have not
     * been added to the grid, call this <code>WordGrid</code>'s
     * <code>getPlaced()</code> method
     * @param words The words to be added to the <code>WordGrid</code>
     */
    public WordGrid(final ArrayList<String> words) {
        super();
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setWords(words);
        setDimensions();
        placeWords();
        fillGrid();

        disp = null;
    }
    
    /**
     * Redraws this <code>WordGrid</code> on the display image.
     * @param g A <code>Graphics</code> object pointing to this component
     */
    private void draw(Graphics g) {
        Graphics2D brush = (Graphics2D)g;
        //Make a display image that will work on this screen
        disp = brush.getDeviceConfiguration().createCompatibleImage(
                                                   getWidth(), getHeight());
        //Make brush point to this image (so brush draws to the image, not
        //the screen)
        brush = (Graphics2D)disp.getGraphics();
        //paint this panel to the display image
        super.paint(brush);
        
        //paint selections to the grid
        brush.setColor(Color.YELLOW);
        Point endPoint;
        Point p;
        boolean loop;
        for(Word w : wordList) {
            if(w.isPlaced()) {
                endPoint = w.getEndPoint();
                
                loop = true;
                p = new Point();
                p.x = w.startPoint.x;
                p.y = w.startPoint.y;
                while(loop) {
                    loop = p.x != endPoint.x || p.y != endPoint.y;
                    brush.fillRect(20*p.x, 20*p.y, 20, 20);
                    p = incWordPoint(p, w.getDirectionX(), w.getDirectionY());
                }
            }
             
        }
        

        //paint the grid onto the image
        brush.setColor(Color.BLACK);
        for(int i = 0; i < getWidth(); i += 20) {
            brush.drawLine(i, 0, i, getHeight());
            brush.drawLine(0, i, getWidth(), i);
        }
        
        //paint the letters to the image
        for(int x = 0; x < grid.size(); x++) {
            for(int y = 0; y < grid.get(0).size(); y++) {
                brush.drawString(grid.get(x).get(y).toString(),
                                                         20*x +5, 20*y +15);
            }
        }
    }
    
    /**
     * Fill all empty spaces in the grid with random characters from A-Z.
     * Call this function after all words have been placed into the grid
     */
    private void fillGrid() {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random r = new Random();
        for(int x = 0; x < grid.size(); x++) {
            for(int y = 0; y < grid.size(); y++) {
                if (grid.get(x).get(y) == '\0') {
                    grid.get(x).set(y, alpha.charAt(r.nextInt(alpha.length())));
                }
            }
        }
    }

    /**
     * Gets the length of the largest <code>String</code> in this
     * <code>WordGrid</code>'s word list
     * @return The size of the largest string the word list
     */
    private int getBiggestWordSize() {
        int result = wordList.get(0).length();
        for(int i = 1; i < wordList.size(); i++) {
            if(result < wordList.get(i).length()) {
                result = wordList.get(i).length();
            }
        }
        return result;
    }
    
    /**
     * Gets the list of all words successfully placed into this
     * <code>WordGrid</code>
     * @return An <code>ArrayList</code> containing the words that have been
     * added to the grid
     */
    public final ArrayList<String> getPlaced() {
        ArrayList<String> result = new ArrayList<String>(wordList.size());
        for(Word w:wordList) {
            result.add(w.toString());
        }
        return result;
    }
    
    /**
     * Constructs the basic letter grid, which will be used later to store all
     * the characters
     * @param size The size of one of the axis of the grid in letters
     */
    private void makeGrid(int size) {
        grid = new ArrayList<ArrayList<Character>>(size);
        for(int x = 0; x <size; x++) {
            grid.add(new ArrayList<Character>(size));
            for(int y = 0; y <size; y++) {
                grid.get(x).add('\0');
            }
        }
    }
    
    /**
     * Places the words from this <code>WordGrid</code>'s word list into the
     * grid itself. If a word fails to be put on 15 times in a row, then it is
     * not placed on the grid
     */
    private void placeWords() {
        Word w; //the word we are adding
        Random rand = new Random(); //our random generator
        ArrayList<Integer> xRange;//The range of permissible starting x vals
        ArrayList<Integer> yRange;//The range of permissible starting y vals
        int x; //The chosen x coordinate
        int y; //The chosen y coordinate

        for(int i = 0; i < wordList.size(); i++) {
            int attempt;
            for(attempt = 0; attempt < 15; attempt++) {
                w = new Word(wordList.get(i));

                int dir;
                switch (rand.nextInt(3)) {
                    case 1 : dir = Word.LEFT;  break;
                    case 2 : dir = Word.RIGHT; break;
                    default: dir = Word.NONE;  break;
                }
                switch (rand.nextInt(3)) {
                    case 0 : dir |= Word.UP;   break;
                    case 1 : if(dir != Word.NONE) { break; }
                    default: dir |= Word.DOWN; break;
                }

                w.setDirection(dir);
                xRange = w.getStartXRange(grid.size() - 1);
                yRange = w.getStartYRange(grid.size() - 1);
//                System.out.println("gridSize=" + grid.size());
//                System.out.println("direction=" + Word.directionToString(w.getDirection()));
//                System.out.println("xRange=" + xRange.get(0) + "-" + xRange.get(1));
//                System.out.println("yRange=" + yRange.get(0) + "-" + yRange.get(1));
                x = rand.nextInt(xRange.get(1) - xRange.get(0));
                x += xRange.get(0);
                y = rand.nextInt(yRange.get(1) - yRange.get(0));
                y += yRange.get(0);
//                System.out.println("x=" + x);
//                System.out.println("y=" + y);
                w.startPoint = new Point(x, y);
                if(insertWord(w)) {
                    wordList.set(i, w);
                    break;
                }
            }
            if(attempt==15) {
                wordList.remove(i);
                i--;
            }
        }
        System.out.println(wordList.size());
    }
    
    /**
     * Get a <code>Point</code> that is 1 space away from <code>p</code> in
     * the specified direction
     * @param p         The initial point to base the final point off of
     * @param xDirection The horizontal direction we must travel in to reach the
     *                   resulting point
     * @param yDirection The vertical direction we must travel in to reach the
     *                   resulting point
     * @return A <code>Point</code> that is 1 square in <code>direction</code>'s
     *         specified direction
     */
    private Point incWordPoint(final Point p, int xDirection, int yDirection) {
        Point result = new Point();
        result.x = p.x;
        result.y = p.y;
        switch(xDirection) {
            case Word.LEFT : result.x -= 1; break;
            case Word.RIGHT: result.x += 1; break;
        }
        switch(yDirection) {
            case Word.UP  : result.y -= 1; break;
            case Word.DOWN: result.y += 1; break;
        }
        return result;
    }
    
    /**
     * Attempts to insert the passed word into the grid. If this method is
     * unsuccessful it returns <code>false</code>. Otherwise, it will return
     * <code>true</code>
     * @param w The word to be inserted
     * @return <code>true</code> if the insertion suceeded, <code>false</code>
     *         if otherwise
     */
    private boolean insertWord(Word w) {
//        System.out.println(w);
        final char c[] = w.toString().toCharArray();
//        System.out.println(c);
        Point curP = new Point();
        curP.x = w.startPoint.x;
        curP.y = w.startPoint.y;
        
        for(int i = 0; i < c.length; i++) {
//            System.out.println(curP.x + ", " + curP.y);
//            System.out.println(c);
            if (!grid.get(curP.x).get(curP.y).equals(c[i]) &&
                                        grid.get(curP.x).get(curP.y) != '\0') {
                return false;
            }
            curP = incWordPoint(curP, w.getDirectionX(), w.getDirectionY());
        }
        
        curP = new Point();
        curP.x = w.startPoint.x;
        curP.y = w.startPoint.y;
        for(int i = 0; i < c.length; i++) {
//            System.out.println(c[i] + "*");
            grid.get(curP.x).set(curP.y, c[i]);
            curP = incWordPoint(curP, w.getDirectionX(), w.getDirectionY());
        }
        return true;
    }
    
    /**
     * Sets the dimensions of this panel, and the calls makeGrid to set the
     * dimensions of the grid. The size of the grid is equal to the size
     * of the largest word in the word list + 3. This ensures a greater
     * probability that all words will fit. Frame size is based off of that.
     */
    private void setDimensions() {
        int size = getBiggestWordSize() + 3;
        makeGrid(size);
        size *= 20;
        this.setPreferredSize(new Dimension(size, size));
        this.setMaximumSize(new Dimension(size, size));
        this.setMinimumSize(new Dimension(size, size));
    }

    /**
     * Set this <code>WordGrid</code>'s <code>wordList</code> to equal the
     * string passed. This method performs a deep copy of all
     * <code>String</code>s passed in <code>words</code>
     * @param words An <code>ArrayList</code> contatining the words you wish
     *              to be in this stored in this <code>WordGrid</code>
     */
    private void setWords(ArrayList<String> words) {
        wordList = new ArrayList<Word>();
        for(String s : words) {
            wordList.add(new Word(new String(s)));
        }
    }

    /**
     * Draw the buffer display of this grid to the screen. If the buffer display
     * does not exist, create it and draw it to the screen
     * @param g A <code>Graphics</code> object pointing to this component
     */
    @Override
    public void paint(Graphics g) {
        if(disp == null) {
            draw(g);
        }
        g.drawImage(disp, 0, 0, null);
    }

    /**
     * Destroys this <code>WordGrid</code>'s drawing buffer, and asks the system
     * to call paint. This effectively makes the <code>WordGrid</code> redraw
     * itself
     */
    @Override
    public void repaint() {
        disp = null;
        super.repaint();
    }
}