/*
BarGraph.java
This class displays the data inside it as a bar graph

Team Triple Threat
Log:
02/14/2008 Mark Lauman Worked on the render and makeBars function
02/12/2008 Mark Lauman Implemented Functions for no data
02/11/2008 Mark Lauman Created Template
*/

package buddyLibrary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

public class BarGraph extends JPanel {
    
    private float[] scores; //This is the data to be graphed
    private BufferedImage disp; //This image contains the graph

    public BarGraph() {
        /* Set up the BarGraph class */
        super();
        disp = null;
        scores = null;
        this.setBackground(Color.WHITE);
    }
    
// -------------------------------------------------------------- //

    private void render(Graphics g) {
        /* Render the graph */
        
        Graphics2D brush = (Graphics2D) g;
        /* create a BufferedImage that will work well with the screen
         * The image will be the same size as this frame (Hence the calls to
         *  getWidth() and getHeight()) */
        disp = brush.getDeviceConfiguration().createCompatibleImage(
                        getWidth(), getHeight());
        //Make a brush that will paint to this image (not the screen!)
        brush = (Graphics2D) disp.getGraphics();
        //draw this panel 1st, but on the image
        super.paint(brush);
        
        if(scores == null) {
            //If there are no scores to display, just say there is nothing to
            // display
            brush.setColor(Color.BLACK);
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
            ArrayList<Rectangle> barArr = makeBars();
            
            for(int i = 0; i < barArr.size(); i++) {
                brush.fill(barArr.get(i));
            }
        }
    }
    
    private ArrayList<Rectangle> makeBars() {
        /* Makes the Rectangles that will be displayed
         * in the BarGraph */
        
        //If we have more scores than we have pixel columns, then
        //we will have to average the scores together.
        //This section combines scores to have at most 1 bar per column
        ArrayList<Float> data = new ArrayList<Float>(); //The averaged data
        //The width of each piece of data (can be fractional)
        float width = ((float)this.getWidth() -33) /scores.length;
        float colData = 0; //The summed data for the current column
        int combinedScores = 0; //The amount of scores in this column
        float currBar = 0; //A running tally of widths added together
        float max = Integer.MIN_VALUE; //The maximum value in data
        for(int i = 0; i < scores.length; i++) {
            //we are iterating through the scores
            colData += scores[i]; //Add this score to the column data
            currBar += width; //add the width in, so we know when this
                              //column is done
            combinedScores++; //Add 1 score entry to the colum
            if(currBar >= 1) {
                //If this column is full
                data.add(colData/combinedScores); //get the average, for this
                                                  //column, make it the value
                                                  //to be displayed
                if(colData/combinedScores > max)
                    //If this is bigger than max, it is now max
                    max = colData/combinedScores;
                currBar -= (int)currBar;//remove this bar's width from the tally
                                        //decimals are preserved for accuracy
                colData = 0; //clear the data sum
                combinedScores = 0; //clear the scores contributing to the sum
            }
        }
        
      //A method to print out all of data (for testing purposes)
        int chars = 0;
        String current = null;
        for(int i =0; i < data.size(); i++) {
            current = data.get(i) + ", ";
            System.out.print(current);
            chars += current.length();
            if(chars > 50){
                System.out.println();
                chars = 0;
            }
        }
        System.out.println("\nMax = " + max);
        
        //Now to make rectangles of our data!
        //The rectangles we are returning
        ArrayList<Rectangle> result = new ArrayList<Rectangle>();
        //reset the width to be relative to the averaged data
        width = ((float)this.getWidth() -33) /data.size();
        int lowerY = this.getHeight() - 16; //The baseline Y value
        int RangeY = lowerY - 16; //The size of the range of acceptable Y values
                                  //If a rectangle is RangeY high, its value
                                  //equals max.
        
        
        
        for(int i = 0; i < data.size(); i++) {
            result.add(new Rectangle((int)(i*width +16),
                                     (int)((data.get(i) / max) * RangeY +16),
                                     (int)((i+1)*width +16),
                                     (int)lowerY));
        }
        
        return result;
    }
    
    public void resetAll() {
        /* Clear all data and force a redraw of the graph */
        scores = null;
        disp = null;
    }
    
    public void setScores(float[] values) {
        /* Set the data to equal the float array passed
           and delete the graph image (so it is redrawn) */
        scores = values;
        disp = null;
        return;
    }

// -------------------------------------------------------------- //
    
    @Override
    public void repaint(){
        disp = null;
        super.repaint();
    }
    
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