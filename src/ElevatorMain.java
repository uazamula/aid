import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ElevatorMain {
    static int numFloor=6;
    static boolean[] floorFrom = new boolean[numFloor];
    static boolean[] floorTo = new boolean[numFloor];
    static int[] floorFromInt = new int[numFloor-1];
    static int[] floorToInt = new int[numFloor-1];
    static int position=0;//initialisation
    static boolean isRushHourUp = false;//input
    static boolean isRushHourDown = false;//input
    static boolean isRushHourSetupCorrect =!(isRushHourUp && isRushHourDown);// check correctness
    static boolean isFull = false;//input
    static boolean isEmpty = true;//input
    static boolean isFullnessSetupCorrect = !(isFull && isEmpty);// check correctness
    static boolean isUp = false;//input;
    static boolean isDown = false;//input;
    static boolean isUpDownSetupCorrect = !(isUp && isDown);//check correctness
    static int x=0; //decision variable;
    static int howFullIs = 0;//0-Empty, 1 - Partially full, 2 - Full
    static int direction = 0;// -1 - down, 0 - stand, +1 - up

    public static void main(String[] args) {
        //
        Arrays.fill(floorFromInt,-1);
        floorFromInt[0]=2;//input
        floorFromInt[1]=3;// input don't repeat previous values (2 in this case)
        floorFrom[floorFromInt[0]]=true;
        floorFrom[floorFromInt[1]]=true;
        //floorToInt[0]=2//input
        //

        switch(howFullIs){
            case(0):
                if(position ==0){
                    isUp = true; direction = 1;
                    if(isRushHourDown)
                        x=farthestUpFrom();
                    else
                        x= nearestUpFrom(position);
                }
                else{
                    if(floorFrom[0]){
                        if(isRushHourUp){
                            x=0;
                            isDown=true; direction = -1;
                        }
                        else{
                            //3*
                            //x=nearest from
                            if (position-x>0)
                                isDown=true;
                            else
                                isUp = true;
                        }
                    }
                    else{
                        //1*

                    }

                }
                break;
            case(1):
                break;
            case(2):
                break;
        }

    }
    public static void setUp3From(){
        if(howFullIs == 0 ){
            // x= nearest from
        }
        if(howFullIs == 1 ||howFullIs ==2){
            // x= nearest to
        }
        if(position - x > 0)
            isDown = true;
        else
            isUp = true;

    }
 /*   public static int farthestUpFrom(){
        int xx=-1;
        for(int i = 0; i< numFloor; i++)
            if (floorFrom[i])
                xx = i;
        return xx;
    }
    public static int farthestUpTo(){
        int xx=-1;
        for(int i = 0; i< numFloor; i++)
            if (floorTo[i])
                xx = i;
        return xx;
    }
    public static int farthestUpFromTo(){
        int xx=-1;
        for(int i = 0; i< numFloor; i++)
            if (floorTo[i]||floorFrom[i])
                xx = i;
        return xx;
    }

  */
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


   /* public static int nearestUpFrom(int x){
        int xx=-1;
        boolean isLoop=true;
        for(int i = x+1; i< numFloor && isLoop; i++)
            if (floorFrom[i]){
                xx = i;
                isLoop = false;
            }
        return xx;
    }
    public static int nearestUpTo(int x){
        int xx=-1;
        boolean isLoop=true;
        for(int i = x+1; i< numFloor && isLoop; i++)
            if (floorTo[i]){
                xx = i;
                isLoop = false;
            }
        return xx;
    }
    public static int nearestUpFromTo(int x){
        int xx=-1;
        boolean isLoop=true;
        for(int i = x+1; i< numFloor && isLoop; i++)
            if (floorFrom[i]||floorTo[i]){
                xx = i;
                isLoop = false;
            }
        return xx;
    }

    */
    public static int nearestUp(int x, int mode){
        int xx=-1;
        boolean isLoop=true;
        boolean[] arr= new boolean[numFloor];
        switch (mode) {
            case (0): arr = floorFrom; break;
            case (2): arr = floorTo; break;
            case (1): for (int i=0; i<numFloor; i++)
                arr[i] = floorFrom[i]||floorTo[i];
                break;
        }
        for(int i = x+1; i< numFloor && isLoop; i++)
            if (arr[i]){
                xx = i;
                isLoop = false;
            }
        return xx;
    }



 /*   public static int nearestDownFrom(int x) {
        int xx = -1;
        boolean isLoop = true;
        for (int i = x-1; i >= 0 && isLoop; i--)
            if (floorFrom[i]) {
                xx = i;
                isLoop = false;
            }
        return xx;
    }
    public static int nearestDownTo(int x){
        int xx=-1;
        boolean isLoop=true;
        for (int i = x-1; i >= 0 && isLoop; i--)
            if (floorTo[i]){
                xx = i;
                isLoop = false;
            }
        return xx;
    }
    public static int nearestDownFromTo(int x){
        int xx=-1;
        boolean isLoop=true;
        for (int i = x-1; i >= 0 && isLoop; i--)
            if (floorTo[i]||floorFrom[i]){
                xx = i;
                isLoop = false;
            }
        return xx;
    }


  */
    public static int nearestDown(int x, int mode){
        int xx=-1;
        boolean isLoop=true;
        boolean[] arr= new boolean[numFloor];
        switch (mode) {
            case (0): arr = floorFrom; break;
            case (2): arr = floorTo; break;
            case (1): for (int i=0; i<numFloor; i++)
                arr[i] = floorFrom[i]||floorTo[i];
                break;
        }
        for (int i = x-1; i >= 0 && isLoop; i--)
            if (arr[i]){
                xx = i;
                isLoop = false;
            }
        return xx;
    }

  /*  public static int nearestFrom(int x){
        int xx=-1;
        int diff =999;
        int min = 999;
        for(int i = 0; i< numFloor; i++)
            if (floorFrom[i]){
                diff=Math.abs(x-i);
                if (diff<min ) {
                    min = diff;
                    xx = i;
                }
            }
        return xx;
    }
    public static int nearestTo(int x){
        int xx=-1;
        int diff =999;
        int min = 999;
        for(int i = 0; i< numFloor; i++)
            if (floorTo[i]){
                diff=Math.abs(x-i);
                if (diff<min ) {
                    min = diff;
                    xx = i;
                }
            }
        return xx;
    }
    public static int nearestFromTo(int x){
        int xx=-1;
        int diff =999;
        int min = 999;
        for(int i = 0; i< numFloor; i++) {
            if (floorFrom[i]||floorTo[i]) {
                diff = Math.abs(x - i);
                if (diff < min) {
                    min = diff;
                    xx = i;
                }
            }
        }
        return xx;
    }

   */
    public static int nearest(int x, int mode){
        int xx=-1;
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

        for(int i = 0; i< numFloor; i++) {
            if (arr[i]) {
                diff = Math.abs(x - i);
                if (diff < min) {
                    min = diff;
                    xx = i;
                }
            }
        }
        return xx;
    }



}
