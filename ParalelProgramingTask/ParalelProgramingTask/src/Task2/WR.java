package Task2;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class WR {
    private int readCount;
    private int writeCount;
    private int value = 0;
    private Semaphore x = new Semaphore(1);
    private Semaphore y = new Semaphore(1);
    private Semaphore z = new Semaphore(1);
    private Semaphore rsem = new Semaphore(1);
    private Semaphore wsem = new Semaphore(1);
    private Random rand = new Random(System.currentTimeMillis());

    public void reader() throws InterruptedException {
            z.acquire();
            rsem.acquire();
            x.acquire();
            readCount++;
            if (readCount == 1) {
                wsem.acquire();
            }
            x.release();
            rsem.release();
            z.release();
            System.out.println("Current value: " + value);
            x.acquire();
            readCount--;
            if (readCount == 0) {
                wsem.release();
            }
            x.release();
    }

    public void writer() throws InterruptedException {
            y.acquire();
            writeCount++;
            if (writeCount == 1) {
                rsem.acquire();
            }
            y.release();
            wsem.acquire();
            value = rand.nextInt();
            System.out.println("Value changed: " + value);
            wsem.release();
            y.acquire();
            writeCount--;
            if (writeCount == 0) {
                rsem.release();
            }
            y.release();
    }
}
