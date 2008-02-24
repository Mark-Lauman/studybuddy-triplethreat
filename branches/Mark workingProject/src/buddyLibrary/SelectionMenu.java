/*
SelectionMenu.java    
It is a structure for each selection menu.
Team Triple Threat
Log:
02/24/2008 Mark Lauman Altered setMainButton(), and overloaded it
02/21/2008 Mark Lauman Location transfer
02/16/2008 Allan Lei Implementation of methods
*/
package buddyLibrary;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class SelectionMenu extends JPanel implements ActionListener {
    private JList jl;
    private JScrollPane jsp;
    protected DefaultListModel list;
    private JMenuBar buttonHolder;
    private ArrayList<JMenuItem> subButtons = new ArrayList<JMenuItem>();
    
    
    public SelectionMenu(int width, int height) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Selection Menu"));
        
        list = new DefaultListModel();
        jl = new JList(list);
        JScrollPane jsp = new JScrollPane(jl);
        jsp.setPreferredSize(new Dimension(width,height));

        add(jsp, BorderLayout.NORTH);
        
        buttonHolder = new JMenuBar();
        buttonHolder.setPreferredSize(new Dimension(jsp.getPreferredSize().width, 16));
        buttonHolder.setBackground(null);
        
        add(buttonHolder, BorderLayout.CENTER);
        validate();        
    }
    
    public void actionPerformed(ActionEvent e) { 
          
        
          }

    public void addChoice(String choice) {
        list.addElement(choice);
        invalidate();
        validate();
    }

    public void addChoice(String[] choice) {
        for(int i = 0; i < choice.length; i++){
            addChoice(choice[i]);
        }
    }
    
    public void addSubButton(String name, int width){
        if(!name.equals("")){
            JMenuItem button = createButton(name);
            subButtons.add(button);
            buttonHolder.add(button);
            validate();
        }else {
            JLabel space = createSpace(width, buttonHolder.getHeight());
            buttonHolder.add(space);
            validate();
        }
    }
    
    public void addToButtonHolder(Component c){
        buttonHolder.add(c);
        validate();
    }
    
    public JMenuItem createButton(String name){
        JMenuItem button = new JMenuItem(name);
        button.setActionCommand(name);
        button.addActionListener(this); //added by vic
        return button;
    }
    
    public JMenuItem createButton(String name, String actioncommand){
        JMenuItem button = createButton(name);
        button.setActionCommand(actioncommand);
        button.addActionListener(this); //added by vic
        return button;
    }
    
    public JLabel createSpace(int width, int height){
        JLabel space = new JLabel();
        space.setPreferredSize(new Dimension(width, height));
        return space;
    }
    
    public String[] getChoices() {
        String[] choices = new String[list.getSize()];
        for(int i = 0; i < list.getSize(); i++){
            choices[i] = list.get(i).toString();
        }
        return choices;
    }    

    public String getSelection() {
        if(jl.getSelectedValue() != null){
            return jl.getSelectedValue().toString();
        }else
            return null;
        
    }
    
    public void removeAllChoices() {
        list.clear();
    }

    public void removeChoice(int choice) {
        list.remove(choice);
    }

    public void removeFromButtonHolder(int index){
        buttonHolder.remove(subButtons.get(index));
        subButtons.remove(index);
    }
    
    //ActionListener is this frame
    public void setMainButton(String name){
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(200, 32));
        button.setActionCommand(name);
        button.addActionListener(this);
        add(button, BorderLayout.SOUTH);
        validate();
    }
    
    //ActionListener is specified by a
    public void setMainButton(String name, ActionListener a){
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(200, 32));
        button.setActionCommand(name);
        button.addActionListener(a);
        add(button, BorderLayout.SOUTH);
        validate();
    }
    
    public void setPosition(Component c, int x, int y) {
        Insets insets = getInsets();
        c.setBounds(x + insets.left, y + insets.top, c.getPreferredSize().width, c.getPreferredSize().height);
        validate();
    }
}