package Buddies;

import buddyLibrary.*;
import coreScreens.*;

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
        //setReference(c);                    // Sets the Core as parent
        setBackground(Color.BLUE);
        //System.out.println(System.getProperty("user.dir") + "1.jar");
        //JarResource j = new JarResource(System.getProperty("user.dir") + "\\build\\classes\\Data\\TestBuddy.jar");  //Initialize a jar resource
        //j.extract("1.png", "C:\\Documents and Settings\\Knight\\Desktop\\");                    //extract a file from jar
        //loadImage(j.getImage("1.png"), 90, 90);                     //load an image at a specific location from a jar file
        //addAudioPlayer(j.getWav("1.wav"), 50, 50);                  //create an audioplayer by accessing wav in jar file
        //float[] q = getStatistics(j.getText("q.txt"));              //Access text file and print out the values stored
        //for (int i = 0; i < q.length; i++) {
            //System.out.println(q[i]);
        //}
        //System.out.println(getUser());                              //Print the user name stored in core
        //setTitle("Changed");                                        //change the title of Core
        
        
        SelectionMenu s = new SelectionMenu(400, 600);            //Create a selection menu and populate
        s.addSubButton("Button1", 0);
        s.addSubButton("", 50);
        s.addSubButton("Button2", 0);
        s.addSubButton("Button3", 0);
        s.addSubButton("Button2", 0);
        s.addSubButton("Button3", 0);

        s.addChoice("asdasda");
        s.addChoice("as");
        s.addChoice("qwras");
        s.setMainButton("Main button");
        
        //add(s);
        s.removeFromButtonHolder(0);
        this.setPosition(s, 0, 0);
        
        BuddySelection us = new BuddySelection(300, 400);
        add(us);
    }

    
    public void a(){
        System.out.println("asd");
    }
}
