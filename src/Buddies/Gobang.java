  /*
   * 
   * BreakBuddy.java
   * 
   * Team Triple Threat
   * Log:
   * 03/27/2008 Vic Kao completed the BreakBuddy.java
   */
package Buddies;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;


class Gobang extends JFrame implements Runnable, ActionListener {
    
    private final static int Player=1;
    private final static int AI =-1;

    ClassLoader cl = this.getClass().getClassLoader();
    Toolkit tk = Toolkit.getDefaultToolkit();

    private int length=14, game_state, winner, check, step;
    private int grid[][] = new int[length][length];
    /** the coordinate of the chess */
    private int locX, locY; 
    /** count how many chess are in a row */
    private int count; 
    /** temp storage for x and y */
    private int x, y; 
    /** displacement of x and y */
    private int displace_x=0;
    private int displace_y=0; 
    private int direction;
    /** an array list to record the history */
    private ArrayList steps = new ArrayList(); 
    /** pop up righ-clicked menu */
    private JPopupMenu control_menu = new JPopupMenu();
    JMenuItem[] command = new JMenuItem[4];
    String[] command_str={"Go Back", "Save", "Read", "Play again"};

    private int[][] dir = { {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1} };
    private boolean[] dir2 = new boolean[8]; 
 
    private boolean turn;
    private String message;
    /** ask the user to choose one color to play */
    private final JDialog dialog = new JDialog(this, "Please choose one color", true);

    private Font font=new Font("new_font", Font.BOLD, 20);
    private Grid grids[][] = new Grid[length][length];

    private Image white= tk.getImage(cl.getResource("res/white.png"));
    private Image black= tk.getImage(cl.getResource("res/black.png"));
    private Image title= tk.getImage(cl.getResource("res/title.png"));
    private Image temp;
    private JPanel boardPanel, bigpanel;
    private JRadioButton[] choice = new JRadioButton[2];

    private final static int Start =0;
    private final static int Select =1;
    private final static int Playing =2;
    private final static int End =3;
    /** no direction */
    private final static int nil=-1; 
    /* �k�W�V���U */
    private final static int oblique_1 =0; 
    /* ���W�V�k�U */
    private final static int oblique_2 =1; 
    /* ��V */
    private final static int horizontal =2; 
    /* ���V */
    private final static int vertical=3; 

 Gobang() {
    super("Welcome to 5-Chess");
  
    boardPanel = new JPanel();
    boardPanel.setLayout(new GridLayout(length, length, 0, 0));
    boardPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

    for(int i = 0; i < length; i++)
        for(int j = 0; j < length; j++) {
            grids[i][j] = new Grid(i, j); 
            boardPanel.add(grids[i][j]);
        }

      bigpanel = new JPanel();
      //set it to border layout
      bigpanel.add(boardPanel, BorderLayout.CENTER);
      getContentPane().add(bigpanel, BorderLayout.SOUTH);   

      game_state = Start;
      ThreadStart();

      dialog.setSize(160, 100);
      dialog.setResizable(false);
      dialog.setLocationRelativeTo(null);

      ButtonGroup choice_group = new ButtonGroup();
      JPanel choice_menu = new JPanel(); 

      choice[0] = new JRadioButton("Black", new ImageIcon(black), true);
      choice[1] = new JRadioButton("White", new ImageIcon(white));

      for(int i=0; i<choice.length; i++) {
         choice_menu.add(choice[i]);
         choice_group.add(choice[i]);
      }

      for(int i=0; i<command.length; i++) {
           command[i] =new JMenuItem(command_str[i]);
           command[i].addActionListener(this);
           control_menu.add(command[i]);
      }

      JButton select = new JButton("OK");
      JPanel select_menu = new JPanel(); 
      select_menu.add(select);
      select.addActionListener(this);

      dialog.getContentPane().add(choice_menu, BorderLayout.NORTH);
      dialog.getContentPane().add(select_menu, BorderLayout.CENTER);

      setIconImage(title);
      setResizable(false);
      setSize(300, 335);
      setVisible(true);
      setLocationRelativeTo(null);
 }

