/*
SelectionMenu.java    
It is a structure for each selection menu.
Team Triple Threat
Log:
02/21/2008 Mark Lauman Location transfer
02/16/2008 Allan Lei Implementation of methods
 */
package buddyLibrary;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import core.*;
import java.awt.event.ActionListener;

public class SelectionMenu extends JPanel implements ActionListener{
    private JList jl;
    private JScrollPane jsp;
    protected DefaultListModel list;
    private JMenuBar buttonHolder;
    private ArrayList<JMenuItem> subButtons = new ArrayList<JMenuItem>();
    private ActionListener[] listener = new ActionListener[2];
    private JButton mainButton;
    private JPanel content;

    public SelectionMenu(String menuName,int width, int height, ActionListener c) {
        listener[0] = this;
        listener[1] = c;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(menuName));
        
        content = new JPanel();
        content.setPreferredSize(new Dimension(width, height));
        content.setLayout(new FlowLayout());
        
        list = new DefaultListModel();
        jl = new JList(list);
        jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//set can only selet one
        JScrollPane jsp = new JScrollPane(jl);
        jsp.setPreferredSize(new Dimension(width, height));

        content.add(jsp);

        buttonHolder = new JMenuBar();
        buttonHolder.setPreferredSize(new Dimension(jsp.getPreferredSize().width, 30));
        buttonHolder.setBackground(null);

        content.add(buttonHolder);
        add(content, BorderLayout.CENTER);
        validate();
    }
    
 /**
 *  Adds a Choice to the list
 *
 * @param  choice The name of the choice to be added to the list
 */
    public void addChoice(String choice) {
        list.addElement(choice);
        jl.setSelectedIndex(list.size()-1);
        invalidate();
        validate();
    }

 /**
 *  Adds a choice to the list
 *
 * @param  choice An Array of strings that will be added to the list
 */
    public void addChoice(String[] choice) {
        for (int i = 0; i < choice.length; i++) {
            addChoice(choice[i]);
        }
    }

 /**
 *  Constructs and adds a button to the sub button bar
 *
 * @param name The name to be used on the button
 * @param width The width of the button/space to be created. Leave "name" as "" if desiring a space
 */
    public void addSubButton(String name, int width) {
        if (!name.equals("")) {
            JMenuItem button = createButton(name);
            subButtons.add(button);
            buttonHolder.add(button);
            validate();
        } else {
            JLabel space = createSpace(width, buttonHolder.getHeight());
            buttonHolder.add(space);
            validate();
        }
    }

    
 /**
 *  Adds a component to the sub button bar
 *
 * @param  c A Component to be added to the sub button bar
 */
    public void addToButtonHolder(Component c) {
        buttonHolder.add(c);
        validate();
    }

 /**
 *  Creates a button with the Core and this Actionlisteners
 *
 * @param  name String of the name to be used on the button
 * @return  button A JMenuItem that has the actionlisteners and name attached
 */
    public JMenuItem createButton(String name) {
        JMenuItem button = new JMenuItem(name);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setActionCommand(name);
        for (int i = 0; i < listener.length; i++) {
            button.addActionListener(listener[i]); //added by vic
        }
        return button;
    }

 /**
 *  Creates a button with the Core and this Actionlisteners
 *
 * @param  name String of the name to be used on the button
 * @param actioncommand A String of the action command to be set on the button
 * @return  button A JMenuItem that has the actionlisteners and name attached
 */
    public JMenuItem createButton(String name, String actioncommand) {
        JMenuItem button = createButton(name);
        button.setActionCommand(actioncommand);
        for (int i = 0; i < listener.length; i++) {
            button.addActionListener(listener[i]); //added by vic
        }
        return button;
    }

 /**
 *  Creates a empty JLabel to act as a space
 *
 * @param  width The width of the space to be
 * @param height The height of the spae to be
 * @return space A JLabel with the specified dimensions
 */
    public JLabel createSpace(int width, int height) {
        JLabel space = new JLabel();
        space.setPreferredSize(new Dimension(width, height));
        return space;
    }

/**
 *  Returns the choices available on the list
 *
 * @return choices Returns the choices available in a String array
 */
    public String[] getChoices() {
        String[] choices = new String[list.getSize()];
        for (int i = 0; i < list.getSize(); i++) {
            choices[i] = list.get(i).toString();
        }
        return choices;
    }

 /**
 *  Gets the currently selected item on the list
 *
 * @return String Returns the string value of the currently selected item
 */    
    public String getSelection() {
        if (jl.getSelectedValue() != null) {
            return jl.getSelectedValue().toString();
        } else {
            return null;
        }
    }

 /**
 *  Removes all the choices that are on the list
 */
    public void removeAllChoices() {
        list.clear();
    }

/**
 *  Removes the choice at a specific index
 *
 * @param choice Index of the item to be removed from the list
 */
    public void removeChoice(int choice) {
        list.remove(choice);
    }

    
/**
 *  Removes a component from the sub button bar at the specified index
 *
 * @param  index The int value of the index to be removed
 */
    public void removeFromButtonHolder(int index) {
        buttonHolder.remove(subButtons.get(index));
        subButtons.remove(index);
    }

/**
 *  Creates and sets the name and action listener to the main button at the bottom
 *
 * @param  name String of the name to be used on the button
 */
    public void setMainButton(String name) {
        mainButton = new JButton(name);
        for (int i = 0; i < listener.length; i++) {
            mainButton.addActionListener(listener[i]); //added by vic
        }
        mainButton.setPreferredSize(new Dimension(200, 32));
        add(mainButton, BorderLayout.SOUTH);
        validate();
    }

/**
 *  Sets the position of a component to the specified location
 *
 * @param c Component to be moved
 * @param x X coordinate to set the component to
 * @param y Y coordinate to set the component to
 * @return  button A JMenuItem that has the actionlisteners and name attached
 */
    public void setPosition(Component c, int x, int y) {
        Insets insets = getInsets();
        c.setBounds(x + insets.left, y + insets.top, c.getPreferredSize().width, c.getPreferredSize().height);
        validate();
    }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}