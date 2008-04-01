/*
 * startPanel.java
 *
 * 03/29/2008 Vic Kao completed the startPanel.java and all its methods
 * 03/28/2008 Vic Kao implementd the structure
 */

package subBreakBuddy;

import Buddies.*;
import javax.swing.*;

/**
 * This class just creates a welcome screen
 * @author  Chen-Wei Kao
 */
public class startPanel extends javax.swing.JPanel {
    
    BreakBuddy breakBuddy;
    /** the path storing the icons used */
    private String iconPath = System.getProperty("user.dir") + "/Buddies/BreakBuddy/icons/";
    
    /** Creates new form startPanel */
    public startPanel(BreakBuddy breakBuddy) {
        //load the interface layouted by NetBeans
        initComponents();
System.out.println("The path is " + System.getProperty("user.dir"));
        this.breakBuddy = breakBuddy;
        jLabel1.setIcon(new javax.swing.ImageIcon(iconPath +  "intro.jpg"));
    }
    
/*public static void main(String args[]){
          JFrame test = new JFrame();

         // test.setSize(700,700);
          test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE  );
          test.add(new startPanel());
          //test.pack();
          test.setVisible(true);
      }    
  */  
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startButton = new javax.swing.JButton();
        introLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(0, 0));
        setLayout(null);

        startButton.setFont(new java.awt.Font("Arial", 0, 18));
        startButton.setText("START!");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        add(startButton);
        startButton.setBounds(380, 440, 150, 80);
        add(introLabel);
        introLabel.setBounds(40, 20, 0, 420);
        add(jLabel1);
        jLabel1.setBounds(80, 20, 810, 410);
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // TODO add your handling code here:
       breakBuddy.startButtonClicked();
    }//GEN-LAST:event_startButtonActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel introLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
    
}
