

public class Client implements Runnable {
    private Bar bar;
    private String name;

    public Client(Bar bar, String name) {
        this.bar = bar;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep( 2000);
            String order = "drink";
            bar.takeOrder(order, name);
            Thread.sleep( 2000);
            bar.serveDrink(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}