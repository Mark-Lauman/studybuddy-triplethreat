/*
 * CodingStyleExample.java
 *
 * Team Triple Threat
 * Log:
 * 02/22/2008 Mark Lauman Created class & Documented it
 */

package testers;

import buddyLibrary.Buddy;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class was created for the sole purpose of providing a coding style
 * example. It is of no use in our project
 * <p>
 * Look! I made a new paragraph!
 * @author Team Triple Threat
 * @see <a href="../buddyLibrary/Buddy.html">buddyLibrary.Buddy</a>
 * @see <a href="http://java.sun.com/javase/6/docs/api/java/awt/Graphics.html">
 *      java.awt.Graphics</a>
 * @see <a href="http://java.sun.com/javase/6/docs/api/java/awt/event/ActionEvent.html">
 *      java.awt.event.ActionEvent</a>
 * @see <a href="http://java.sun.com/javase/6/docs/api/java/awt/event/ActionListener.html">
 *      java.awt.event.ActionListener</a>
 */
public class CodingStyleExample extends Buddy implements ActionListener {
    
    /** An example parameter */
    public boolean paramA;
    /** Another example parameter */
    private String parameterB;
    
    /**
     * Calls the constructor of <code>JFrame</code>
     */
    public CodingStyleExample(){
        super();
    }
    
    // -------------------------------------------------------------- //
    
    /**
     * Returns <code>parameterB</code>, so you can view it
     * @return The value of <code>parameterB</code>
     */
    public String getParameterB(){
        return parameterB;
    }
    
    /**
     * Set <code>parameterB</code> to equal <code>s</code>
     * @param s The value you wish to assign to <code>parameterB</code>.
     *          This shows what you should do if you run out of space
     */
    public void setParameterB(String s){
        parameterB = s;
    }
    
    /**
     * Replaces the current <code>parameterB</code> with <code>s</code> and
     * returns the current <code>parameterB</code>
     * @param s The new value for <code>parameterB</code>
     * @param i A dummy value, so you can see how order affects param order.
     * @return  The current value of <code>parameterB</code>
     */
    public String switchParameterB(String s, int i){
        String temp = parameterB; //I can comment as I wish in here!
        parameterB = s; //Wheee!!!
        return temp;
    }
    
    // -------------------------------------------------------------- //
    
    /**
     * This function does nothing that <code>JFrame</code>'s
     * <code>paint()</code> doesn't do
     * @param g A <code>Graphics</code> object pointing to this object
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
    
    /**
     * This function does nothing at present
     * @param e An <code>ActionEvent</code> to be handled
     */
    public void actionPerformed(ActionEvent e){
        
    }
}
