/*
SelectionMenu.java    
It is a structure for each selection menu.
Team Triple Threat
Log:
02/16/2008 Allan Lei Implementation of methods
*/
package buddyLibrary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SelectionMenu extends JPanel {
    private JList jl;
    private JScrollPane jsp;
    private DefaultListModel list;
    private JMenuBar buttonHolder;
    private ArrayList<JMenuItem> subButtons = new ArrayList<JMenuItem>();
    
    
    public SelectionMenu(int width, int height) {
        /* Constructor for SelectionMenu.  Arguments are for width and height of the selection menu.
         * Adds in a JList and Buttons. */
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

    
    
    public void setPosition(Component c, int x, int y) {
        /* Sets the position of any component 
        Input: Component c - The Component to be moved
               int x - X coordinate that the component will be moved to
               int y - Y coordinate that the component will be moved to
         Output: None */
        Insets insets = getInsets();
        c.setBounds(x + insets.left, y + insets.top, c.getPreferredSize().width, c.getPreferredSize().height);
        validate();
    }
    
    
    public String[] getChoices() {
        /* returns the choices currently loaded into the list
         Input: None
         Output: String[] choices - returns the menu list items in a String array*/
        String[] choices = new String[list.getSize()];
        for(int i = 0; i < list.getSize(); i++){
            choices[i] = list.get(i).toString();
        }
        return choices;
    }

    
    public void addChoice(String choice) {
        /* Add a choice to the list
         Input: String choice - Name of the menu item to be added to the list
         Output: None - adds a new choice to te list*/
        list.addElement(choice);
        invalidate();
        validate();
    }

    
    public void addChoice(String[] choice) {
        /* Add multiple choices to the list
         Input: Sting[] choice -  Array of choice names to be added to the menu list
         Output: None - adds array items to list*/
        for(int i = 0; i < choice.length; i++){
            addChoice(choice[i]);
        }
    }

    
    public String getSelection() {
        /* get the currently selected item on the list
         Input: None
         Output: String - Returns the currently selected menu item*/
        return jl.getSelectedValue().toString();
    }

    
    public void removeAllChoices() {
        /* Removes all the choices on the list
         Input: None
         Output: None - Removes all the menu items from the list*/
        list.clear();
    }

    
    public void removeChoice(int choice) {
        /* Removes a specific choice on the list
         Input: int choice - Index of the menu item on the list to be removed
         Output: None - removes the indexed menu item from the list*/
        list.remove(choice);
    }

    
    public JMenuItem createButton(String name){
        /* Creates a JMenuItm and sets the action command
         Input: String name - name to be used on the button
         Output: JMenuItem button - returns a JMenuItem with specified name */
        JMenuItem button = new JMenuItem(name);
        button.setActionCommand(name);
        return button;
    }
    
    public JMenuItem createButton(String name, String actioncommand){
        /* Creats a JMenuItem and sets the action command to be something different
         Input: String name - Name to be used on the button
                String actioncommand - action command to be used on the button
         Output: JMenuItem button - returns a JMenuItem with specified name and action command */
        JMenuItem button = createButton(name);
        button.setActionCommand(actioncommand);
        return button;
    }

    public JLabel createSpace(int width, int height){
        /* Creates a JLabel to act as a space in the subButtonHolder
         Input: int width - Width of the space
                int height - Height of the space (Not important)
         Output: JLabel space - returns a JLabel with the width specified (height will be overridden)*/
        JLabel space = new JLabel();
        space.setPreferredSize(new Dimension(width, height));
        return space;
    }
    
    
    public void addToButtonHolder(Component c){
        /* adds a component to the buttonholder bar
         Input: Component c - Component to be added to the button holder bar
         Output: None - adds component to button holder bar*/
        buttonHolder.add(c);
        validate();
    }
    
    
    public void addSubButton(String name, int width){
        /* add a button/space to the sub button holder bar
         Input: String name - Name of the subbutton (Leave blank if making a space i.e "")
                int width - Width of the space to be created (Make name = "" if making space)
         Output: None - Adds the created object to the buttonholder bar*/
        if(!name.equals("")){
            JMenuItem button = createButton(name);
            subButtons.add(button);
            buttonHolder.add(button);
            validate();
        }else{
            JLabel space = createSpace(width, buttonHolder.getHeight());
            buttonHolder.add(space);
            validate();
        }
    }
    
    
    public void removeFromButtonHolder(int index){
        /* Removes a component in the button holder at the specified index
         Input: int index - Index of the object to be removed from the sub button bar
         Output: None - removes object of specified index*/
        buttonHolder.remove(subButtons.get(index));
        subButtons.remove(index);
    }
    
    
    public void setMainButton(String name){
        /* Sets the main button
         Input: String name - name of the main button
         Output: None - Creates and sets the Main button*/
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(200, 32));
        add(button, BorderLayout.SOUTH);
        validate();
    }
}