import java.util.Set;
import java.util.Iterator;


public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        private K key;
        private V val;
        private int size;

        private Node left;
        private Node right;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
            left = null;
            right = null;
            size = 1;
        }
    }
    private Node root;

    public  V remove(K key) {
        throw new UnsupportedOperationException();
    }
    public  V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
    public  Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    public BSTMap() {
        root = null;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node n, K key) {
        if (n == null) {
            return null;
        }
        int temp = key.compareTo(n.key);
        if (temp == 0) {
            return n.val;
        } else if (temp < 0) {
            return get(n.left, key);
        } else {
            return get(n.right, key);
        }
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return root.size;
    }

    @Override
    public void put(K key, V value) {
        root = insert(root, key, value);
    }

    private Node insert(Node n, K key, V value) {
        if (n == null) {
            return new Node(key, value);
        }
        int temp = key.compareTo(n.key);
        if (temp == 0) {
            n.val = value;
        } else if (temp < 0) {
            n.left = insert(n.left, key, value);
        } else {
            n.right = insert(n.right, key, value);
        }
        n.size = size(n.right) + size(n.left) + 1;
        return n;
    }

    private int size(Node n) {
        if (n == null) {
            return 0;
        }
        return n.size;
    }

    public void printInOrder() {
        if (root == null) {
            System.out.println(" None ");
        } else {
            printInOrder(root);
        }
    }

    private void printInOrder(Node n) {
        if (n.right == null && n.left == null) {
            System.out.println(" " + n.key.toString() + " ");
        } else if (n.right == null && n.left != null) {
            printInOrder(n.left);
            System.out.println(" " + n.key.toString() + " ");
        } else if (n.right != null && n.left == null) {
            printInOrder(n.right);
            System.out.println(" " + n.key.toString() + " ");
        } else {
            printInOrder(n.left);
            System.out.println(" " + n.key.toString() + " ");
            printInOrder(n.right);
        }
    }
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
