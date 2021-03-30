import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    OffByN offBy6 = new OffByN(6);
    OffByN offBy32 = new OffByN(32);

    @Test
    public void testEqualChars() {
        assertTrue(offBy6.equalChars('a', 'g'));
        assertTrue(offBy6.equalChars('g', 'a'));
        assertFalse(offBy6.equalChars('f', 'z'));
        assertFalse(offBy6.equalChars('a', 'a'));
        assertFalse(offBy6.equalChars('a', 'b'));

        assertTrue(offBy32.equalChars('a', 'A'));
        assertTrue(offBy32.equalChars('z', 'Z'));
        assertFalse(offBy32.equalChars('a', 'z'));
        assertFalse(offBy32.equalChars('a', 'a'));
    }
}
