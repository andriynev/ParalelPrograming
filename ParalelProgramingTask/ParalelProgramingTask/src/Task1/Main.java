package Task1;

public class Main {
    private static final int countProducer = 5;
    private static final int countCustomer = 2;
    public static void main(String[] args) {
        PC pc = new PC();
//        Thread thread1 = new Customer(pc);
//        Thread thread2 = new Producer(pc);
//
//        thread1.start();
//        thread2.start();

        for (int i = 0; i < countCustomer; i++) {
            Thread t  = new Consumer(pc);
            t.setName("Thread " + i);
            t.start();
        }

        for (int i = 0; i < countProducer; i++) {
            Thread t  = new Producer(pc);
            t.setName("Thread " + i);
            t.start();
        }
    }
}
