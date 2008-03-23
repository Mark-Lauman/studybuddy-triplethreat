/*
 * TestBinaryData.java
 *
 * Team Triple Threat
 * Log:
 * 03/10/2008 Mark Lauman Created class & Documented it
 * 03/11/2008 Mark Lauman Added final assert statements
 */

package testers;

import java.awt.Point;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class is designed to test the functionality of Buddy's binary access
 * methods.
 * @author Team Triple Threat
 * @see <a href="../buddyLibrary/Buddy.html">buddyLibrary.Buddy</a>
 * @see <a href="../testers/BinaryTester2.html">testers.BinaryTester2</a>
 */
public class TestBinaryData extends BinaryTester2 {
    public static void main(String[] args) {
        //prepare variables
        String cwd = System.getProperty("user.dir");
        TestBinaryData t1 = new TestBinaryData();
        BinaryTester2  t2 = new BinaryTester2();
        
        //Remove these files so we can make sure all the asserts work
        File f = new File(cwd + "\\Data\\Bob");
        if(f.exists()) {
            String l[] = f.list();
            for (int i = 0; i < l.length; i++) {
                (new File(f.getAbsolutePath() + "\\" + l[i])).delete();
            }
            f.delete();
        }
        f = new File(cwd + "\\Data");
        if(f.exists()) {
            String l[] = f.list();
            for (int i = 0; i < l.length; i++) {
                (new File(f.getAbsolutePath() + "\\" + l[i])).delete();
            }
            f.delete();
        }
        System.out.println();
        assert !(new File(cwd + "\\Data")).exists() : "Data Not Removed! "
                                                      + "Remove Data Manually";
        
        //Begin
        System.out.println("--------------");
        System.out.println("Current Working Directory:");
        System.out.println(cwd);
        
        //Make the objects so that we can be sure my strange method of getting
        //buddy names works
        assert t1.getBuddyName().equals("TestBinaryData") : "A1 Failed";
        assert t2.getBuddyName().equals("BinaryTester2") : "A2 Failed";
        
        //Verify that the file access is correct
        File f1 = t1.getDataFile();
        File f2 = t2.getDataFile();
        assert f1.toString().equals(cwd + "\\Data\\Bob\\TestBinaryData.dat") : "A3 Failed";
        assert f2.toString().equals(cwd + "\\Data\\Bob\\BinaryTester2.dat") : "A4 Failed";
        
        //Try getting a data reader. Both are empty, so you should get nothing
        //and the file should not be created.
        assert t1.getDataReader() == null : "A5 Failed";
        assert t2.getDataReader() == null : "A6 Failed";
        assert !f1.exists() : "A7 Failed";
        assert !f2.exists() : "A8 Failed";
        
        //Try getting data from the file. Should return an empty ArrayList, and
        //the files still should not exist
        assert t1.getDataContent().size() == 0 : "A9 Failed";
        assert t2.getDataContent().size() == 0 : "A10 Failed";
        assert !f1.exists() : "A11 Failed";
        assert !f2.exists() : "A12 Failed";
        
        //Create some output streams. They should point to the right file. The
        //files should now exist
        ObjectOutputStream s1 = t1.getDataWriter();
        ObjectOutputStream s2 = t2.getDataWriter();
        assert s1 != null : "A13 Failed";
        assert s2 != null : "A14 Failed";
        assert f1.exists() : "A15 Failed";
        assert f2.exists() : "A16 Failed";
        
        //Try storing these values. There should be no errors
        int data = 50302;
        Point p = new Point(5, 256);
        try{
            s1.writeObject(data);
            s1.writeObject(p);
            s2.writeObject(data);
            s2.writeObject(p);
        } catch(Exception e) {
            assert false : "A17 Failed";
        }
        
        //Tidy Up :)
        try {
            s1.close();
            s2.close();
        } catch(Exception e) {
            assert false : "A18 Failed";
        }
        
        //Try getting a data reader. This should work now.
        //We do not need to test the reader passed, that is the purpose of
        //getDataContent()'s test section below
        ObjectInputStream o1 = t1.getDataReader();
        assert o1 != null : "A19 Failed";
        ObjectInputStream o2 = t2.getDataReader();
        assert o2 != null : "A20 Failed";
        
        //Tidy Up again :)
        try {
            o1.close();
            o2.close();
        } catch(Exception e) {
            assert false : "A20 Failed";
        }
        
        //Try reading the objects. They should equal the ones stored.
        ArrayList<Object> al1 = t1.getDataContent();
        ArrayList<Object> al2 = t2.getDataContent();
        assert al1 != null : "A20 Failed";
        assert al2 != null : "A21 Failed";
        assert al1.size() == 2 : "A22 Failed";
        assert al2.size() == 2 : "A23 Failed";
        assert (Integer)al1.get(0) == data : "A22 Failed";
        assert (Integer)al2.get(0) == data : "A23 Failed";
        assert ((Point)al1.get(1)).equals(p) : "A24 Failed";
        assert ((Point)al2.get(1)).equals(p) : "A25 Failed";
        
        System.out.println("ALL TESTS PASSED\n--------------\n");
    }
    
    public TestBinaryData() {
        super();
    }
    
    // -------------------------------------------------------------- //
    
    // -------------------------------------------------------------- //
    
}
