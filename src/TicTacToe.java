//p. 491 (Game Zone 2)
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

public class TicTacToe{
	public static void main (String args[]){
		String fileS = "idpasswords.txt";
		Path file = Paths.get(fileS);
		byte[] data;
		String array[];
		String delimiter = ",";
		final String ID = "000";
		final String PASSWORD ="          ";
		String str = ID + delimiter + PASSWORD + delimiter + System.getProperty("line.separator");
		String id, password;
		int RECSIZE = str.length();
		boolean isPlay = false;
		try{
			Scanner kb = new Scanner(System.in);
			System.out.print("Enter your id (format XXX):");
			id = kb.nextLine();
			System.out.print("Enter your password (format XXX):");
			password = kb.nextLine();

			FileChannel fc = (FileChannel) Files.newByteChannel(file,READ,WRITE);
			data = str.getBytes();
			ByteBuffer buffer = ByteBuffer.wrap(data);
			fc.position(Integer.parseInt(id) * (RECSIZE));
			fc.read(buffer);
			str = new String(data);
			array = str.split(delimiter);
			if (array[0].equals(id) && array[1].trim().equals(password))
				isPlay = true;
			fc.close();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		System.out.println(str);

		if(isPlay) {
			final int NUM = 3;
			String[][] ttts = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};
			Scanner input = new Scanner(System.in);
			String s, s1 = "", s2 = "", s3 = "", s4 = "";
			int arr[] = new int[NUM * NUM];
			for (int i = 0; i < NUM * NUM; i++)
				arr[i] = i + 1;
			int x, y = 0, ii = 0, jj = 0;
			int count = 0;
			boolean isTrue = true, isNotChosen = true;

			while (count < NUM * NUM && isTrue) {
				System.out.print("Enter a number(1-9): ");
				s = input.nextLine();
				x = Integer.parseInt(s);
				for (int i = 0; i < NUM * NUM; i++) System.out.print(arr[i] + " ");
				System.out.println();

				for (int i = 0; i < NUM * NUM; i++)
					if (x == arr[i])
						arr[i] = 99;
				Arrays.sort(arr);
				for (int i = 0; i < NUM; i++) {
					for (int j = 0; j < NUM; j++) {
						if (j + i * NUM + 1 == x)
							ttts[i][j] = "X";
						System.out.print(ttts[i][j] + " ");
					}
					System.out.println();
				}
				count++;
//if player is winner
				for (int i = 0; i < NUM; i++)
					if ((ttts[i][0] + ttts[i][1] + ttts[i][2]).equals("XXX")
							|| (ttts[0][i] + ttts[1][i] + ttts[2][i]).equals("XXX")) {
						count = NUM * NUM;
						System.out.println("Player has won");
					}
				if ((ttts[0][0] + ttts[1][1] + ttts[2][2]).equals("XXX")
						|| (ttts[0][2] + ttts[1][1] + ttts[2][0]).equals("XXX")) {
					count = NUM * NUM;
					System.out.println("Player has won");
				}

//it concerns a computer
				if (count < NUM * NUM) {

//There is two O in a line and comp is going to win

					ii = -1;
					jj = -1;
					s3 = "";
					s4 = "";
					for (int i = 0; i < NUM; i++) {
						s1 = "";
						s2 = "";
						for (int j = 0; j < NUM; j++) {
							if (ttts[i][j].equals("O") || ttts[i][j].equals("X"))
								s1 = s1 + ttts[i][j];
							if (ttts[j][i].equals("O") || ttts[j][i].equals("X"))
								s2 = s2 + ttts[j][i];
						}
						if (ttts[i][i].equals("O") || ttts[i][i].equals("X"))
							s3 = s3 + ttts[i][i];
						if (ttts[i][NUM - i - 1].equals("O") || ttts[i][NUM - i - 1].equals("X"))
							s4 = s4 + ttts[i][NUM - i - 1];
						if ((s1).equals("OO")) ii = i;
						if ((s2).equals("OO")) jj = i;
						System.out.println("ii=" + ii + "jj=" + jj);
					}
					for (int i = 0; i < NUM; i++) {
						if (ii >= 0 && ttts[ii][i] != "O") ttts[ii][i] = "O";
						if (jj >= 0 && ttts[i][jj] != "O") ttts[i][jj] = "O";
						if (s3.equals("OO") && ttts[i][i] != "O") ttts[i][i] = "O";
						if (s4.equals("OO") && ttts[NUM - i - 1][i] != "O") ttts[NUM - i - 1][i] = "O";
					}
					if (!(ii >= 0 || jj >= 0 || s3.equals("OO") || s4.equals("OO"))) {

//there is not two OO yet in a line and comp choose a random position

						y = (int) (Math.random() * 1000) % (arr.length - count);
						for (int i = 0; i < NUM; i++) {
							for (int j = 0; j < NUM; j++) {
								if (j + i * NUM + 1 == arr[y])
									ttts[i][j] = "O";
								System.out.print(ttts[i][j] + " ");
							}
							System.out.println();
						}
						for (int i = 0; i < NUM * NUM - count; i++)
							if (arr[y] == arr[i])
								arr[i] = 99;
						Arrays.sort(arr);
					} else {
						for (int i = 0; i < NUM; i++) {
							for (int j = 0; j < NUM; j++)
								System.out.print(ttts[i][j] + " ");
							System.out.println();
						}
					}
//if computer is a winner
					for (int i = 0; i < NUM; i++)
						if ((ttts[i][0] + ttts[i][1] + ttts[i][2]).equals("OOO")
								|| (ttts[0][i] + ttts[1][i] + ttts[2][i]).equals("OOO")) {
							count = NUM * NUM;
							System.out.println("Computer has won");
						}
					if (count < NUM * NUM)
						if ((ttts[0][0] + ttts[1][1] + ttts[2][2]).equals("OOO")
								|| (ttts[0][2] + ttts[1][1] + ttts[2][0]).equals("OOO")) {
							count = NUM * NUM;
							System.out.println("Computer has won");
						}
					count++;
				}
			}
		}


	}
}