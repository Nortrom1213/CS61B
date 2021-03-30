package bearmaps;

import java.util.HashMap;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private class Node {
        private T item;
        private double priority;
        private int index;

        Node(T item, double priority, int index) {
            this.item = item;
            this.priority = priority;
            this.index = index;
        }

        public T item() {
            return this.item;
        }

        public double priority() {
            return this.priority;
        }
    }

    private HashMap<T, Node> hashmap;

    public ArrayHeapMinPQ() {
        heap = new ArrayHeapMinPQ.Node[16];
        heap[0] = null;
        size = 0;

        hashmap = new HashMap<>();
    }

    private Node[] heap;
    private int size;

    private void validate(int index) {
        if (index < 1 || index > size || heap[index] == null) {
            throw new IllegalArgumentException();
        }
    }

    private int left(int i) {
        return i * 2;
    }

    private int right(int i) {
        return i * 2 + 1;
    }

    private int parent(int i) {
        return i / 2;
    }

    private Node get(int index) {
        if ((index > size) || (index < 1)) {
            return null;
        }
        return heap[index];
    }

    private void swap(int i, int j) {
        Node node1 = get(i);
        Node node2 = get(j);
        heap[i] = node2;
        heap[j] = node1;
        heap[i].index = i;
        heap[j].index = j;
    }

    private int min(int i, int j) {
        Node node1 = get(i);
        Node node2 = get(j);
        if (node1 == null) {
            return j;
        } else if (node2 == null) {
            return i;
        } else if (node1.priority() < node2.priority()) {
            return i;
        } else {
            return j;
        }
    }

    private void up(int index) {
        validate(index);

        int parentIndex = parent(index);
        Node indexNode = heap[index];
        Node parentNode = heap[parentIndex];
        if (parentNode == null || index == 0) {
            return;
        } else if (indexNode.priority() < parentNode.priority()) {
            swap(index, parentIndex);
            up(parentIndex);
        }
    }

    private void down(int index) {
        validate(index);

        if (min(index, left(index)) == index && min(index, right(index)) == index) {
            return;
        }
        int sonIndex = min(left(index), right(index));
        if (right(index) > size()) {
            sonIndex = left(index);
        }
        if (sonIndex > size()) {
            return;
        } else {
            swap(index, sonIndex);
            down(sonIndex);
        }
    }

    private void resize(int capacity) {
        Node[] temp = new ArrayHeapMinPQ.Node[capacity];
        for (int i = 1; i < heap.length; i++) {
            temp[i] = heap[i];
        }
        heap = temp;
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }

        if (size() + 1 == heap.length) {
            resize(2 * size());
        }

        size += 1;
        Node current = new Node(item, priority, size);
        heap[size()] = current;
        up(size());

        hashmap.put(item, current);
    }

    @Override
    public boolean contains(T item) {
        return hashmap.get(item) != null;
    }

    @Override
    public T getSmallest() {
        return heap[1].item();
    }

    @Override
    public T removeSmallest() {
        swap(1, size());
        Node removeNode = heap[size()];
        size -= 1;
        if (size() != 1) {
            down(1);
        }
        hashmap.remove(removeNode.item());
        return removeNode.item();
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        Node point = hashmap.get(item);
        int index = point.index;
        double oldpriority = point.priority;
        point.priority = priority;
        if (oldpriority < priority) {
            down(index);
        } else {
            up(index);
        }

    }
}
