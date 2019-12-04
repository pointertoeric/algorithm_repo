package algorithms.strings;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class NativeStringMatcherTest {

    private NativeStringMatcher stringMatcher = new NativeStringMatcher();

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
    public void TestSearchEmpty() {
        int [] matches = stringMatcher.findMatch("aaab", "");
        assertArrayEquals(new int[]{}, matches);
    }

    @Test
    public void TestPatternLongerThanSrc() {
        int [] matches = stringMatcher.findMatch("aaab", "aa111111");
        assertArrayEquals(new int[]{}, matches);
    }
}
