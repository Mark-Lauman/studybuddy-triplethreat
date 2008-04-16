/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        JOptionPane.showMessageDialog(b.getParent(),
                                      b.getText() + " was clicked!");
    }
}