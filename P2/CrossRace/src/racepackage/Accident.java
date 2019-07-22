package racepackage;
import java.util.Random;

public class Accident extends Thread {
    Thread thread;

    public Accident(Thread thread){
        this.thread = thread;
    }  //Konstruktor

    private int getCrashTime() {
        Random bound = new Random();

        //Zufällige Zeit des Sturzes bzw. Unfalls generieren
        int crashTime = bound.nextInt((CrossRaceSimulator.TOTAL_LAPS)*100);
        return crashTime;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(getCrashTime());
        }
        //falls das Thread-Objekt durch Interrupt geweckt wurde, dann setzte Interrupt- Flag auf false
        catch (InterruptedException e) {}

        //Beende Thread
        thread.interrupt();
    }

}
