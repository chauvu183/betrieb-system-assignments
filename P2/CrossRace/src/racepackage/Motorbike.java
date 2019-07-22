package racepackage;

import java.util.Random;

public class Motorbike extends Thread implements Comparable<Motorbike> {

    private String name;
    private int raceTime;

    public Motorbike(String name) {
        this.name = name;
    }  //Konstruktor

    //Objektmethoden

    public int getRaceTime() {
        return raceTime;
    }

    public String getMotorbikeName() {
        return name;
    }



    @Override

    //diese Methode wird mittels statrt() in der main-Methode aufgerufen
    public void run() {

        for (int i = 0; i < CrossRaceSimulator.TOTAL_LAPS; i++) {

            if (!isInterrupted()) {  //Interrupt-Flag abfragen

                Random bound = new Random();
                int lapTime = bound.nextInt(100);  //0 <= Rundenzeit < 100 ms
                raceTime += lapTime;

                try {
                     //Für lapTime ms anhalten */
                    Thread.sleep(lapTime);

                    //catch setzt das Interrupt-Flag automatisch auf false zurück
                } catch (InterruptedException e) {
                }

            }
        }
    }

    @Override
    public int compareTo(Motorbike bike) {
        if (this.raceTime < bike.getRaceTime()) return -1;
        else if (this.raceTime == bike.getRaceTime()) return 0;
        else return 1;

    }
}
