package support;
//for editable
import javax.swing.JTextArea;
import java.awt.Font;

//for opening a file
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.JCheckBoxMenuItem;

//for writing a file
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//for appending dates
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Utilities
{
 // open file; should only be called from framepanel and requires
 // jfilechooser and editable
 public String openFile(JTextArea editable, File file)
 {
     editable.setText(null);

     // instantiate the scanner and make string pointing to filepath
     Scanner fileReader = null;
     String filePath = file.getPath();

     // try to open file
     try
     {
         fileReader = new Scanner(new FileReader(filePath));

         // loop until all files are appended to editable
         while (fileReader.hasNextLine())
             editable.append(fileReader.nextLine() + "\n");
     }
     // catch any errors and exceptions
     catch (Exception except) {
         except.printStackTrace();
     }
     // close the reader
     finally {
         fileReader.close();
     }

     return file.getName();
 }

 // save file; should only be called from framepanel and requires
 // jfilechooser and editable
 public String saveFile(JTextArea editable, File file, boolean overWrite)
 {
     String fileName = file.getName();
     String text = editable.getText();
     BufferedWriter writer = null;

     if (overWrite)
     {
         try
         {
             writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
             writer.write(""); // to clear out text
             writer.append(text);
         }
         catch (Exception e) { e.printStackTrace(); }
         finally
         {
             try
             {
                 writer.flush();
                 writer.close();
             }
             catch (Exception e) { e.printStackTrace(); }
         }

         fileName += "0";
     }
     else
     {
         try
         {
             writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
             writer.write(text);
         }
         catch (Exception e) { e.printStackTrace(); }
         finally
         {
             try
             {
                 writer.flush();
                 writer.close();
             }
             catch (Exception e) { e.printStackTrace(); }
         }

         fileName += "1";
     }

     return fileName;
 }

 // gives date to caller
 public String getDate()
 {
     DateFormat dateList = new SimpleDateFormat("hh:mm MM/dd/yyyy");
     Date date = new Date();
     return dateList.format(date);
 }

 // changes font of Jtextarea
 public void changeFont(JTextArea editable)
 {
     Font current = editable.getFont();
 }
}
