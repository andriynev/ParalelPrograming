import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;


public class Lab3 {

    private static final int SIZE = 10;

    public static void main(String[] args) {

        int [] array = new int [SIZE];

        AtomicInteger atomicArrayCounter = new AtomicInteger();
        AtomicInteger atomicArrayMax = new AtomicInteger();
        AtomicInteger atomicArrayMin = new AtomicInteger();
        AtomicInteger atomicSum = new AtomicInteger();


        /**
         * Fill array random number
         */
        for (int i = 0; i < SIZE; i++) {
            array[i] = (int)((Math.random()*1000));
        }


        /**
         *  get array size parallel
         */
        IntStream.of(array).parallel().forEach(x-> {
            int oldValueCounter;  int newValueCounter;
            do {
                oldValueCounter = atomicArrayCounter.get();
                newValueCounter = oldValueCounter + 1;
            }
            while(!atomicArrayCounter.compareAndSet(oldValueCounter , newValueCounter));
        });
        System.out.printf("Array size : %d\n",atomicArrayCounter.get());


        /**
         *  get max number in array parallel
         */
        IntStream.of(array).parallel().forEach(x-> {
            int oldValueMax;  int newValueMax;
            do {
                oldValueMax = atomicArrayMax.get();
                newValueMax = Math.max(oldValueMax, x);
            }
            while(!atomicArrayMax.compareAndSet(oldValueMax , newValueMax));
        });

        System.out.printf("Max value in array : %d\n",atomicArrayMax.get());


        /**
         *  get min number in array parallel
         */
        atomicArrayMin.set(Integer.MAX_VALUE);
        IntStream.of(array).parallel().forEach(x-> {
            int oldValueMin;  int newValueMin;
            do {
                oldValueMin = atomicArrayMin.get();
                newValueMin = Math.min(oldValueMin, x);
            }
            while(!atomicArrayMin.compareAndSet(oldValueMin , newValueMin));
        });

        System.out.printf("Min value in array : %d\n",atomicArrayMin.get());


        /**
         *  get sum number in array parallel
         */
        IntStream.of(array).parallel().forEach( x-> {
            int oldValue, newValue;
            do{
                oldValue = atomicSum.get();
                newValue = oldValue + x;
            }while(!atomicSum.compareAndSet(oldValue , newValue));
        });
        System.out.printf("Sum  array : %d\n\n",atomicSum.get());

    }

}