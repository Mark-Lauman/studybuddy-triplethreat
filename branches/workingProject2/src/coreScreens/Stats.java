/*
 * Stats.java
 * This class handles statistic of each buddy
 * 
 * Team Triple Threat
 * Log:
 * 02/23/2008 Vic Kao completed the class
 * 02/11/2008 Mark Lauman Created Template
 */

package coreScreens;

import buddyLibrary.BarGraph;
import buddyLibrary.Buddy;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import java.util.HashMap;

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
    
    /** the size of the JPanel */
    public final int StatsWidth = 400;
    public final int StatsHeight = 400;
    
    private JPanel menu = new JPanel(); 
    private BarGraph graph;
    private Buddy buddy;
    //variables used for the right hand side JPanel
    private JPanel rightScorePanel = new JPanel();
    private JLabel scores = new JLabel();
    private JLabel highScore = new JLabel();
    private JLabel minScore = new JLabel();
    private JLabel avgScore = new JLabel();
    /** the size of the score JPanel */
    public final int rightWidth = StatsWidth /2;
    public final int rightHeight = (int)(StatsHeight /4.5);
    
    //the place where stores the buddies's stats, which are stored in text file
    public final String buddyDirText = "./buddies" ;
    
    /**
    * Handles the event of the clicked JLabel when clicked
    */
    private void buddylabelSelected(MouseEvent arg0) {
        //get the file name of the buddy but the event source, get teh file name of the buddy
        String buddySelected = exportmap.get(arg0.getSource());

        //create a buddy , and call it's getStatis function with file name
        Buddy tempBuddy = new Buddy(); 

        //diplay the statistic
        float [] tempScore = tempBuddy.getStatistics(buddySelected);
               
        //set the scores of the graph
        graph.setScores(tempScore);
        //tell the graph to update itself, by calling it's repaint function
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
    
    public Stats(Buddy b) {
        super();
        //set the layout type to GridLayout
        setLayout(new GridLayout(1,2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Stats"));
        
        //add the selection panel
        menu.setLayout(new GridLayout(0,1, 10, 10));
        //create a panel on the left hand side
        JPanel leftPanel = new JPanel();
        
        leftPanel.add(menu, BorderLayout.NORTH); //makes menu display at the top postion
        
        add(leftPanel);
        setSize(StatsWidth,StatsHeight);    //set the size of leftPanel
        
        //create a panel on the right hand side
        JPanel rightPanel = new JPanel();       
       rightPanel.setLayout(new BorderLayout());    //set its layout to BorderLayout
       
       //add the score graph on the right hand panel(the upper part)
       graph = new BarGraph();
       rightPanel.add(graph,BorderLayout.CENTER);
     
       //add the scroe panel(the bottom part)
       rightPanel.add(rightScorePanel, BorderLayout.SOUTH);
       rightScorePanel.setSize(rightWidth,rightHeight);
       
       scores.setText("Scores:");   //set the text to "Scores:"
       
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
        
        rightScorePanel.setPreferredSize(new Dimension(rightWidth,rightHeight));
       
       
        //read the score data from a text file and display it
        File f = new File(buddyDirText);  //add all buddies to the list and create a directory      
        String[] fileList = f.list(); 
       
        for(int i = 0; i < fileList.length; i++) {
            if(fileList[i].indexOf(".") < 0) {//if the file doesn't have "."
               JLabel budyScreen = new JLabel(fileList[i]);
               budyScreen.addMouseListener(new MouseListener(){ 
			public void mouseClicked(MouseEvent arg0) {			
			}
 
			public void mousePressed(MouseEvent arg0) {
				buddylabelSelected(arg0);
			}
 
			public void mouseReleased(MouseEvent arg0) {				
			}
 
			public void mouseEntered(MouseEvent arg0) {				
			}
 
			public void mouseExited(MouseEvent arg0) {			
			}});
                        
                menu.add(budyScreen); //add the names to list                       
                exportmap.put(budyScreen, buddyDirText + "/" + fileList[i]);
           }
           else {
                JLabel budyScreen=new JLabel(fileList[i].substring(0, fileList[i].indexOf(".")));
                budyScreen.addMouseListener(new MouseListener(){ 
			public void mouseClicked(MouseEvent arg0) {			
			}
 
			public void mousePressed(MouseEvent arg0) {
				buddylabelSelected(arg0);
			}
 
			public void mouseReleased(MouseEvent arg0) {				
			}
 
			public void mouseEntered(MouseEvent arg0) {				
			}
 
			public void mouseExited(MouseEvent arg0) {			
			}});
                        
                menu.add(budyScreen); //add the names to list
                exportmap.put(budyScreen, buddyDirText + "/" + fileList[i]);
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
                        f.setSize(400, 600);
                        
                        
			f.setVisible(true);

    }
}