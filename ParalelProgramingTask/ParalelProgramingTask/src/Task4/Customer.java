package Task4;

import static Task4.SleepingBarber.*;
import static java.lang.Thread.sleep;

public class Customer extends Thread {
    int id;
    boolean notCut = true;

    /* Constructor for the Customer */

    public Customer(int i) {
        id = i;
    }

    public void run() {
        while (notCut) { // as long as the customer is not cut
            accessSeats.lock(); // tries to get access to the chairs
            if (numberOfFreeSeats > 0) { // if there are any free seats
                System.out.println("Customer " + this.id + " just sat down.");
                numberOfFreeSeats--; // sitting down on a chair
                customers.releaseCustomer(); // notify the barber that there is
                // a customer
                accessSeats.unlock(); // don't need to lock the chairs
                barber.lock();
                try {
                    barberAvailable.await();  // now it's this customers turn
                    // but we have to wait if the
                    // barber is busy
                } catch (InterruptedException e) {
                } finally {
                    barber.unlock();
                }
                notCut = false;
                this.get_haircut(); // cutting...
            } else { // there are no free seats
                System.out.println("There are no free seats. Customer " + this.id + " has left the barbershop.");
                accessSeats.unlock(); // release the lock on the seats
                notCut = false; // the customer will leave since there
            }
        }
    }

    /* this method will simulate getting a hair-cut */

    public void get_haircut() {
        System.out.println("Customer " + this.id + " is getting his hair cut");
        try {
            sleep(5050);
        } catch (InterruptedException ex) {
        }
    }
}
