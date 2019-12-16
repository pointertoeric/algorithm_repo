package algorithms.strings;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class KMPStringMatcherTestrTest {
    private static Logger logger = LoggerFactory.getLogger(KMPStringMatcherTestrTest.class);

    private KnuthMorrisPrattStringMatcher stringMatcher = new KnuthMorrisPrattStringMatcher();

    @Test
    public void TestSearchNormal1() {
        int [] matches = stringMatcher.findMatch("aaab", "aa");
        assertArrayEquals(new int[]{0, 1}, matches);
    }

    @Test
    public void TestSearchNormal2() {
        int [] matches = stringMatcher.findMatch("aaaa", "aa");
        assertArrayEquals(new int[]{0, 1, 2}, matches);
    }

    @Test
    public void TestSearchNormal3() {
        int [] matches = stringMatcher.findMatch("abcabcabcc", "abc");
        assertArrayEquals(new int[]{0, 3, 6}, matches);
    }
}
