import java.util.Locale;
import java.util.Scanner;
public class IntTo000Format {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        final int LEN=4;
        final int NAME_LENGTH=10;
        int num0=LEN-1;
        int num=0;
        double d;
        String s="ppp";
        s = String.format("%-"+NAME_LENGTH+"s", s);
        System.out.println(s+1);
        s="pp123456789p";
        //s = String.format("%-"+NAME_LENGTH+"s", s);
        System.out.println(s.substring(0,NAME_LENGTH));
        d=0.6;
        s = String.format(Locale.FRANCE,"%0"+7+".2f", d);
        System.out.println(s);
        s = String.format("%0"+7+"d", 5);
        System.out.println(s);
        while(num>=0) {
            try {
                System.out.print("Enter a positive integer (or any negative - to quit): ");
                num = kb.nextInt();
                if (num >= 0) {
                    s = Integer.toString(num);
                    while (num / 10 > 0) {
                        num /= 10;
                        num0--;
                    }
                    for (int i = num0; i > 0; i--) {
                        s = "0" + s;
                    }
                    num0 = LEN - 1;
                    System.out.println("You've got: " + s);
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
