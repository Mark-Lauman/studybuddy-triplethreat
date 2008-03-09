/*
 * BarGraph.java
 * 
 * Team Triple Threat
 * Log:
 * 02/23/2008 Mark Lauman Changed the comments so they fit our standard
 * 02/15/2008 Mark Lauman Rewrote rendering functions - beta v1
 * 02/14/2008 Mark Lauman Worked on the render and makeBars function
 * 02/12/2008 Mark Lauman Implemented Functions for no data
 * 02/11/2008 Mark Lauman Created Template
 */

package buddyLibrary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This class displays the an array of <code>float</code>s (scores) as a bar
 * graph. It can be treated as a <code>JPanel</code> for easy installation into
 * a window.
 * <p>
 * All <code>float</code>s are accepted by the graph, including negative values.
 * If all <code>float<code> values follow the rule
 * <code>1.0 >= val >= 0.0</code> then the scores are treated as percentages,
 * and graphed accordingly.
 * <p>
 * The foreground colour of the graph is the colour of the axis and
 * the bars themselves. The background colour is everything else. The default is black
 * foreground and white background.
 * @author Team Triple Threat
 * @see <a href="http://java.sun.com/javase/6/docs/api/java/awt/Color.html">
 *      java.awt.Color</a>
 * @see <a href="http://java.sun.com/javase/6/docs/api/javax/swing/JPanel.html">
 *      javax.swing.JPanel</a>
 */
public class BarGraph extends JPanel {
    
    /** The data to be graphed */
    private ArrayList<Float> scores;
    /** Contains the graph as an image */
    private BufferedImage disp;
    /* The maximum value for the graph */
    private float max;
    /** the minimum value for the graph */
    private float min;

    /**
     * Constructs a <code>BarGraph</code> containing no scores. It has a white
     * background, and a black foreground.
     */
    public BarGraph() {
        super();
        disp = null;
        scores = new ArrayList<Float>();
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
    }
    
// -------------------------------------------------------------- //

    /**
     * Calculates the data that will be displayed to the screen.
     * This is an <code>ArrayList</code> of floats, with a <code>length</code>
     * no greater than the amount of pixels on the x axis.
     * @return An <code>ArrayList</code> of floats extracted from the data that
     *         is ready to be graphed.
     */
    private ArrayList<Float> calculateDisplayData() {
        ArrayList<Float> result; //The display data
        
        float width = ((float)this.getWidth() -33) /scores.size();
            //The width of each piece of data (can be fractional)
        
        if(1 > width) {
            /* If we have more scores than we have pixel columns, then
               we will have to average the scores together.
               This section averages scores to have 1 bar per pixel column */
            
            result = new ArrayList<Float>();
                //make a new list for the display data
            float colData = 0; //The summed data for the current column
            int combinedScores = 0; //The amount of scores in this column
            float currBar = 0; //A running tally of widths added together
            
            for(int i = 0; i < scores.size(); i++) {
                //we are iterating through the scores
                colData += scores.get(i); //Add this score to the column data
                currBar += width; //add the width in, so we know when this
                                  //column is done
                combinedScores++; //Add 1 score entry to the column
                
                if(currBar >= 1) {
                    //If this column is full
                    result.add(colData/combinedScores);
                        /* get the average, for this column, make it the value
                         * to be displayed */
                    currBar -= (int)currBar;
                        /* remove this bar's width from the tally decimals are
                         * preserved for accuracy */
                    colData = 0; //clear the data sum
                    combinedScores = 0;
                        //clear the scores contributing to the sum
                }
            }
        
//        //A method to print out all of data (for testing purposes)
//            int chars = 0;
//            String current = null;
//            System.out.println("data =");
//            for(int i =0; i < data.size(); i++) {
//                current = data.get(i) + ", ";
//                System.out.print(current);
//                chars += current.length();
//                if(chars > 50){
//                    System.out.println();
//                    chars = 0;
//                }
//            }
        }
        else {
            //The scores take up 1 or more columns as is, so let's use them
            result = scores;
        }
        
        return result;
    }
    
