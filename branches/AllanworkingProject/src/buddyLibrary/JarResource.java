/*
JarConvert.java
Class is used to access resources from Jar files
Team Triple Threat
Log:
02/14/2008 Allan Lei    Implementation of all methods
 */

package buddyLibrary;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import javax.imageio.ImageIO;
import sun.audio.AudioStream;

public class JarResource {

    private JarFile jar;

    public JarResource(String jarName) {
        /* Constructor for JarResource. Accepts a file location to a .jar file */
        try {
            System.out.println(jarName);
            jar = new JarFile(jarName);
            

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    
    public Image getImage(String file) {
        /* Retrieves an Image file from a jar file
         Input: String file - Name of the file to be retrieved from jar file
         Output: Image image - Image in the Image format*/
        try {
            InputStream is = jar.getInputStream(jar.getEntry(file));
            Image image = ImageIO.read(is);
            return image;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    
    public BufferedReader getText(String file) {
        /* Retrieves a text file from a jar file and returns as BufferedReader
         Input: String file - filename of the text file to be retrieved from jar file
         Output: BufferedReader read - Stores text of accessed file into a buffered reader*/
        try {
            InputStream is = jar.getInputStream(jar.getEntry(file));
            BufferedReader read = new BufferedReader(new InputStreamReader(is));
            return read;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    
    public AudioStream getWav(String file) {
        /* Retrieves a wav file from a jar file and returns as a Audiostream
         Input: String file - filename of the .wav file to be retrieved from jar file
         Output: AudioStream as - Returns the retrieved .wav as a AudioStream*/
        try {
            InputStream is = jar.getInputStream(jar.getEntry(file));
            AudioStream as = new AudioStream(is);
            return as;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    
    public void extract(String filename, String destination) {
        /* Extracts a file from a jar file and copies to a specified location
         Input: String filename - filename of the file to be extracted
                String destination - file location for the extracted file to be extracted to
         Output: None - Copies the file to destination*/
        try {
            InputStream in = jar.getInputStream(jar.getEntry(filename));
            OutputStream out = new FileOutputStream(destination + filename);
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.close();
        } catch (Exception ex) {
        }
    }
}
