package schereSteinPapier;

/**
 * <p>Klasse zur Bereitstellung der Spiellogik.</p>
 */
class GameLogik {
    /**
     * <p>Gewinn-/Verliertabelle.</p>
     */
    private static Boolean[][] winTable = new Boolean[][]{
            new Boolean[]{null, false, true},
            new Boolean[]{true, null, false},
            new Boolean[]{false, true, null}
    };

    /**
     * <p>Getter fuer Gewinn-/Verliertabelle.</p>
     * @return <p>Gewinn-/Verliertabelle.</p>
     */
    private static Boolean[][] getWinTable() {
        return winTable;
    }

    /**
     * <p>Ermittelt das Ergebniss einer Runde fuer zwei Spieler.</p>
     * @param playerChoise1 <p>Entscheidung von Spieler 1</p>
     * @param playerChoise2 <p>Entscheidung von Spieler 2</p>
     * @return <p>Wahr wenn Spieler 1 gewonnen hat.</p>
     * <p>Falsch wenn Spieler 2 gewonnen hat.</p>
     * <p>Null bei unentschieden.</p>
     */
    static Boolean getResult(PlayerChoise playerChoise1, PlayerChoise playerChoise2) {
        return getWinTable()[playerChoise1.ordinal()][playerChoise2.ordinal()];
    }
}