 public static void main(String[] arg)
 {
  Gobang application = new Gobang();
  application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }

 public void actionPerformed(ActionEvent event) {
      if( event.getSource().equals(command[0]) ) {
       if(steps.size()!=0)
            undo();
      }
      else if(event.getSource().equals(command[1]) )
            Save();
      else if(event.getSource().equals(command[2]) )
            Load();
      else if(event.getSource().equals(command[3]) )
            ReStart();
      else {
       if(choice[1].isSelected()) {
            temp=white;
            white=black;
            black=temp;
       }
       dialog.dispose();
      }
 }

 /**
  * This function checks the codition of winning
  */
 private void WinCheck() {
    check = turn? Player: AI;
    direction=oblique_1;
    WinCheck2();
 }

  /**
  * This function checks the codition of winning
  */
 private void WinCheck2() {
  count=1;
  //check the directions
  switch(direction) {
   case oblique_1: displace_x = 1;
                 displace_y = -1;
                 direction = oblique_2;
                 break;

   case oblique_2: displace_x = displace_y=1;
                 direction = horizontal;               
                 break;

   case horizontal: displace_x=1;
                 displace_y=0;
                 direction=vertical;                    
                 break;
                           
   case vertical: displace_x=0;
               displace_y=1;
               direction=nil;
               break;
  }

    x = locX + displace_x;
    y = locY + displace_y;

     while(x>=0 && x<length && y>=0 && y<length && grid[x][y]==check) {
           count=count+1;
           x=x+displace_x;
           y=y+displace_y;
      }

      x = locX-displace_x;
      y = locY-displace_y;
 
      while(x>=0 && x<length && y>=0 && y<length && grid[x][y]==check){
           count=count+1;
           x=x-displace_x;
           y=y-displace_y;
      }
     
      if(count >= 5){
           game_state=End;
           winner=check;
           ThreadStart();
      }
      else if(direction != nil)
            WinCheck2();
 }

 /**
  * This function restarts the game
  */
 public void ReStart() {
      for(int i=0; i<length; i++)
          for(int j=0; j<length; j++)
                grids[i][j].Initial();

      winner=0;
      steps.clear();
      game_state = Playing;
 }

 /**
  * This function generates the next location of the chess
  */
 public void gobangRandom() {
      displace_y=0;

      do {
           displace_x = (int)(Math.random()*8);
           x =locX+dir[displace_x][0];
           y =locY+dir[displace_x][1];

           if(!dir2[displace_x]) {
                displace_y = displace_y+1;
                dir2[displace_x] = true;
           }
      } while((x<0 || x>=length || y<0 || y>=length || grid[x][y]!=0) && displace_y<8);

      for(int i=0; i<8; i++)
          dir2[i]=false;

      if(x>=0 && x<length && y>=0 && y<length && grid[x][y]==0)
          setMark(x, y);
      else
          gobangRandom2();
 }

  /**
  * This function generates the next location of the chess
  */
 private void gobangRandom2() {
  do {
      x = (int)(Math.random()*length);
      y = (int)(Math.random()*length);
  } while(grid[x][y]!=0);

  setMark(x, y);
 }

/**
  * This function determines AI
  */
 private void gobangAI() {
     boolean play=true;

     for(int i=2; i>0; i--) {
        play=!play;
        gobangAI4(play);

        if(turn)
            return;
      }

      for(int i=7; i>=4; i--) {
        play=!play;

        step=i/2;
        gobangAI2(play);

       if(turn)
            return;
      }

      gobangAI5();

      if(!turn)
        gobangRandom();
     }

