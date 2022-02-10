public class Client {

    private Shop shop;


    public Client(Shop shop) {
        this.shop = shop;
    }

    public void buyCar() {
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
            shop.getCars().remove(0);
    }
}
