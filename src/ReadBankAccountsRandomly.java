//p.721 (Exercise 9c)
/*
  Create an application that uses the file created by the user in Exercise 9a
and allows the user to enter an account number to view the account
balance. Allow the user to view additional account balances until
entering an application-terminating value of 9999. Save the file as
ReadBankAccountsRandomly.java.
 */

import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;
public class ReadBankAccountsRandomly {
    public static void main(String[] args)
    {
        Scanner input =new Scanner(System.in);
        Path file = Paths.get("accountInformation.txt");
        final String ID_FORMAT = "0000";
        final int ID_LENGTH = ID_FORMAT.length();
        final String NAME_FORMAT = "        ";
        final int NAME_LENGTH = NAME_FORMAT.length();
        final String BALANCE_FORMAT = "00000.00";
        final double MAX_BALANCE = 99999.99;
        final int BALANCE_LENGTH = BALANCE_FORMAT.length();
        String delimiter = ",";
        String s = ID_FORMAT + delimiter + NAME_FORMAT +
                delimiter + BALANCE_FORMAT + System.getProperty("line.separator");
        final int RECSIZE = s.length();
        int minID = 1;
        int maxID = 9998;
        int NUM = maxID-minID+1;
        String idString="";
        String name;
        String balance;
        String[] array;
        double balanceD=0;
        final int QUIT = (maxID+1);
        int id=QUIT+1;
        byte[] data = s.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(data);
        boolean isLoop=false;
        try{
            FileChannel fc = (FileChannel)Files.newByteChannel(file, READ, WRITE);
            id = QUIT-1;
            while(id!=QUIT){
                id = getID(minID,maxID);
                fc.position(id * (RECSIZE));
                fc.read(buffer);
                s = new String(data);
                data = s.getBytes();
                buffer = ByteBuffer.wrap(data);
                if(!s.substring(0,ID_LENGTH).equals(ID_FORMAT)) {
                    array = s.split(delimiter);
                    System.out.println("Balance of customer #" + id + " is $"
                            + String.format(Locale.US,"%.2f",Double.parseDouble(array[2])));
                }
                else
                    System.out.println("This account does not exist");
            }
            fc.close();
        }
        catch(Exception e){
            System.out.println("Message: " + e);
        }
    }
    public static int getID(int minID, int maxID){
        Scanner kb = new Scanner(System.in);
        int id=-1;
        final int QUIT = maxID+1;
        boolean isLoop=true;
        while(id!=QUIT && isLoop){
            try{
                System.out.print("Enter id (" + minID + " - " + maxID +") or " + QUIT + " to quit: ");
                id = kb.nextInt();
                if (id >= minID && id <= maxID)
                    isLoop = false;
                else if (id!=QUIT)
                    System.out.println("This id is out of range. Try again.");
            }
            catch(Exception e){
                System.out.println("Format you entered is not valid. Try again.");
                kb.nextLine();
            }
        }
        return id;
    }
}
