/*
 * Stats.java
 * This class handles statistic of each buddy
 * 
 * Team Triple Threat
 * Log:
 * 02/25/2008 Vic Kao added the border and revised some comments
 * 02/23/2008 Vic Kao completed the class
 * 02/11/2008 Mark Lauman Created Template
 */

package coreScreens;

import buddyLibrary.BarGraph;
import buddyLibrary.Buddy;
import buddyLibrary.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

/**
 * This class handles the statistic of each body and displays it with a 
 * bargraph and displays the maximum, minimum and average scores.
 * @author Team Triple Threat
 * @see <a href="http://java.sun.com/javase/6/docs/api/javax/swing/JPanel.html">
 *      javax.swing.JPanel</a>
 */
public class Stats extends Buddy implements ActionListener {
    
    //HashMap to keep track of the list
    HashMap<JLabel,String> exportmap= new HashMap<JLabel,String>();
    
    /** the width of the JPanel */
    public final int StatsWidth = 400;
    /** the height of the JPanel */
    public final int StatsHeight = 400;
    /** the JPanel menu for the left hand side */
    private JPanel menu = new JPanel(); 
    /** the graph object to be displayed */
    private BarGraph graph;
    //private Buddy buddy;
    /** the JPanel for the right hand side(bottom part) */
    private JPanel rightScorePanel = new JPanel();
    /** the JLabel "Scores" used for the score JPanel */
    private JLabel scores = new JLabel();
    /** the JLabel "High Score" used for the score JPanel */
    private JLabel highScore = new JLabel();
    /** the JLabel "Lowest Score" used for the score JPanel */
    private JLabel minScore = new JLabel();
    /** the JLabel "Average Score" used for the score JPanel */
    private JLabel avgScore = new JLabel();
    /** the width of the score JPanel */
    public final int rightWidth = StatsWidth /2;
    /** the height of the score JPanel */
    public final int rightHeight = (int)(StatsHeight /4.5);    
    /** the place where stores the buddies's stats, which are stored in text file */
    public final String buddyDirText = "./buddies" ;
    
  /**
    * Handles the event of a JLabel on the list when clicked
    */
    private void buddyLabelSelected(MouseEvent arg0) {
        //get the file name of the buddy but the event source, get teh file name of the buddy
        String buddySelected = exportmap.get(arg0.getSource());

        //create a buddy , and call it's getStatis function with file name
        Buddy tempBuddy = new Buddy(); 

        //diplay the statistic
        float [] tempScore = tempBuddy.getStatistics(buddySelected);
               
        //set the scores of the graph
        graph.setScores(tempScore);
        //tell the graph to update itself, by calling its repaint function
        graph.repaint();
        
        //for loop to find the maximun, minimun, and average score
        float min = tempScore[0];
        float max = tempScore[0];
        float total = 0;
        float avg;
                
        for(int i = 0; i < tempScore.length; i++) {
            if(tempScore[i] < min)
                min = tempScore[i];
            if(tempScore[i] > max)
                max = tempScore[i];
           total = tempScore[i] + total;
        }
                
       avg = total / tempScore.length;

       //displays the score information with its integer part
       highScore.setText("Highest Score: " + (int)max);
       minScore.setText("Lowest Score: " + (int) min);
       avgScore.setText("Average Score: " + (int) avg);                
    }
    
