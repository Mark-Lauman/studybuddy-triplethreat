### Introduction ###

A class file is divided into sections, with one empty space between each section. There are no empty spaces inside sections unless otherwise noted.

Comments follow the [JavaDoc standards](http://java.sun.com/j2se/javadoc/writingdoccomments/), unless inside a method. Whenever you mention a variable inside a method, be sure to surround it with ```
 ```

An example class file for these rules may be found [here](http://code.google.com/p/studybuddy-triplethreat/source/browse/branches/workingProject2/src/testers/CodingStyleExample.java).

To build the documents when you are done commenting, choose "Build" then "Generate JavaDoc for ProjectName"

### Order: ###
> #### 1. Header ####
```
 /*
  * Name_of_Java_file
  *
  * Team Triple Threat
  * Log:
  * MM/DD/YYYY Name_of_Modifier Modification_Done
   */
```
> Each alteration has its own log line. These lines are ordered chronologically, with the most recent one on the top. All the !Name\_of\_Modifiers should line up, as well as all the Modification\_Dones.

> #### 2. Package name ####
```
 package package_name;
```

> #### 3. Import statements ####
```
 import import_path;
```
> Ordered Alphabetically. Packages always come after classes (ie, "java.awt.Graphics" comes before "java.awt.event.ActionEvent")

> #### 4. Class Declaration ####
```
 /**
  * Class Description
  * @author Team Triple Threat
  * @see <a href="JAVA_URL">DISPLAYED_LINK</a>
  */
 class_declaration
```
  * The class description is separated into paragraphs using the <p> command<br>
<ul><li>For each class you import that affects your public methods and variables, include an @see statement linking us to its documentation. JAVA_URL is the location of the document, while DISPLAYED_LINK should be its full name (like "javax.swing.JFrame" or "buddyLibrary.Buddy")<br>
<ul><li>for one of our classes, the documentation is always located at "../packageName/buddyName.html". Note the two periods at the beginning of the path - they are essential<br>
</li><li>for a Java API class, it is always located at "<a href='http://java.sun.com/javase/6/docs/api/'>http://java.sun.com/javase/6/docs/api/</a>" then its pathname. For instance, the pathname of "java.awt.Color" is "java/awt/Color.html"<br>
</li></ul></li><li>@see statements are ordered just like their import statements are<br>
</li><li>If you need an additional line for either of the @ lines, create a new line, and start typing with the left edge lining up with your description on the above line.</li></ul>

<blockquote><h4>5. Objects of the class</h4>
<pre><code> /** Object description */<br>
 object_declaration<br>
</code></pre>
Objects are ordered alphabetically: first by type, then by name. <b>Note:</b> There is no white space between objects.</blockquote>

<blockquote><h4>6. Functions</h4>
<pre><code>   /**<br>
    * Function Description<br>
    * @param parameter_name parameter_description<br>
    * @return description_of_returned_value<br>
   */<br>
   @Override<br>
   scope return_type func_name(params) {<br>
     //function is implemented...<br>
   }<br>
</code></pre>
<ul><li>The function description works the same way as the class description.<br>
</li><li>Each parameter has its own @param line. These lines follow the order of the parameters inside the brackets of the function.<br>
</li><li>If you need an additional line for either of the @ lines, create a new line, and start typing with the left edge lining up with your description on the above line.<br>
</li><li>Don't have an @param line if there are no parameters, and don't have a @return line if the return type is void.<br>
</li><li>If the function overrides its parent's function, the @Override line is included. Otherwise, leave it out.<br>
</li><li><b>Tip:</b> If you type in the function, then try to place the comment in the line above (remember the double asterisks at the beginning!) then NetBeans will place the @lines in the correct order for you.</li></ul></blockquote>

<blockquote>Inside categories, functions are ordered first alphabetically by name, then by the number of parameters in ascending order. They are separated from other functions by one space.</blockquote>

<blockquote>Function categories (in order) are:<br>
<ol><li>Constructors<br>
</li><li>public static void main<br>
</li><li>A horizontal rule<br>
<ul><li>A comment line starting with // ending with // and containing several dashes.  The line ends 12 characters before the red line in NetBeans.<br>
</li></ul></li><li>Setters, Getters and Misc functions<br>
<ul><li>Setters start with set<br>
</li><li>Getters start with get<br>
</li></ul></li><li>A horizontal rule<br>
</li><li>Overridden functions<br>
</li><li>Interface functions</li></ol></blockquote>

<h3>Exceptions</h3>
<blockquote>Inside functions programmers have free reign for commenting. Make sure the comments are understandable to a reader.</blockquote>

<hr />
<a href='updatedDesignDoc.md'>Back to updated Design Document</a>