/*
BarGraph.java
This class displays the data inside it as a bar graph

Team Triple Threat
Log:
02/12/2008 Mark Lauman 
02/11/2008 Mark Lauman Created Template
*/

package buddyLibrary;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class BarGraph extends JPanel {
    
    private float[] scores; //This is the data to be graphed
    private BufferedImage disp; //This image contains the graph

    public BarGraph() {
        /* Set up the BarGraph class */
        super();
        disp = null;
        scores = null;
    }
    
// -------------------------------------------------------------- //

    private void render(Graphics g) {
        /* Render the graph */
        System.out.print(this.getHeight() + " " + this.getWidth());
        Graphics2D brush = (Graphics2D) g;
        /* create a BufferedImage that will work well with the screen
         * The image will be the same size as this frame (Hence the calls to
         *  getWidth() and getHeight()) */
        disp = brush.getDeviceConfiguration().createCompatibleImage(
                        getWidth(), getHeight());
        //Make a brush that will paint to this image (not the screen!)
        brush = (Graphics2D) disp.getGraphics();
        
        if(scores == null) {
            //If there are no scores to display, just say there is nothing to
            // display
            brush.drawString("No Data Available",
                    (this.getWidth()/2), (this.getHeight()/2));
        }
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
    public void paint(Graphics g) {
        /* repaint the graph */
        if(disp == null) {
            System.out.println(this.getHeight() + " " + this.getWidth());
            render(g);
        }
        g.drawImage(disp, 0, 0, null);
    }
}