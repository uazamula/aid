import java.util.Arrays;
import java.util.Scanner;

public class ResidentialBuilding {
    static int numFloor=12;
    static int limitOfWeight = 500;//kg
    static double coefOfFullness = 0.9;
    static int isFullByMoreThan =(int)(limitOfWeight*coefOfFullness);
    static int weight=0;
    static boolean[] floorFrom = new boolean[numFloor];
    static boolean[] floorTo = new boolean[numFloor];
    static int[] floorFromInt = new int[numFloor-1];
    static int[] floorToInt = new int[numFloor-1];
    static int position=2;//initialisation
    static int x=0; //decision variable;
    static int howFullIs = 0;//0-Empty, 1 - Partially full, 2 - Full
    static int direction = 0;// -1 - down, 0 - stand, +1 - up
    static int rushHourMode = 2;// 0 - no RH, 1 - RHDown, 2 -RHUp
    static boolean isDirectionCorrect = (howFullIs!=0||direction==0);
    static boolean isMove;
    static boolean doorIsClosed;
    static int xTry=-1;
    final static int FILLED_WITH = -1;

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        boolean isLoop=true;

        Arrays.fill(floorFrom,false);
        Arrays.fill(floorTo,false);
        System.out.println(Arrays.toString(floorFromInt));
        doorIsClosed = true;
        isMove = false;
        direction = 0;
        System.out.print("Enter floor from or enter " + numFloor + " to quit:) ");
        floorFromInt[0]=kb.nextInt();//input
        if (floorFromInt[0]>=0&&floorFromInt[0]<numFloor) {
            floorFrom[floorFromInt[0]] = true;
            System.out.print("Enter an initial position (from 0 to " + (numFloor-1)+ "): ");
            position = kb.nextInt();//input
        }
        else
            isLoop = false;

