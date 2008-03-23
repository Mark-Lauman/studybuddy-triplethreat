package Buddies;

import buddyLibrary.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CircuitBuddy extends Buddy implements ActionListener {

    private JPanel startScreen;
    private JPanel circuitB;
    private JPanel board;
    private int wiresize = 30;
    private JLabel pic0 = new JLabel(new ImageIcon("lightning.png"));
    private JLabel pic1 = new JLabel(new ImageIcon("lightning.png"));
    private JLabel pic2 = new JLabel(new ImageIcon("lightning.png"));
    private JLabel pic3 = new JLabel(new ImageIcon("lightning.png"));
    private Timer time;
    private Timer time0,  time1,  time2,  time3;
    private boolean counter0 = false;
    private boolean counter1 = false;
    private boolean counter2 = false;
    private boolean counter3 = false;
    private int index0,  move0X,  move0Y;
    private int index1,  move1X,  move1Y;
    private int index2,  move2X,  move2Y;
    private int index3,  move3X,  move3Y;
    private ArrayList<ArrayList<JPanel>> list = new ArrayList<ArrayList<JPanel>>();
    private ArrayList<JButton> finish = new ArrayList<JButton>();

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
    }

    private void start() {
        startScreen = new JPanel();
        startScreen.setBorder(BorderFactory.createTitledBorder("Intoduction"));
        startScreen.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height));
        startScreen.setBackground(Color.BLUE);
        JButton startB = new JButton("Start");
        startB.setActionCommand("Start");
        startB.addActionListener(this);
        startScreen.add(startB);
    }

    private void circuit() {
        circuitB = new JPanel();
        circuitB.setBorder(BorderFactory.createTitledBorder("Circuit"));
        circuitB.setLayout(new BorderLayout());
        circuitB.setPreferredSize(getPreferredSize());
        circuitB.setBackground(Color.WHITE);

        board = new JPanel();
        board.setLayout(null);
        board.setPreferredSize(new Dimension(circuitB.getPreferredSize().width * 3 / 4, circuitB.getPreferredSize().height * 3 / 4));
        board.setBackground(Color.DARK_GRAY);
        circuitB.add(board, BorderLayout.CENTER);

        JPanel score = new JPanel();
        score.setLayout(new FlowLayout());
        score.setBorder(BorderFactory.createTitledBorder("Scores"));
        score.setBackground(null);
        score.setPreferredSize(new Dimension(circuitB.getPreferredSize().width / 4, circuitB.getPreferredSize().height / 4));
        JLabel label = new JLabel("Level:");
        score.add(label);

        JButton startround = new JButton("Start Round");
        startround.setActionCommand("startround");
        startround.addActionListener(this);
        score.add(startround);
        circuitB.add(score, BorderLayout.EAST);
    }

    private void spark() {
        if (time == null || !time.isRunning()) {
            board.add(pic0);
            board.add(pic1);
            board.add(pic2);
            board.add(pic3);

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
                board.remove(pic0);
            case 1:  
                pic1.setVisible(false);
                board.remove(pic1);
            case 2:  
                pic2.setVisible(false);
                board.remove(pic2);
            case 3: 
                pic3.setVisible(false);
                board.remove(pic3);

        }
    }

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
                    j = horizontal(c);
                    j.setName("hor+");
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
                    j = horizontal(c);
                    j.setName("hor-");
                    list.get(index).add(j);
                    board.add(j);
                    setPosition(j, board, x - wiresize + 5, y);
                    x = x - wiresize + 5;
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
                    j = horizontal(c);
                    j.setName("hor+");
                    list.get(index).add(j);
                    board.add(j);
                    setPosition(j, board, x, y);
                    x = x + wiresize;
                    hor = true;
                } else if (rand == 2 && !hor) {
                    j = horizontal(c);
                    j.setName("hor-");
                    list.get(index).add(j);
                    board.add(j);
                    setPosition(j, board, x - wiresize + 5, y);
                    x = x - wiresize + 5;
                    hor = true;
                }
            }
        }
        //ImageIcon image = new ImageIcon("ico.gif");
        JButton j = new JButton();
        j.setPreferredSize(new Dimension(20, 20));
        board.add(j);
        setPosition(j, board, x - j.getPreferredSize().width / 2, y);
        j.addActionListener(this);
        j.setActionCommand("Finish");
        finish.add(j);
    }

    private void setPosition(Component c, JPanel ref, int x, int y) {
        Insets insets = ref.getInsets();
        c.setBounds(x + insets.left, y + insets.top, c.getPreferredSize().width, c.getPreferredSize().height);
        validate();
    }

    private JPanel vertical(Color c) {
        JPanel block = new JPanel();
        block.setPreferredSize(new Dimension(5, wiresize));
        block.setBackground(c);
        return block;
    }

    private JPanel horizontal(Color c) {
        JPanel block = new JPanel();
        block.setPreferredSize(new Dimension(wiresize, 5));
        block.setBackground(c);
        return block;
    }

    private JLabel skip() {
        ImageIcon image = new ImageIcon("./build/classes/Buddies/skip.png");
        JLabel pic = new JLabel(image);
        return pic;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            startScreen.setVisible(false);
            this.remove(startScreen);
            circuit();
            this.add(circuitB, BorderLayout.CENTER);
            place(0, 100, 50);
            place(1, 250, 50);
            place(2, 400, 50);
            place(3, 550, 50);
        } else if (e.getActionCommand().equals("Finish")) {
            spark();
            for (int i = 0; i < finish.size(); i++) {
                finish.get(i).setEnabled(false);
            }
        } else if (e.getActionCommand().equals("startround")) {
            clear(0);
            clear(1);
            clear(2);
            clear(3);
            place(0, 100, 50);
            place(1, 250, 50);
            place(2, 400, 50);
            place(3, 550, 50);
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
                if (list.get(0).get(index0).getName().equals("hor+")) {
                    setPosition(pic0, board, pic0.getX() + 1, pic0.getY());
                } else {
                    setPosition(pic0, board, pic0.getX() - 1, pic0.getY());
                }
                move0X--;
                counter0 = false;
            } else {
                if (time0 == null) {
                    time0 = new Timer(10, this);
                    time0.setActionCommand("counter0");
                    time0.start();
                } else if (!time0.isRunning()) {
                    time0.start();
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
                if (move0Y == 5) {
                    move0Y = 0;
                }

                if (list.get(0).get(index0).getName().equals("hor-")) {
                    move0X = move0X - 5;
                }
            }

            if (move1X > 0 && counter1) {
                if (list.get(1).get(index1).getName().equals("hor+")) {
                    setPosition(pic1, board, pic1.getX() + 1, pic1.getY());
                } else {
                    setPosition(pic1, board, pic1.getX() - 1, pic1.getY());
                }
                move1X--;
                counter1 = false;
            } else {
                if (time1 == null) {
                    time1 = new Timer(10, this);
                    time1.setActionCommand("counter1");
                    time1.start();
                } else if (!time1.isRunning()) {
                    time1.start();
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
                if (move1Y == 5) {
                    move1Y = 0;
                }

                if (list.get(1).get(index1).getName().equals("hor-")) {
                    move1X = move1X - 5;
                }
            }

            if (move2X > 0 && counter2) {
                if (list.get(2).get(index2).getName().equals("hor+")) {
                    setPosition(pic2, board, pic2.getX() + 1, pic2.getY());
                } else {
                    setPosition(pic2, board, pic2.getX() - 1, pic2.getY());
                }
                move2X--;
                counter2 = false;
            } else {
                if (time2 == null) {
                    time2 = new Timer(10, this);
                    time2.setActionCommand("counter2");
                    time2.start();
                } else if (!time2.isRunning()) {
                    time2.start();
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
                if (move2Y == 5) {
                    move2Y = 0;
                }

                if (list.get(2).get(index2).getName().equals("hor-")) {
                    move2X = move2X - 5;
                }
            }

            if (move3X > 0 && counter3) {
                if (list.get(3).get(index3).getName().equals("hor+")) {
                    setPosition(pic3, board, pic3.getX() + 1, pic3.getY());
                } else {
                    setPosition(pic3, board, pic3.getX() - 1, pic3.getY());
                }
                move3X--;
                counter3 = false;
                time3.stop();
            } else {
                if (time3 == null) {
                    time3 = new Timer(100, this);
                    time3.setActionCommand("counter3");
                    time3.start();
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
                if (move3Y == 5) {
                    move3Y = 0;
                }

                if (list.get(3).get(index3).getName().equals("hor-")) {
                    move3X = move3X - 5;
                }
            }

            if (pic0.getY() >= 488 || pic1.getY() >= 488 || pic2.getY() >= 488 || pic3.getY() >= 488) {
                time.stop();
            }
        }
    }
}
