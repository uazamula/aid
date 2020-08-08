//p.721 (Exercise 10c)
/*
   Write an application that takes customer orders. Allow a user to enter
a customer number and item ordered. Display an error message if the
customer number does not exist in the customer file or the item does not
exist in the item file; otherwise, display all the customer information and
item information. Save the program as CustomerItemOrder.java.
 */

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

public class CustomerItemOrder {
    public static void main(String[] args)
    {
        Scanner input =new Scanner(System.in);
        Path fileCustomer = Paths.get("customerInformation.txt");
        Path fileItem = Paths.get("itemInformation.txt");
        final String ID_FORMAT = "000";
        final int ID_LENGTH = ID_FORMAT.length();
        final String NAME_FORMAT = "       ";
        final int NAME_LENGTH = NAME_FORMAT.length();
        final String ZIPCODE_FORMAT = "00000";
        final int ZIPCODE_LENGTH = ZIPCODE_FORMAT.length();
        String delimiter = ",";
        String sCustomer = ID_FORMAT + delimiter + NAME_FORMAT +
                delimiter + ZIPCODE_FORMAT + System.getProperty("line.separator");
        final String DESCRIPTION_FORMAT = "                    ";
        final int DESCRIPTION_LENGTH = DESCRIPTION_FORMAT.length();
        String sItem = ID_FORMAT + delimiter + DESCRIPTION_FORMAT
                + System.getProperty("line.separator");
        final int RECSIZE_CUSTOMER = sCustomer.length();
        final int RECSIZE_ITEM = sItem.length();
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
            FileChannel fcCustomer = (FileChannel)Files.newByteChannel(fileCustomer, READ, WRITE);
            FileChannel fcItem = (FileChannel)Files.newByteChannel(fileItem, READ, WRITE);
            id = QUIT-1;
            while(id!=QUIT){
                id = getInteger(minID,maxID,"id");
                data = sCustomer.getBytes();
                buffer = ByteBuffer.wrap(data);
                fcCustomer.position(id * (RECSIZE_CUSTOMER));
                fcCustomer.read(buffer);
                sCustomer = new String(data);
                if(!sCustomer.substring(0,ID_LENGTH).equals(ID_FORMAT)) {
 //                   System.out.println(sCustomer);
                    id = getInteger(minID,maxID,"item code");
                    data = sItem.getBytes();
                    buffer = ByteBuffer.wrap(data);
                    fcItem.position(id * (RECSIZE_ITEM));
                    fcItem.read(buffer);
                    sItem = new String(data);
                    if(!sItem.substring(0,ID_LENGTH).equals(ID_FORMAT))
                         System.out.print("Info about the customer: "
                                 + sCustomer.substring(0,sCustomer.length()-2)
                                 + "  Info about the item: " + sItem);
                    else
                        System.out.println("This item number does not exist. Try again.");
                }
                else if(id!=QUIT)
                    System.out.println("This ID does not exist. Try again.");
            }
            fcCustomer.close();
            fcItem.close();
        }
        catch(Exception e){
            System.out.println("Message: " + e.getMessage());
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