   /**
    * Constructs a <code>Stats</code> window, and displays the name of each buddy,
    * It will load the statistic of the selected buddy and display as a bar graph,
    * and the statistic of the user will be shown as well.
    */    
    public Stats(Buddy b) {
        super();
        //set the layout type to GridLayout
        setLayout(new GridLayout(1,2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Statistics"));
        
        //add the selection panel
        menu.setLayout(new GridLayout(0,1, 10, 10));
        //create a JPanel on the left hand side
        JPanel leftPanel = new JPanel();
        //makes menu display at the top postion
        leftPanel.add(menu, BorderLayout.NORTH);         
        add(leftPanel);
        //set the size of leftPanel
        setSize(StatsWidth, StatsHeight);    
        //set the border to black
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        //create a panel on the right hand side
        JPanel rightPanel = new JPanel();       
        rightPanel.setLayout(new BorderLayout());    //set its layout to BorderLayout
       
        //add the score graph on the right hand panel(the upper part)
        graph = new BarGraph();
        rightPanel.add(graph,BorderLayout.CENTER);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        //add the scroe panel(the bottom part)
        rightPanel.add(rightScorePanel, BorderLayout.SOUTH);
        rightScorePanel.setSize(rightWidth, rightHeight);
       
        //set the text, "Scores:"
        scores.setText("Scores:");   
       
        //set the score panel to BorderLayout type
        rightScorePanel.setLayout(new BorderLayout()); 
        rightScorePanel.add(scores, BorderLayout.NORTH);
        
        JPanel scoreButtom = new JPanel();
        
        //set the buttom layout
        scoreButtom.setLayout(new GridLayout(0,1, 10, 5));
        scoreButtom.add(highScore);
        scoreButtom.add(minScore);
        scoreButtom.add(avgScore);
        
        rightScorePanel.add(scoreButtom, BorderLayout.SOUTH);        
        rightScorePanel.setPreferredSize(new Dimension(rightWidth, rightHeight));
       
        //read the score data from a text file and display it
        File f = new File(buddyDirText);  //add all buddies to the list and create a directory      
        String[] fileList = f.list(); 
       
        for(int i = 0; i < fileList.length; i++) {
            //if the file doesn't have "."
            if(fileList[i].indexOf(".") < 0) {
                JLabel buddyScreen = new JLabel(fileList[i]);
                //set the border to black
                buddyScreen.setBorder(BorderFactory.createLineBorder(Color.black));
                //set the font size to 20
                Font aaaFont = buddyScreen.getFont();
                Font font5 = aaaFont.deriveFont(20.0f);
                buddyScreen.setFont(font5);
               
                buddyScreen.addMouseListener(new MouseListener(){ 
			public void mouseClicked(MouseEvent arg0) {			
			}
 
			public void mousePressed(MouseEvent arg0) {
				buddyLabelSelected(arg0);
			}
 
			public void mouseReleased(MouseEvent arg0) {				
			}
 
			public void mouseEntered(MouseEvent arg0) {				
			}
 
			public void mouseExited(MouseEvent arg0) {			
			}});
                        
                menu.add(buddyScreen); //add the names to list                       
                exportmap.put(buddyScreen, buddyDirText + "/" + fileList[i]);
           }
           else {
                JLabel buddyScreen = new JLabel(fileList[i].substring(0, fileList[i].indexOf(".")));
                //set the border to black
                buddyScreen.setBorder( BorderFactory.createLineBorder(Color.black) );
                //set the font size to 20.0
                Font aaaFont = buddyScreen.getFont();
                Font font3 = aaaFont.deriveFont(20.0f);
                buddyScreen.setFont(font3);
                
                buddyScreen.addMouseListener(new MouseListener(){ 
			public void mouseClicked(MouseEvent arg0) {			
			}
 
			public void mousePressed(MouseEvent arg0) {
				buddyLabelSelected(arg0);
			}
 
			public void mouseReleased(MouseEvent arg0) {				
			}
 
			public void mouseEntered(MouseEvent arg0) {				
			}
 
			public void mouseExited(MouseEvent arg0) {			
			}});
                        
                menu.add(buddyScreen); //add the names to list
                exportmap.put(buddyScreen, buddyDirText + "/" + fileList[i]);
           }           
       }
        
       add(rightPanel); //add the right hand side panel
    }
    
    

// -------------------------------------------------------------- //
    
// -------------------------------------------------------------- //
    
    @Override
    //public void paint(Graphics g) {
        /*  */
 //   }
    
    public void actionPerformed(ActionEvent e) {
        /*  */
    }
    
    //amin function to test
    public static void main(String []args)
    {
       
			//set display tip text
                        JFrame f=new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        
                        
                        JPanel p=new JPanel();
                        p.setSize(400,600);
                        p.setBackground(Color.red);
                        
                        
                        
                        Stats s=new Stats(null);

                        
			f.add(s);
                        f.setSize(600, 600);
                        
                        
			f.setVisible(true);

    }
}