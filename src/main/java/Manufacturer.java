public class Manufacturer {

    private Shop shop;

    public Manufacturer(Shop shop) {
        this.shop = shop;
    }

    public void createCar() {
        shop.getCars().add(new Car());
        System.out.println("Производитель Toyota выпустил 1 авто");
    }
}

