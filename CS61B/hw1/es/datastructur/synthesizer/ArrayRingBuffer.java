package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T>  implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    private int capacity;

    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<T> {
        private int pointer;

        private BufferIterator() {
            pointer = 0;
        }

        @Override
        public boolean hasNext() {
            return pointer != fillCount;
        }

        @Override
        public T next() {
            T returnItem = rb[pointer];
            pointer += 1;
            return returnItem;
        }

    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            rb[last] = x;
            last += 1;
            if (last >= this.capacity) {
                last = 0;
            }
            fillCount += 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        T item;
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            item = rb[first];
            first += 1;
            if (first >= this.capacity) {
                first = 0;
            }
            fillCount -= 1;
        }
        return item;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            return rb[first];
        }
    }

    @Override
    public boolean equals(Object oo) {
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) oo;
        int size1 = this.fillCount();
        int size2 = o.fillCount();
        if (size1 != size2) {
            return false;
        }
        T[] t1 = (T[]) new Object[this.capacity()];
        T[] t2 = (T[]) new Object[o.capacity()];
        for (int i = 0; i < size1; i++) {
            t1[i] = this.dequeue();
            t2[i] = o.dequeue();
        }
        boolean trueornot = true;
        for (int i = 0; i < size1; i++) {
            if (t1[i] != t2[i]) {
                trueornot = false;
            }
            this.enqueue(t1[i]);
            o.enqueue(t2[i]);
        }
        return trueornot;
    }

}
