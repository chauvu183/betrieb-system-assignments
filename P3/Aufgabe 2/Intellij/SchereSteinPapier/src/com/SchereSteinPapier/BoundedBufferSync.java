package com.SchereSteinPapier;

/**
 * <p>Abgeleitete Klasse von {@link com.SchereSteinPapier.BoundedBuffer} (Synchronisationsmechanismus: Objekt-Monitor).</p>
 * <p>Nutzt einen Objekt-Monitor zur Loesung des Erzeuger-/ Verbraucher-Problems.</p>
 * @param <E> <p>Typ der Elemente im Puffer.</p>
 */
public class BoundedBufferSync<E> extends BoundedBuffer<E> {
    /**
     * <p>Konstruktor fuer {@link com.SchereSteinPapier.BoundedBufferSync}.</p>
     * @param bufferMaxSize <p>Maximale Puffergroesse.</p>
     */
    BoundedBufferSync(int bufferMaxSize) {
        super(bufferMaxSize);
    }

    /**
     * <p>Abgeleitete Methode von {@link com.SchereSteinPapier.BoundedBuffer}.</p>
     * <p>Reiht ein Element in den Puffer ein.</p>
     * <p>Nutzt ein Objekt-Monitor zur Loesung des Erzeuger-/ Verbraucher-Problems.</p>
     * @param item <p>Das Element das in den Puffer eingereiht werden soll.</p>
     * @throws InterruptedException <p>Wenn der Thread unterbrochen wird.</p>
     */
    @Override
    public synchronized void enter(E item) throws InterruptedException {
        // Solange Puffergroesse nicht gleich der maximalen Puffergroesse ist.
        while (getBuffer().size() == getBufferMaxSize()) {
            // Der aktuelle Thread wird in Wartezustand versetzt.
            this.wait();
        }
        // Fuegt dem Puffer ein das uebergebene Element hinzu.
        getBuffer().add(item);
        // Weckt alle Threads.
        this.notifyAll();
    }

    /**
     * <p>Abgeleitete Methode von {@link com.SchereSteinPapier.BoundedBuffer}.</p>
     * <p>Entfernt ein Element aus dem Puffer und gibt dieses zurueck.</p>
     * <p>Nutzt ein Objekt-Monitor zur Loesung des Erzeuger-/ Verbraucher-Problems.</p>
     * @return <p>Das Element das aus dem Puffer entfernt wurde.</p>
     * @throws InterruptedException <p>Wenn der Thread unterbrochen wird.</p>
     */
    @Override
    public synchronized E remove() throws InterruptedException {
        // Solange die Puffergroesse gleich 0 ist.
        while (getBuffer().size() == 0) {
            // Der aktuelle Thread wird in Wartezustand versetzt.
            this.wait();
        }
        // Entfernt das erste Element aus dem Puffer.
        E item = getBuffer().remove(0);
        // Weckt alle Threads.
        this.notifyAll();
        // Gibt das aus dem Puffer entfernte Element zurueck.
        return item;
    }
}
