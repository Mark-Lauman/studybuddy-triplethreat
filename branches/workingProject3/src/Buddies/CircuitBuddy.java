package Buddies;

import buddyLibrary.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CircuitBuddy extends Buddy implements ActionListener {

    private JPanel startScreen; // Placeholder for startscreen
    private JPanel circuitB; // Placeholder for circuitB
    private JPanel board; //placeholder for board that is added to circuitB
    private JPanel gameoverS;
    private int wiresize = 36;  //The size of the "wire"
    private JLabel pic0 = new JLabel(new ImageIcon(System.getProperty("user.dir") + "/Buddies/CircuitBuddy/lightning.png")); //Lightning image
    private JLabel pic1 = new JLabel(new ImageIcon(System.getProperty("user.dir") + "/Buddies/CircuitBuddy/lightning.png"));
    private JLabel pic2 = new JLabel(new ImageIcon(System.getProperty("user.dir") + "/Buddies/CircuitBuddy/lightning.png"));
    private JLabel pic3 = new JLabel(new ImageIcon(System.getProperty("user.dir") + "/Buddies/CircuitBuddy/lightning.png"));
    private JLabel battery0 = new JLabel(new ImageIcon(System.getProperty("user.dir") + "/Buddies/CircuitBuddy/battery.png"));
    private JLabel battery1 = new JLabel(new ImageIcon(System.getProperty("user.dir") + "/Buddies/CircuitBuddy/battery.png"));
    private JLabel battery2 = new JLabel(new ImageIcon(System.getProperty("user.dir") + "/Buddies/CircuitBuddy/battery.png"));
    private JLabel battery3 = new JLabel(new ImageIcon(System.getProperty("user.dir") + "/Buddies/CircuitBuddy/battery.png"));
    private Timer time; //Timer for end of round animation
    private Timer time0,  time1,  time2,  time3;
    private Timer timeRound1 = new Timer(1000, this);
    private Timer timeRound2 = new Timer(1000, this); //Timer for each lightning bolt movement
    private boolean counter0 = false; // Counter to set the pace of lightning bolt movement
    private boolean counter1 = false; // Counter to set the pace of lightning bolt movement
    private boolean counter2 = false; // Counter to set the pace of lightning bolt movement
    private boolean counter3 = false; // Counter to set the pace of lightning bolt movement
    private int index0,  move0X,  move0Y; //package of variable place holders for each circuit
    private int index1,  move1X,  move1Y;//package of variable place holders for each circuit
    private int index2,  move2X,  move2Y;//package of variable place holders for each circuit
    private int index3,  move3X,  move3Y;//package of variable place holders for each circuit
    private ArrayList<ArrayList<JPanel>> list = new ArrayList<ArrayList<JPanel>>(); //Arraylist grid to hold all the circuits
    private ArrayList<JButton> finish = new ArrayList<JButton>(); // Arraylist to hold the finish buttons
    private int switchcounter = 0; //Counts the number of on/off switches used
    private int choice;
    private int points = 0;
    private int round = 1;
    private int timeleft = 10;
    private JLabel label1, label2, label3;
    private String fileloc = System.getProperty("user.dir") + "/Buddies/CircuitBuddy/";

    public CircuitBuddy() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
        start();
        add(startScreen, BorderLayout.CENTER);
        list.add(new ArrayList<JPanel>());
        list.add(new ArrayList<JPanel>());
        list.add(new ArrayList<JPanel>());
        list.add(new ArrayList<JPanel>());
        timeRound2.setActionCommand("startround");
        timeRound1.setActionCommand("countdown");
    }

    /**
     *  Creates the start screen of CircuitBuddy
     */
    private void start() {
        startScreen = new JPanel();
        startScreen.setLayout(null);
        startScreen.setBorder(BorderFactory.createTitledBorder("Intoduction"));
        startScreen.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height));
        startScreen.setBackground(Color.BLUE);
        JButton startB = new JButton("Start");
        startB.setActionCommand("Start");
        startB.addActionListener(this);
        startScreen.add(startB);
        setPosition(startB, startScreen, this.getPreferredSize().width/2 - startB.getPreferredSize().width/2, -10);
        JLabel intro = new JLabel(new ImageIcon(fileloc + "intro.gif"));
        startScreen.add(intro);
        setPosition(intro, startScreen, this.getPreferredSize().width/2-intro.getIcon().getIconWidth()/2, 20);
    }

    private void gameover(){
        //writeStats((float)points);
        timeleft = 10;
        round = 1;
        points = 0;
        timeRound1.stop();
        gameoverS = new JPanel();
        gameoverS.setBorder(BorderFactory.createTitledBorder("Game Over"));
        gameoverS.setPreferredSize(getPreferredSize());
        gameoverS.setBackground(Color.GRAY);
        JButton go = new JButton("Start Again");
        go.setActionCommand("new");
        go.addActionListener(this);
        gameoverS.add(go);
        circuitB.setVisible(false);
        add(gameoverS, BorderLayout.CENTER);
    }

    /**
     *  Creates the game screen of the circuitBuddy which adds the circuitboard, animation board and score board
     *
     */
    private void circuit() {
        circuitB = new JPanel();
        circuitB.setBorder(BorderFactory.createTitledBorder("Circuit Buddy"));
        circuitB.setLayout(new BorderLayout());
        circuitB.setPreferredSize(getPreferredSize());
        circuitB.setBackground(Color.WHITE);

        board = new JPanel();
        board.setLayout(null);
        board.setPreferredSize(new Dimension(circuitB.getPreferredSize().width * 3 / 4, circuitB.getPreferredSize().height * 3 / 4));
        board.setBackground(Color.DARK_GRAY);
        board.add(pic0);
        board.add(pic1);
        board.add(pic2);
        board.add(pic3);
        board.add(battery0);
        setPosition(battery0, board, 95, 20);
        board.add(battery1);
        setPosition(battery1, board, 245, 20);
        board.add(battery2);
        setPosition(battery2, board, 395, 20);
        board.add(battery3);
        setPosition(battery3, board, 545, 20);
        circuitB.add(board, BorderLayout.CENTER);

        JPanel score = new JPanel();
        score.setLayout(null);
        score.setBorder(BorderFactory.createTitledBorder("Scores"));
        score.setBackground(null);
        score.setPreferredSize(new Dimension(circuitB.getPreferredSize().width / 4, circuitB.getPreferredSize().height / 4));
        label1 = new JLabel("Round: " + round);
        label1.setPreferredSize(new Dimension(100, 20));
        setPosition(label1, score, 50, 50);
        score.add(label1);
        label2 = new JLabel("Score: " + points);
        label2.setPreferredSize(new Dimension(100, 20));
        setPosition(label2, score, 50, 100);
        score.add(label2);
        label3 = new JLabel("Time Remaining: " + timeleft);
        label3.setPreferredSize(new Dimension(150, 20));
        setPosition(label3, score, 50, 200);
        score.add(label3);

        JButton startround = new JButton("Start Round");
        startround.setActionCommand("startround");
        startround.addActionListener(this);
        score.add(startround);
        setPosition(startround, score, 50, 0);
        circuitB.add(score, BorderLayout.EAST);
    }

    /**
     *  Initializes the lightning animations for each circuit by using timers.  
     *  Determines the starting point for each lightning bolt
     *
     */
    private void spark() {
        if (time == null || !time.isRunning()) {
            pic0.setVisible(true);
            pic1.setVisible(true);
            pic2.setVisible(true);
            pic3.setVisible(true);

            index0 = 0;
            int x0 = list.get(0).get(0).getX() - pic0.getIcon().getIconWidth() / 2;
            int y0 = list.get(0).get(0).getY() - pic0.getIcon().getIconHeight() / 2;
            setPosition(pic0, board, x0, y0);
            move0X = list.get(0).get(index0).getWidth();
            move0Y = list.get(0).get(index0).getHeight();
            if (move0X == 5) {
                move0X = 0;
            }
            if (move0Y == 5) {
                move0Y = 0;
            }


            index1 = 0;
            int x1 = list.get(1).get(0).getX() - 12;
            int y1 = list.get(1).get(0).getY() - 12;
            setPosition(pic1, board, x1, y1);
            move1X = list.get(1).get(index1).getWidth();
            move1Y = list.get(1).get(index1).getHeight();
            if (move1X == 5) {
                move1X = 0;
            }
            if (move1Y == 5) {
                move1Y = 0;
            }

            index2 = 0;
            int x2 = list.get(2).get(0).getX() - 12;
            int y2 = list.get(2).get(0).getY() - 12;
            setPosition(pic2, board, x2, y2);
            move2X = list.get(2).get(index2).getWidth();
            move2Y = list.get(2).get(index2).getHeight();
            if (move2X == 5) {
                move2X = 0;
            }
            if (move2Y == 5) {
                move2Y = 0;
            }

            index3 = 0;
            int x3 = list.get(3).get(0).getX() - 12;
            int y3 = list.get(3).get(0).getY() - 12;
            setPosition(pic3, board, x3, y3);
            move3X = list.get(3).get(index3).getWidth();
            move3Y = list.get(3).get(index3).getHeight();
            if (move3X == 5) {
                move3X = 0;
            }
            if (move3Y == 5) {
                move3Y = 0;
            }

            time = new Timer(0, this);
            time.setActionCommand("time");
            time.start();
        }
    }

    /**
     *  Clears the board of the circuit, finish button, and lightning bolt
     *
     * @param index clears the indicated circuit
     */
    private void clear(int index) {
        for (int i = 0; i < list.get(index).size(); i++) {
            list.get(index).get(i).setVisible(false);
            board.remove(list.get(index).get(i));
        }
        list.get(index).clear();
        for (int k = 0; k < finish.size(); k++) {
            finish.get(k).setVisible(false);
            board.remove(finish.get(k));
        }
        finish.clear();
        switch (index) {
            case 0:
                pic0.setVisible(false);
            case 1:
                pic1.setVisible(false);
            case 2:
                pic2.setVisible(false);
            case 3:
                pic3.setVisible(false);

        }
        switchcounter = 0;
    }

    /**
     *  Lays out the circuit in a random order which includes resistors, switches, and normal wires.
     * Adds a Finish button at the very end
     *
     * @param index Index given to the circuit
     * @param indexX The initial X coordinate to start creating the circuit at
     * @param indexY The initial Y coordinate to start creating the circuit at
     */
    private void place(int index, int indexX, int indexY) {
        Color c = new Color((int) (Math.random() * 200 + 55), (int) (Math.random() * 200 + 55), (int) (Math.random() * 200 + 55));
        int x = indexX;
        int y = indexY;
        boolean hor = true;
        while (y < 500) {
            if (x < (60 + 100 * index)) {
                int rand = (int) (Math.random() * 4);
                JPanel j;
                if (rand == 0) {
                    j = vertical(c);
                    j.setName("vert");
                    list.get(index).add(j);
                    board.add(j);
                    setPosition(j, board, x, y);
                    y = y + wiresize;
                    hor = false;
                } else if (rand == 1 && !hor) {
                    if (Math.random() >= 0.3) {
                        j = horizontal(c);
                        j.setName("hor+");
                    } else {
                        if (Math.random() >= 0.8 && switchcounter < 2) {
                            j = onoff(c);

                        } else {
                            j = resistor(c);
                        }
                        j.setName(j.getName() + "+");
                    }
                    list.get(index).add(j);
                    board.add(j);
                    setPosition(j, board, x, y);
                    x = x + wiresize;
                    hor = true;
                }

            } else if (x > (60 + 100 * (index + 1))) {
                int rand = (int) (Math.random() * 4);
                JPanel j;
                if (rand == 0) {
                    j = vertical(c);
                    j.setName("vert");
                    list.get(index).add(j);
                    board.add(j);
                    setPosition(j, board, x, y);
                    y = y + wiresize;
                    hor = false;
                } else if (rand == 1 && !hor) {
                    if (Math.random() >= 0.3) {
                        j = horizontal(c);
                        setPosition(j, board, x - wiresize + 5, y);
                        x = x - wiresize + 5;
                        j.setName("hor-");
                    } else {
                        if (Math.random() >= 0.8 && switchcounter < 2) {
                            j = onoff(c);
                        } else {
                            j = resistor(c);
                        }

                        setPosition(j, board, x - wiresize, y);
                        x = x - wiresize;
                        j.setName(j.getName() + "-");
                    }
                    list.get(index).add(j);
                    board.add(j);

                    hor = true;
                }
            } else {
                int rand = (int) (Math.random() * 4);
                JPanel j;
                if (rand == 0) {
                    j = vertical(c);
                    j.setName("vert");
                    list.get(index).add(j);
                    board.add(j);
                    setPosition(j, board, x, y);
                    y = y + wiresize;
                    hor = false;
                } else if (rand == 1 && !hor) {
                    if (Math.random() >= 0.3) {
                        j = horizontal(c);
                        j.setName("hor+");
                    } else {
                        if (Math.random() >= 0.2) {
                            j = resistor(c);
                        } else {
                            j = onoff(c);
                        }
                        j.setName(j.getName() + "+");
                    }

                    list.get(index).add(j);
                    board.add(j);
                    setPosition(j, board, x, y);
                    x = x + wiresize;
                    hor = true;
                } else if (rand == 2 && !hor) {
                    if (Math.random() >= 0.3) {
                        j = horizontal(c);
                        setPosition(j, board, x - wiresize + 5, y);
                        x = x - wiresize + 5;
                        j.setName("hor-");
                    } else {
                        if (Math.random() >= 0.8 && switchcounter < 2) {
                            j = onoff(c);
                        } else {
                            j = resistor(c);
                        }
                        setPosition(j, board, x - wiresize, y);
                        x = x - wiresize;
                        j.setName(j.getName() + "-");
                    }

                    list.get(index).add(j);
                    board.add(j);

                    hor = true;
                }
            }
        }
        JButton j = new JButton();
        finish.add(j);
        j.setPreferredSize(new Dimension(20, 20));
        board.add(j);
        setPosition(j, board, x - j.getPreferredSize().width / 2, y);
        j.addActionListener(this);
        j.setActionCommand("Finish" + index);

    }

    /**
     *  Sets the position of any component 
     *
     * @param c The Component to be moved
     * @param ref The component to move the object in relation to
     * @param x X coordinate that the component will be moved to
     * @param y Y coordinate that the component will be moved to
     */
    private void setPosition(Component c, JPanel ref, int x, int y) {
        Insets insets = ref.getInsets();
        c.setBounds(x + insets.left, y + insets.top, c.getPreferredSize().width, c.getPreferredSize().height);
        validate();
    }

    /**
     *  Creates a Height-wise JPanel to replicate a vertical wire
     *
     * @param c Color of the JPanel to be created
     * @return block The created JPanel with specified parameters
     */
    private JPanel vertical(Color c) {
        JPanel block = new JPanel();
        block.setPreferredSize(new Dimension(5, wiresize));
        block.setBackground(c);
        return block;
    }

    /**
     *  Creates a width-wise JPanel to replicate a horizontal wire
     *
     * @param c Color of the JPanel to be created
     * @return block The created JPanel with specified parameters
     */
    private JPanel horizontal(Color c) {
        JPanel block = new JPanel();
        block.setPreferredSize(new Dimension(wiresize, 5));
        block.setBackground(c);
        return block;
    }

    /**
     *  Creates a JPanel with the Resistor image
     *
     * @param c Color of the JPanel to be created
     * @return block The created JPanel with specified parameters
     */
    private JPanel resistor(Color c) {
        JPanel block = new JPanel();
        block.add(new JLabel(new ImageIcon(fileloc + "res.png")));
        block.setBackground(c);
        block.setName("res0050");
        return block;
    }

    /**
     *  Creates a JPanel with the Switch image
     *
     * @param c Color of the JPanel to be created
     * @return block The created JPanel with specified parameters
     */
    private JPanel onoff(Color c) {
        JPanel block = new JPanel();
        block.add(new JLabel(new ImageIcon(fileloc + "switch.png")));
        block.setBackground(c);
        block.setName("switch");
        switchcounter++;
        return block;
    }

    /**
     *  Calculates the slow down value of the timer according to the resistor name
     *
     * @param resname The name of the resistor JPanel
     * @return resistance The value of the resistor
     */
    private int calcRes(String resname) {
        int resistance = Integer.parseInt(resname.substring(3, 7));
        return resistance;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            startScreen.setVisible(false);
            remove(startScreen);
            circuit();
            add(circuitB, BorderLayout.CENTER);
            timeRound1.start();
            place(0, 100, 50);
            place(1, 250, 50);
            place(2, 400, 50);
            place(3, 550, 50);
        } else if(e.getActionCommand().equals("new")){
            clear(0);
            clear(1);
            clear(2);
            clear(3);
            gameoverS.setVisible(false);
            remove(gameoverS);
            start();
            add(startScreen, BorderLayout.CENTER);
        } else if (e.getActionCommand().contains("Finish")) {
            ((JButton)e.getSource()).setBackground(Color.BLUE);
            timeRound1.stop();
            spark();
            choice = Integer.parseInt(e.getActionCommand().substring(6, 7));
            for (int i = 0; i < finish.size(); i++) {
                finish.get(i).setEnabled(false);
            }
            
        } else if(e.getActionCommand().equals("countdown")){
            timeleft--;
            label3.setText("Time Left: " + timeleft);
            if(timeleft <= 0){
                timeRound1.stop();
                gameover();
            }
        } else if (e.getActionCommand().equals("startround")) {
            timeRound2.stop();
            clear(0);
            clear(1);
            clear(2);
            clear(3);
            place(0, 100, 50);
            place(1, 250, 50);
            place(2, 400, 50);
            place(3, 550, 50);
            timeleft = 10;
            timeRound1.start();
        } else if (e.getActionCommand().equals("counter0")) {
            counter0 = true;
        } else if (e.getActionCommand().equals("counter1")) {
            counter1 = true;
        } else if (e.getActionCommand().equals("counter2")) {
            counter2 = true;
        } else if (e.getActionCommand().equals("counter3")) {
            counter3 = true;
        } else if (e.getActionCommand().equals("time")) {
            if (move0X > 0 && counter0) {
                if (list.get(0).get(index0).getName().endsWith("+")) {
                    setPosition(pic0, board, pic0.getX() + 1, pic0.getY());
                } else {
                    setPosition(pic0, board, pic0.getX() - 1, pic0.getY());
                }
                move0X--;
                counter0 = false;
            } else {

                if (time0 == null) {
                    time0 = new Timer(9, this);

                    time0.setActionCommand("counter0");
                    time0.start();

                } else if (time0.isRunning()) {
                    if (list.get(0).get(index0).getName().contains("res")) {
                        time0.setDelay(calcRes(list.get(0).get(index0).getName()));
                    } else if (list.get(0).get(index0).getName().contains("switch")) {
                        time0.stop();
                    } else {
                        time0.setDelay(9);
                    }
                }
            }

            if (move0Y > 0) {
                setPosition(pic0, board, pic0.getX(), pic0.getY() + 1);
                move0Y--;
            }

            if (move0X == 0 && move0Y == 0 && index0 < list.get(0).size() - 1) {
                index0++;
                move0X = list.get(0).get(index0).getWidth();
                move0Y = list.get(0).get(index0).getHeight();
                if (move0X == 5) {
                    move0X = 0;
                }
                if (move0Y == 5 || move0Y == 20) {
                    move0Y = 0;
                }

                if (list.get(0).get(index0).getName().endsWith("-")) {
                    move0X = move0X - 5;
                }
            }

            if (move1X > 0 && counter1) {
                if (list.get(1).get(index1).getName().endsWith("+")) {
                    setPosition(pic1, board, pic1.getX() + 1, pic1.getY());
                } else {
                    setPosition(pic1, board, pic1.getX() - 1, pic1.getY());
                }
                move1X--;
                counter1 = false;
            } else {

                if (time1 == null) {
                    time1 = new Timer(9, this);

                    time1.setActionCommand("counter1");
                    time1.start();
                } else if (time1.isRunning()) {
                    if (list.get(1).get(index1).getName().contains("res")) {
                        time1.setDelay(calcRes(list.get(1).get(index1).getName()));
                    } else if (list.get(1).get(index1).getName().contains("switch")) {
                        time1.stop();
                    } else {
                        time1.setDelay(9);
                    }
                }
            }
            if (move1Y > 0) {
                setPosition(pic1, board, pic1.getX(), pic1.getY() + 1);
                move1Y--;
            }

            if (move1X == 0 && move1Y == 0 && index1 < list.get(1).size() - 1) {
                index1++;
                move1X = list.get(1).get(index1).getWidth();
                move1Y = list.get(1).get(index1).getHeight();
                if (move1X == 5) {
                    move1X = 0;
                }
                if (move1Y == 5 || move1Y == 20) {
                    move1Y = 0;
                }

                if (list.get(1).get(index1).getName().endsWith("-")) {
                    move1X = move1X - 5;
                }
            }

            if (move2X > 0 && counter2) {
                if (list.get(2).get(index2).getName().endsWith("+")) {
                    setPosition(pic2, board, pic2.getX() + 1, pic2.getY());
                } else {
                    setPosition(pic2, board, pic2.getX() - 1, pic2.getY());
                }
                move2X--;
                counter2 = false;
            } else {

                if (time2 == null) {
                    time2 = new Timer(9, this);

                    time2.setActionCommand("counter2");
                    time2.start();
                } else if (list.get(2).get(index2).getName().contains("switch")) {
                    time2.stop();
                } else if (time2.isRunning()) {
                    if (list.get(2).get(index2).getName().contains("res")) {
                        time2.setDelay(calcRes(list.get(2).get(index2).getName()));
                    } else {
                        time2.setDelay(9);
                    }
                }
            }
            if (move2Y > 0) {
                setPosition(pic2, board, pic2.getX(), pic2.getY() + 1);
                move2Y--;
            }

            if (move2X == 0 && move2Y == 0 && index2 < list.get(2).size() - 1) {
                index2++;
                move2X = list.get(2).get(index2).getWidth();
                move2Y = list.get(2).get(index2).getHeight();
                if (move2X == 5) {
                    move2X = 0;
                }
                if (move2Y == 5 || move2Y == 20) {
                    move2Y = 0;
                }

                if (list.get(2).get(index2).getName().endsWith("-")) {
                    move2X = move2X - 5;
                }
            }

            if (move3X > 0 && counter3) {
                if (list.get(3).get(index3).getName().endsWith("+")) {
                    setPosition(pic3, board, pic3.getX() + 1, pic3.getY());
                } else {
                    setPosition(pic3, board, pic3.getX() - 1, pic3.getY());
                }
                move3X--;
                counter3 = false;
            } else {

                if (time3 == null) {
                    time3 = new Timer(9, this);

                    time3.setActionCommand("counter3");
                    time3.start();
                } else if (list.get(3).get(index3).getName().contains("switch")) {
                    time3.stop();
                } else if (time3.isRunning()) {
                    if (list.get(3).get(index3).getName().contains("res")) {
                        time3.setDelay(calcRes(list.get(3).get(index3).getName()));
                    } else {
                        time3.setDelay(9);
                    }
                }
            }
            if (move3Y > 0) {
                setPosition(pic3, board, pic3.getX(), pic3.getY() + 1);
                move3Y--;
            }

            if (move3X == 0 && move3Y == 0 && index3 < list.get(3).size() - 1) {
                index3++;
                move3X = list.get(3).get(index3).getWidth();
                move3Y = list.get(3).get(index3).getHeight();
                if (move3X == 5) {
                    move3X = 0;
                }
                if (move3Y == 5 || move3Y == 20) {
                    move3Y = 0;
                }

                if (list.get(3).get(index3).getName().endsWith("-")) {
                    move3X = move3X - 5;
                }
            }

            if (pic0.getY() >= 500 || pic1.getY() >= 500 || pic2.getY() >= 500 || pic3.getY() >= 500) {
                time0.start();
                time1.start();
                time2.start();
                time3.start();
                time.stop();
                if (pic0.getY() >= 500 && choice == 0) {
                    timeRound2.start();
                    points += 50;
                    round++;
                    label1.setText("Round: " + round);
                    label2.setText("Score: " + points);
                    choice = 4;
                } else if (pic1.getY() >= 500 && choice == 1) {
                    timeRound2.start();
                    points += 50;
                    round++;
                    label1.setText("Round: " + round);
                    label2.setText("Score: " + points);
                    choice = 4;
                } else if (pic2.getY() >= 500 && choice == 2) {
                    timeRound2.start();
                    points += 50;
                    round++;
                    label1.setText("Round: " + round);
                    label2.setText("Score: " + points);
                    choice = 4;
                } else if (pic3.getY() >= 500 && choice == 3) {
                    timeRound2.start();
                    points += 50;
                    round++;
                    label1.setText("Round: " + round);
                    label2.setText("Score: " + points);
                    choice = 4;
                } else {
                    System.out.println("You Lose");
                    gameover();
                }
            }
        }
    }
}