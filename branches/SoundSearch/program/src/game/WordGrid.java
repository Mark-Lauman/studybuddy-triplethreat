/*
 * WordGrid.java
 * 
 * Team Triple Threat
 * Log:
 * 03/25/2008 Mark Lauman created drawSelections()
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
import java.awt.Polygon;
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
    /** The <code>ArrayList</code> that specifies what words have been found */
    private ArrayList<Boolean> foundWords;
    
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
        foundWords = new ArrayList<Boolean>(wordList.size());
        for(int i = 0; i < wordList.size(); i++) {
            foundWords.add(false);
        }
        setDimensions();
        placeWords();
        fillGrid();

        disp = null;
    }
    
    //------------------------------------------------------------------------//
    
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
        
        //paint the grid onto the image
        brush.setColor(Color.BLACK);
        for(int i = 0; i < getWidth(); i += Const.LETTER_WIDTH) {
            brush.drawLine(i, 0, i, getHeight());
            brush.drawLine(0, i, getWidth(), i);
        }
        
        //paint the letters to the image
        for(int x = 0; x < grid.size(); x++) {
            for(int y = 0; y < grid.get(0).size(); y++) {
                brush.drawString(grid.get(x).get(y).toString(),
                                 Const.LETTER_WIDTH*x +5,
                                 Const.LETTER_WIDTH*y +15);
            }
        }
    }
    
    /**
     * Draws ovals showing the selected words. This draws the current selection
     * and all found words.
     * @param g A <code>Graphics</code> object pointing to this component
     */
    private void drawSelections(Graphics g) {
        //Create the brush that will be used for drawing and set constants
        Graphics2D brush = (Graphics2D)g;
        final Color SELECTION_COLOR = new Color(255, 255, 0, 100);
        
        //Variables used in the loop
        Point startPoint;
        Point endPoint;
        Word w;
        int[] arcs;
        
        //iterate through the words
        for(int i = 0; i < wordList.size(); i++) {
            //get the word
            w = wordList.get(i);
            
            //If the word is selected
            if(true) {
                //get the word's placement and get the arc orientation
                startPoint = w.startPoint;
                endPoint = w.getEndPoint();
                arcs = getSelectionArcs(w.getDirection());
                Polygon p = getSelectionPoly(Const.LETTER_WIDTH*startPoint.x,
                                             Const.LETTER_WIDTH*startPoint.y,
                                             Const.LETTER_WIDTH*endPoint.x,
                                             Const.LETTER_WIDTH*endPoint.y);
                
                //draw the selection background
                brush.setColor(SELECTION_COLOR);
                brush.fillArc(Const.LETTER_WIDTH*startPoint.x + 1,
                               Const.LETTER_WIDTH*startPoint.y + 1,
                               Const.LETTER_WIDTH-1,
                               Const.LETTER_WIDTH-1, arcs[0], arcs[1]);
                brush.fillArc(Const.LETTER_WIDTH*endPoint.x + 1,
                               Const.LETTER_WIDTH*endPoint.y + 1,
                               Const.LETTER_WIDTH-1,
                               Const.LETTER_WIDTH-1, arcs[2], arcs[3]);
                brush.fillPolygon(p);
                
                //draw the selection outline
                brush.setColor(Color.BLACK);
                brush.drawArc(Const.LETTER_WIDTH*startPoint.x + 1,
                               Const.LETTER_WIDTH*startPoint.y + 1,
                               Const.LETTER_WIDTH-1,
                               Const.LETTER_WIDTH-1, arcs[0], arcs[1]);
                brush.drawArc(Const.LETTER_WIDTH*endPoint.x + 1,
                               Const.LETTER_WIDTH*endPoint.y + 1,
                               Const.LETTER_WIDTH-1,
                               Const.LETTER_WIDTH-1, arcs[2], arcs[3]);
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
    
    private int[] getSelectionArcs(int direction) {
        int[] result = {0, 180, 180, 180};
        switch(direction) {
            case (Word.DOWN|Word.LEFT)  : result[0] += 45;
                                          result[2] += 45;
            case (Word.LEFT)            : result[0] += 45;
                                          result[2] += 45;
            case (Word.UP|Word.LEFT)    : result[0] += 45;
                                          result[2] += 45;
            case (Word.UP)              : result[0] += 45;
                                          result[2] += 45;
            case (Word.UP|Word.RIGHT)   : result[0] += 45;
                                          result[2] += 45;
            case (Word.RIGHT)           : result[0] += 45;
                                          result[2] += 45;
            case (Word.DOWN|Word.RIGHT) : result[0] += 45;
                                          result[2] += 45;
            default: 
        }
        if(result[2] >= 360) {
            result[2] -= 360;
        }
        return result;
    }
    
    /**
     * Retrieves a <code>Polygon</code> that can be drawn between two letters to
     * select all the letters between them. This polygon is ready to be drawn
     * immediately
     * @param startX The starting <b>X</b> coordinate of the word (in pixels)
     * @param startY The starting <b>Y</b> coordinate of the word (in pixels)
     * @param endX   The ending <b>X</b> coordinate of the word (in pixels)
     * @param endY   The ending <b>Y</b> coordinate of the word (in pixels)
     * @return       A <code>Polygon</code> that can be drawn to complete a
     *               selection
     */
    private Polygon getSelectionPoly(int startX, int startY,
                                                           int endX, int endY) {
        int dirX = endX - startX;
        int dirY = endY - startY;
        //initialize variables
        int[] xPoints = {startX+1, startX+1, endX+1, endX+1};
        int[] yPoints = {startY+1, startY+1, endY+1, endY+1};
        int width = Const.LETTER_WIDTH;
        
        //determine the x-coordinates of the polygon
        if(dirX != 0 && dirY != 0) {
            //If the arc is at an angle
            xPoints[0] += width/4;
            xPoints[1] += (int)(width * 3f/4);
            xPoints[2] += (int)(width * 3f/4);
            xPoints[3] += width/4;
        }
        else if(dirY != 0) {
            //If the arc is pointing up or down
            xPoints[1] += width - 1;
            xPoints[2] += width - 1;
        }
        else {
            //the arc is pointing either left or right
            for(int i=0; i<xPoints.length; i++) {
                xPoints[i] += width/2 - 1;
            }
        }
        
        //determine the y-coordinates of the polygon
        if(dirX != 0 && dirY != 0) {
            //If the arc is at an angle
            int y1;
            int y2;
            //A reminder - the java operator ^ stands for exclusive-or
            if(dirX > 0 ^ dirY > 0) {
                //Down-Left or UP-Right
                y1 = width/4;
                y2 = (int)(width * 3f/4);
                
            }
            else {
                //Down-Right or UP-Left
                y1 = (int)(width * 3f/4);
                y2 = width/4;
            }
            yPoints[0] += y1;
            yPoints[1] += y2;
            yPoints[2] += y2;
            yPoints[3] += y1;
        }
        else if(dirY == 0) {
            //the arc is pointing left or right
            yPoints[1] += width - 1;
            yPoints[2] += width - 1;
        }
        else {
            //if the arc is pointing either up or down
            for(int i=0; i<xPoints.length; i++) {
                yPoints[i] += width/2 - 1;
            }
        }
        
        return new Polygon(xPoints, yPoints, 4);
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
     * Sets the dimensions of this panel, and the calls makeGrid to set the
     * dimensions of the grid. The size of the grid is equal to the size
     * of the largest word in the word list + 3. This ensures a greater
     * probability that all words will fit. Frame size is based off of that.
     */
    private void setDimensions() {
        int size = getBiggestWordSize() + 3;
        makeGrid(size);
        size *= Const.LETTER_WIDTH;
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
    
    //------------------------------------------------------------------------//
    
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
        drawSelections(g);
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