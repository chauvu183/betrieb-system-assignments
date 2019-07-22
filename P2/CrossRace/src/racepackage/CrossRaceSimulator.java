package racepackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CrossRaceSimulator {

    //Klassenkonstanten erzeugen
    public static final int TOTAL_MOTORBIKES = 5;
    public static final int TOTAL_LAPS = 3;

    public static void main(String[] args) {

        //Motorräder erzeugen, initialisieren und starten
        List<Motorbike> allBikes = new ArrayList<>();

        int i = 1;
        while (i <= TOTAL_MOTORBIKES) {
            Motorbike bike = new Motorbike("Motorrad " + i);
            allBikes.add(bike);
            bike.start();  //ruft die in der Motorrad- Klasse run() Methode auf
            i++;
        }


        //Accident erzeugen, initialisieren und starten
        Accident accident = new Accident(Thread.currentThread());
        accident.start();

        try {
            for (Motorbike bike: allBikes) {
                //main-Thread auf Ende des Motorbikes (Zielobjekt) warten lassen
                bike.join();
            }

            Collections.sort(allBikes); //damit unsere Ausgabe sortiert rauskommt

            //im Falle kein Interrupts
            //Race-Results ausgeben
            System.out.println("**** ENDSTAND ****");
            int place = 1;
            for (Motorbike bike: allBikes) {
                System.out.println(place + ". Platz: " + bike.getMotorbikeName() + " time: " + bike.getRaceTime());
                place++;
            }

            //im Falle eines Interrupts von der Accident-Klasse
        } catch (InterruptedException e) {
            System.out.println("Ein Unfall ist geschehen!! das Rennen wurde abgebrochen!!");

            //Race beenden für alle Motorräde
            for (Motorbike bike : allBikes) {
                bike.interrupt();
            }
        }
    }

    //wir implementieren einen Hilfscomparator, so dass die Collections-Methode (sort()) starten haben
    private static class addedComperator implements Comparator<Motorbike> {

        @Override
        //compare Methode des Comparator-Interfaces implementieren
        public int compare(Motorbike bike1, Motorbike bike2) {
            return Long.compare(bike1.getRaceTime(), bike2.getRaceTime());
        }

    }
}
