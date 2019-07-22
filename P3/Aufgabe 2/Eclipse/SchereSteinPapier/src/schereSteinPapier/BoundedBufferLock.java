package schereSteinPapier;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Abgeleitete Klasse von {@link schereSteinPapier.BoundedBuffer} (Synchronisationsmechanismus: Ausschlusssperre).</p>
 * <p>Nutzt eine Ausschlusssperre zur Loesung des Erzeuger-/ Verbraucher-Problems.</p>
 * @param <E> <p>Typ der Elemente im Puffer.</p>
 */
public class BoundedBufferLock<E> extends BoundedBuffer<E> {
    /**
     * <p>Wiedereintretende gegenseitige Ausschlusssperre.</p>
     */
    private final Lock lock = new ReentrantLock();

    /**
     * <p>Bedingung fuer Ausschlusssperre, wenn der Puffer nicht voll ist.</p>
     */
    private final Condition noFullCondition  = lock.newCondition();

    /**
     * <p>Bedingung fuer Ausschlusssperre, wenn der Puffer nicht leer ist.</p>
     */
    private final Condition noEmptyCondition = lock.newCondition();

    /**
     * <p>Konstruktor fuer {@link schereSteinPapier.BoundedBufferLock}.</p>
     * @param bufferMaxSize <p>Maximale Puffergroesse.</p>
     */
    BoundedBufferLock(int bufferMaxSize) {
        super(bufferMaxSize);
    }

    /**
     * <p>Getter fuer Wiedereintretende gegenseitige Ausschlusssperre.</p>
     * @return <p>Wiedereintretende gegenseitige Ausschlusssperre.</p>
     */
    private Lock getLock() {
        return lock;
    }

    /**
     * <p>Getter fuer Bedingung fuer Ausschlusssperre, wenn der Puffer nicht voll ist.</p>
     * @return <p>Bedingung fuer Ausschlusssperre, wenn der Puffer nicht voll ist.</p>
     */
    private Condition getNoFullCondition() {
        return noFullCondition;
    }

    /**
     * <p>Getter fuer Bedingung fuer Ausschlusssperre, wenn der Puffer nicht leer ist.</p>
     * @return <p>Bedingung fuer Ausschlusssperre, wenn der Puffer nicht leer ist.</p>
     */
    private Condition getNoEmptyCondition() {
        return noEmptyCondition;
    }

    /**
     * <p>Abgeleitete Methode von {@link schereSteinPapier.BoundedBuffer}.</p>
     * <p>Reiht ein Element in den Puffer ein.</p>
     * <p>Nutzt eine Ausschlusssperre zur Loesung des Erzeuger-/ Verbraucher-Problems.</p>
     * @param item <p>Das Element das in den Puffer eingereiht werden soll.</p>
     * @throws InterruptedException <p>Wenn der Thread unterbrochen wird.</p>
     */
    @Override
    public void enter(E item) throws InterruptedException {
        // Sperrt die Funktion.
        getLock().lock();
        try { // Versuche...
            // Solange die Puffergroesse nicht gleich der maximalen Puffergroesse ist.
            while (getBuffer().size() == getBufferMaxSize()) {
                // Versetzt die Threads von der Nicht Voll Bedingung in Wartezustand. 
                getNoFullCondition().await();
            }
            // Fuegt dem Puffer ein Element hinzu.
            getBuffer().add(item);
            // Weckt die Threads von der Nicht Leer Bedingung.
            getNoEmptyCondition().signal();
        } finally { // Anschliessend
            // Gibt die Funktion wieder frei.
            getLock().unlock();
        }
    }

    /**
     * <p>Abgeleitete Methode von {@link schereSteinPapier.BoundedBuffer}.</p>
     * <p>Entfernt ein Element aus dem Puffer und gibt dieses zurueck.</p>
     * <p>Nutzt eine Ausschlusssperre zur Loesung des Erzeuger-/ Verbraucher-Problems.</p>
     * @return <p>Das Element das aus dem Puffer entfernt wurde.</p>
     * @throws InterruptedException <p>Wenn der Thread unterbrochen wird.</p>
     */
    @Override
    public E remove() throws InterruptedException {
        // Sperrt die Funktion.
        getLock().lock();
        try { // Versuche...
            // Solange die Puffergroesse gleich 0 ist.
            while (getBuffer().size() == 0) {
                // Versetzt die Threads von der Nicht Leer Bedingung in Wartezustand.
                getNoEmptyCondition().await();
            }
            // Weckt die Threads von der Nicht Voll Bedingung.
            getNoFullCondition().signal();
            // Entfernt das erste Element aus dem Puffer und gibt dieses zurueck.
            return getBuffer().remove(0);
        } finally { // Anschliessend
            // Gibt die Funktion wieder frei.
            getLock().unlock();
        }
    }
}
