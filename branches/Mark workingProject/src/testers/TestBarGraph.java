/*
TestBarGraph.java
This class is designed to 

Team Triple Threat
Log:
02/14/2008 Mark Lauman Sperated tests into functions, implemented tests 2-5
02/12/2008 Mark Lauman Created main, ran test 1
*/

package testers;

import buddyLibrary.BarGraph;
import java.awt.Dimension;
import javax.swing.JFrame;


public abstract class TestBarGraph {
    public static void main(String[] args) {
        /* run test on BarGraph
           check functions below for various tests */
        JFrame test = makeTest3();
        test.setVisible(true);
    }
    
    private static JFrame makeTest1() {
        //1st test - No scores, Not resizeable
        JFrame frame = new JFrame("Test 1: No Scores, Fixed");
        BarGraph graph1 = new BarGraph();
        graph1.setPreferredSize(new Dimension(100, 100));
        frame.getContentPane().add(graph1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        return frame;
    }
    
    private static JFrame makeTest2() {
        //2nd test - No scores, resizable
        JFrame frame = new JFrame("Test 2: No Scores, Resizable");
        BarGraph graph1 = new BarGraph();
        graph1.setPreferredSize(new Dimension(80, 80));
        frame.setContentPane(graph1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
    
    private static JFrame makeTest3() {
        //3rd test - 3 scores, not resizable (bars over 1 pxl wide)
        JFrame frame = new JFrame("Test 1: 3 Scores, Fixed");
        BarGraph graph1 = new BarGraph();
        graph1.setPreferredSize(new Dimension(100, 90));
        float scores[] = {5, 15, 22};
        graph1.setScores(scores);
        frame.setContentPane(graph1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        return frame;
    }
    
    private static JFrame makeTest4() {
        //4th test - 200 scores, not resizable (bars under pxl wide)
        JFrame frame = new JFrame("Test 1: 200 Scores, Fixed");
        BarGraph graph1 = new BarGraph();
        graph1.setPreferredSize(new Dimension(100, 90));
        float scores[] = new float[200];
        for(int i = 0; i < 200; i++) {
            scores[i] = i;
        }
        graph1.setScores(scores);
        frame.setContentPane(graph1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        return frame;
    }
    
    private static JFrame makeTest5() {
        //5th test - 200 scores, resizable (variable bars, render times)
        JFrame frame = new JFrame("Test 1: 200 Scores, Fixed");
        BarGraph graph1 = new BarGraph();
        graph1.setPreferredSize(new Dimension(100, 90));
        float scores[] = new float[200];
        for(int i = 0; i < 200; i++) {
            scores[i] = i;
        }
        graph1.setScores(scores);
        frame.setContentPane(graph1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        return frame;
    }
}
