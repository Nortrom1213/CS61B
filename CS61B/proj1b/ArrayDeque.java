public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private T[] items;
    private int first;
    private int last;

    private int lastone(int x) {
        if (x - 1 < 0) {
            return items.length - 1;
        }
        return x - 1;
    }

    private int nextone(int x) {
        if (x + 1 == items.length) {
            return 0;
        }
        return x + 1;
    }

    public ArrayDeque() {
        size = 0;
        first = 0;
        last = 1;
        items = (T[]) new Object[8];
    }

    public ArrayDeque(ArrayDeque other) {
        this.size = other.size;
        this.items = (T[]) new Object[other.size];
        this.first = other.first;
        this.last = other.last;
        System.arraycopy(other.items, 0, this.items, 0, other.size);
    }

    private void resize(int N) {
        T[] newitems = (T[]) new Object[N];
        int start = nextone(first);
        int end = lastone(last);  
        if (start > end) {
            System.arraycopy(items, start, newitems, 0, items.length - start);
            System.arraycopy(items, 0, newitems, items.length - start, end + 1);
        } else {
            System.arraycopy(items, start, newitems, 0, size);
        }
        first = newitems.length - 1;
        last = size;
        items = newitems;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[first] = x;
        first = lastone(first);
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[last] = x;
        last = nextone(last);
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
        int i = nextone(first);
        while (i != last) {
            System.out.print(items[i] + " ");
            i = nextone(i);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if ((float) size / items.length < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        int removed = nextone(first);
        T removeditem = items[removed];
        items[removed] = null;
        first = nextone(first);
        size -= 1;
        return removeditem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if ((float) size / items.length < 0.25 && items.length > 15) {
            resize(items.length / 2);
        }
        int removed = lastone(last);
        T removeditem = items[removed];
        items[removed] = null;
        last = lastone(last);
        size = size - 1;
        return removeditem;
    }

    @Override
    public T get(int index) {
        int x = nextone(first);
        for (int i = 0; i < index; i++) {
            x = nextone(x);
        }
        if (index > size) {
            return null;
        }
        return items[x];
    }
}
