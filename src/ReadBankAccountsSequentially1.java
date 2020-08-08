//p.721 (Exercise 9b) Use 2 better
/*
 Create an application that uses the file created by the user in Exercise 9a
and displays all existing accounts in account-number order. Save the file as
ReadBankAccountsSequentially.java.
 */

import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;
public class ReadBankAccountsSequentially1 {
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
        double balanceD=0;
        final int QUIT = (maxID+1);
        int id=QUIT+1;
        byte[] data = s.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(data);
        boolean isLoop=false;
        try{
            FileChannel fc = (FileChannel)Files.newByteChannel(file, READ, WRITE);
            id = 0;
            while(id<=maxID){
                fc.position(id * (RECSIZE));
                fc.read(buffer);
                s = new String(data);
                data = s.getBytes();
                buffer = ByteBuffer.wrap(data);
                if(!s.substring(0,ID_LENGTH).equals(ID_FORMAT))
                    System.out.print(s);
                id++;
            }
            fc.close();
        }
        catch(Exception e){
            System.out.println("Message: " + e);
        }
    }
}
