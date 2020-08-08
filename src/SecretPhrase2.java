//p. 439 (Game Zone 4)
import java.util.Scanner;
public class SecretPhrase2{
    public static void main (String args[]){
        Scanner input = new Scanner(System.in);
        String[] arrOfStrings = {"Njau njaw", "No money no honey", "No man is an island"};
        int arrL = arrOfStrings.length;
        int[] lengthOfStrings = new int[arrL];
        char chToAsterisk = '*';
        for(int i = 0; i < arrL; i++)
            lengthOfStrings[i] = arrOfStrings[i].length();
        int randIndex = (int)(Math.random()*100) % arrL;
        String str = arrOfStrings[randIndex];
//to asterisks
        StringBuilder strToAsterisk = new StringBuilder(str);
        for (int i = 0; i < lengthOfStrings[randIndex]; i++)
            if (str.charAt(i) != ' ')
                strToAsterisk.setCharAt(i,chToAsterisk);
        StringBuilder sb = new StringBuilder(strToAsterisk);
        char ch;
        System.out.println("Guess a phrase \"" + sb +"\"");
        boolean isLoop;
//guess
        do{
            System.out.print("Guess a letter: ");
            ch = input.nextLine().charAt(0);
            for (int i = 0; i < lengthOfStrings[randIndex]; ++i){
                if (Character.isLetter(ch)){
                    if (Character.toLowerCase(ch) == str.toLowerCase().charAt(i))
                        if (Character.isUpperCase(str.charAt(i)))
                            sb.setCharAt(i,Character.toUpperCase(ch));
                        else
                            sb.setCharAt(i,Character.toLowerCase(ch));
                }
                else if (ch == str.charAt(i))
                    sb.setCharAt(i,ch);

            }
            isLoop = !(str.equals(sb.toString()));
            System.out.println(sb);
        } while(isLoop);
        System.out.println("Congratulation! The phrase is \"" + sb +"\"");
    }
}
