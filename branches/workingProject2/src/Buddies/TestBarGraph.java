/*
TestBarGraph.java

Team Triple Threat
Log:
02/24/2008 Mark Lauman Changed the comments & made all methods public
02/16/2008 Mark Lauman Some minor formatting fixes
02/14/2008 Mark Lauman implemented tests 6-9, ran all tests on BarGraph
02/14/2008 Mark Lauman Sperated tests into functions, implemented tests 2-5
02/12/2008 Mark Lauman Created main, ran test 1
*/

package Buddies;

import buddyLibrary.BarGraph;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.JFrame;

public abstract class TestBarGraph {
    
    /**
     * Run a test on BarGraph. The test run is determined by the second line in
     * the code. Change the number there to change the test.
     * @param args arguments passed on command line. These are ignored.
     */
    public static void main(String[] args) {
        JFrame test = makeTest7();
        test.setVisible(true);
    }
    
    /**
     * The first test, a <code>BarGraph</code> without any <code>score</code>s
     * in a non-resizable window
     * @return A <code>JFrame</code> containing the first test, ready to be set
     *         visible
     */
    public static JFrame makeTest1() {
        JFrame frame = new JFrame("Test 1: No Scores, Fixed");
        BarGraph graph1 = new BarGraph();
        graph1.setPreferredSize(new Dimension(100, 100));
        frame.getContentPane().add(graph1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        return frame;
    }
    
    /**
     * The second test, a <code>BarGraph</code> without any <code>score</code>s
     * in a resizable window
     * @return A <code>JFrame</code> containing the second test, ready to be set
     * visible
     */
    public static JFrame makeTest2() {
        JFrame frame = new JFrame("Test 2: No Scores, Resizable");
        BarGraph graph1 = new BarGraph();
        graph1.setPreferredSize(new Dimension(80, 80));
        frame.setContentPane(graph1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
    
    /**
     * The third test, a <code>BarGraph</code> with 3 <code>score<code>s in a
     * non-resizable window. This forces the bars to be more than 1 pixel wide
     * @return A <code>JFrame</code> containing the third test, ready to be set
     *         visible
     */
    public static JFrame makeTest3() {
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
    
    /**
     * The fourth test, a <code>BarGraph</code> with 200 <code>score</code>s in
     * ascending order in a non-resizable window. This makes the data less than
     * 1 pixel wide, and ensures the average functions are working correctly
     * @return A <code>JFrame</code> containing the fourth test, ready to be set
     *         visible
     */
    public static JFrame makeTest4() {
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
    
    /**
     * The fifth test, a <code>BarGraph</code> with 3 <code>score</code>s in a
     * resizable window. This allows your to see if there are any problems at
     * any bar size
     * @return A <code>JFrame</code> containing the fifth test, ready to be set
     * visible
     */
    public static JFrame makeTest5() {
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
    
    /**
     * The sixth test, a <code>BarGraph</code> with 200 <code>score</code>s in
     * ascending order in a resizable window. This allows your to see if there
     * are any problems at any bar size, and also allows you to see how long it
     * takes to re-render the graph
     * @return A <code>JFrame</code> containing the sixth test, ready to be set
     *         visible
     */
    public static JFrame makeTest6() {
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
    
    /**
     * The seventh test, a <code>BarGraph</code> identical to the sixth test,
     * except the background and foreground colours have been changed. This
     * allows you to verify that those functions work correctly
     * @return A <code>JFrame</code> containing the seventh test, ready to be
     *         set visible
     */
    public static JFrame makeTest7() {
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
    
    /**
     * The eighth test, a <code>BarGraph</code> with 200 random percent
     * <code>score</code>s in a resizable window. The largest value is 75% (or
     * 0.75) which means if the graph has resized for percentages, the
     * highest value will take up 3/4 of the y axis
     * @return A <code>JFrame</code> containing the eighth test, ready to be set
     *         visible
     */
    public static JFrame makeTest8() {
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
    
    /**
     * The ninth test, a <code>BarGraph</code> with 200 random 
     * <code>score</code>s (both negative and positive) in a resizable window.
     * This allows you to see if the negative values are being graphed
     * appropriately
     * @return A <code>JFrame</code> containing the ninth test, ready to be set
     *         visible
     */
    public static JFrame makeTest9() {
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
