  /*
 * MathFunbuddy.java
 * 
 * Team Triple Threat
 * Log:
 * 03/17/2008 Vic Kao updated the database for qustions and pictures needed
 * 03/16/2008 Vic Kao implemented the structure
 */

package Buddies;

import buddyLibrary.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This Math buddy has a database of math questions related to daily life 
 * to help Grade 6 students learn and improve their math skills.
 * @author Team Triple Threat
 * @see <a href="http://java.sun.com/javase/6/docs/api/javax/swing/JPanel.html">
 *      javax.swing.JPanel</a>
 */
public class MathFunBuddy extends Buddy implements ActionListener {
    
    //variables used for the starting screen
    /** the JPanel for welcome screen showing the introduction */
    private JPanel startPanel;
    /** the JButton - "START" */
    private JButton startButton;
    /** introduction on the welcome screen */
    String introText = "Welcome to this fun MATH world!";
    /** the width for the START button */
    int startButtonWidth = 100;
    /** the height for the START button */
    int startButtonHeight = 50;
    
    //variables used for the question screen
    /** the JButtons for displaying the choices */
    private JButton[] choiceButtons= new JButton[3];    
    /** the JPanel for question */
    private JPanel questionPanel;
    /** the JLabel for displaying the question */
    private JLabel question;
    /** the JLabel for displaying the current score */
    private JLabel score;
    /** the label for displaying description of the question */
    private JLabel questionText;
    //private JButton AnswerButton;
    /** the width for questionText */
    int questionTextWidth = 100;
    /** the width for questionText */
    int questionTextHight = 100;
    
   
    /** the JButtons - "Next" to go to the next question */
    private JButton nextButton;
    /** the JLabel for displaying whether the user got it correct or wrong */
    private JLabel correctOrWrongLabel;
    /** the variable to keep record the score */
    private int scoreGained;
    /** the ArrayList for storing the question names */
    private ArrayList<String> QuestionNames;
    /** the variable acting as a pointer, pointing the current question */
    private int playgameIndex;

    
   /**
     * Constructs a <code>MathFunBuddy</code> buddy, and loads the welcome screen
     * and question screen.
     */
    public MathFunBuddy (){
        createStartPanel();
        add(startPanel);                
        
        createQuestionPanel();
    }
    
    /**
     * Handles the event of a JButtom "START" when clicked
     */
    private void startButtonClicked()
    {
        //remove the startpanel 
        remove(startPanel);
        //and then add the questionPanel
        add(questionPanel);
        
        this.repaint();
        validate();
        
        //startGame();
    }
    
    /**
     * Starts the game
     */
    private void startGame()
    {
        //create QuestionNames
        
        //open the file for a list of question names
        //while it is not the end of file
            //get a line from the file
            //add it to the QuestionNames
        
        //record that it is play the first game
        playgameIndex=0;
        
        //initialize score
        scoreGained=0;
        
        playGame(playgameIndex);
    }
    
    /**
     * Play the game by passing the gameIndex, the current question
     * @param gameIndex
     */
    private void playGame(int gameIndex)
    {
        //get the game name with the game index
        //open the "question.txt" in the dirctor of the game
        
        //for i = 0 to 2
        //create the image icon with img0.png
        //set choiceButtons[i]'s icon to the icon created
        
        //create the image icon with img0alternative.png
        //set choiceButtons[1]'s rollover icon to the icon created
        
        //get the a line from the text file
        //set the choiceButtons[0]'s text 
                
                
        //get a line from the .txt file as instruction
        
        //get a integer as the index value of the answer
        //record the button to the answer button
        //AnswerButton=choiceButtons[];
        
        
        
    }
    
