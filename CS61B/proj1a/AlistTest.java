public class AlistTest {

    public static void main(String[] args) {

        System.out.println("Test begins.");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        if (lld1.isEmpty()) {
            System.out.println("is empty. ");
        } else {
            System.out.println("is not empty. ");
        }

        lld1.addFirst(10);

        lld1.addFirst(20);

        if (lld1.isEmpty()) {
            System.out.println("is empty. ");
        } else {
            System.out.println("is not empty. ");
        }

        lld1.removeFirst();
        lld1.addLast(30);
        lld1.addLast(40);
        lld1.removeLast();
        lld1.printDeque();
    }
}