        while(isLoop) {
            Arrays.fill(floorFromInt,FILLED_WITH);
            Arrays.fill(floorToInt,FILLED_WITH);
            System.out.print("Enter floor1 from or enter " + numFloor + " to quit:) ");
            floorFromInt[0]=kb.nextInt();//input
            System.out.print("Enter floor2 from: " );
            floorFromInt[1]=kb.nextInt();// input don't repeat previous values (2 in this case)
            floorFrom[floorFromInt[0]]=true;
            floorFrom[floorFromInt[1]]=true;

            System.out.print("How full is: " );
            howFullIs=kb.nextInt();
            if(howFullIs!=0) {
                System.out.print("Enter floor1 to: ");
                floorToInt[0] = kb.nextInt();//input
                System.out.print("Enter floor2 to: ");
                floorToInt[1] = kb.nextInt();// input don't repeat previous values (2 in this case)
                floorTo[floorToInt[0]] = true;
                floorTo[floorToInt[1]] = true;
            }
            System.out.print("Enter an initial position: " );
            position=kb.nextInt();
            System.out.print("Direction: " );//! move before loop
            direction=kb.nextInt();//move before loop, it will keep changing
            System.out.print("RushHourMode: ");
            rushHourMode=kb.nextInt();


            System.out.println("Initially");
            System.out.println("Lift's position: " + position);
            System.out.println("Rush hour mode: " + rushHourMode);
            System.out.println("Call From: " + Arrays.toString(floorFrom));
            System.out.println("Call From: " + Arrays.toString(floorTo));
            System.out.println("Is full? (0-empty, 1 - neither empty nor full, 2 - full): "
                    + howFullIs);
            System.out.println("direction: " + direction);
            System.out.println();



            //position=7;
            int lift = 178;
            int to = 183;
            int from = 176;
            int underline=95;
            int space =32;
            int it1=space, it2=underline,it3=space;
            //floorTo[3]=true;
            //floorTo[numFloor-1]=true;
            //floorFrom[0]=true;
            //floorFrom[4]=true;
            //for (int i =128; i<255;i++)
             //   System.out.println(i + " " +(char)i);

            System.out.println("   ___________");
            for (int i = numFloor-1; i>=0;i--) {
                if(i==position) {
                    it1 = 91;it3=93;
                }
                if(floorFrom[i])
                    it2=from;
                if(floorTo[i])
                    it1=to;
                System.out.printf("%2d",(i + 0)) ;
                System.out.println(" ____" + (char)it1 + (char)it3 +(char)it2  + "____");
                it1=space; it3=space; it2=underline;
            }
            System.out.print("RushHourMode: ");
            rushHourMode=kb.nextInt();



            switch (howFullIs) {
                case (0):
                    switch (direction){
                        case(-1):
                            x=nearest(position,0,-1);
                            break;
                        case(1):
                            x=farthestUp(position,0);
                            break;
                        case(0):
                            x= floorFromInt[0];//the first pressed call button
                    }
                    break;
                case (1):
                    switch (direction){
                        case (-1):
                            xTry=nearest(position,1,-1);
                           // if xTry=
                            break;
                        case (1):
                            xTry=-1;
                    }
                    if (position == 0) {
                        direction = 1;
                        if (rushHourMode == 1)
                            x = farthestUp(position,0);//from
                        else
                            x = nearest(position, 0, 1);
                    } else {
                        if (floorFrom[0]) {
                            if (rushHourMode == 2) {
                                x = 0;
                                direction = -1;
                            } else
                                setUp3(howFullIs);
                        } else
                            setUp1(0);
                    }
                    break;

                case (2):
                    break;
            }
            if (x==numFloor-1||x==0)//
                direction =0;
            System.out.println("Finally");
            System.out.println("direction: " + direction);
            System.out.println("Where go to?: " + x);
            System.out.println();
        }

    }
    public static void setUp3(int mode){
        x = nearest(position,mode, 0);
        if(position - x > 0)
            direction = -1;
        else
            direction = 1;

    }
    public static void setUp1(int mode){
        switch (rushHourMode){
            case (0): setUp3(mode);//no rushHour
                break;
            case (1): x=nearest(position,mode,1); //RHDown    //up
                direction = 1;
                break;
            case (2): x=nearest(position,mode,-1) ;//RHUp//down
                direction = -1;

        }
    }
    public static void setUpLiftIsNotEmpty(int mode){
        if (position==0){
            x=nearest(position,mode,1);
            direction=1;
        }
        else {
            int xTry=-1;
            switch (direction){
                case(-1):
                case (1):
                    xTry = nearest(position,mode, direction);
                    if(xTry<0)
                        direction = 0;
                    else
                        x= xTry;
                    break;
                case (0):
                    setUp1(2);//mode=2
                    break;
            }
        }
    }
    public static void setupNextStep(int m, int d){
        if(farthestUp(position,m)<0)
            if (farthestDown(position, m)>=0)
                direction=-1;
            else
                direction=0;
        else
            direction = 1;
        if(farthestDown(position,m)<0)
            if (farthestUp(position, m)>=0)
                direction=1;
            else
                direction=0;
        else
            direction = -1;

        floorFrom[x]=false;
        floorTo[x]=false;



    }

    public static int farthestUp(int x, int mode){
        int xx=-1;
        boolean[] arr= new boolean[numFloor];
        switch (mode) {
            case (0): arr = floorFrom; break;
            case (2): arr = floorTo; break;
            case (1): for (int i=0; i<numFloor; i++)
                        arr[i] = floorFrom[i]||floorTo[i];
                    break;
        }
        if (x<numFloor-1)
            for(int i = x+1; i< numFloor; i++)
                if (arr[i])
                    xx = i;
        return xx;
    }
    public static int farthestDown(int position, int mode){
        int xx=-1;
        boolean[] arr= new boolean[numFloor];
        switch (mode) {
            case (0): arr = floorFrom; break;
            case (2): arr = floorTo; break;
            case (1): for (int i=0; i<numFloor; i++)
                arr[i] = floorFrom[i]||floorTo[i];
                break;
        }
        if (x>0)
            for(int i = x-1; i>=0; i--)
            if (arr[i])
                xx = i;
        return xx;
    }

    public static int nearest(int x, int mode, int direction){
        int xx=-1;//-1 means :There is no from/to in this direction
        boolean isLoop=true;
        int diff =999;
        int min = 999;
        boolean[] arr= new boolean[numFloor];
        switch (mode) {
            case (0): arr = floorFrom; break;
            case (2): arr = floorTo; break;
            case (1): for (int i=0; i<numFloor; i++)
                arr[i] = floorFrom[i]||floorTo[i];
                break;
        }
        switch (direction) {
            case(-1): // down
                for (int i = x-1; i >= 0 && isLoop; i--)
                    if (arr[i]){
                        xx = i;
                     isLoop = false;
                    }
                break;

            case(0)://all directions
                for (int i = 0; i < numFloor; i++)
                    if (arr[i]) {
                        diff = Math.abs(x - i);
                        if (diff < min) {
                         min = diff;
                            xx = i;
                        }
                    }
                break;

            case(1):// up
                for(int i = x+1; i< numFloor && isLoop; i++)
                    if (arr[i]){
                        xx = i;
                        isLoop = false;
                    }
                break;
        }
        return xx;
    }



}