    /** create the start panel
     * 
     */
    private void createStartPanel()
    {
        //create the startPanel
        startPanel = new JPanel();
        //create a JLabel, and set the introduction text to the label
        JLabel introJLabel = new JLabel(introText);
        
        //make the startPanel into two parts: upper and buttom panels
        //upper panel for displaying the introduction
        JPanel upperPanel = new JPanel();
        //buttom panel for displaying the "START" buttom
        JPanel buttomPanel = new JPanel();
        
        //set the sizze of starting panel
        startPanel.setSize(700, 700);        
        //set the layout to BorderLayout
        startPanel.setLayout(new BorderLayout());

        
        
        //add the label to the upper panel
        upperPanel.add(introJLabel);
//set the size of the upper panel
//upperPanel.setPreferredSize(new Dimension(100, 40));
upperPanel.setBackground(Color.WHITE);        
        

        //set the size of the buttom panel
        buttomPanel.setPreferredSize(new Dimension(100, 100));
          
        
        //create the button, "START"
        startButton = new JButton("Start");
        //set the preferred size of the button
        startButton.setPreferredSize((new Dimension(startButtonWidth, startButtonHeight)));
        //set the event listenter to this 
        startButton.addActionListener(this);
        
        
        //add the button to the buttom panel
        buttomPanel.add(startButton);
        
        
        //add the upperPanel and buttomPanel to the main panel (startPanel)
        startPanel.add(upperPanel,BorderLayout.CENTER);
        startPanel.add(buttomPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Creates the question panel
     */
    private void createQuestionPanel()
    {   questionPanel=new JPanel();//points to a new JPanel
        questionPanel.setLayout(new BorderLayout());
//set the background color
questionPanel.setBackground(Color.GREEN);
        //set the size of question panel
        questionPanel.setSize(700,660);
        
        //crate the upper panel
        JPanel upperPanel = new JPanel();
        //set it's layout to bordered
        upperPanel.setLayout(new BorderLayout());
        //create the question label
        question = new JLabel("Question 1");
        //set the preferred size
        question.setPreferredSize(new Dimension(100, 50));
        //add it to the west of upper panel
        upperPanel.add(question, BorderLayout.WEST);
upperPanel.setBackground(Color.MAGENTA);
        
        //create the socre label
        score = new JLabel("Score: ");
        //set the prefered size
        score.setPreferredSize(new Dimension(100,50));
        upperPanel.setPreferredSize(new Dimension(800,50));
        
        //add it to the east of upper panel
        upperPanel.add(score, BorderLayout.EAST);
        questionPanel.add(upperPanel, BorderLayout.NORTH);
        
        
        //create the middle panel of the question panel
        JPanel middlePanel = new JPanel();
middlePanel.setBackground(Color.cyan); 
        //set it's layout to bordered
        middlePanel.setLayout(new BorderLayout());
        //questioText points to a new JLabel
        questionText = new JLabel("Question description goes here......");
        //set the preferred size of questioText
        questionText.setPreferredSize(new Dimension(questionTextWidth, questionTextHight));      
        //add the questioText to the middle pane's north
        middlePanel.add(questionText, BorderLayout.NORTH);
        
        
        //create a choice button panel
        JPanel choiceButtonPanel = new JPanel();
        //set it's layout to gridbag layout, to 1 row 3 columns
        choiceButtonPanel.setLayout(new GridLayout(1,3));
        
        //create the 3 choices buttons by looping                
        for (int i = 0; i <  choiceButtons.length; i++){
            choiceButtons[i] = new JButton("Choice " + (i + 1));
            //set button's event listener to this class
            choiceButtons[i].addActionListener(this);
            //add the button to the panel
            choiceButtonPanel.add(choiceButtons[i]);
        }
        
        //set the preferred size of button panel
        choiceButtonPanel.setPreferredSize(new Dimension(300,250));
        
        //add the button panel to the center of middle panel
        middlePanel.add(choiceButtonPanel, BorderLayout.CENTER);
        //add the middle panel to the center of the question panel
        questionPanel.add(middlePanel, BorderLayout.CENTER);
        
        
        //create the lower panel of the question panel
        JPanel lowerPanel = new JPanel();
lowerPanel.setBackground(Color.orange);
        //set it's layout to gridbag layout, 2 rows , 1 column
        lowerPanel.setLayout(new GridLayout(2,1));
        //create the correct-or-wrong label
        correctOrWrongLabel = new JLabel("CORRECT ANSWER!!");
        correctOrWrongLabel.setPreferredSize(new Dimension(100,90));
        //add it to the panel
        lowerPanel.add(correctOrWrongLabel);
        
        //create the next button
        nextButton = new JButton("Next");
        //set it's preferred size
        //nextButton.setPreferredSize(new Dimension(60,50));
        //add it to the panel
        lowerPanel.add(nextButton);
        nextButton.addActionListener(this);
         
        lowerPanel.setPreferredSize(new Dimension(10,100));
        //add the panel to the queston panel's south
        questionPanel.add(lowerPanel, BorderLayout.SOUTH);
    }
    

    
      public void actionPerformed(ActionEvent e) 
      {
          //if the startbutton clicked
          if(e.getSource() == startButton)
            startButtonClicked();
          
      }
      
      public static void main(String args[]){
          JFrame test = new JFrame();

          test.setSize(700,700);
          test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE  );
          test.add(new MathFunBuddy());
          test.setVisible(true);
      }
      
}
