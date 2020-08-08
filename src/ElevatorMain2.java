import java.util.Arrays;

public class ElevatorMain2 {
    static int numFloor=6;
    static boolean[] floorFrom = new boolean[numFloor];
    static boolean[] floorTo = new boolean[numFloor];
    static int[] floorFromInt = new int[numFloor-1];
    static int[] floorToInt = new int[numFloor-1];
    //static boolean isRushHourUp = false;//input
    //static boolean isRushHourDown = false;//input
    //static boolean isRushHourSetupCorrect =!(isRushHourUp && isRushHourDown);// check correctness
    //static boolean isFull = false;//input
    //static boolean isEmpty = true;//input
    //static boolean isFullnessSetupCorrect = !(isFull && isEmpty);// check correctness
   // static boolean isUp = false;//input;
   // static boolean isDown = false;//input;
    //static boolean isUpDownSetupCorrect = !(isUp && isDown);//check correctness
    static int position=0;//initialisation
    static int x=0; //decision variable;
    static int howFullIs = 0;//0-Empty, 1 - Partially full, 2 - Full
    static int direction = 0;// -1 - down, 0 - stand, +1 - up
    static int rushHourMode = 0;// 0 - no RH, 1 - RHDown, 2 -RHUp
    static boolean isDirectionCorrect = (howFullIs!=0||direction==0);

    public static void main(String[] args) {
        //
        Arrays.fill(floorFromInt,-1);
        floorFromInt[0]=2;//input
        floorFromInt[1]=3;// input don't repeat previous values (2 in this case)
        floorFrom[floorFromInt[0]]=true;
        floorFrom[floorFromInt[1]]=true;
        //floorToInt[0]=2//input
        //
        System.out.println("Initially");
        System.out.println("Lift's position: "  +position);
        System.out.println("Call From: " +floorFrom);
        System.out.println("Is full? (0-empty, 1 - neither empty nor full, 2 - full): "
                + howFullIs);
        System.out.println("direction: " + direction);
        System.out.println();

        switch(howFullIs){
            case(0):
                if(position ==0){
                    direction = 1;
                    if(rushHourMode==1)
                        x = farthestUp(0);//from
                    else
                        x = nearest(position,0,1);
                }
                else{
                    if(floorFrom[0]){
                        if(rushHourMode==2){
                            x=0;
                            direction = -1;
                        }
                        else
                            setUp3(howFullIs);
                    }
                    else
                        setUp1(0);
                }
                break;
            case(1):
                break;
            case(2):
                break;
        }
        System.out.println("Finally");
        System.out.println("Lift's position: "  +position);
        System.out.println("Call From: " +floorFrom);
        System.out.println("Is full? (0-empty, 1 - neither empty nor full, 2 - full): "
                + howFullIs);
        System.out.println("direction: " + direction);
        System.out.println();

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

    public static int farthestUp(int mode){
        int xx=-1;
        boolean[] arr= new boolean[numFloor];
        switch (mode) {
            case (0): arr = floorFrom; break;
            case (2): arr = floorTo; break;
            case (1): for (int i=0; i<numFloor; i++)
                        arr[i] = floorFrom[i]||floorTo[i];
                    break;
        }
        for(int i = 0; i< numFloor; i++)
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
