//p.721 (Exercise 9a)
/*
 The Rochester Bank maintains customer records in a random access file.
Write an application that creates 10,000 blank records and then allows the
user to enter customer account information, including an account number
that is 9998 or less, a last name, and a balance. Insert each new record into
a data file at a location that is equal to the account number. Assume that
the user will not enter invalid account numbers. Force each name to eight
characters, padding it with spaces or truncating it if necessary. Also assume
that the user will not enter a bank balance greater than 99,000.00. Save the
file as CreateBankFile.java.
 */


import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;

public class CreateBankFile {
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
        byte[] data;
        ByteBuffer buffer;
        boolean isLoop=false;
        try{
            FileChannel fc = (FileChannel)Files.newByteChannel(file, READ, WRITE);

            while(id!=QUIT){
                do {
                    id = getID(minID, maxID);
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
                    System.out.print("Enter name for customer #"
                            + id + "(8 chars exactly): ");
                    name = input.nextLine();
                    if (name.length() < NAME_LENGTH)
                        name = String.format("%-" + NAME_LENGTH + "s", name);
                    else
                        name = name.substring(0, NAME_LENGTH);
                    balanceD = getBalance(MAX_BALANCE);
                    balance = String.format(Locale.US, "%0" + BALANCE_LENGTH + ".2f", balanceD);
                    s = idString + delimiter + name + delimiter
                            + balance + System.getProperty("line.separator");
                    data = s.getBytes();
                    buffer = ByteBuffer.wrap(data);
                    fc.position(id * (RECSIZE));
                    fc.write(buffer);
                }
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

    public static double getBalance(double max){
        Scanner kb = new Scanner(System.in);
        double balance=0;
        boolean isLoop=true;
        while(isLoop){
            try{
                System.out.print("Enter balance : ");
                balance = kb.nextDouble();
                if (balance <0)
                    System.out.println("Balance is negative. Try again.");
                else if(balance>max)
                    System.out.println("Balance is too high. Try again.");
                else
                    isLoop = false;
            }
            catch(Exception e){
                System.out.println("Format you entered is not valid. Try again.");
                kb.nextLine();
            }
        }
        return balance;
    }

}
