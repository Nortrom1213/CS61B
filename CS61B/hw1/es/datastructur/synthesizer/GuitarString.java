package es.datastructur.synthesizer;
import java.util.Arrays;


//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayRingBuffer<>((int) Math.round(SR / frequency));
        for (int i = 0; i < buffer.capacity(); i += 1) {
            buffer.enqueue(0.0);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void pluck() {
        double[] r = new double[buffer.capacity()];
        r[0] = Math.random() - 0.5;
        double random;

        //generate random
        for (int i = 1; i < r.length; i += 1) {
            random = Math.random() - 0.5;
            while (Arrays.asList(r).contains(random)) {
                random = Math.random() - 0.5;
            }
            r[i] = random;
        }

        //reload part
        for (int i = 0; i < buffer.capacity(); i += 1) {
            buffer.dequeue();
        }

        for (int i = 0; i < buffer.capacity(); i += 1) {
            buffer.enqueue(r[i]);
        }
    }

    public void tic() {
        double first = buffer.dequeue();
        double second = buffer.peek();
        double answer = (first + second) * 0.5 * DECAY;
        buffer.enqueue(answer);
    }

    public double sample() {
        return buffer.peek();
    }

}
