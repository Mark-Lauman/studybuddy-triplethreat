package Buddies;

import buddyLibrary.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;


public class testframe{

    public static void main(String args[]){
        JFrame frame = new JFrame("Test");
        Container content = frame.getContentPane();
        Buddy b = new CircuitBuddy();
        content.add(b);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
}
