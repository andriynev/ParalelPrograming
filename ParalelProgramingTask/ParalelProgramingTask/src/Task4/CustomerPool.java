package Task4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomerPool {
    private final Lock lock = new ReentrantLock();
    private final Condition poolAvailable = lock.newCondition();
    private int num_customers;
    private final int max_num_customers;


    public CustomerPool(int num_customer_pools) {
        this.max_num_customers = num_customer_pools;
        this.num_customers = 0;
    }

    public void acquireCustomer() throws InterruptedException {
        lock.lock();
        try {
            while (num_customers <= 0)
                poolAvailable.await();
            --num_customers;
        } finally {
            lock.unlock();
        }
    }

    public void releaseCustomer() {
        lock.lock();
        try {
            // check to ensure release does not occur before acquire
            if(num_customers >= max_num_customers)
                return;
            ++num_customers;
            poolAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

    public int getNumOfCustomers() {
        return num_customers;
    }
}
