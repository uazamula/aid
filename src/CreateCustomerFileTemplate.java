///p.721 (Exercise 10a)
/*
 Write a program that allows you to create a file of customers for a company.
The first part of the program should create an empty file suitable for writing
a three-digit ID number, six-character last name, and five-digit zip code
for each customer. The second half of the program accepts user input to
populate the file. For this exercise, assume that the user will correctly enter
ID numbers and zip codes, but force the customer name to seven characters
if it is too long or too short. Issue an error message, and do not save the
records if the user tries to save a record with an ID number that already has
been used. Save the program as CreateCustomerFile.java.
 */

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class CreateCustomerFileTemplate {
    public static void main(String[] args)
    {
        Path file = Paths.get("customerInformation.txt");
        final String ID_FORMAT = "000";
        final int ID_LENGTH = ID_FORMAT.length();
        final String NAME_FORMAT = "       ";
        final int NAME_LENGTH = NAME_FORMAT.length();
        final String ZIPCODE_FORMAT = "00000";
        final int ZIPCODE_LENGTH = ZIPCODE_FORMAT.length();
        String delimiter = ",";
        String s = ID_FORMAT + delimiter + NAME_FORMAT +
                delimiter + ZIPCODE_FORMAT + System.getProperty("line.separator");
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