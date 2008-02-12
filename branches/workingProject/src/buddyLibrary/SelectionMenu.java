/*
SelectionMenu.java
Brief File Description

Team Triple Threat
Log:
02/11/2008 Mark Lauman Created Template
*/

package buddyLibrary;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

public class SelectionMenu extends JPanel {
    
    private JButton mainButton; //
    private JButton subButtons; //
    private JList choices; //
    public static final int CENTER = 1; //
    public static final int LEFT = 0; //
    public static final int RIGHT = 2; //

    public SelectionMenu() {
        /*  */
    }

    public String[] getChoices() {
        /*  */
        return null;
    }

// -------------------------------------------------------------- //
    
    public void addChoice(String choice) {
        /*  */
    }

    public void addChoice(String[] choice) {
        /*  */
    }

    public String getSelection() {
        /*  */
        return null;
    }
    
    public void removeAllChoices() {
        /*  */
    }
    
    public void removeChoice(String choice) {
        /*  */
    }

    public void setMainButton(String name) {
        /*  */
    }

    public void setSubButton(String name, int alignment) {
        /*  */
    }

// -------------------------------------------------------------- //

    @Override
    public void paint(Graphics g) {
        /*  */
    }
}