 /**
  * This function determines AI
  */
 private void gobangAI2(boolean player) {
      check = player? Player: AI;

      for(int i=0; i<length; i++)
          for(int j=0; j<length; j++) {
                if(turn)
                    break;

        if(grid[i][j] == check) {
            count=1;
            direction=oblique_1;
            gobangAI3(i, j);
        }
       }
 }

/**
  * This function determines AI
  */
 private void gobangAI3(int x, int y) {
     if(count==1) {
         locX = x;
         locY = y;
     }

      switch(direction) {
            case oblique_1: displace_x = 1;
                          displace_y=-1;
                          break;

            case oblique_2: displace_x=displace_y = 1;
                           break;

       case horizontal: displace_x = 1;
                     displace_y=0;
                     break;

       case vertical: displace_x = 0;
                   displace_y=1;
                   break;
      }

      x = x + displace_x;
      y = y + displace_y;

      if(x>=0 && x<length && y>=0 && y<length && grid[x][y]==check) {
            count=count+1;

           if(count==step) {
                if(x+displace_x>=0 && x+displace_x<length && y+displace_y>=0 && y+displace_y<length && grid[x+displace_x][y+displace_y]==0) {
                      /* playing situation */
                    if(x-step*displace_x<0 || x-step*displace_x>=length || y-step*displace_y<0 || y-step*displace_y>=length || !gobang_SpaceAI(x+displace_x, y+displace_y) || (grid[x-step*displace_x][y-step*displace_y]!=0 && step<3) )
                        gobangAI3_2();  
                    else    
                        setMark2(x + displace_x, y + displace_y);
                 }
            else
                gobangAI3_2();
           }
           else
                gobangAI3(x, y);
      }
      else
            gobangAI3_2();
}

/**
  * This function determines AI
  */
 private void gobangAI3_2() {
      if(direction!=vertical) {
       count = 1;
       direction = direction + 1;
       gobangAI3(locX, locY);
      }
 }
 
 
/**
  * This function determines AI
  */
 private void gobangAI4(boolean player) {
      check = player? Player: AI;

      for(int i=0; i<length; i++)
          for(int j=0; j<length; j++) {
              if(turn)
                  break;

            if(grid[i][j] == 0) {
                direction = oblique_1;
                locX = i;
                locY = j;
                gobangAI4();
            }
          }
 }

 /**
  * This function determines AI
  */
 private void gobangAI4() {
      count=0;

      switch(direction) {
       case oblique_1: displace_x = 1;
                     displace_y = -1;
                     direction = oblique_2;
                     break;

       case oblique_2: displace_x=displace_y = 1;
                     direction = horizontal;
                     break;

       case horizontal: displace_x = 1;
                     displace_y = 0;
                     direction = vertical;
                     break;

       case vertical: displace_x = 0;
                   displace_y = 1;
                   direction = nil;
                   break;
      }

      x = locX + displace_x;
      y = locY + displace_y;

      while(x>=0 && x<length && y>=0 && y<length && grid[x][y]==check) {
           count = count + 1;
           x = x + displace_x;
           y = y + displace_y;
      }

      x = locX-displace_x;
      y = locY-displace_y;

      while(x>=0 && x<length && y>=0 && y<length && grid[x][y]==check) {
           count = count + 1;
           x = x + displace_x;
           y = y + displace_y;
      }

      if(count>=4)
          setMark(locX, locY);
      else if(direction != nil)
          gobangAI4();
}

  /**
  * This function determines AI
  */
 private void gobangAI5() {
      for(int i=0; i<length; i++)
          for(int j=0; j<length; j++) {
              if(turn)
                  break;

            if(grid[i][j]==-1) {
                 direction = oblique_1;
                 locX = i;
                 locY = j;
                 gobangAI5_2();
            }
         }
 }

