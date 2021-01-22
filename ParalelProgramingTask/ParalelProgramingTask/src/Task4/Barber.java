package Task4;

import static Task4.SleepingBarber.*;

public class Barber extends Thread {
    public Barber() {
    }

    public void run() {
        while (true) { // runs in an infinite loop
            // none is available he goes to
            // sleep
            accessSeats.lock(); // at this time he has been awaken ->
            // want to modify the number of
            // available seats
            numberOfFreeSeats++; // one chair gets free
            barber.lock();
            try {
                barberAvailable.signal(); // the barber is ready to cut
            } finally {
                barber.unlock();
            }
            accessSeats.unlock(); // we don't need the lock on the
            // chairs anymore
            this.cutHair(); // cutting...
        }
    }

    /* this method will simulate cutting hair */

    public void cutHair() {
        System.out.println("The barber is cutting hair");
        try {
            sleep(5000);
        } catch (InterruptedException ex) {
        }
    }
}

