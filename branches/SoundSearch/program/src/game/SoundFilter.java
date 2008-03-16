/*
 * SoundFilter.java
 * 
 * Team Triple Threat
 * Log:
 * 03/16/2008 Mark Lauman Created class and implemented all methods.
 */

package game;

import java.io.File;
import java.io.FileFilter;

/**
 * A filter for abstract pathnames. Only allows files through, not directories
 * <p>
 * Instances of this class may be passed to the
 * <code>listFiles(FileFilter)</code> method of the <code>File</code> class.
 * @author Team Triple Threat
 */
public class SoundFilter implements FileFilter {
    
    /**
     * Tests whether or not the specified abstract pathname should be included
     * in a pathname list.
     * @param pathname The abstract pathname to be tested
     * @return <code>true</code> if and only if pathname  should be included
     */
    public boolean accept(File pathname) {
        return pathname.isFile();
    }
    
}