  /**
  * This function calculates the break space
  */
 private void gobangAI5_2() {
      count=0;

      switch(direction) {
       case oblique_1: displace_x = 1;
                     displace_y = -1;
                     direction = oblique_2;
                     break;

       case oblique_2: displace_x = displace_y = 1;
                     direction=horizontal;
                     break;

       case horizontal: displace_x = 1;
                     displace_y=0;
                     direction=vertical;
                     break;

       case vertical: displace_x = 0;
                   displace_y =1;
                   direction = nil;
                   break;
      }

      x = locX+displace_x;
      y = locY+displace_y;

      while(x>=0 && x<length && y>=0 && y<length && grid[x][y]==0 && count<4) {
          count = count + 1;
          x = x + displace_x;
          y = y + displace_y;
      }

      x = locX-displace_x;
      y = locY-displace_y;

      if(count==4 && x>=0 && x<length && y>=0 && y<length && grid[x][y]==0)
          setMark(locX + displace_x, locY + displace_y);
      else if(count>1) {
          count = count == 4? 3: count;

       while(x>=0 && x<length && y>=0 && y<length && grid[x][y]==0 && count<4) {
            count = count + 1;
            x = x - displace_x;
            y = y - displace_y;
       }

       if(count == 4)
           setMark(locX+displace_x, locY+displace_y);
       else if(direction != nil)
           gobangAI5_2();
      }
      else if(direction != nil)
          gobangAI5_2();
 }

  /**
   * This function calculates how much space left
   */
 private boolean gobang_SpaceAI(int x, int y) {
      int space=0;

      do {
          space = space + 1;
          x = x + displace_x;
          y = y + displace_y;

      } while(x>=0 && x<length && y>=0 && y<length && grid[x][y]==0 && space<4);

      /* check if the space left has the possibility to be a line*/
      if(space+count>=5) 
          return true;
      else {
          space=0;
          x = locX - displace_x;
          y = locY - displace_y;

       while(x>=0 && x<length && y>=0 && y<length && grid[x][y]==0 && space<4) {
           space = space + 1;
           x = x - displace_x;
           y = y - displace_y;
       }
       
       //if possible
       if(space+count >= 5) 
           return true;
       else
           return false;
      }
 }
/**
 * Starts the game
 */
 public void ThreadStart() {
  new Thread(this).start();
 }
 
 /**
  * Set the chess coordinate
  * @param x x-coordinate
  * @param y y-coordinate
  */
 private void setMark(int x, int y) {
      steps.add(grids[x][y]);
      grids[x][y].setValue(-1);
      WinCheck();  
      turn=true;
 }

 /**
  * This function calculates the next possible location of the mark
  */
 private void setMark2(int x, int y) {
      int space=0, temp_x=x, temp_y=y;

      do {
          space = space + 1;
          temp_x = temp_x + displace_x;
          temp_y = temp_y + displace_y;

      } while(temp_x>=0 && temp_x<length && temp_y>=0 && temp_y<length && grid[temp_x][temp_y]==0 && space<4);

      if(space+step >= 5)
          setMark(x, y);
      else
          setMark(x-(step+1)*displace_x, y-(step+1)*displace_y); 
 }

 public void run() {
  try {
       switch(game_state) {
        case Start: Thread.sleep(2000);
                   dialog.show();
                   game_state=Playing; 
                   repaint();
                   break;

        case End: repaint();
                  Thread.sleep(1500);
                  ReStart();
                  repaint();
                  break;
       }
      }
      catch(InterruptedException ex)
      {
          System.out.println(ex);
      }
 }

 public void paint(Graphics g) {
     super.paint(g);
 
     switch(game_state) {
           case Start: g.drawString("2008.03  by Vic Kao", 91, 240);
                       g.setFont(font);
                       g.setColor(Color.BLUE);
                       g.drawString("Gobang", 112, 120);
                       g.drawImage(title, 134, 135, this);

                       break;

           case Playing:
               
           case End: 
                     g.drawString("Player:        CPU:", 105, 40);
                     g.drawImage(black, 132, 30, this);
                     g.drawImage(white, 182, 30, this);

                     if(winner!=0) {
                          g.setFont(font);
                          g.setColor(Color.RED);
                          message = winner==1? "You Win!":"You Lost!";
                          g.drawString(message, 120, 185);
                     }

                     break;
          }
 }
 
