package Task4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SleepingBarber extends Thread{
    /* No of Chairs in the barbershop is 5. */

    public static final int CHAIRS = 5;

    /*
     * we create the integer numberOfFreeSeats so that the customers can either
     * sit on a free seat or leave the barbershop if there are no seats
     * available
     */

    public static int numberOfFreeSeats = CHAIRS;

    /*
     * We create Customer pool which will be waiting for their hair cut.
     */
    public static CustomerPool customers = new CustomerPool(CHAIRS);
    /*
     * We create a ReentrantLock for barber with a condition to wait if the barber is not available
     */
    public static Lock barber = new ReentrantLock();
    public static Condition barberAvailable = barber.newCondition();
    /*
     * We create a ReentrantLock for chairs so that we can increment the counter safely.
     */
    public static Lock accessSeats = new ReentrantLock();
}
