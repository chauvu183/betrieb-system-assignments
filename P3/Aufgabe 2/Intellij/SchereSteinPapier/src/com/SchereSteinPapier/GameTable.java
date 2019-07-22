package com.SchereSteinPapier;

/**
 * <p>Klasse fuer den Spieltisch fuer zwei Spieler.</p>
 */
class GameTable {
    /**
     * <p>Threadsicherer Puffer fuer die Entscheidung fuer Spieler 1</p>
     */
    private BoundedBuffer<PlayerChoise> player1ChoiseBoundedBuffer;

    /**
     * <p>Threadsicherer Puffer fuer die Entscheidung fuer Spieler 2</p>
     */
    private BoundedBuffer<PlayerChoise> player2ChoiseBoundedBuffer;

    /**
     * <p>Konstruktor fuer {@link com.SchereSteinPapier.GameTable}.</p>
     */
    GameTable() {
        if (true) {
            player1ChoiseBoundedBuffer = new BoundedBufferSync<>(1);
            player2ChoiseBoundedBuffer = new BoundedBufferSync<>(1);
        } else {
            player1ChoiseBoundedBuffer = new BoundedBufferLock<>(1);
            player2ChoiseBoundedBuffer = new BoundedBufferLock<>(1);
        }
    }

    /**
     * <p>Getter fuer Puffer fuer die Entscheidung fuer Spieler 1.</p>
     * @return <p>Puffer fuer die Entscheidung fuer Spieler 1.</p>
     */
    BoundedBuffer<PlayerChoise> getPlayer1ChoiseBoundedBuffer() {
        return player1ChoiseBoundedBuffer;
    }

    /**
     * <p>Getter fuer Puffer fuer die Entscheidung fuer Spieler 2.</p>
     * @return <p>Puffer fuer die Entscheidung fuer Spieler 2.</p>
     */
    BoundedBuffer<PlayerChoise> getPlayer2ChoiseBoundedBuffer() {
        return player2ChoiseBoundedBuffer;
    }

    /**
     * <p>Fuer Spieler 1 Bereitgestellte Methode fuer das Uebergeben einer Entscheidung.</p>
     * @param playerChoise <p>Entscheidung von Spieler 1.</p>
     * @throws InterruptedException <p>Wenn der Thread unterbrochen wird.</p>
     */
    void player1Choose(PlayerChoise playerChoise) throws InterruptedException {
        getPlayer1ChoiseBoundedBuffer().enter(playerChoise);
    }

    /**
     * <p>Fuer Spieler 2 Bereitgestellte Methode fuer das Uebergeben einer Entscheidung.</p>
     * @param playerChoise <p>Entscheidung von Spieler 1.</p>
     * @throws InterruptedException <p>Wenn der Thread unterbrochen wird.</p>
     */
    void player2Choose(PlayerChoise playerChoise) throws InterruptedException {
        getPlayer2ChoiseBoundedBuffer().enter(playerChoise);
    }
}
