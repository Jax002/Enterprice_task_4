public class Bartender implements Runnable {
    private Bar bar;

    public Bartender(Bar bar) {
        this.bar = bar;
    }

    @Override
    public void run() {
        try {
            while (bar.isOpen) {
                bar.prepareDrink();
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}