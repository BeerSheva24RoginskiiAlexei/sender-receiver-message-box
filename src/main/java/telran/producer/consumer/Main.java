package telran.producer.consumer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int N_MESSAGES = 20; 
    static final int N_RECEIVERS = 10;        

    public static void main(String[] args) throws InterruptedException {
        MessageBox messageBox = new SimpleMessageBox();
        Sender sender = new Sender(N_MESSAGES, messageBox);

        List<Receiver> receivers = new ArrayList<>();
        for (int i = 0; i < N_RECEIVERS; i++) {
            Receiver receiver = new Receiver(messageBox);
            receivers.add(receiver);
            receiver.start();
        }

        sender.start(); 
        sender.join();  

        System.out.println("Stopping all receivers...");
        for (Receiver receiver : receivers) {
            receiver.stopReceiver();
        }

        for (Receiver receiver : receivers) {
            receiver.join();
        }

        System.out.println("All receivers stopped. Program finished.");
    }
}
