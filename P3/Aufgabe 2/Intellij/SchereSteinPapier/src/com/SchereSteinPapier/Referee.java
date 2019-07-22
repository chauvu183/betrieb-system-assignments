package com.SchereSteinPapier;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * <p>Klasse fuer Schiedsrichter.</p>
 * <p>Implementiert das Interface {@link Runnable}.</p>
 */
public class Referee implements Runnable {
    /**
     * <p>Spieltisch.</p>
     */
    private GameTable gameTable;

    /**
     * <p>Spieldauer.</p>
     */
    private long gameDuration;

    /**
     * <p>Thread fuer Spieler 1.</p>
     */
    private Thread player1Thread;

    /**
     * <p>Thread fuer Spieler 2.</p>
     */
    private Thread player2Thread;

    /**
     * <p>Ergebnis.</p>
     */
    private String result;

    /**
     * <p>Konstrukor fuer {@link com.SchereSteinPapier.Referee}.</p>
     * @param gameTable <p>Spieltisch.</p>
     * @param gameDuration <p>Spieldauer.</p>
     * @param player1Thread <p>Thread fuer Spieler 1.</p>
     * @param player2Thread <p>Thread fuer Spieler 2.</p>
     */
    Referee(
            GameTable gameTable,
            long gameDuration,
            Thread player1Thread,
            Thread player2Thread
    ) {
        this.gameTable = gameTable;
        this.gameDuration = gameDuration;
        this.player1Thread = player1Thread;
        this.player2Thread = player2Thread;
    }

    /**
     * <p>Getter fuer Spieltisch.</p>
     * @return <p>Spieltisch.</p>
     */
    private GameTable getGameTable() {
        return gameTable;
    }

    /**
     * <p>Getter fuer Spieldauer.</p>
     * @return <p>Spieldauer.</p>
     */
    private long getGameDuration() {
        return gameDuration;
    }

    /**
     * <p>Getter fuer Thread fuer Spieler 1.</p>
     * @return <p>Thread fuer Spieler 1.</p>
     */
    private Thread getPlayer1Thread() {
        return player1Thread;
    }

    /**
     * <p>Getter fuer Thread fuer Spieler 2.</p>
     * @return <p>Thread fuer Spieler 2.</p>
     */
    private Thread getPlayer2Thread() {
        return player2Thread;
    }

    /**
     * <p>Getter fuer Ergebnis.</p>
     * @return <p>Ergebnis.</p>
     */
    String getResult() {
        return result;
    }

    /**
     * <p>Setter fuer Ergebnis.</p>
     * @param result <p>Ergebnis.</p>
     */
    private void setResult(String result) {
        this.result = result;
    }

    /**
     * <p>Abgeleitete Methode von {@link Runnable}.</p>
     * <p>Solange die Spielzeit nicht abgelaufen ist nimmt der Schiedsrichter die Spielerentscheidungen entgegen, 
     * anschliessend wird das Ergebniss zurueckgegeben.</p>
     */
    @Override
    public void run() {
        int gamesCount = 0;
        int draws = 0;
        int player1Wins = 0;
        int player2Wins = 0;
        long startTimestamp = System.nanoTime();
        // Solange die Spielzeit nicht ueberschritten wurde.
        while (System.nanoTime() - startTimestamp < getGameDuration()) {
            try { // Versuche...
                // Holt die Entscheidung von Spieler 1 vom Spieltisch.
                PlayerChoise player1Choise = getGameTable().getPlayer1ChoiseBoundedBuffer().remove();
                // Holt die Entscheidung von Spieler 2 vom Spieltisch.
                PlayerChoise player2Choise = getGameTable().getPlayer2ChoiseBoundedBuffer().remove();
                // Ermittelt das Ergebnis von beiden Spielern.
                Boolean result = GameLogik.getResult(player1Choise, player2Choise);
                // Inkrementiert die Anzahl der gespielten Spiele.
                gamesCount++;
                if (result == null) { // Wenn das Ergebniss gleich Null ist, dann ...
                    // Inkrementiert die Anzahl der unentschieden gespielten Spiele. 
                    draws++;
                } else if (result) { // Ansonsten wenn das Ergebnis Wahr ist...
                    // Inkrementiert die Anzahl der von Spieler 1 gewonnenen Spiele.
                    player1Wins++;
                } else { // Ansonsten...
                    // Inkrementiert die Anzahl der von Spieler 2 gewonnenen Spiele.
                    player2Wins++;
                }
            } catch (InterruptedException e) { // Wenn der Thread unterbrochen wurde.
                // Stacktrace ausgeben.
                e.printStackTrace();
            }
        }
        // Neues Zahlenformat erstellen fuer Prozentuale Angaben.
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        // Setzt das Ergebniss vom Schiedsrichter.
        setResult(
                "Gesamtanzahl gespielter Runden: " + gamesCount +
                "\nAnzahl Unentschieden: " + draws + " " + decimalFormat.format((double)draws / gamesCount * 100) + "%" +
                "\nAnzahl Gewinne Spieler 1: " + player1Wins + " " + decimalFormat.format((double)player1Wins / gamesCount * 100) + "%" +
                "\nAnzahl Gewinne Spieler 2: " + player2Wins + " " + decimalFormat.format((double)player2Wins / gamesCount * 100) + "%"
        );
        // Unterbricht den Thread von Spieler 1.
        getPlayer1Thread().interrupt();
        // Unterbricht den Thread von Spieler 2.
        getPlayer2Thread().interrupt();
    }
}
