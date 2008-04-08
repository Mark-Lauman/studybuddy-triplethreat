package buddyLibrary;

/*
 * Buddy.java
 * Brief File Description
 * Team Triple Threat
 * Log:
 * 03/18/2008 Vic Kao modified the method for reading stats files
 * 03/13/2008 Vic Kao added the data type for statistics and some functions for
 * the new file accessing system
 * 03/10/2008 Mark Lauman Created templates for binary data functions
 * 02/22/2008 Mark Lauman Moved elements
 * 02/14/2008 Allan Lei   Total revision, implementation of all methods 
 * 02/11/2008 Mark Lauman Created Template
 */

import core.Core;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import core.*;
import java.applet.AudioClip;
import java.awt.event.ActionListener;
import sun.audio.AudioStream;


public class Buddy extends JPanel implements ActionListener{
    public Core frame;
    
    //the following is added by Vic
    public static final String SCORE_STATS = "0";
    public static final String TIME_STATS = "1";
    public String statType;
    
    public Buddy() {
        setLayout(null);
        setPreferredSize(new Dimension(800, 600));
    }
    
     /**
     *  Create an audioplayer with a Audiostream at a specific location
     *
     * @param filename File location of the .wav file to be stored
     * @param x X coordinate where the audioplayer will be located
     * @param y Y coordinate where the audioplayer will be located
     * @return Creates a audiplayer with the specified AudioStream and moved to specified location
     */
    public void addAudioPlayer(String filename, int x, int y) {
        try{
        SoundPlayer player = new SoundPlayer(filename);
        add(player);
        setPosition(player, x, y);
        validate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
     /**
     *  Create an audioplayer with a Audiostream at a specific location
     *
     * @param filename File location of a AudioClip to be stored
     * @param x X coordinate where the audioplayer will be located
     * @param y Y coordinate where the audioplayer will be located
     * @return   Creates a audiplayer with the specified AudioClip and moved to specified location
     */
    public void addAudioPlayer(AudioClip ac, int x, int y) {
        SoundPlayer player = new SoundPlayer(ac);
        add(player);
        setPosition(player, x, y);
        validate();
    }
    
    /**
     * Clear the statistics data
     */    
    public void clearStats(){
        //get the file
        File temp = new File(getDataFilename() + ".txt");
//not sure which for clearing stats.....Vic (delete the file or just clear the data inside the file?)        
        //if it is deleted
        temp.delete();
        //if empty the file
        try{
            //create a printWriter with the file to empty the file
            PrintWriter pw = new PrintWriter(temp);
            //close the printWriter
            pw.close();
        }catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    /**
     * Get all abjects stored in this buddy's binary data file and return them
     * as an ArrayList
     * 
     * @return An ArrayList containing the objects stored in this buddy's binary
     *         data file. These objects are of type Object, and must be cast to
     *         their correct types by the calling buddy. If the file is empty or
     *         does not exist then this function returns an empty ArrayList.
     */
    public ArrayList<Object> getDataContent() {
        ObjectInputStream o = getDataReader();
        ArrayList<Object> result = new ArrayList<Object>();
        if(o != null) {
            try {
                do {
                    result.add(o.readObject());
                } while(true);
            } catch(Exception e) {
                //do nothing - the ArrayList is finished
            }
            try {
                o.close();
            } catch(Exception e) {
                //Do nothing. If it doesn't need closing then we're done
            } 
        }
        return result;
    }
    
    /**
     * Get a File reference to the binary data file for this buddy.
     * @return A File that points to this buddy's binary data file
     */
    public String getDataFilename() {
        //first we get the buddy's name
        String temp[] = this.getClass().getName().replace(".", " ").split(" ");
            //replace(".", " ") is needed because to split, "." means split on
            //every character - which returns an empty array
        String buddyName = temp[temp.length - 1];
            //the last element in the array is the classname without any
            //package extensions
        
        String filename = System.getProperty("user.dir");
        filename += "\\Data\\" + getUser() + "\\";
        filename += buddyName;
        return filename;
    }
    
    /**
     * Get an ObjectInputStream that points to this buddy's binary data file.
     * This allows for more control over reading than getDataContent()
     * @return An ObjectInputStream that points to this buddy's binary data file.
     *         If  file does not exist then it returns null
     */
    public ObjectInputStream getDataReader() {
        ObjectInputStream objStream;
        try {
            FileInputStream inStream = new FileInputStream(
                                                new File(getDataFilename()+".dat"));
            objStream = new ObjectInputStream(inStream);
        } catch(IOException e) {
            objStream = null;
        }
        return objStream;
    }
    
    /**
     * Get an ObjectOutputStream that points to this buddy's binary data file.
     * This allows the buddy to write to the binary file
     * @return An ObjectOutputStream that points to this buddy's binary data
     *         file. The file is created if it does not exist already. If the
     *         file cannot be created for some reason, then this function
     *         returns null.
     */
    public ObjectOutputStream getDataWriter() {
        ObjectOutputStream objStream;
        try {
            File f = new File(getDataFilename() + ".dat");
            f.getParentFile().mkdirs();
            FileOutputStream outStream = new FileOutputStream(f);
            objStream = new ObjectOutputStream(outStream);
        } catch(IOException e) {
            objStream = null;
        }
        return objStream;
    }

    
     /**
     * Retrieve the statistics for a certain buddy
     *
     * @return  stats Returns the values of the specified study buddy in a Float object array
     */
    public ArrayList<Float> getStats(){
        
       float[] tempFloat=null;
       
       try {
           BufferedReader bf = new BufferedReader(new FileReader(new File(getDataFilename() + ".txt")));
           statType = bf.readLine();    //get the statType first
           //get the statistics with the path, and then return the statistics in float[]
           tempFloat = getStatistics(bf);
       } catch (Exception ex) {
            System.out.println(ex);
       }
       
       //create an arraylist with the float[]
       //and add them to ArrayList as objects
       ArrayList<Float> arrayFloat = new ArrayList<Float>();
       for(int i = 0; i <  tempFloat.length; i++){
           arrayFloat.add(tempFloat[i]);
       }

       return arrayFloat;        
    }
    
    /**
     * Return the statistics type of the data file
     * 
     * @return It returns the statistics type, Score or Time, as String
     */
    public String getStatType(){
        return statType;
    }

     /**
     *  Retrieve the statistics for a certain buddy
     *
     * @param  buddyName  Name of the study buddy to get the scores from
     * @return  stats Returns the values of the specified study buddy in a float array
     */
    public float[] getStatistics(String buddyName) {
        float[] stats = null;
        ArrayList<Float> temp = new ArrayList<Float>();
        try {
            BufferedReader read = new BufferedReader(new FileReader(new File(buddyName)));

            String line = null;
            while ((line = read.readLine()) != null) {
                temp.add(Float.parseFloat(line));
            }
            read.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        stats = new float[temp.size()];
        for (int i =
                0; i < temp.size(); i++) {
            stats[i] = temp.get(i);
        }
        return stats;
    }
    
     /**
     *  Retrieve the statistics for a certain buddy
     *
     * @param br Buffered reader that contains an data stream to be read from
     * @return stats Returns the values of the specified Buffered Reader in a float array
     */ 
    public float[] getStatistics(BufferedReader br) {
        float[] stats = null;
        ArrayList<Float> temp = new ArrayList<Float>();
        try {
            BufferedReader read = br;

            String line = null;
            while ((line = read.readLine()) != null) {
                temp.add(Float.parseFloat(line));
            }
            read.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        stats = new float[temp.size()];
        for (int i =
                0; i < temp.size(); i++) {
            stats[i] = temp.get(i);
        }
        return stats;
    }

     /**
     *  Retrieve the user name from Core parent
     *
     * @return   Retrieves the username from the reference frame (Core)
     */ 
    public String getUser() {
        try {
            System.out.println(frame.getUser());
            return frame.getUser();
        } catch(NullPointerException e) {
            return "user";
        }
    }
    
     /**
     *  Loads and displays an image from a file location and specify the location
     *
     * @param filename File location of an image file
     * @param x X coordinate for the image to be placed at
     * @param y Y coordinate for the image to be placed at
     * @return   Places the accessed image at the specified location
     */ 
    public void loadImage(String filename, int x, int y) {
        ImageIcon image = new ImageIcon(filename);
        JLabel pic = new JLabel(image);
        add(pic);
        setPosition(pic, x, y);
        validate();
    }
    
     /**
     *  Loads and displays an image from a file location and specify the location
     *
     * @param i Image stream to be read from
     * @param x X coordinate for the image to be placed at
     * @param y Y coordinate for the image to be placed at
     * @return   Places the accessed image at the specified location
     */ 
    public void loadImage(Image i, int x, int y) {
        ImageIcon image = new ImageIcon(i);
        JLabel pic = new JLabel(image);
        add(pic);
        setPosition(pic, x, y);
        validate();
    }

     /**
     *  Load a sound file from a specific location
     *
     * @param filename - File location of a .wav file
     * @return player Returns a object that has the .wav file stored inside
     */ 
    public SoundPlayer loadSound(String filename) {
        try{
        SoundPlayer player = new SoundPlayer(filename);
        return player;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
     /**
     *  Sets the position of any component 
     *
     * @param c The Component to be moved
     * @param x X coordinate that the component will be moved to
     * @param y Y coordinate that the component will be moved to
     * @return Set the position of the component
     */ 
    public void setPosition(Component c, int x, int y) {
        Insets insets = getInsets();
        c.setBounds(x + insets.left, y + insets.top, c.getPreferredSize().width, c.getPreferredSize().height);
        validate();
    }
    
     /**
     *  Sets the parent to f
     *
     * @param f A Core object
     * @return   saves the reference to the Core object
     */ 
    public void setReference(Core f) {
        frame = f;
    }
    
     /**
     *  Sets the title on the parent Core window
     *
     * @param title Name that the Core object's title will be changed to
     * @return   Sets the new title in Core object
     */ 
    public void setTitle(String title) {
        frame.setTitle(title);
    }
    
    /**
     * Sets the statistics type to the given type, Score or Time
     * 
     * @param flag
     */
    public void setStatType(String flag) {
        statType = flag;
    }
    
    
    /**
     * This function is referenced from http://www.javareference.com/jrexamples/viewexample.jsp?id=5
     * 
     * @param The float numbers to be added to the end of the data file
     */
    public void writeStats(float... stats){

        File f = new File(getDataFilename() + ".txt");

        try{            
            //set a flag to check if the file exists
            boolean isFileExists = f.exists();
            
            PrintWriter pw=new PrintWriter(
	                  new FileWriter(f,true));
            //if file does not exist
            if (!isFileExists)
                //write the flag to the file
                pw.println(statType);            
           
            //write the float[] to the file
            for(float t:stats)
                pw.println(t);
            
            pw.close();         
      
            }catch (Exception ex) {
                System.out.println(ex);
            }
    }

    public void actionPerformed(ActionEvent e) {
    }

    //testing purpose    
//    public static void main(String args []){
//         File f = new File("tttt.txt");
//         
//        try{            
//            //set a flag to check if the file exists
//            boolean isFileExists = f.exists();
//            
//            PrintWriter pw=new PrintWriter(
//	                  new FileWriter(f,true));
//            //if file does not exist
//            if (!isFileExists)
//                //write the flag to the file
//                pw.println("TIME");            
//           
//            //write the float[] to the file
//          
//                pw.println(90);
//                pw.println(99);
//            
//            pw.close();         
//      
//            }catch (Exception ex) {
//                System.out.println(ex);
//            }
//    }
}