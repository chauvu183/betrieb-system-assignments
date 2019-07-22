package com.SchereSteinPapier;

import java.util.ArrayList;

/**
 * <p>Abstrakte Klasse zur Loesung des Erzeuger-/ Verbraucher-Problems.</p>
 * <p>Nutzt eine Arrayliste als Puffer.</p>
 * @param <E> <p>Typ der Elemente im Puffer.</p>
 */
public abstract class BoundedBuffer<E> {
    /**
     * <p>Puffer.</p>
     */
    private ArrayList<E> buffer = new ArrayList<>();

    /**
     * <p>Maximale Puffergroesse.</p>
     */
    private final int bufferMaxSize;

    /**
     * <p>Konstruktor fuer {@link com.SchereSteinPapier.BoundedBuffer}.</p>
     * @param bufferMaxSize <p>Maximale Puffergroesse.</p>
     */
    BoundedBuffer(int bufferMaxSize) {
        this.bufferMaxSize = bufferMaxSize;
    }

    /**
     * <p>Getter fuer Puffer.</p>
     * @return <p>Puffer.</p>
     */
    ArrayList<E> getBuffer() {
        return buffer;
    }

    /**
     * <p>Getter fuer Maximale Puffergroesse.</p>
     * @return <p>Maximale Puffergroesse.</p>
     */
    int getBufferMaxSize() {
        return bufferMaxSize;
    }

    /**
     * <p>Reiht ein Element in den Puffer ein.</p>
     * <p>Threadsicher: Solange die Maximale Puffergroesse erreicht worden ist, wird der aktuelle Thread in Wartezustand versetzt bis
     * ein Element aus dem Puffer entfernt wird.</p>
     * @param item <p>Das Element das in den Puffer eingereiht werden soll.</p>
     * @throws InterruptedException <p>Wenn der Thread unterbrochen wird.</p>
     */
    public abstract void enter(E item) throws InterruptedException;

    /**
     * <p>Entfernt das erste Element aus dem Puffer.</p>
     * <p>Threadsicher: Solange die Puffergroesse gelich Null ist, wird der aktuelle Thread in Wartezustand versetzt bis
     * ein Element in den Puffer eingereiht wird.</p>
     * @return <p>Das Element das aus dem Puffer entfernt wurde.</p>
     * @throws InterruptedException <p>Wenn der Thread unterbrochen wird.</p>
     */
    public abstract E remove() throws InterruptedException;
}
