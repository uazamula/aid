import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Elevator {
    public static void main(String[] args) {
        int numFloor = 6;
        boolean[] floorFrom = new boolean[numFloor];
        boolean[] floorTo = new boolean[numFloor];
        int[] floorFromInt = new int[numFloor-1];
        int[] floorToInt = new int[numFloor-1];
        //
        Arrays.fill(floorFromInt,-1);
        floorFromInt[0]=2;//input
        floorFromInt[1]=3;// input don't repeat previous values
        //floorToInt[0]=2//input
        //

        int position=0;//initialisation
        boolean isRushHourUp = false;//input
        boolean isRushHourDown = false;//input
        boolean isRushHourSetupCorrect =!(isRushHourUp && isRushHourDown);// check correctness
        boolean isFull = false;//input
        boolean isEmpty = true;//input
        boolean isFullnessSetupCorrect = !(isFull && isEmpty);// check correctness
        boolean isUp = false;//input;
        boolean isDown = false;//input;
        boolean isUpDownSetupCorrect = !(isUp && isDown);//check correctness
        int x=0; //decision variable;
        int howFullIs = 0;//0-Empty, 1 - Partially full, 2 - Full
        int direction = 0;// -1 - down, 0 - stand, +1 - up
        switch(howFullIs){
            case(0):
                if(position ==0){
                    isUp = true; direction = 1;
                    if(isRushHourDown){
                        //x=fahrthest up;
                    }
                    else{
                        // x= nearest up from;
                    }
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
                            if (position-x>0){
                                isDown=true;
                            }
                            else{
                                isUp = true;
                            }

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
  /*  public void setUp3(){
        if(howFullIs == 0 ){
            // x= nearest from
        }
        if(howFullIs == 1 ){
            // x= nearest to
        }
        if(position - x > 0)
            isDown;
        else
            isUp;

    }
    */

}
