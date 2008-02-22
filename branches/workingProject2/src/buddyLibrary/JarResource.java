package buddyLibrary;

/*
JarConvert.java
Class is used to access resources from Jar files
Team Triple Threat
Log:
02/14/2008 Allan Lei    Implementation of all methods
 */
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
        try {
            System.out.println(jarName);
            jar = new JarFile(jarName);
            

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Retrieves an Image file from a jar file
    public Image getImage(String file) {
        try {
            InputStream is = jar.getInputStream(jar.getEntry(file));
            Image image = ImageIO.read(is);
            return image;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    //Retrieves a text file from a jar file and returns as BufferedReader
    public BufferedReader getText(String file) {
        try {
            InputStream is = jar.getInputStream(jar.getEntry(file));
            BufferedReader read = new BufferedReader(new InputStreamReader(is));
            return read;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    //Retrieves a wav file from a jar file and returns as a Audiostream
    public AudioStream getWav(String file) {
        try {
            InputStream is = jar.getInputStream(jar.getEntry(file));
            AudioStream as = new AudioStream(is);
            return as;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    //Extracts a file from a jar file and copies to a specified location
    public void extract(String filename, String destination) {
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
