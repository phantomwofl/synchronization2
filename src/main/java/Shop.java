import java.util.ArrayList;
import java.util.List;

public class Shop {
    Manufacturer manufacturer = new Manufacturer(this);
    Client client = new Client(this);
    List<Car> cars = new ArrayList<>(10);

    public Car sellCar () {
        return manufacturer.buyCar();
    }

    public void acceptCar () {
        manufacturer.createCar();
    }

    List<Car> getCars() {
        return cars;
    }

}
