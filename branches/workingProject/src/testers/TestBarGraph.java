/*
TestBarGraph.java
This class is designed to 

Team Triple Threat
Log:
02/12/2008 Mark Lauman Created main, ran test 1
*/

package testers;

import buddyLibrary.BarGraph;
import java.awt.Dimension;
import javax.swing.JFrame;


public abstract class TestBarGraph {
    public static void main(String[] args) {
        /* testing method */
        
        //1st test - No data
        JFrame test1 = new JFrame("Test 1: No Data");
        BarGraph graph1 = new BarGraph();
        graph1.setPreferredSize(new Dimension(100, 100));
        test1.getContentPane().add(graph1);
        test1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test1.setResizable(false);
        test1.setVisible(true);
        test1.pack();
    }
}
