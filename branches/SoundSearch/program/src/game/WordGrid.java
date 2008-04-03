/*
 * WordGrid.java
 * 
 * Team Triple Threat
 * Log:
 * 03/30/2008 Mark Lauman Added all interactivity and some minor selection
                          drawing changes
 * 03/26/2008 Mark Lauman redesigned text drawing and selections to resize with
 *                        constants
 * 03/25/2008 Mark Lauman created drawSelections()
 * 03/19/2008 Mark Lauman Modified getPlaced, it now returns the words as strings
 * 03/18/2008 Mark Lauman Modified placeWords, so words not placed are removed
 *                        from the wordList
 * 03/17/2008 Mark Lauman Created class
 */

package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import screens.GameScreen;

/**
 * <code>WordGrid</code> is responsible for drawing the letter grid on the game
 * screen. 
 * @author Team Triple Threat
 */
public class WordGrid extends JPanel {
    
    /**
     * This is the currently selected word on the display. It allows the
     * selection to be drawn when the users mouse moves and clicks on something
     */
    private ArrayList<Point> curSelection;
    /**
     * This image is used as a buffer before the grid is displayed to the
     * screen
     */
    private BufferedImage disp;
    /** The <code>ArrayList</code> that specifies what words have been found */
    private ArrayList<Boolean> foundWords;
    /** The character grid itself */
    private ArrayList<ArrayList<Character>> grid;
    /** 
     * This variable is needed so that the <code>WordGrid</code> can call the
     * <code>GameScreen</code>'s <code>update</code> method when a word is found
     */
    private GameScreen parent;
    /** The <code>Color</code> used to draw all selections in this grid */
    private final Color SELECTION_COLOR = new Color(Const.SELECT_COLOR[0],
                                                   Const.SELECT_COLOR[1],
                                                   Const.SELECT_COLOR[2],
                                                   Const.SELECT_COLOR[3]);;
    /** The <code>ArrayList</code> containing all the words in the grid */
    private ArrayList<Word> wordList;
    
    /**
     * Constructs a new <code>WordGrid</code> and places as many of the passed
     * <code>words</code> as possible into it. To determine what items have not
     * been added to the grid, call this <code>WordGrid</code>'s
     * <code>getPlaced()</code> method
     * @param words The words to be added to the <code>WordGrid</code>
     */
    public WordGrid(final ArrayList<String> words, GameScreen parent) {
        super();
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setWords(words);
        curSelection = new ArrayList<Point>(2);
        curSelection.add(new Point(-1, -1));
        curSelection.add(new Point(-1, -1));
        setDimensions();
        placeWords();
        foundWords = new ArrayList<Boolean>(wordList.size());
        for(int i = 0; i < wordList.size(); i++) {
            foundWords.add(false);
        }
        fillGrid();
        this.parent = parent;
        
        GridListener listener = new GridListener();
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
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
        FontMetrics metric = brush.getFontMetrics();
        String word;
        int xPixel;
        int yPixel;
        int buffer;
        for(int x = 0; x < grid.size(); x++) {
            for(int y = 0; y < grid.get(0).size(); y++) {
                word = grid.get(x).get(y).toString();
                
                buffer = (Const.LETTER_WIDTH-metric.stringWidth(word))/2;
                xPixel = x*Const.LETTER_WIDTH + buffer;
                
                buffer = (Const.LETTER_WIDTH-metric.getHeight())/2;
                buffer += metric.getHeight() - 2;
                yPixel = Const.LETTER_WIDTH*y + buffer;
                
                brush.drawString(word, xPixel, yPixel);
            }
        }
    }
    
