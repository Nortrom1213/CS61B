public class Palindrome { 
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> answer = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            answer.addLast(word.charAt(i));
        }
        return answer;
    }

    public boolean isPalindrome(String word) {
        return isPalindromeHelper(wordToDeque(word));
    }

    private boolean isPalindromeHelper(Deque word) {
        if (word.size() <= 1) {
            return true;
        }
        if (word.removeFirst() != word.removeLast()) {
            return false;
        }
        return isPalindromeHelper(word);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeHelper(wordToDeque(word), cc);
    }

    private boolean isPalindromeHelper(Deque word, CharacterComparator cc) {
        if (word.size() <= 1) {
            return true;
        } 
        if (!cc.equalChars((char) word.removeFirst(), (char) word.removeLast())) {
            return false;
        }
        return isPalindromeHelper(word, cc);
    }
}
