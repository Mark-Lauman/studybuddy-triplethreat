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

    
    //Sets the position of any component
    public void setPosition(Component c, int x, int y) {
        Insets insets = getInsets();
        c.setBounds(x + insets.left, y + insets.top, c.getPreferredSize().width, c.getPreferredSize().height);
        validate();
    }
    
    //returns the choices currently loaded into the list
    public String[] getChoices() {
        String[] choices = new String[list.getSize()];
        for(int i = 0; i < list.getSize(); i++){
            choices[i] = list.get(i).toString();
        }
        return choices;
    }

    //Add a choice to the list
    public void addChoice(String choice) {
        list.addElement(choice);
        invalidate();
        validate();
    }

    //Add multiple choices to the list
    public void addChoice(String[] choice) {
        for(int i = 0; i < choice.length; i++){
            addChoice(choice[i]);
        }
    }

    //get the currently selected item on the list
    public String getSelection() {
        return jl.getSelectedValue().toString();
    }

    //Removes all the choices on the list
    public void removeAllChoices() {
        list.clear();
    }

    //Removes a specific choice on the list
    public void removeChoice(int choice) {
        list.remove(choice);
    }

    //Creates a JMenuItm and sets the action command
    public JMenuItem createButton(String name){
        JMenuItem button = new JMenuItem(name);
        button.setActionCommand(name);
        return button;
    }
    
    
    //Creats a JMenuItem and sets the action command to be something different
    public JMenuItem createButton(String name, String actioncommand){
        JMenuItem button = createButton(name);
        button.setActionCommand(actioncommand);
        return button;
    }
    
    //Creates a JLabel to act as a space in the subButtonHolder
    public JLabel createSpace(int width, int height){
        JLabel space = new JLabel();
        space.setPreferredSize(new Dimension(width, height));
        return space;
    }
    
    //adds a component to the buttonholder bar
    public void addToButtonHolder(Component c){
        buttonHolder.add(c);
        validate();
    }
    
    //add a button/space to the sub button holder bar
    public void addSubButton(String name, int width){
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
    
    //Removes a component in the button holder at the specified index
    public void removeFromButtonHolder(int index){
        buttonHolder.remove(subButtons.get(index));
        subButtons.remove(index);
    }
    
    //Sets the main button
    public void setMainButton(String name){
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(200, 32));
        add(button, BorderLayout.SOUTH);
        validate();
    }
}