  /*
 * MathFunbuddy.java
 * 
 * Team Triple Threat
 * Log:
 * 03/17/2008 Vic Kao updated the database and implemented functions in detail
 * 03/16/2008 Vic Kao implemented the structure
 */

package Buddies;

import buddyLibrary.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * This Math buddy has a database of 20 math questions related to daily life 
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
    private JTextArea questionText;
    /** the JButtons used to get the answer*/
    private JButton AnswerButton;
    /** the width for questionText */
    int questionTextWidth = 100;
    /** the width for questionText */
    int questionTextHight = 50;
    /** the counter used to record number of questions conseuctively answered correctly */
    int conseuctiveCorrectCounter = 0;
    /** the counter used to record number of questions conseuctively answered incorrectly */
    int conseuctiveIncorrectCounter = 0;
   
    /** the JButtons - "Next" to go to the next question */
    private JButton nextButton;
    /** the JLabel for displaying whether the user got it correct or wrong */
    private JTextArea correctOrWrongLabel;
    /** the variable to keep record the score */
    private int scoreGained;
    /** the ArrayList for storing the question names */
    private ArrayList<String> questionNames;
    /** the variable acting as a pointer, pointing the current question */
    private int questionIndex;
    /** set the easy level */
    private final int easyLvl = 1;
    /** the ArrayList to store the questions that are not played yet */
    private ArrayList<Integer> notPlayedYet = new ArrayList<Integer>();
    /** the variable to what the current question is */
    private int currentQuestion;//record the # of the question of the game
    
    
   /**
     * Constructs a <code>MathFunBuddy</code> buddy, and loads the welcome screen
     * and question screen.
     */
    public MathFunBuddy (){
        this.setLayout(new BorderLayout());
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
        
        //start the game
        startGame();
    }
    
    /**
     * Starts the game
     */
    private void startGame()
    {
        //set currentQuestion to 0
        currentQuestion = 0;
        //set nextButton and correctOrWrongLabel to false
        nextButton.setVisible(false);
        correctOrWrongLabel.setVisible(false);
        try{
            //create an instance of String for QuestionNames 
            questionNames = new ArrayList<String>();

            //open the file for a list of question names
            File f = new File("./questions/questionRecord.txt");
            
            Scanner scan = new Scanner(f);

            //while it is not the end of file
            while(scan.hasNext()){
                //get a line from the file and add it to the QuestionNames
                questionNames.add(scan.nextLine());
            }
            
            //random generate the first question of easy level
            Random generator = new Random();
            questionIndex = generator.nextInt(easyLvl);

            //initialize score to 0
            scoreGained = 0;
            //set the score text
            score.setText("Score: " + scoreGained);

            //add all questions to the notPlayedYet ArrayList
            for(int i = 0; i < questionNames.size(); i++){
                notPlayedYet.add(i);
            }          
            
            //play the game
            playGame(questionIndex);
            }catch (Exception e){
                System.out.println(e);                 
            }
    }
    
    
    /**
     * Play the game by passing the gameIndex, the current question
     * @param gameIndex The index of the current question, like "Question 1"
     */
    private void playGame(int gameIndex)
    {
        //remove the current question from notPlayedYet ArrayList
        notPlayedYet.remove(new Integer(gameIndex));        
        
        //update the question label by 1
        currentQuestion++;
        question.setText("Question: " + currentQuestion);
        
        //get the game name with the game index
        String currentQuestionName = questionNames.get(gameIndex);
        //open the "question.txt" in the directory of the currentQuestionName
        File f = new File("./questions/"+ currentQuestionName+"/question.txt");
        
        try{
            Scanner scan = new Scanner(f);
        
            //for loop to set images and text on the buttons
            for(int i = 0; i < 3; i++){
                choiceButtons[i].setIcon(new ImageIcon("./questions/"+ currentQuestionName+ "/img" + i + ".jpg"));
                choiceButtons[i].setRolloverIcon(new ImageIcon("./questions/"+ currentQuestionName+ "/img" +  i + "a.jpg"));
                choiceButtons[i].setRolloverEnabled(true); 
                //get the a line from the text file
                choiceButtons[i].setText(scan.nextLine());
                //set the choiceButtons[0]'s text 
                choiceButtons[i].setVerticalTextPosition(JButton.BOTTOM);                
            } 
            
            //get a line from the .txt file as instruction
            questionText.setText(scan.nextLine());
        
            //get a integer as the index value of the answer
            AnswerButton = choiceButtons[scan.nextInt()];
            } catch (Exception e){
                System.out.println(e);
            }        
    }
    
    /** 
     * Create the start panel
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
        
        startPanel.setSize(700, 700);        
        //set the layout to BorderLayout
        startPanel.setLayout(new BorderLayout());

        
        //add the label to the upper panel
        upperPanel.add(introJLabel);

//tesing purpose
upperPanel.setBackground(Color.WHITE);        

        //set the size of the buttom panel
        buttomPanel.setPreferredSize(new Dimension(100, 100));   
        
        //create the button, "START"
        startButton = new JButton("START!");
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
    {   
        
        questionPanel=new JPanel();
        //set its layout to BorderLayout
        questionPanel.setLayout(new BorderLayout());
//tesing purpose: set the background color
questionPanel.setBackground(Color.GREEN);
        //set the size of question panel
        questionPanel.setSize(700,660);
        
        //crate the upper panel
        JPanel upperPanel = new JPanel();
        //set it's layout to bordered
        upperPanel.setLayout(new BorderLayout());
        //create the question label
        question = new JLabel();
        //set the preferred size
        question.setPreferredSize(new Dimension(100, 50));
        //add it to the west of upper panel
        upperPanel.add(question, BorderLayout.WEST);
//tesing purpose: set the background color        
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
//tesing purpose: set the background color        
middlePanel.setBackground(Color.pink); 
        //set its layout to BorderLayout
        middlePanel.setLayout(new BorderLayout());
        //questionText points to a new JTextArea
        questionText = new JTextArea();
//set the preferred size of questionText
// questionText.setPreferredSize(new Dimension(questionTextWidth, questionTextHight));  
        //set questionText uneditable and the background to transparent
        questionText.setEditable(false);
        questionText.setBackground(new Color(255,255,255, 100));
        //let questionText break line automatically
        questionText.setOpaque( false );
        questionText.setLineWrap(true);
        questionText.setWrapStyleWord(true);
        
        //add the questioText to the middle panel's north
        middlePanel.add(questionText, BorderLayout.NORTH);
        
        //create a choice button panel
        JPanel choiceButtonPanel = new JPanel();
        //set its layout to GridLayout, 1 row 3 columns
        choiceButtonPanel.setLayout(new GridLayout(1,3));
        
        //create the 3 choices buttons by for loop
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
//tesing purpose: set the background color           
lowerPanel.setBackground(Color.orange);
        //set its layout to GridLayout, 1 row , 2 column
        lowerPanel.setLayout(new GridLayout(1,2));
        //create the correct-or-wrong label
        correctOrWrongLabel = new JTextArea();
//correctOrWrongLabel.setPreferredSize(new Dimension(100,90));
        //set correctOrWrongLabel uneditable and the background to transparent
        correctOrWrongLabel.setEditable(false);
        correctOrWrongLabel.setBackground(new Color(255,255,255, 100));
        //let correctOrWrongLabel break line automatically by word
        correctOrWrongLabel.setOpaque( false );
        correctOrWrongLabel.setLineWrap(true);
        correctOrWrongLabel.setWrapStyleWord(true);        
        //add it to the panel
        lowerPanel.add(correctOrWrongLabel);
        
        //create the next button
        nextButton = new JButton("Next");
        //set it's preferred size

        //add it to the panel
        lowerPanel.add(nextButton);
        nextButton.addActionListener(this);
         
        lowerPanel.setPreferredSize(new Dimension(50,50));
        //add the panel to the queston panel's south
        questionPanel.add(lowerPanel, BorderLayout.SOUTH);
    }
    
    /**
     * This function handles the event when the user answers correctly
     */
    public void answerCorrectquestion()
    {
        String msgCorrect = "Excellent! You have gained 10 points! :)";
        correctOrWrongLabel.setText(msgCorrect);
        //update the score by 10 points
        scoreGained =+10;
        score.setText("Score: " + scoreGained);
        //increment the conseuctiveCorrectCounter
        conseuctiveCorrectCounter++;
        //reset conseuctiveIncorrectCounter to 0
        conseuctiveIncorrectCounter = 0;
    }
    
    /**
     * This function handles the event when the user answers incorrectly
     */    
    public void answerWrongquestion()
    {
        String msgWrong = "Sorry, your answer was not correct. " +
                "Maybe you missed some details? Try harder for the next one! :)";
        correctOrWrongLabel.setText(msgWrong);
        //increment the conseuctiveIncorrectCounter
        conseuctiveIncorrectCounter++;
        //reset conseuctiveCorrectCounter to 0
        conseuctiveCorrectCounter = 0;
    }

      
      /**
       * This function handles when the user finishes playing the buddy
       */
      public void finishPlayingBuddy()
      {
          
      }
      
      /**
       * This function handles the event when "Next" button is clicked
       */
      public void nextButtonClicked(){
          
          //decide which question to display next
          updateQuestionIndex();
          
          //re-enable all choice buttoms
          for(int i = 0; i < choiceButtons.length; i++)
              choiceButtons[i].setEnabled(true);
          
          
          //make correctorwronglable and nextbutton invisible
          correctOrWrongLabel.setVisible(false);
          nextButton.setVisible(false);
          
          //play the next game
          playGame(questionIndex);
      }
      
      /**
       * This function updates the current question index. If the question showed 
       * up before, it will not show up again. Instead, it will look for unused
       * questions.
       */
      public void updateQuestionIndex(){
          
          int i = 0;
          //find the location in notPlayedYet that is larger than questionIndex
          for(; i < notPlayedYet.size(); i++){
              if(notPlayedYet.get(i) > questionIndex)
                  break;
          }
          
          //if the user answers correctly 1 question conseuctively
          //then the next question would be harder
          if(conseuctiveCorrectCounter >= 3)
              questionIndex = notPlayedYet.get(i+1);          
          else{
              //else the next question will be eaiser
              //if easier question is available
              if ( (i-1) >=0)
                  questionIndex = notPlayedYet.get(i-1); //roll back the question by 1
              else
                  questionIndex = notPlayedYet.get(i); //no change
          }
      }
      
      /**
       * This function interactes with the user and handles the events when
       * some particular actions are performed
       * @param e The action performed by the user
       */
      public void actionPerformed(ActionEvent e) {
          //the user clickes "START" button on the welcome screen
          if(e.getSource() == startButton)
            startButtonClicked();
          //the user makes decision
          if(e.getSource() == choiceButtons[0] ||e.getSource() == choiceButtons[1]
                  || e.getSource() == choiceButtons[2]){
              if(e.getSource() == AnswerButton)                   
                  answerCorrectquestion();  //the user answers correctly
              else
                   answerWrongquestion();   //the user answers incorrectly
                            
              //disable the choice buttons after the user answered
              for(int i = 0; i < choiceButtons.length; i++)
                  choiceButtons[i].setEnabled(false);
              
              //if the user have played 10 questioins, end the program
              if (currentQuestion == 10)
                finishPlayingBuddy();
              
              //set correctOrWrongLabel and nextButton to visible
              correctOrWrongLabel.setVisible(true);
              nextButton.setVisible(true);
          }
          //the user clicks the "Next" button
          if(e.getSource() == nextButton)
              nextButtonClicked();
      }
      
      //testing codes
      public static void main(String args[]){
          JFrame test = new JFrame();

          test.setSize(700,700);
          test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE  );
          test.add(new MathFunBuddy());
          //test.pack();
          test.setVisible(true);
      }
      
}
