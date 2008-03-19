/*
 * InfoPanel.java
 * 
 * Team Triple Threat
 * Log:
 * 03/18/2008 Mark Lauman Implemented class
 */

package game;

import buddyLibrary.SoundPlayer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import testers.ButtonListener;

/**
 * 
 * @author Team Triple Threat
 */
public class InfoPanel extends JPanel {
    private ArrayList<SoundPlayer> playButtons;
    private double time;
    private JLabel timeLabel;
    private JButton quit;
    private boolean passed;
    
    public InfoPanel(int level, ArrayList<String> soundFiles, ActionListener a) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.WHITE);
        
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        
        //Add the level title to the screen
        JLabel label = new JLabel("Level " + level);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setFont(new Font("SansSerif", Font.BOLD, 25));
        this.add(label);
        
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        
        //Add the "Time" title to the screen
        label = new JLabel("Time");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        this.add(label);
        
        //Add time to the screen
        timeLabel = new JLabel("00 : 00");
        timeLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        timeLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        this.add(timeLabel);
        
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        
        //Add the "Words" title to the screen
        label = new JLabel("Words");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        this.add(label);
        
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        
        //make the word buttons and place them to the screen
        playButtons = new ArrayList<SoundPlayer>(soundFiles.size());
        for(String file : soundFiles) {
            try {
                playButtons.add(new SoundPlayer(file));
            } catch(Exception e) {
                throw new Error("Problems opening sound file " + file);
            }
        }
        
        for(SoundPlayer s : playButtons) {
            s.setAlignmentX(SoundPlayer.CENTER_ALIGNMENT);
            this.add(s);
            this.add(Box.createRigidArea(new Dimension(0, 2)));
        }
        
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        
        quit = new JButton("Give Up");
        quit.setAlignmentX(JButton.CENTER_ALIGNMENT);
        quit.addActionListener(new ButtonListener());
        this.add(quit);
        
        time = 60*14 + 45;
        
        Timer t = new Timer(1000, new CounterListener());
        t.start();
        
        passed = false;
    }
    
    /**
     * If the user passed this level, this function returns <code>true</code>.
     * Othewise, this returns <code>false</code>
     * @return A <code>representing the state of the users game. If the user
     *         passed this level, this function returns <code>true</code>.
     *         Otherwise, this returns <code>false</code>
     */
    public boolean getPassed() {
        return passed;
    }
    
    /**
     * Returns the time that the the user has played in seconds.
     * @return An <code>int</code> representing the time that the the user has
     *         played in seconds.
     */
    public int getTime() {
        return (int) time;
    }
    
    private class CounterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            time++;
            String text = (int)(time / 60) + " : ";
            if ((int)(time / 60) < 10) {
                text = "0" + text;
            }
            if (time - 60*Math.floor(time / 60) < 10) {
                text += "0";
            }
            text += (int)(time - 60*Math.floor(time / 60)) + "";
            timeLabel.setText(text);
            if(Math.floor(time / 60) >= 15) {
                ActionListener a = quit.getActionListeners()[0];
                ((Timer)e.getSource()).stop();
            }
            repaint();
        }
    }
}
