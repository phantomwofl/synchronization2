import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Shop {
    final int CREATE_TIME = 3000;
    final int TIME = 1000;

    Manufacturer manufacturer = new Manufacturer(this);
    Client client = new Client(this);
    List<Car> cars = new ArrayList<>(10);
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void sellCar() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (getCars().isEmpty()) {
                System.out.println("Машин нет");
                condition.await();
            }
            Thread.sleep(TIME);
            client.buyCar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void acceptCar() {
        try {
            lock.lock();
            for (int i = 1; i <= 10; i++) {
                Thread.sleep(CREATE_TIME);
                manufacturer.createCar();
                condition.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    List<Car> getCars() {
        return cars;
    }

}
