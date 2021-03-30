import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("A"));
        assertTrue(palindrome.isPalindrome("AAAAA"));
        assertTrue(palindrome.isPalindrome("AAAA"));
        assertFalse(palindrome.isPalindrome("Aa"));
        assertTrue(palindrome.isPalindrome("1ABCBA1"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("ab"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("1"));
        assertTrue(palindrome.isPalindrome("%"));
        assertFalse(palindrome.isPalindrome("%&"));
        assertFalse(palindrome.isPalindrome("aabbba"));
        assertFalse(palindrome.isPalindrome("aab"));
    }
    
    @Test
    public void testIsPalindrome2() {
        OffByOne offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("axdyb", offByOne));
        assertTrue(palindrome.isPalindrome("axyb", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));        
        assertTrue(palindrome.isPalindrome("ab", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertFalse(palindrome.isPalindrome("aa", offByOne));
        assertFalse(palindrome.isPalindrome("aA", offByOne));
        assertFalse(palindrome.isPalindrome("ac", offByOne));
    }
}
