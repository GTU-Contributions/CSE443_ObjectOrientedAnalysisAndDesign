/**
 * Created by Mehmed Mustafa on 26-Nov-17.
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {

    private static final Lock lock = new ReentrantLock();
    private static final Condition ingrediantsReady = lock.newCondition();
    private static final Condition outputReady = lock.newCondition();
    public static boolean outputsReady = false;

    // [0] - input file, [1] - transformer, [2] - output file
    private static boolean[] availableItems = {false, false, false};

    public static void produce2ingredients(int whichIsMissing){
        lock.lock();
        switch(whichIsMissing){
            case 0:
                availableItems[1] = true;
                availableItems[2] = true;
                break;
            case 1:
                availableItems[0] = true;
                availableItems[2] = true;
                break;
            case 2:
                availableItems[0] = true;
                availableItems[1] = true;
                break;
        }
        lock.unlock();
    }

    public static void signalOthers() throws InterruptedException {
        lock.lock();
        outputsReady = false;
        ingrediantsReady.signalAll();

        while(!outputsReady){
            outputReady.await();
        }
        lock.unlock();
    }

    public static void consume2ingredients(int item1, int item2) throws InterruptedException {
        lock.lock();

        while(!(availableItems[item1] && availableItems[item2])){
            ingrediantsReady.await();
        }

        availableItems[item1] = false;
        availableItems[item2] = false;
        lock.unlock();
    }

    public static void signalKezban(){
        lock.lock();
        outputsReady = true;
        outputReady.signal();
        lock.unlock();
    }

}