 /**
  * This function does the undo
  */
 public void undo() {
      if(steps.size() > 0) {
          for(int i=0; i<2; i++) {
              ((Grid)steps.get(steps.size() - 1)).Initial();
              steps.remove(steps.size() - 1);
          }
      }
 }

  /**
  * This function does the save
  */
 private void Save() {
      String str;
      try {
           File file = new File("innings.men");
           file.createNewFile();

           BufferedWriter write = new BufferedWriter(new FileWriter(file)); 

           for(int i = 0; i < steps.size(); i++) {
               str=(((Grid)steps.get(i)).getLocX())+","+(((Grid)steps.get(i)).getLocY())+","+(((Grid)steps.get(i)).getValue());

               write.write(str);
               write.newLine();
           }

          write.close();
      }
      catch(Exception ex) {
          System.out.println(ex);
      }
 }

  /**
  * This function does the load
  */
 private void Load() {
      try {
            String[] step_array;
            String str;

            File file = new File("innings.men");

            if(!file.exists()) {
                JOptionPane.showMessageDialog(null, "�S���s��");
                return;
           }

           BufferedReader read = new BufferedReader(new FileReader(file)); 
           ReStart();

           while(read.ready()) {
                str=read.readLine();
                step_array=str.split(",");

                grids[Integer.parseInt(step_array[0])][Integer.parseInt(step_array[1])].setValue(Integer.parseInt(step_array[2]));
                steps.add(grids[Integer.parseInt(step_array[0])][Integer.parseInt(step_array[1])]);
           }
      }
      catch(Exception ex){
          System.out.println(ex);
      }
 }

 private class Grid extends JPanel implements MouseListener {
      int x, y, value;
      boolean selected;

      public Grid(int x, int y) {
       this.x = x;
       this.y = y;

       addMouseListener(this);
  }

      
  public void mousePressed(MouseEvent event) {
       if(game_state == Playing) {
           int button=event.getButton();

        if(button == MouseEvent.BUTTON1) {
            if(value==0) {
                steps.add(this);
                setValue(1);
                WinCheck(); 
                turn=false;

                if(game_state==Playing)
                    gobangAI();
            }
        }
        else
            control_menu.show(this, event.getX(), event.getY());
       }
  }

  public void mouseEntered(MouseEvent event) { 
        if(game_state==Playing)
            setSelected(true);
  }
  
  public void mouseExited(MouseEvent event) {
        if(game_state==Playing)
            setSelected(false);
  }

  public void mouseClicked(MouseEvent event) {
  }

  public void mouseReleased(MouseEvent event)
  {
  }

  public Dimension getPreferredSize() { 
        return new Dimension(20, 20);
  }

  public Dimension getMinimumSize() {
        return getPreferredSize();
  }

  public int getLocX() {
        return x;
  }

  public int getLocY() {
        return y;
  }

  public int getValue() {
        return value;
  }

  public void Initial() {
       value = grid[x][y] = 0;
       selected = false;
       repaint();
  }

  public void setValue(int new_value) {
       locX = x;
       locY = y;
       value = grid[x][y] = new_value;
       repaint();
  }

  public void setSelected(boolean select) {
       selected = select;
       repaint();
  }

  public void paintComponent(Graphics g) {
       super.paintComponent(g);

       if(game_state != Start && game_state != Select) {
            g.drawLine(0, 10, 19, 10);
            g.drawLine(10, 0, 10, 19);

           /* �e��u */

            g.setColor(Color.BLUE);

            if(x==0)   
                g.drawLine(0, 0, 19, 0);
            else if(x == length - 1)
                g.drawLine(0, 19, 19, 19);  

            if(y == 0)   
                g.drawLine(19, 0, 19, 19);
            else if(y == length - 1)
                 g.drawLine(0, 0, 0, 19);

            if(selected)
                setBackground(Color.WHITE);
            else
                setBackground(Color.LIGHT_GRAY);
       }

       
       if(value != 0) {
           temp = value == 1? black: white;
           g.drawImage(temp, 4, 4, this);
       }
}

}
}
