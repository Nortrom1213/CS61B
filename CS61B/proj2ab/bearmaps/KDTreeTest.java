package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.ArrayList;

public class KDTreeTest {
    @Test
    public void basicTest() {
        Point p1 = new Point(1.0, 2.0);
        Point p2 = new Point(5.0, 5.0);
        Point p3 = new Point(10.0, 10.0);
        List<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p2);
        KDTree pointset = new KDTree(points);
        assertEquals(p1, pointset.nearest(1.0, 1.0));

    }
}
