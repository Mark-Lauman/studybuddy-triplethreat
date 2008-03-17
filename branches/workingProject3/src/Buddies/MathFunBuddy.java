  /*
 * MathFunbuddy.java
 * 
 * Team Triple Threat
 * Log:
 * 02/28/2008 Allan Lei added file checking to add buddy
 * 02/22/2008 Vic Kao complete deleteBuddy and exportBuddy
 * 02/21/2008 Vic Kao complete addBuddy  
 */

package Buddies;

import buddyLibrary.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MathFunBuddy extends Buddy implements ActionListener {
    
    private JPanel startPanel;
    private JButton startButton;
    
    private JPanel questionPanel;
    private JLabel question;
    private JLabel score;
    private JLabel questionText;//the label for displaying the text of the question
    private JButton[] choiceButtons= new JButton[3];
    private JButton AnswerButton;
    
    //private JLabel[] choiceLabels = new JLabel[3];
    
    private JButton nextButton;
    private JLabel correctOrWrongLable;
    private int scoreGained;
    
    private ArrayList<String> QuestionNames;
    
    private int playgameIndex;
    public MathFunBuddy (){
        createStartPanel();
        add(startPanel);
                
         createQuestionPanel();
    }
    
    private void startButtoClicked()
    {
        //remove the startpanel 
        remove(startPanel);
        
        add(questionPanel);
        
        this.repaint();
        
        //startGame();
    }
    
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
    
    //create the start panel
    private void createStartPanel()
    {
        //start panel points to a panel
        startPanel = new JPanel();
        
     
        //sert the layout as gridbag layout, with 2 row, 1 column
        startPanel.setLayout(new BorderLayout());
        
        //make a upper panel for the start panel
        JPanel upperPanel = new JPanel();
        //make a buttom panel for the start panel
        JPanel buttomPanel = new JPanel();
        
        
        
        //create a jlabel
        JLabel jb = new JLabel(introText);
        //set the introduction text to the label
        //add the label to the upper panel
        upperPanel.add(jb);
        upperPanel.setBackground(Color.blue);
        
        
        //create a button "start"
        startButton = new JButton("Start");
        //set the event listenter to this class
        startButton.addActionListener(this);
        
        //set the preferred size of the button
        startButton.setPreferredSize((new Dimension(startButtonWidth, startButtonHeight)));
        //add the button to the bottom panel of the start panel
        buttomPanel.add(startButton);
        buttomPanel.setPreferredSize(new Dimension(100, 100));
        
        
        startPanel.add(upperPanel,BorderLayout.CENTER);
        startPanel.add(buttomPanel, BorderLayout.SOUTH);
        
        startPanel.setSize(700, 700);
        
        
        
    }
    
    String introText = "intro";
    int startButtonWidth = 150;
    int startButtonHeight = 150;
    
    private void createQuestionPanel()
    {   questionPanel=new JPanel();//points to a new JPanel
        questionPanel.setLayout(new BorderLayout());
        
        questionPanel.setBackground(Color.GREEN);
        
        questionPanel.setSize(600,650);
        
        //crate the upper panel
        JPanel upperPanel = new JPanel();
        //set it's layout to bordered
        upperPanel.setLayout(new BorderLayout());
        //create the question label
        question = new JLabel("que");
        //set the preferred size
        question.setPreferredSize(new Dimension(100, 50));
        //add it to the west of upper panel
        upperPanel.add(question, BorderLayout.WEST);
        upperPanel.setBackground(Color.MAGENTA);
        
        //create the socre label
        score = new JLabel("scor");
        //set the preferred size
        score.setPreferredSize(new Dimension(100,50));
        upperPanel.setPreferredSize(new Dimension(800,50));
        
        //add it to the east of upper panel
        upperPanel.add(score, BorderLayout.EAST);
        questionPanel.add(upperPanel, BorderLayout.NORTH);
        
        
        //create the middle panel of the question panel
        JPanel middlePanel = new JPanel();
        
        //set it's layout to bordered
        middlePanel.setLayout(new BorderLayout());
        //questioText//points to a new JLabel
        questionText = new JLabel("qtx");
        questionText.setPreferredSize(new Dimension(questionTextWidth, questionTextHight));
                //set the preferred size of questioText
        //add the questioText to the middle pane's north
        middlePanel.add(questionText, BorderLayout.NORTH);
        
        
        //create a choice button panel
        JPanel choiceButtonPanel = new JPanel();
        //set it's layout to gridbag layout, to 1 row 3 columns
        choiceButtonPanel.setLayout(new GridLayout(1,3));
        //create the 3 choices buttons
            //loop for each button
               
                //set button's event listener to this class
                //add the button to the panel
        for (int i = 0; i <  choiceButtons.length; i++){
            choiceButtons[i] = new JButton("bun");
            choiceButtons[i].addActionListener(this);
            choiceButtonPanel.add(choiceButtons[i]);
        }
        
        //set the preferred size of button panel
        choiceButtonPanel.setPreferredSize(new Dimension(300,250));
        
        //add the button panel to the center of middle panel
        middlePanel.add(choiceButtonPanel, BorderLayout.CENTER);
        //add the middle panel to the center of the question panel
        questionPanel.add(middlePanel, BorderLayout.CENTER);
        
        
        //create the buttom panel of the question panel
        JPanel buttomPanel = new JPanel();
       
        //set it's layout to gridbag layout, 2 rows , 1 column
        buttomPanel.setLayout(new GridLayout(2,1));
                //create the corrector wrong label
        correctOrWrongLable = new JLabel("coe");
        correctOrWrongLable.setPreferredSize(new Dimension(100,50));
        
                //add it to the panel
        buttomPanel.add(correctOrWrongLable);
        //create the next button
        nextButton = new JButton("NEXT");
                //set it's preferred size
        nextButton.setPreferredSize(new Dimension(100,50));
                //add it to the panel
         buttomPanel.add(nextButton);
         nextButton.addActionListener(this);
         
         buttomPanel.setPreferredSize(new Dimension(400,100));
        //add the panel to the queston panel's south
        questionPanel.add(buttomPanel, BorderLayout.SOUTH);
        
        
        
    }
    
    int questionTextHight = 100;
    int questionTextWidth = 100;
    
      public void actionPerformed(ActionEvent e) 
      {
          //if the startbutton clicked
          if(e.getSource() == startButton)
            startButtoClicked();
          
      }
      
      public static void main(String args[]){
          JFrame test = new JFrame();
          test.setSize(700,700);
          test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE  );
          test.add(new MathFunBuddy());
          test.setVisible(true);
      }
      
}
