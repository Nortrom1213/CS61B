package bearmaps;
import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    public Point nearest(double x, double y) {
        double dist = 1 << 30;
        Point point = new Point(x, y);
        Point answer = new Point(x, y);
        for (Point i : points) {
            if (Point.distance(i, point) < dist) {
                answer = i;
                dist = Point.distance(i, point);
            }
        }
        return answer;
    }
}