    /**
     * Creates an <code>ArrayList</code> of <code>Rectangles</code> from a
     * <code>float ArrayList</code> that has been prepared for drawing.
     * <p>
     * The data provided by <code>calculateDisplayData()</code> is prepared for
     * drawing.
     * @param data An <code>ArrayList</code> of <code>float</code>s prepared for
     *        drawing
     * @return A set of rectangles that can be drawn as the graph's bars
     */
    private ArrayList<Rectangle> makeBars(ArrayList<Float> data) {
        
        /* We need an integer value of the width to increment the bars, but we
         * also need a decimal representation so the bars stay close to the
         * real width.
         * 
         * Therefore, we will create 2 variables. One will hold the fraction,
         * while the other holds the integer component. */
        float decimal = ((float)this.getWidth() -33) /data.size();
            //represents the unused decimals in each column
            //currently it doesn't, so it can help the next line
        int width = (int)decimal;
            //represents the integer width of most columns
        decimal -= width;
            //now decimal represents the unused decimals
        float decTally = 0;
            /* This tallies up unused decimals, so we know when to make a column
             * a pixel bigger */
        ArrayList<Rectangle> result = new ArrayList<Rectangle>();
            //The rectangles we are returning
        
        //used in the loop each time
        int height;
        int x = 17;
        int y = 0;
        Rectangle rect;
        
        //Beginning our calculations...
        //First divide the graph up
        int yRange = this.getHeight() - 32;
            //The size of the range of acceptable Y values
        int posRange = Math.round((max / (max - min)) * yRange);
            //The size of the range of positive values
        int negRange = yRange - posRange;
            //The size of the range of negative values
        
        for(int i = 0; i < data.size(); i++) {
            //iterate through the display data
            
            // +/- Distinction
            if(0 > data.get(i)) {
                //negative value
                height = Math.round(data.get(i) / min * negRange);
                y = posRange + 16;
            }
            else {
                //positive value
                height = Math.round(data.get(i) / max * posRange);
                y = posRange + 16 - height;
            }
            
            // 1 pixel wider or not
            if(decTally >= 1) {
                rect = new Rectangle(x, y, width + 1, height);
                decTally--;
                x += width + 1;
            }
            else {
                rect = new Rectangle(x, y, width, height);
                x += width;
            }
            
            //apply iterative changes
            decTally += decimal;
            result.add(rect);
        }
        
//        //A method to print out the rectangles (for testing purposes)
//        System.out.println("Rectangles =");
//        for(int i = 0; i < result.size(); i++) {
//            rect = result.get(i);
//            System.out.println(rect.getX() + ", "
//                               + rect.getY() + ", "
//                               + rect.getWidth() + ", "
//                               + rect.getHeight());
//        }
        
        return result;
    }
    
    /**
     * Redraws the graph in <code>disp</code> using
     * <code>calculateDisplayData()</code> and
     * <code>makeBars(ArrayList<Float> data)</code>. This function is called
     * whenever paint wants the graph redrawn.
     * @param g A <code>Graphics</code> object that points to this BarGraph.
     */
    private void render(Graphics g) {
        /* Render the graph */
        
        Graphics2D brush = (Graphics2D) g;
        disp = brush.getDeviceConfiguration().createCompatibleImage(
                        getWidth(), getHeight());
           /* create a BufferedImage that will work well with the screen
            * The image will be the same size as this frame (Hence the calls to
            *  getWidth() and getHeight()) */
        brush = (Graphics2D) disp.getGraphics();
            //Make a brush that will paint to this image (not the screen!)
        super.paint(brush);
            //draw this panel first, but on the image
        
        brush.setColor(this.getForeground());
            //the foreground colour is used for the graph
        
        if(scores.isEmpty()) {
            /* If there are no scores to display, just say there is nothing to
               display */
            brush.drawString("No Data Available",
                    (this.getWidth()/2 -46), (this.getHeight()/2 +5));
        }
        else {
            //draw the axis of the graph
            brush.drawLine(15, 15, 15, this.getHeight() -15);
            brush.drawLine(15, this.getHeight() -15,
                              this.getWidth() -15, this.getHeight() -15);
            //Label the axis
            if(this.getHeight() > 89) {
                //If the vertical is too small to label, don't label it
                brush.drawString("S", 4, 25);
                brush.drawString("C", 4, 38);
                brush.drawString("O", 4, 51);
                brush.drawString("R", 4, 64);
                brush.drawString("E", 4, 77);
                brush.drawString("S", 4, 90);
            }
            brush.drawString("TIME",
                                this.getWidth() - 45 , this.getHeight() - 2);
            
            //get the bars to display
            ArrayList<Rectangle> barArr = makeBars(calculateDisplayData());
            
            for(int i = 0; i < barArr.size(); i++) {
                brush.fill(barArr.get(i));
            }
        }
    }
    
    /**
     * Clears all the scores and forces a redraw of the panel and the graph.
     */
    public void resetAll() {
        scores.clear();
        repaint();
    }
    
    /**
     * Sets the scores to equal the passed float array, and calculates some
     * score statistics for graphing. This function also forces a redraw of the
     * panel and the graph.
     * @param values The new scores
     */
    public void setScores(float[] values) {
        
        //make it so these will definitely be overwritten
        max = Float.MIN_VALUE;
        min = Float.MAX_VALUE;
        scores.clear();
        
        for(int i = 0; i < values.length; i++) {
            //loop through the values
            scores.add(values[i]); //add it to the scores
            
            //see if it is the max or min value
            if(values[i] > max)
                max = values[i];
            if(values[i] < min)
                min = values[i];
        }
        
        if(min >= 0){
            //if the minimum is above 0, make it 0!
            min = 0;
            if(1 >= max) {
                //if the max is 1 or less, the values are percents.
                //To graph them appropriately, make 1 the max
                max = 1;
            }
        }
        //redraw display
        repaint();
    }

// -------------------------------------------------------------- //
    /**
     * Forces a redraw of the panel and the graph.
     */
    @Override
    public void repaint(){
        disp = null;
        super.repaint();
    }
    
    /**
     * This method paints the panel on the screen. If the panel has been
     * resized, or the graph has not been drawn, then it tells the graph to draw
     * itself again.
     * @param g A <code>Graphics</code> oject that points to this object
     */
    @Override
    public void paint(Graphics g) {
        /* repaint the graph */
        
        //does the graph need to be re-rendered?
        boolean render = (disp == null ||
                        this.getWidth() != disp.getWidth() ||
                        this.getHeight() != disp.getHeight());
        //if so, render it!
        if(render) {
            render(g);
        }
        //draw the graph - it takes up the full JPanel
        g.drawImage(disp, 0, 0, null);
    }
}