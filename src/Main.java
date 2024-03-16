
public class Main {
    public static void main(String[] args) {
        Bar bar = new Bar();

        Thread[] bartenders = new Thread[2];
        for (int i = 0; i < bartenders.length; i++) {
            bartenders[i] = new Thread(new Bartender(bar));
            bartenders[i].start();
        }

        Thread[] clients = new Thread[5];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = new Thread(new Client(bar, "N" + (i + 1)));
            clients[i].start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bar.closeBar();


        for (Thread clientThread : clients) {
            try {
                clientThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Thread bartenderThread : bartenders) {
            try {
                bartenderThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Bar close");
    }
}