    /**
     * Draws a selection on the screen that is placed according to the
     * parameters passed
     * @param startPoint The start location of the letter in the grid (not on
     *                   the screen)
     * @param endPoint   The end location of the letter in the grid (not on the
     *                   screen)
     * @param brush
     */
    private void drawOneSelection(Point startPoint, Point endPoint,
                                  Graphics2D brush) {
        int[] arcs=getSelectionArcs(Word.makeDirection(endPoint.x-startPoint.x,
                                                       endPoint.y-startPoint.y )
                                    );
        Polygon p = getSelectionPoly(startPoint.x, startPoint.y,
                                     endPoint.x, endPoint.y  );
        
        //draw the selection background
        brush.setColor(SELECTION_COLOR);
//        System.out.println(Const.LETTER_WIDTH*startPoint.x + Const.SELECT_DIFF);
//        System.out.println(Const.LETTER_WIDTH*startPoint.y + Const.SELECT_DIFF);
//        System.out.println(Const.LETTER_WIDTH*endPoint.x + Const.SELECT_DIFF);
//        System.out.println(Const.LETTER_WIDTH*endPoint.y + Const.SELECT_DIFF);
        brush.fillArc(Const.LETTER_WIDTH*startPoint.x + Const.SELECT_DIFF,
                      Const.LETTER_WIDTH*startPoint.y + Const.SELECT_DIFF,
                      Const.SELECT_WIDTH,
                      Const.SELECT_WIDTH, arcs[0], arcs[1]);
        brush.fillArc(Const.LETTER_WIDTH*endPoint.x + Const.SELECT_DIFF,
                      Const.LETTER_WIDTH*endPoint.y + Const.SELECT_DIFF,
                      Const.SELECT_WIDTH,
                      Const.SELECT_WIDTH, arcs[2], arcs[3]);
        brush.fillPolygon(p);

        //draw the selection outline
        brush.setColor(Color.BLACK);
        brush.drawArc(Const.LETTER_WIDTH*startPoint.x + Const.SELECT_DIFF,
                      Const.LETTER_WIDTH*startPoint.y + Const.SELECT_DIFF,
                      Const.SELECT_WIDTH,
                      Const.SELECT_WIDTH, arcs[0], arcs[1]);
        brush.drawArc(Const.LETTER_WIDTH*endPoint.x + Const.SELECT_DIFF,
                      Const.LETTER_WIDTH*endPoint.y + Const.SELECT_DIFF,
                      Const.SELECT_WIDTH,
                      Const.SELECT_WIDTH, arcs[2], arcs[3]);
        brush.drawLine(p.xpoints[0], p.ypoints[0],
                       p.xpoints[3], p.ypoints[3]);
        brush.drawLine(p.xpoints[1], p.ypoints[1],
                       p.xpoints[2], p.ypoints[2]);
    }
    
