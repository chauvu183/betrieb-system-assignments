package schereSteinPapier;

/**
 * <p>Haupteinstiegspunkt des Programms.</p>
 */
public class main {
	/**
     * <p>Haupteinstiegspunkt.</p>
     * @param args <p>Kommandozeilenparameter.</p>
     */
	public static void main(String[] args) {
		// Erstellt ein Schere-Stein-Papier-Spiel.
        SchereSteinPapier schereSteinPapier = new SchereSteinPapier(
        		(long)1000 * (long)1000 * (long)1000 * (long)10
        );
        // Startet das Spiel.
        System.out.println(schereSteinPapier.startGame());
	}

}
