/**
 * Created by Mehmed Mustafa on 23-Nov-17.
 */

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class TheClass {

    private final int INPUT = 0;
    private final int TRANSFORMER =1;
    private final int OUTPUT = 2;

    public final void runScenario() throws InterruptedException {
        Thread metin = new Thread(new Metin());
        Thread ali = new Thread(new Ali());
        Thread feyyaz = new Thread(new Feyyaz());
        Thread kezban = new Thread(new Kezban());

        metin.start();
        ali.start();
        feyyaz.start();
        kezban.start();

        metin.join();
        ali.join();
        feyyaz.join();
        kezban.join();
    }

    private class Metin implements Runnable {
        private void printText(){
            System.out.println("Metin grabbed a transformer and an output file");
            System.out.println("Metin is calculating the outputs");
            System.out.println("Metin has delivered the outputs");
            System.out.println("Metin is waiting for a transformer and an output file");
        }

        public void run() {
            System.out.println("Metin is waiting for a transformer and an output file");

            while(true){
                try {
                    Monitor.consume2ingredients(TRANSFORMER, OUTPUT);
                    printText();
                    Monitor.signalKezban();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Ali implements Runnable {
        private void printText(){
            System.out.println("Ali grabbed an input file and an output file");
            System.out.println("Ali is calculating the outputs");
            System.out.println("Ali has delivered the outputs");
            System.out.println("Ali is waiting for an input file and an output file");
        }

        public void run(){
            System.out.println("Ali is waiting for an input file and an output file");

            while(true){
                try {
                    Monitor.consume2ingredients(INPUT, OUTPUT);
                    printText();
                    Monitor.signalKezban();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Feyyaz implements Runnable {
        private void printText(){
            System.out.println("Feyyaz grabbed an input file and a transformer");
            System.out.println("Feyyaz is calculating the outputs");
            System.out.println("Feyyaz has delivered the outputs");
            System.out.println("Feyyaz is waiting for an input file and a transformer");
        }

        public void run(){
            System.out.println("Feyyaz is waiting for an input file and a transformer");

            while(true){
                try {
                    Monitor.consume2ingredients(INPUT, TRANSFORMER);
                    printText();
                    Monitor.signalKezban();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Kezban implements Runnable {

        public void printText(int whichIsMissing){
            System.out.println();
            if(whichIsMissing == 0){
                System.out.println("Kezban brought a transformer and an output file");
            }
            else if(whichIsMissing == 1){
                System.out.println("Kezban brought an input file and an output file");
            }
            else if(whichIsMissing == 2){
                System.out.println("Kezban brought an input file and a transformer");
            }
            System.out.println("Kezban is waiting for the outputs to be calculated");
        }

        public void generate2ingredients() throws InterruptedException {
            Random rand = new Random(System.currentTimeMillis());
            // Choose which 2 items to provide
            int whichIsMissing = rand.nextInt(3);
            printText(whichIsMissing);
            Monitor.produce2ingredients(whichIsMissing);
        }

        public void run(){
            while(true) {
                try {
                    generate2ingredients();
                    Monitor.signalOthers();
                    if(Monitor.outputsReady){
                        System.out.println("Kezban has taken the outputs and left");
                    }
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
