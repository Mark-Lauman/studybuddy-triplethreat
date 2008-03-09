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
import core.*;

public class UserChoice extends JPanel implements ActionListener {

    public UserChoice(ActionListener c) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("User Choice"));
        setPreferredSize(new Dimension(300, 300));
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        
        
        JButton studybuddy = new JButton("Study Buddy");
        studybuddy.setPreferredSize(new Dimension(this.getPreferredSize().width, 20));
        studybuddy.setActionCommand("Study Buddy");
        studybuddy.addActionListener(this);
        studybuddy.addActionListener(c);
        content.add(studybuddy);
        
        JButton stats = new JButton("Statistics");
        stats.setPreferredSize(new Dimension(this.getPreferredSize().width, 20));
        stats.setActionCommand("Statistics");
        stats.addActionListener(this);
        stats.addActionListener(c);
        content.add(stats);
        add(content, BorderLayout.CENTER);
    }

// -------------------------------------------------------------- //
    
// -------------------------------------------------------------- //

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Statistics")){
        }else if(e.getActionCommand().equals("Study Buddy")){
        }
    }
}