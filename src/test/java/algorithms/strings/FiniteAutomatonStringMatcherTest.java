package algorithms.strings;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class FiniteAutomatonStringMatcherTest {
    private static Logger logger = LoggerFactory.getLogger(FiniteAutomatonStringMatcherTest.class);


    @Test
    public void TestSearchNormal1() {
        FiniteAutomatonStringMatcher stringMatcher = new FiniteAutomatonStringMatcher("aa");
        int [] matches = stringMatcher.findMatch("aaab");
        assertArrayEquals(new int[]{0, 1}, matches);
    }

    @Test
    public void TestSearchNormal2() {
        FiniteAutomatonStringMatcher stringMatcher = new FiniteAutomatonStringMatcher("aa");
        int [] matches = stringMatcher.findMatch("aaaa");
        assertArrayEquals(new int[]{0, 1, 2}, matches);
    }

    @Test
    public void TestSearchNormal3() {
        FiniteAutomatonStringMatcher stringMatcher = new FiniteAutomatonStringMatcher("abc");
        int [] matches = stringMatcher.findMatch("abcabcabcc");
        assertArrayEquals(new int[]{0, 3, 6}, matches);
    }
}
