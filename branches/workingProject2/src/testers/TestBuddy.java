package testers;

import buddyLibrary.*;
import core.Core;

/*
Buddy.java
Brief File Description
Team Triple Threat
Log:
 02/20/2008  Allan Lei   Updated test cases
 02/14/2008  Allan Lei   Created unit test buddy
 */
public class TestBuddy extends Buddy {

    public TestBuddy(Core c) {
        setReference(c);                    // Sets the Core as parent
        
        JarResource j = new JarResource("C:\\Documents and Settings\\Knight\\Desktop\\1.jar");  //Initialize a jar resource
        j.extract("1.png", "C:\\Documents and Settings\\Knight\\Desktop\\");                    //extract a file from jar
        loadImage(j.getImage("1.png"), 90, 90);                     //load an image at a specific location from a jar file
        //addAudioPlayer(, 50, 50);                  //create an audioplayer by accessing wav in jar file
        float[] q = getStatistics(j.getText("q.txt"));              //Access text file and print out the values stored
        for (int i = 0; i < q.length; i++) {
            System.out.println(q[i]);
        }
        System.out.println(getUser());                              //Print the user name stored in core
        setTitle("Changed");                                        //change the title of Core
        
        
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
        
        add(s);
        s.removeFromButtonHolder(0);
        this.setPosition(s, 0, 0);



    }
}
