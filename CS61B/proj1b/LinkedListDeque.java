public class LinkedListDeque<T> implements Deque<T> {
    private Node sentinel;
    private int size;

    public class Node {
        private Node prev;
        private T item;
        private Node next;
        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
    
    public LinkedListDeque(LinkedListDeque other) {
        this.sentinel = new Node(null, null, null);
        this.size = 0;
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
        int i = 0;
        while (i < other.size()) {
            T newitem = (T) other.get(i);
            this.addLast(newitem);
            i = i + 1;
        }
    }

    @Override
    public void addFirst(T x) {
        Node first = new Node(x, sentinel, sentinel.next);
        sentinel.next.prev = first;
        if (size == 0) {
            sentinel.next = first;
            sentinel.prev = first;
        } else {
            sentinel.next = first;
        }
        size += 1;
    } 

    @Override
    public void addLast(T x) {
        Node last = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = last;
        if (size == 0) {
            sentinel.prev = last;
            sentinel.next = last;
        } else {
            sentinel.prev = last;
        }
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node now = sentinel.next;
        while (now != sentinel) {
            System.out.print(now.item + " ");
            now = now.next;
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size = size - 1;
        return item;
    }

    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        int i = 0;
        Node now = sentinel.next;
        while (i < index) {
            now = now.next;
            i++;
        }
        return now.item;
    }

    public T getRecursive(int index) {
        return helper(sentinel.next, index);
    }

    private T helper(Node now, int index) {
        if (index == 0) {
            return now.item;
        } 
        return helper(now.next, index - 1);
    }

}
