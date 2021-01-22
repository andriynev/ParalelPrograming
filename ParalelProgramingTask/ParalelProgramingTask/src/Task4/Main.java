package Task4;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String args[]) {

        SleepingBarber barberShop = new SleepingBarber();
        barberShop.start();

        Barber barber = new Barber();
        barber.start();

        for (int i = 1; i < 16; i++) {
            Customer aCustomer = new Customer(i);
            aCustomer.start();
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
            }
        }
    }
}