import java.util.Random;

class ThreadCalc extends Thread {

    int[] vectA;
    int startIndex;
    int endIndex;
    int max, min;

    public ThreadCalc(int[] vectA, int startIndex, int endIndex) {
        this.vectA = vectA;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run(){
        max = vectA[startIndex];
        min = vectA[startIndex];
        for(int i = startIndex + 1; i<endIndex; i++ ){
           if (vectA[i] > max) {
               max = vectA[i];
           } else if (vectA[i] < min) {
               min = vectA[i];
           }
        }
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}

public class ThreadTest {
    public static int SIZE = 10000000;
    public static int NUMBER_JOBS = 5;

    public static void main(String [] args ) throws InterruptedException{

        int[] vectA = new int [SIZE];
        Random random = new Random();
        for(int i =0; i<SIZE; i++){
            vectA[i]=random.nextInt();
        }

        long startTime = System.currentTimeMillis();
        ThreadCalc serial = new ThreadCalc(vectA, 0, SIZE);
        serial.run();
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println("Serial result: Max=" + serial.getMax() + " Min=" + serial.getMin());



        ThreadCalc[] TreadArray = new ThreadCalc[NUMBER_JOBS];
        startTime = System.currentTimeMillis();
        for(int i = 0; i < NUMBER_JOBS; i++){

            TreadArray[i] = new ThreadCalc(vectA ,
                    SIZE/ NUMBER_JOBS * i,
                    i== NUMBER_JOBS -1 ?SIZE:SIZE/ NUMBER_JOBS * (i + 1) );
            TreadArray[i].start();

        }
        for(int i = 0; i < NUMBER_JOBS; i++){
            TreadArray[i].join();
        }
        int min = 0, max = 0;
        for(int i = 0; i < NUMBER_JOBS; i++){
            if (TreadArray[i].getMin() < min) {
                min = TreadArray[i].getMin();
            }
            if (TreadArray[i].getMax() > max) {
                max = TreadArray[i].getMax();
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println("Parallel result: Max=" + max + " Min=" + min);
    }


}
