/*
Buddy.java
Brief File Description
Team Triple Threat
Log:
02/14/2008  Allan Lei   Created unit test buddy
 */

public class TestBuddy extends Buddy{
    
    public TestBuddy(Core c) {
        setReference(c);
        
        loadImage(new JarResource("C:\\Documents and Settings\\Knight\\Desktop\\1.jar").getImage("1.png"), 50,50);
        addAudioPlayer(new JarResource("C:\\Documents and Settings\\Knight\\Desktop\\1.jar").getWav("1.wav"), 50, 50);     
        float[] q = getStatistics(new JarResource("C:\\Documents and Settings\\Knight\\Desktop\\1.jar").getText("q.txt"));
        for(int i = 0; i < q.length;i++)
        System.out.println(q[i]);
        System.out.println(getUser());
        setTitle("Changed");
        
        
    }
}
