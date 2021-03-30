package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {

    /*
    @Test
    public void testIndex() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        assertEquals(6, pq.left(3));
        assertEquals(10, pq.left(5));
        assertEquals(7, pq.right(3));
        assertEquals(11, pq.right(5));

        assertEquals(3, pq.parent(6));
        assertEquals(5, pq.parent(10));
        assertEquals(3, pq.parent(7));
        assertEquals(5, pq.parent(11));
    }
    */

    /*
    @Test
    public void testUpDown() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        pq.size = 6;
        pq.heap[1] = new ArrayHeapMinPQ<Integer>.Node(1, 10);
        pq.heap[2] = new ArrayHeapMinPQ<Integer>.Node(2, 8);
        pq.heap[3] = new ArrayHeapMinPQ<Integer>.Node(3, 6);
        pq.heap[4] = new ArrayHeapMinPQ<Integer>.Node(4, 4);
        pq.heap[5] = new ArrayHeapMinPQ<Integer>.Node(5, 2);
        pq.heap[6] = new ArrayHeapMinPQ<Integer>.Node(6, 0);



        pq.up(6);
        assertEquals(6, pq.heap[1].item);
        assertEquals(1, pq.heap[3].item);
        assertEquals(3, pq.heap[6].item);

        pq.down(3);
        assertEquals(6, pq.heap[3].item);
        assertEquals(10, pq.heap[6].item);
    }
    */

    @Test
    public void voidtest() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        pq.add(1, 1);
        pq.add(2, 2);
        pq.add(3, 3);
        assertEquals(3, pq.size());
        assertEquals(true, pq.contains(1));
        assertEquals(1, (int) pq.getSmallest());
        assertEquals(1, (int) pq.removeSmallest());
        assertEquals(2, (int) pq.getSmallest());
        assertEquals(2, pq.size());
        assertEquals(false, pq.contains(1));
        pq.changePriority(2, 4);
        assertEquals(3, (int) pq.getSmallest());

    }
}
