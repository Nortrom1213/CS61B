import java.util.*;

public class MyTrieSet implements TrieSet61B {
    private static final int R = 128; // ASCII
    private Node root;

    public MyTrieSet() {
        root = new Node();
    }
  
    private class Node {
      private boolean isKey = false;   
      private char c;
      private Map<Character, Node> map = new HashMap<>();
      public Node(char c) {
          this.c = c;
      }
      public Node() {}
    }

   @Override 
   public void clear() {
       root = new Node();
   }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public boolean contains(String key) {
        Node pointer = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!pointer.map.containsKey(c)) {
                return false;
            }
            pointer = pointer.map.get(c);
        }
        return pointer.isKey;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        Node pointer = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!pointer.map.containsKey(c)) {
                return null;
            }
            pointer = pointer.map.get(c);
        }
        List<String> answer = new ArrayList<>();
        boolean temp = pointer.isKey;
        pointer.isKey = false;
        search(pointer, new StringBuilder(prefix), answer);
        pointer.isKey = temp;
        return answer;
    }

    private void search(Node node, StringBuilder prefix, List<String> answer) {
        if (node.isKey) {
            answer.add(prefix.toString());
        }
        if (node.map.isEmpty()) {
            return;
        }
        for (char c = 0; c < 256; c++) {
            if (!node.map.containsKey(c)) {
                continue;
            }
            prefix.append(c);
            search(node.map.get(c), prefix, answer);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
