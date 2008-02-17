  /*
  SelectionMenu.java    
  It is a structure for each selection menu.
  
  Team Triple Threat
  Log:
  02/16/2008 Vic Kao
  */

package buddyLibrary;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;


public class SelectionMenu extends javax.swing.JPanel {    
    private DefaultListModel model; 
    
    /** Creates new form SelectionMenu */
    public SelectionMenu() {
        initComponents();

        model = new DefaultListModel(); // declare an instance of
                                        // DefaultListModel       
                
       choices.setModel(model);         // setModel and multiple interval selction
       choices.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    }
   
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        choices = new javax.swing.JList();

        jButton1.setText("jButton1");
        jButton1.setAutoscrolls(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setActionCommand("jButton2");
        jButton2.setLabel("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        choices.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(choices);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(376, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(135, 135, 135))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        /* most left button */

//      testing code...        
//        System.out.println("clicked");
//        String [] w={"a","b","c"};
//        addChoice(w);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        /* middle button */
        
//      testing code...          
//        System.out.println("clicked");
//        String [] w={"A","B","C"};
//        addChoice(w);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        /* most right button */
        
//      testing code...          
//       setMainButton("h");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        /* main button */

        
//      testing code...           
//      setSubButton3("c", SwingConstants.LEFT);
    }//GEN-LAST:event_jButton4ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList choices;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    //JButton1 the left most button in the upper row
    //JButton2 the middle button in the upper row
    //JButton3 the right most button in the upper row
    //JButton4 the main button
    
    
      public String[] getChoices(){
      /* get the input choices and return as string array */
            
        Object tempString[] = choices.getSelectedValues();
        
        String tempString2[] = new String [tempString.length];
        
        //loop all cells tempString and copy them to the tempString2
        for(int i = 0; i < tempString.length; i++) {
            tempString2[i] = (String) tempString[i];
            System.out.println(tempString2[i]);
        }
        
        return tempString2;        
    }
      
   
    public void addChoice(String choice) {
     /* add the input choice to the selection menu using alphabetical order:
      * Capital letter goes to the top first and followed by small letters
      * ie., A
      *      A
      *      a
      *      c             
      */   
     int i;
     for(i= 0; i < model.size() && 
             choice.compareTo(((String)model.get(i)))>0; i++) {         
         model.add(i,choice);
         validate();
     }        
    }

    public void addChoice(String[] choice) {
        /* add the input choices array to the selection menu by calling the 
         * addChoice function.
         */
        for(int i = 0; i < choice.length; i++) {
             addChoice(choice[i]);
        }
    }

    public String getSelection() {
        /* get the selction chosen from the user and return it */
        return (String)choices.getSelectedValue();
    }
    
    public void removeAllChoices() {
        /* clear everything in the selection menu */
        model.clear();
    }
    
    public void removeChoice(String choice) {
        /* remove the specific choice from the selection menu */
        model.removeElement(choice); 
    }

    public void setMainButton(String name) {
        /* set the text field name of the main button */
        jButton4.setText(name);
    }

    public void setSubButton1(String name, int alignment) {
        /* set the text field name and the alignment
         * of the left most button(jButton1) */
        jButton1.setText(name);        
        jButton1.setHorizontalAlignment(alignment);
        jButton1.setVisible(true); 
    }
    
    public void setSubButton2(String name, int alignment) {
         /* set the text field name and the alignment
         * of the middle button(jButton2) */
        jButton2.setText(name);        
        jButton2.setHorizontalAlignment(alignment);
        jButton2.setVisible(true);
    }
    
    public void setSubButton3(String name, int alignment) {
        /* set the text field name and the alignment
         * of the right most button(jButton3) */
        jButton3.setText(name);        
        jButton3.setHorizontalAlignment(alignment);
        jButton3.setVisible(true);
    }    
}
