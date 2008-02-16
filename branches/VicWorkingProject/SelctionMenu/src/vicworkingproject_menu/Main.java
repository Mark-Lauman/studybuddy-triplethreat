/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vicworkingproject_menu;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        
        //testing codes:
        JFrame jf=new JFrame();
        jf.add(new SelectionMenu());        
        jf.setVisible(true);
        jf.setSize(700, 450);        
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
