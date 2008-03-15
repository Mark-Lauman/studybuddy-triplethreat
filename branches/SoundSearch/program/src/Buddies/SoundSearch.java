/*
 * SoundSearch.java
 * 
 * Team Triple Threat
 * Log:
 * 03/15/2008 Mark Lauman Rewrote class to use new architecture
 * 03/12/2008 Mark Lauman Wrote initial class
 */

package Buddies;

import buddyLibrary.Buddy;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import screens.MessageScreen;

public class SoundSearch extends Buddy implements ActionListener {
    private MessageScreen ms;
    
    public SoundSearch() {
        super();
        this.setTitle("SoundSearch");
        this.setLayout(new GridLayout(0,1));
        this.setPreferredSize(new Dimension(800, 600));
        
        ms = new MessageScreen(this);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        
    }
}
