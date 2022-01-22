import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client {

    private Shop shop;
    final int TIME = 1000;
    private Lock lock;
    private Condition condition;

    public Client(Shop shop) {
        this.shop = shop;
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    public synchronized Car buyCar() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (shop.getCars().size() == 0) {
                System.out.println("Машин нет");
                condition.await();
            }
            Thread.sleep(TIME);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return shop.getCars().remove(0);
    }
}
