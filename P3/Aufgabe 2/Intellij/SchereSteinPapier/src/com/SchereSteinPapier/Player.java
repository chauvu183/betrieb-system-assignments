package com.SchereSteinPapier;

import java.util.Random;

/**
 * <p>Klasse fuer Spieler.</p>
 * <p>Implementiert das Interface {@link Runnable}.</p>
 */
public class Player implements Runnable {
    /**
     * <p>Spielernummer.</p>
     */
    private int playerNumber;

    /**
     * <p>Spieltisch.</p>
     */
    private GameTable gameTable;

    /**
     * <p>Zufallsgenerator.</p>
     */
    private Random random = new Random();

    /**
     * <p>Konstrukor fuer {@link com.SchereSteinPapier.Player}</p>
     * @param gameTable <p>Spieltisch.</p>
     * @param playerNumber <p>Spielernummer.</p>
     */
    Player(GameTable gameTable, int playerNumber) {
        this.gameTable = gameTable;
        this.playerNumber = playerNumber;
    }

    /**
     * <p>Getter fuer Spielernummer.</p>
     * @return <p>Spielernummer.</p>
     */
    private int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * <p>Getter fuer Spieltisch.</p>
     * @return <p>Spieltisch.</p>
     */
    private GameTable getGameTable() {
        return gameTable;
    }

    /**
     * <p>Getter fuer Zufallsgenerator.</p>
     * @return <p>Zufallsgenerator.</p>
     */
    private Random getRandom() {
        return random;
    }

    /**
     * <p>Entscheidung zufaellig waehlen und auf dem Spieltisch ablegen.</p>
     * @throws InterruptedException <p>Wenn der Thread unterbrochen wird.</p>
     */
    private void choose() throws InterruptedException {
        if (getPlayerNumber() == 1) {
            switch (getRandom().nextInt(3)) {
                case 0:
                    getGameTable().player1Choose(PlayerChoise.SCHERE);
                case 1:
                    getGameTable().player1Choose(PlayerChoise.STEIN);
                case 2:
                    getGameTable().player1Choose(PlayerChoise.PAPIER);
            }
        } else {
            switch (getRandom().nextInt(3)) {
                case 0:
                    getGameTable().player2Choose(PlayerChoise.SCHERE);
                case 1:
                    getGameTable().player2Choose(PlayerChoise.STEIN);
                case 2:
                    getGameTable().player2Choose(PlayerChoise.PAPIER);
            }
        }
    }

    /**
     * <p>Abgeleitete Methode von {@link Runnable}.</p>
     * <p>Ermittelt solange zufaellige Entscheidungen bis der Thread unterbrochen wird.</p>
     */
    @Override
    public void run() {
        while (true) {
            try {
                choose();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
