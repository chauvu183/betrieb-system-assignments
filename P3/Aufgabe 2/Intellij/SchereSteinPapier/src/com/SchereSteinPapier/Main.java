package com.SchereSteinPapier;

/**
 * <p>Haupteinstiegspunkt des Programms.</p>
 */
public class Main {
    /**
     * <p>Haupteinstiegspunkt.</p>
     * @param args <p>Kommandozeilenparameter.</p>
     */
    public static void main(String[] args) {
        // Erstellt ein Schere-Stein-Papier-Spiel.
        SchereSteinPapier schereSteinPapier = new SchereSteinPapier((long)1000 * (long)1000 * (long)1000 * (long)60);
        // Startet das Spiel.
        System.out.println(schereSteinPapier.startGame());
    }
}
