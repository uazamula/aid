///p.721 (Exercise 10b)
/*
 Write a program that creates a file of items carried by the company. Include a
three-digit item number and up to a 20-character description for each item.
Issue an error message if the user tries to store an item number that already
has been used. Save the program as CreateItemFile.java.
 */

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class CreateItemFileTemplate {
    public static void main(String[] args)
    {
        Path file = Paths.get("itemInformation.txt");
        final String ID_FORMAT = "000";
        final int ID_LENGTH = ID_FORMAT.length();
        final String DESCRIPTION_FORMAT = "                    ";
        final int DESCRIPTION_LENGTH = DESCRIPTION_FORMAT.length();
        String delimiter = ",";
        String s = ID_FORMAT + delimiter + DESCRIPTION_FORMAT
                 + System.getProperty("line.separator");
        final int NUMRECS = 1000;
        final int RECSIZE = s.length();
        try
        {
            OutputStream output = new
                    BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new
                    BufferedWriter(new OutputStreamWriter(output));
            for(int count = 0; count < NUMRECS; ++count)
                writer.write(s, 0, s.length());
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("Error message: " + e);
        }
    }
}