    /**
     * Draws ovals showing the selected words. This draws the current selection
     * and all found words.
     * @param g A <code>Graphics</code> object pointing to this component
     */
    private void drawSelections(Graphics g) {
        //Create the brush that will be used for drawing and set constants
        Graphics2D brush = (Graphics2D)g;
        
        //Variables used in the loop
        Word w;
        
        //iterate through the words
        for(int i = 0; i < wordList.size(); i++) {
            //get the word
            w = wordList.get(i);
            
            //If the word is selected
            if(foundWords.get(i)) {
                //draw the word's selection
                drawOneSelection(w.startPoint, w.getEndPoint(), brush);
            }
        }
        if(curSelection.get(0).x >= 0 && curSelection.get(0).y >= 0 &&
           curSelection.get(1).x < grid.size() &&
           curSelection.get(1).y < grid.size()) {
            //if the selection is inside the grid
            drawOneSelection(curSelection.get(0), curSelection.get(1), brush);
        }
//        drawOneSelection(new Point(2, 2), new Point(2, 2), brush);
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
     * Checks the given location for a word in the wordlist. If it is there,
     * then returns its index. Otherwise it returns -1
     * @param startX The X co-ordinate of the starting character
     * @param startY The Y co-ordinate of the starting character
     * @param endX   The X co-ordinate of the ending character
     * @param endY   The Y co-ordinate of the ending character
     * @return The index of the word if it is found, or -1 if it is not
     */
    private int findMatchingWord(final int startX, final int startY,
                                 final int endX,   final int endY   ) {
        //Declare constants used in the loop
        Word w;
        ArrayList<Integer> xVals = new ArrayList<Integer>(2);
        xVals.add(0);
        xVals.add(0);
        ArrayList<Integer> yVals = new ArrayList<Integer>(2);
        yVals.add(0);
        yVals.add(0);
        //go through all words in the wordList to make sure none of them
        //match (linear search)
        for(int i = 0; i < wordList.size(); i++) {
            //first initialize the variables
            w = wordList.get(i);
            xVals.set(0, w.startPoint.x);
            xVals.set(1, w.getEndPoint().x);
            yVals.set(0, w.startPoint.y);
            yVals.set(1, w.getEndPoint().y);
            
            if(xVals.get(0) == startX && yVals.get(0) == startY &&
               xVals.get(1) == endX && yVals.get(1) == endY) {
                //If the word is a match (forwards)
                return i;
            }
            else if(xVals.get(1) == startX && yVals.get(1) == startY &&
                    xVals.get(0) == endX && yVals.get(0) == endY) {
                //If the word is a match (backwards)
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Gets a <code>boolean ArrayList</code> that shows what words have been
     * found in this grid. Each <code>boolean</code> is true if its accompanying
     * word has been found. The accompanying words can be retrieved with the
     * <code>getPlaced</code> method
     * @return A <code>boolean ArrayList</code> that shows what words have been
     *         found in this grid.
     */
    public final ArrayList<Boolean> getFoundWords() {
        return foundWords;
    }
    
    /**
     * This function gets the angles needed to draw arcs for selections. The
     * first two elements of the returned <code>int</code> array relate to the
     * first character in the selection, the second two relate to the last
     * character
     * @param direction The direction in which the <code>Word</code> is
     *                  travelling. You can supply the direction passed from the
     *                  word's <code>getDirection</code> method to satisfy this
     *                  parameter
     * @return An integer array that outlines the arc angles needed to
     *         select the word<br />
     *         Element 0 is the first character's starting arc angle
     *         Element 1 is the first character's arc width (in degrees)
     *         Element 2 and 3 specify the same things for the last character's
     *         arc
     */
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
     * @param startX The starting <b>X</b> coordinate of the word
     * @param startY The starting <b>Y</b> coordinate of the word
     * @param endX   The ending <b>X</b> coordinate of the word
     * @param endY   The ending <b>Y</b> coordinate of the word
     * @return       A <code>Polygon</code> that can be drawn to complete a
     *               selection
     */
    private Polygon getSelectionPoly(int startX, int startY,
                                                           int endX, int endY) {
        //initialize variables
        int dirX = endX - startX; //The X direction
        int dirY = endY - startY; //The Y direction
        //The X coordinates of the polygon
        int[] xPoints = {startX*Const.LETTER_WIDTH,
                         startX*Const.LETTER_WIDTH + Const.LETTER_WIDTH,
                         endX*Const.LETTER_WIDTH + Const.LETTER_WIDTH,
                         endX*Const.LETTER_WIDTH};
        //The Y coordinates of the polygon
        int[] yPoints = {startY*Const.LETTER_WIDTH,
                         startY*Const.LETTER_WIDTH + Const.LETTER_WIDTH,
                         endY*Const.LETTER_WIDTH + Const.LETTER_WIDTH,
                         endY*Const.LETTER_WIDTH};
        int x0; //The leftmost X point
        int x1; //The rightmost X point
        int y0; //The left Y point
        int y1; //The right Y point
        int diff = Const.SELECT_DIFF;
        int width = Const.SELECT_WIDTH;
        
        //determine the x-coordinates of the polygon
        if(dirX != 0 && dirY != 0) {
            //If the arc is at an angle
            x0 = x1 = (int)Math.round(0.15*(width - 1)) + diff;
        }
        else if(dirY != 0) {
            //If the arc is pointing up or down
            x0 = x1 = diff;
        }
        else {
            //If the arc is pointing left or right
            x0 = x1 = Const.LETTER_WIDTH/2;
        }
        xPoints[0] += x0;
        xPoints[1] -= x1;
        xPoints[2] -= x1;
        xPoints[3] += x0;
        
        //determine the y-coordinates of the polygon
        if(dirX != 0 && dirY != 0) {
            //If the arc is at an angle
            //A reminder - the java operator ^ stands for exclusive-or
            if(dirX > 0 ^ dirY > 0) {
                //Down-Left or UP-Right
                y0 = y1 = (int)Math.round(0.15*(width - 1)) + diff;
            } else {
                //Down-Right or UP-Left
                y0 = y1 = (int)Math.round(0.85*(width - 1)) + diff + 1;
            }
        }
        else if(dirY == 0) {
            //the arc is pointing left or right
            y0 = y1 = diff;
        }
        else {
            //if the arc is pointing either up or down
            y0 = y1 = Const.LETTER_WIDTH/2;
        }
        yPoints[0] += y0;
        yPoints[1] -= y1;
        yPoints[2] -= y1;
        yPoints[3] += y0;
        
        Polygon result = new Polygon(xPoints, yPoints, 4);
//        System.out.println("------------");
//        for(int i = 0; i < result.npoints; i++) {
//            System.out.println("(" + result.xpoints[i] + ", " +
//                               result.ypoints[i] + ")");
//        }
        return result;
    }
    
    /**
     * Gets the length of the largest <code>String</code> in this
     * <code>WordGrid</code>'s word list
     * @return The size of the largest string the word list
     */
    public int getBiggestWordSize() {
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
     * Destroys this <code>WordGrid</code>'s drawing buffer, and asks the system
     * to call paint. This effectively makes the <code>WordGrid</code> redraw
     * itself
     */
    public void redraw() {
        disp = null;
        super.repaint();
    }
    
    /**
     * Finds all words for the user, so they don't have to find them themselves
     */
    public void revealAllWords() {
        for(int i = 0; i < foundWords.size(); i++) {
            foundWords.set(i, true);
        }
        repaint();
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
     * This class listens to this WordGrid and adjusts its image accordingly.
     */
    private class GridListener extends MouseAdapter{
        
        /** This value is true if the mouse is being dragged */
        private boolean drag;
        
        /**
         * This function changes the selection's position and calls for a
         * redraw. It is used whenever the mouse is moved
         * @param e The <code>MouseEvent</code> that was passed to the listener
         */
        private void changeSelection(MouseEvent e, boolean dragVal) {
            if(curSelection.get(0).x >= 0 && curSelection.get(0).y >= 0) {
                //if the current selection is valid
                drag = dragVal;
                
                //get the mouse location
                int x = e.getX()/Const.LETTER_WIDTH;
                int y = e.getY()/Const.LETTER_WIDTH;
                
                if(curSelection.get(1).x != x || curSelection.get(1).y != y) {
                    //If the second point in the current selection does not
                    //equal this point
                    
                    //These values help reduce the confusion of the next if
                    //statement
                    boolean insideGrid = x >= 0 && x < grid.size() &&
                                         y >= 0 && y < grid.size();
                    boolean at45degreeAngle =
                                       Math.abs(x - curSelection.get(0).x) ==
                                            Math.abs(y - curSelection.get(0).y);
                    boolean flat = x - curSelection.get(0).x == 0 ||
                                                 y - curSelection.get(0).y == 0;
                    
                    if(insideGrid && (at45degreeAngle || flat)) {
                        //if the selection is inside the grid and at a 45 degree
                        // angle or flat, then it is valid for highlighting
                        curSelection.set(1, new Point(x, y));
                        repaint();
                    }
                }
                
            }
        }
        
        /**
         * This function is called when the mouse is dragged inside the
         * <code>WordGrid</code> with the mouse button down
         * @param e A mouse event that indicates the drag
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            changeSelection(e, true);
        }
        
        /**
         * This function is called when the mouse is moved inside the
         * <code>WordGrid</code> without the mouse button down
         * @param e A mouse event that indicates the movement
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            changeSelection(e, false);
        }
        
        /**
         * This function is called whenever the user presses the left mouse
         * button over the panel
         * @param e A mouse event that indicates the click
         */
        @Override
        public void mousePressed(MouseEvent e) {
            int x, y;
            if(curSelection.get(0).x < 0 || curSelection.get(0).y < 0) {
                //If the current selection is invalid
                x = e.getX()/Const.LETTER_WIDTH;
                y = e.getY()/Const.LETTER_WIDTH;
            }
            else {
                //if the selection is valid
                //Find out if they have found a word and flag it if they have
                int word = findMatchingWord(curSelection.get(0).x,
                                            curSelection.get(0).y,
                                            curSelection.get(1).x,
                                            curSelection.get(1).y );
//                System.out.println(word);
                if(word >= 0) {
                    foundWords.set(word, true);
                    parent.update();
                }
                
                //Invalidate the selection
                x = y = -1;
            }
//            System.out.println("X=" + x);
//            System.out.println("Y=" + y);
            curSelection.set(0, new Point(x, y));
            curSelection.set(1, new Point(x, y));
            drag = false; //to reset everything
            repaint();
        }
        
        /**
         * This function is called whenever the user releases the left mouse
         * button over the panel
         * @param e A mouse event that indicates the release
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            if(drag) {
                mousePressed(e);
            }
        }
    }
}