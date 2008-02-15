/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package selectionmenu;
import javax.swing.*;

/**
 *
 * @author Chen-Wei Kao
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFrame jf=new JFrame();
        
        
        
        jf.add(new TestMenu());
        
        jf.setVisible(true);
        jf.setSize(500, 500);
        
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
    }

}
