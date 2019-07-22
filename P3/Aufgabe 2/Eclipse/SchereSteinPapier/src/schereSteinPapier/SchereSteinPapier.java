package schereSteinPapier;

/**
 * <p>Klasse fuer das "Scheere Stein Papier"-Spiel.</p>
 */
class SchereSteinPapier {
    /**
     * <p>Spieldauer.</p>
     */
    private long gameDuration;

    /**
     * <p>Konstruktor fuer {@link schereSteinPapier.Referee}.</p>
     * @param gameDuration <p>Spieldauer.</p>
     */
    SchereSteinPapier(long gameDuration) {
        this.gameDuration = gameDuration;
    }

    /**
     * <p>Getter fuer Spieldauer.</p>
     * @return <p>Spieldauer.</p>
     */
    private long getGameDuration() {
        return gameDuration;
    }

    /**
     * <p>"Scheere Stein Papier"-Spiel starten.</p>
     * @return <p>Ergebnis.</p>
     */
    String startGame() {
        // Erstellt ein neuen Spieltisch.
        GameTable gameTable = new GameTable();
        // Erstellt den Spieler 1.
        Player player1 = new Player(gameTable, 1);
        // Erstellt den Spieler 2.
        Player player2 = new Player(gameTable, 2);
        // Erstellt ein Thread fuer den Spieler 1.
        Thread player1Thread = new Thread(player1);
        // Erstellt ein Thread fuer den Spieler 2.
        Thread player2Thread = new Thread(player2);
        // Erstellt ein Schiedsrichter.
        Referee referee = new Referee(gameTable, getGameDuration(), player1Thread, player2Thread);
        // Erstellt ein Thread fuer den Schiedsrichter.
        Thread refereeThread = new Thread(referee);
        // Thread fuer den Schiedsrichter wird gestartet.
        refereeThread.start();
        // Thread fuer den Spieler 1 wird gestartet.
        player1Thread.start();
        // Thread fuer den Spieler 2 wird gestartet.
        player2Thread.start();
        try { // Versuche...
            // Warte auf das beenden von dem Thread fuer den Spieler 1,
            player1Thread.join();
            // Warte auf das beenden von dem Thread fuer den Spieler 2,
            player2Thread.join();
            // Warte auf das beenden von dem Thread fuer den Schiedsrichter,
            refereeThread.join();
        } catch (InterruptedException e) { // Wenn der Thread unterbrochen wird.
            // Stacktrace ausgeben.
            e.printStackTrace();
        }
        // Ergebnis zurueckgeben.
        return referee.getResult();
    }
}
