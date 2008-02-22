/*
UserChoice.java
Brief File Description

Team Triple Threat
Log:
02/11/2008 Mark Lauman Created Template
*/

package coreScreens;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UserChoice extends JPanel implements ActionListener {

    public UserChoice() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("User Choice"));
        setPreferredSize(new Dimension(300, 300));
        JButton studybuddy = new JButton("Study Buddy");
        studybuddy.setActionCommand("Study Buddy");
        studybuddy.addActionListener(this);
        add(studybuddy, BorderLayout.NORTH);
        
        JButton stats = new JButton("Statistics");
        stats.setActionCommand("Statistics");
        stats.addActionListener(this);
        add(stats, BorderLayout.SOUTH);
    }

// -------------------------------------------------------------- //
    
// -------------------------------------------------------------- //

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Statistics")){
            System.out.println("asd");
        }else if(e.getActionCommand().equals("Study Buddy")){
            System.out.println("qwe");
        }
    }
}