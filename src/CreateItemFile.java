//p.721 (Exercise 10a)
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

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

public class CreateItemFile {
    public static void main(String[] args)
    {
        Scanner input =new Scanner(System.in);
        Path file = Paths.get("itemInformation.txt");
        final String ID_FORMAT = "000";
        final int ID_LENGTH = ID_FORMAT.length();
        final String DESCRIPTION_FORMAT = "                    ";
        final int DESCRIPTION_LENGTH = DESCRIPTION_FORMAT.length();
        String delimiter = ",";
        String s = ID_FORMAT + delimiter + DESCRIPTION_FORMAT
                 + System.getProperty("line.separator");
        final int RECSIZE = s.length();
        int minID = 1;
        int maxID = 998;
        int NUM = maxID-minID+1;
        String idString="";
        String name;
        final int QUIT = (maxID+1);
        int id=QUIT+1;
        byte[] data;
        ByteBuffer buffer;
        boolean isLoop=false;
        try{
            FileChannel fc = (FileChannel)Files.newByteChannel(file, READ, WRITE);

            while(id!=QUIT){
                do {
                    id = getInteger(minID, maxID,"id");
                    idString = String.format("%0" + ID_LENGTH + "d", id);
                    data = idString.getBytes();
                    buffer = ByteBuffer.wrap(data);
                    fc.position(id * (RECSIZE));
                    fc.read(buffer);
                    s = new String(data);
                    isLoop = s.equals(idString);
                    if (isLoop){
                        String toOverwrite;
                        System.out.print("This ID is already exists.\n"
                                +"Choose another value or type 'yes' to overwrite\n"
                                + "Overwrite?(yes/no): ");
                        toOverwrite = input.nextLine();
                        if(toOverwrite.trim().toLowerCase().equals("yes"))
                            isLoop=false;
                    }
                }while(isLoop&&id!=QUIT);
                if(id!=QUIT) {
                    System.out.print("Enter a description for the item #" + id + ": ");
                    name = input.nextLine();
                    if (name.length() < DESCRIPTION_LENGTH)
                        name = String.format("%-" + DESCRIPTION_LENGTH + "s", name);
                    else
                        name = name.substring(0, DESCRIPTION_LENGTH);
                    s = idString + delimiter + name + System.getProperty("line.separator");
                    data = s.getBytes();
                    buffer = ByteBuffer.wrap(data);
                    fc.position(id * (RECSIZE));
                    fc.write(buffer);
                    System.out.println("The record has been added\n");
                }
            }
            fc.close();
        }
        catch(Exception e){
            System.out.println("Message: " + e);
        }
    }
    public static int getInteger(int minID, int maxID, String s){
        Scanner kb = new Scanner(System.in);
        int id=-1;
        int quit = maxID+1;
        boolean isLoop=true;
        while(id!=quit && isLoop){
            try{
                System.out.print("Enter " + s + " (" + minID + " - " + maxID +")");
                if (s.toLowerCase().equals("id"))
                    System.out.print(" or " + quit + " to quit: ");
                else {
                    System.out.print(": ");
                    quit=Integer.MIN_VALUE;
                }
                id = kb.nextInt();
                if (id >= minID && id <= maxID)
                    isLoop = false;
                else if (id!=quit)
                    System.out.println("This " + s + " is out of range. Try again.");
            }
            catch(Exception e){
                System.out.println("Format you entered is not valid. Try again.");
                kb.nextLine();
            }
        }
        return id;
    }
}
