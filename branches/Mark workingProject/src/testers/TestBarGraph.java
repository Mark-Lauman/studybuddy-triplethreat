/*
TestBarGraph.java
This class is designed to 

Team Triple Threat
Log:
02/16/2008 Mark Lauman Some minor formatting fixes
02/14/2008 Mark Lauman implemented tests 6-9, ran all tests on BarGraph
02/14/2008 Mark Lauman Sperated tests into functions, implemented tests 2-5
02/12/2008 Mark Lauman Created main, ran test 1
*/

package testers;

import buddyLibrary.BarGraph;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.JFrame;

public abstract class TestBarGraph {
    
    public static void main(String[] args) {
        /* run test on BarGraph
           check functions below for various tests */
        JFrame test = makeTest7();
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
        JFrame frame = new JFrame("Test 3: 3 Scores, Fixed");
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
        JFrame frame = new JFrame("Test 4: 200 Scores, Fixed");
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
        //5th test - 3 scores, resizable (variable bars, bar discontinutities)
        JFrame frame = new JFrame("Test 5: 3 Scores, Variable");
        BarGraph graph1 = new BarGraph();
        graph1.setPreferredSize(new Dimension(100, 90));
        float scores[] = {5, 15, 22};
        graph1.setScores(scores);
        frame.setContentPane(graph1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
    
    private static JFrame makeTest6() {
        //6th test - 200 scores, resizable (variable bars, render times)
        JFrame frame = new JFrame("Test 6: 200 Scores, Variable");
        BarGraph graph1 = new BarGraph();
        graph1.setPreferredSize(new Dimension(100, 90));
        float scores[] = new float[200];
        for(int i = 0; i < 200; i++) {
            scores[i] = i;
        }
        graph1.setScores(scores);
        frame.setContentPane(graph1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
    
    private static JFrame makeTest7() {
        //same as 6th test with Blue BG + White Fore
        JFrame frame = new JFrame("Test 7: 200 Scores, Variable, Coloured");
        BarGraph graph1 = new BarGraph();
        graph1.setPreferredSize(new Dimension(100, 90));
        graph1.setBackground(Color.BLUE);
        graph1.setForeground(Color.WHITE);
        float scores[] = new float[200];
        for(int i = 0; i < 200; i++) {
            scores[i] = i;
        }
        graph1.setScores(scores);
        frame.setContentPane(graph1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
    
    private static JFrame makeTest8() {
        //Percent Scores - largest value will be 75% - 3/4 total height
        //No score is at 100% - this relies on the max being resized correctly
        JFrame frame = new JFrame("Test 8: 200 Random Percent Scores, Variable");
        BarGraph graph1 = new BarGraph();
        graph1.setPreferredSize(new Dimension(100, 90));
        float scores[] = new float[200];
        Random rand = new Random();
        scores[0] = 0.75f;
        for(int i = 1; i < 200; i++) {
            scores[i] = rand.nextFloat()*0.75f;
        }
        graph1.setScores(scores);
        frame.setContentPane(graph1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
    
    private static JFrame makeTest9() {
        //9th test - 200 random scores, resizable (variable bars, render times)
        JFrame frame = new JFrame("Test 9: 200 Random Scores, Variable");
        BarGraph graph1 = new BarGraph();
        graph1.setPreferredSize(new Dimension(100, 90));
        float scores[] = new float[200];
        Random rand = new Random();
        for(int i = 0; i < 200; i++) {
            scores[i] = rand.nextInt();
        }
        graph1.setScores(scores);
        frame.setContentPane(graph1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
}
