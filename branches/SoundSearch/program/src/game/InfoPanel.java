/*
 * InfoPanel.java
 * 
 * Team Triple Threat
 * Log:
 * 04/01/2008 Mark Lauman Wrote both endGames, getWinState and QuitListener
 * 03/30/2008 Mark Lauman Wrote setButtonEnabled
 * 03/25/2008 Mark Lauman Fixed max timer event
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
 * An <code>InfoPanel</code> is a panel displaying the word list and counter
 * for the current game. It also contains the "Give Up" button, which allows the
 * user to surrender the match.
 * @author Team Triple Threat
 */
public class InfoPanel extends JPanel {
    private ArrayList<SoundPlayer> playButtons;
    private double time;
    private JLabel timeLabel;
    private Timer  timer;
    private JButton quit;
    private boolean win;
    
    /**
     * Constructs an <code>InfoPanel</code> using the passed values. The panel's
     * timer starts at 0:00, and counts up until 15:00. When 15:00 is reached,
     * the quit event is called
     * @param level      The number of the user's current level. This is
     *                   displayed at the top of the panel
     * @param soundFiles An <code>ArrayList</code> of <code>String</code>s
     *                   that are the locations of all the sound files for the
     *                   words. These will be attached to the sound buttons.
     * @param a          The <code>ActionListener</code> that will respond to
     *                   the quit event. The event is a click on the quit
     *                   <code>JButton</code>, even if the game is ending
     *                   because the time is now 15:00
     */
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
        //Add quit's two listeners. QuitListener is called first on a click,
        //then a.
        quit.addActionListener(a);
        quit.addActionListener(new QuitListener());
        this.add(quit);
        
        win = false;
        time = 0;
//        time = 60*14 + 45;
        
        timer = new Timer(1000, new CounterListener());
        timer.start();
    }
    
    /**
     * Ends the current Game. This will always result in a lose state.
     */
    public void endGame() {
        endGame(false);
    }
    
    /**
     * Ends the current Game. Game state will be determined by the
     * passed <code>boolean</code>, which is true if the game is won.
     * @param win Win should be true if and only if the game is won.
     *            If the game is lost, win should be false.
     */
    public void endGame(boolean win) {
        this.win = win;
        quit.doClick();
    }
    
    /**
     * Returns the time that the the user has played in seconds.
     * @return An <code>int</code> representing the time that the the user has
     *         played in seconds.
     */
    public int getTime() {
        return (int) time;
    }
    
    /**
     * If the user passed this level, this function returns <code>true</code>.
     * Othewise, this returns <code>false</code>
     * @return A <code>boolean</code> representing the state of the users game.
     *         If the user passed this level, this function returns
     *         <code>true</code>. Otherwise, this returns <code>false</code>
     */
    public boolean getWinState() {
        return win;
    }
    
    /**
     * Sets a the specified button to be enabled or disabled as specified by
     * <code>enabled</code>
     * @param button  The index of the button to enable/disable
     * @param visible The new state of the button. <code>true</code> to enable
     *                the button, and <code>false</code> to disable it
     */
    public void setButtonEnabled(int button, boolean enabled) {
        playButtons.get(button).setEnabled(enabled);
    }
    
    /**
     * This is the ActionListener used by the clock. It increments the timer,
     * and triggers the quit event if needed.
     */
    private class CounterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //Increment the timer
            time++;
            
            //Change the display so it shows the current time
            String text = (int)(time / 60) + " : ";
            if ((int)(time / 60) < 10) {
                text = "0" + text;
            }
            if (time - 60*Math.floor(time / 60) < 10) {
                text += "0";
            }
            text += (int)(time - 60*Math.floor(time / 60)) + "";
            timeLabel.setText(text);
            
            //If it is time to quit
            if(Math.floor(time / 60) >= 15) {
                endGame();
            }
            repaint();
        }
    }
    
    /**
     * This listener is attached to the quit button so that the timer stops
     * correctly.
     */
    private class QuitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            System.out.println("Win=" + win);
            timer.stop();
        }
    }
}
