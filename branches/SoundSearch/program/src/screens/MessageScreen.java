/*
 * MessageScreen.java
 * 
 * Team Triple Threat
 * Log:
 * 03/15/2008 Mark Lauman Added set and get methods
 * 03/14/2008 Mark Lauman Created class constructor and tweaked positioning
 */

package screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Team Triple Threat
 */
public class MessageScreen extends JPanel {
    private JLabel message;
    private JLabel timeTitle;
    private JLabel time;
    private JButton next;
    
    public MessageScreen(ActionListener a) {
        super();
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.add(Box.createRigidArea(new Dimension(0,190)));
        
        message = new JLabel("SoundSearch");
        message.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        message.setFont(new Font("SansSerif", Font.BOLD, 70));
        this.add(message);
        
        this.add(Box.createRigidArea(new Dimension(0,10)));
        
        timeTitle = new JLabel("Time");
        timeTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        timeTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        timeTitle.setVisible(false);
        this.add(timeTitle);
        
        time = new JLabel("00 : 00");
        time.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        time.setFont(new Font("SansSerif", Font.PLAIN, 12));
        time.setVisible(false);
        this.add(time);
        
        this.add(Box.createRigidArea(new Dimension(0,40)));
        
        next = new JButton("Start A Puzzle!");
        next.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        next.setMaximumSize(new Dimension(150, 20));
        next.addActionListener(a);
        this.add(next);
        
        this.add(Box.createRigidArea(new Dimension(0,5)));
    }
    
    // ---------------------------------------------------------------------- //
    
    public void setMessage(String s) {
        message.setText(s);
        next.setText("One More Round...");
    }
    
    public void setTime(float seconds) {
        double minutes = Math.floor(seconds / 60);
        seconds -= 60 * minutes;
        time.setText((int)minutes + " : " + (int)seconds);
        time.setVisible(true);
        timeTitle.setVisible(true);
    }
    
    public void setTimeVisible(boolean b) {
        time.setVisible(b);
        timeTitle.setVisible(b);
    }
}
