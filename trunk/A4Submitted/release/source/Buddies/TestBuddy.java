package Buddies;

import buddyLibrary.*;
import coreScreens.*;
import core.*;
/*
Buddy.java
Brief File Description
Team Triple Threat
Log:
 02/20/2008  Allan Lei   Updated test cases
 02/14/2008  Allan Lei   Created unit test buddy
 */
import java.awt.Color;

public class TestBuddy extends Buddy{

    public TestBuddy() {
        setBackground(Color.BLUE);
        JarResource j = new JarResource(System.getProperty("user.dir") + "\\build\\classes\\Buddies\\test.jar");  //Initialize a jar resource
        j.extract("1.png", "C:\\Documents and Settings\\Knight\\Desktop\\");                    //extract a file from jar
        loadImage(j.getImage("1.png"), 90, 90);                     //load an image at a specific location from a jar file
        addAudioPlayer(j.getAudio("1.wav"), 50, 50);                  //create an audioplayer by accessing wav in jar file
        float[] q = getStatistics(j.getText("q.txt"));              //Access text file and print out the values stored
        System.out.println("Test scores:");
        for (int i = 0; i < q.length; i++) {
            System.out.println(q[i]);
        }
    }
}
