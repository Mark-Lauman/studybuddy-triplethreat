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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import javax.imageio.ImageIO;
import sun.audio.AudioStream;

public class JarResource {
    JarFile jar;

    public JarResource(String jarName) {
        try {
            jar = new JarFile(jarName);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Image getImage(String file) {
        try {
            ZipEntry entry = jar.getEntry(file);
            InputStream is = jar.getInputStream(entry);
            Image image = ImageIO.read(is);
            return image;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public BufferedReader getText(String file){
        try {
            ZipEntry entry = jar.getEntry(file);
            InputStream is = jar.getInputStream(entry);
            BufferedReader read = new BufferedReader(new InputStreamReader(is));
            System.out.println(read.readLine());
            return read;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public AudioStream getWav(String file){
        try {
            ZipEntry entry = jar.getEntry(file);
            InputStream is = jar.getInputStream(entry);
            AudioStream as= new AudioStream(is);
            return as;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
}
