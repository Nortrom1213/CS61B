import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void test() {
        StudentArrayDeque<Integer> d1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> d2 = new ArrayDequeSolution<>();
        String message = "";
        for (int i = 0; i < 100; i++) {
            Integer random = StdRandom.uniform(1000);
            d1.addFirst(random);
            d2.addFirst(random);
            message = message + "addFirst(" + random + ")\n";
        }
        for (int i = 0; i < 50; i++) {
            message = message + "removeFirst()\n";
            assertEquals(message, d1.removeFirst(), d2.removeFirst());
            message = message + "removeLast()\n";
            assertEquals(message, d1.removeLast(), d2.removeLast());
        }
    }
}