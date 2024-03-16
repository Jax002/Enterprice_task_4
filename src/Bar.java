import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Bar {
    private Queue<String> orders = new LinkedList<>();
    private Queue<String> preparedDrinks = new LinkedList<>();
    private Map<String, String> servedDrinks = new HashMap<>();
    boolean isOpen = true;

    public synchronized void takeOrder(String order, String clientName) {
        if (!isOpen) {
            System.out.println("Bar close " + clientName + " can't order");
            return;
        }
        System.out.println("Client order " + clientName + " order " + order);
        orders.add(order);
        notify();
    }

    public synchronized void prepareDrink() throws InterruptedException {
        while (orders.isEmpty() && isOpen) {
            wait();
        }
        if (!isOpen) {
            return;
        }
        String order = orders.poll();
        System.out.println("Bartender prepare " + order);
        preparedDrinks.add(order);
        notify();
    }

    public synchronized void serveDrink(String clientName) throws InterruptedException {
        while (preparedDrinks.isEmpty() && isOpen) {
            wait();
        }
        if (!isOpen) {
            return;
        }
        String drink = preparedDrinks.poll();
        System.out.println("Client " + clientName + " get " + drink);
        servedDrinks.put(drink, clientName);
        notify();
    }

    public synchronized void closeBar() {
        isOpen = false;
        notifyAll();
    }
}