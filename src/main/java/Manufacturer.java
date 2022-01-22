import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Manufacturer {

    private Shop shop;
    final int CREATE = 3000;
    final int TIME = 1000;
    private Lock lock;
    Condition condition;

    public Manufacturer(Shop shop) {
        this.shop = shop;
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    public void createCar() {
        try {
            lock.lock();
            Thread.sleep(CREATE);
            shop.getCars().add(new Car());
            System.out.println("Производитель Toyota выпустил 1 авто");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
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
