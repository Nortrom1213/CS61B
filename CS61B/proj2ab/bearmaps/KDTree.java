package bearmaps;

import java.util.List;

public class KDTree {
    private Node root;
    private int size;

    private class Node {
        private Point p;
        private Node left;
        private Node right;

        private Node(Point p, double[] coord) {
            this.p = p;
        }

    }

    private double compared(Point p, Node n, boolean odd) {
        if (odd) {
            return p.getX() - n.p.getX();
        } else {
            return p.getY() - n.p.getY();
        }
    }

    public KDTree(List<Point> points) {
        for (Point i : points) {
            insert(i);
        }
    }

    public void insert(Point p) {
        root = insert(root, p, true, new double[] {0, 0, 1, 1});
    }

    public Node insert(Node n, Point p, boolean odd, double[] coord) {
        if (n == null) {
            size++;
            return new Node(p, coord);
        }
        double cmp = compared(p, n, odd);
        if (cmp < 0 && odd) {
            coord[2] = n.p.getX(); // decrease x_max
            n.left = insert(n.left, p, !odd, coord);
        } else if (cmp < 0 && !odd) {
            coord[3] = n.p.getY(); // decrease y_max
            n.left = insert(n.left, p, !odd, coord);
        } else if (cmp > 0 && odd) {
            coord[0] = n.p.getY(); // increase x_min
            n.right = insert(n.right, p, !odd, coord);
        } else if (cmp > 0 && !odd) {
            coord[1] = n.p.getY(); // increase y_min
            n.right = insert(n.right, p, !odd, coord);
        } else if (!n.p.equals(p)) {
            n.right = insert(n.right, p, !odd, coord);
        }

        return n;
    }

    public Point nearest(double x, double y) {
        Point point = new Point(x, y);
        if (root == null) {
            return null;
        }
        return nearest(root, point, root.p, true);
    }

    private Point nearest(Node n, Point p, Point near, boolean odd) {
        if (n == null) {
            return near;
        }

        if (n.p.equals(p)) {
            return n.p;
        }

        if (Point.distance(n.p, p) < Point.distance(near, p)) {
            near = n.p;
        }

        double cmp = compared(p, n, odd);

        if (cmp < 0) {
            near = nearest(n.left, p, near, !odd);

            if (Point.distance(near, p) >= cmp * cmp) {
                near = nearest(n.right, p, near, !odd);
            }
        } else {
            near = nearest(n.right, p, near, !odd);

            if (Point.distance(near, p) >= cmp * cmp) {
                near = nearest(n.left, p, near, !odd);
            }
        }

        return near;
    }


}
