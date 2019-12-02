package algorithms.strings;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class NativeStringFinderTest {

    private NativeStringFinder stringFinder = new NativeStringFinder();

    @Test
    public void TestSearchNormal1() {
        int [] matches = stringFinder.findString("aaab", "aa");
        assertArrayEquals(new int[]{0, 1}, matches);
    }

    @Test
    public void TestSearchEmpty() {
        int [] matches = stringFinder.findString("aaab", "");
        assertArrayEquals(new int[]{}, matches);
    }

    @Test
    public void TestPatternLongerThanSrc() {
        int [] matches = stringFinder.findString("aaab", "aa111111");
        assertArrayEquals(new int[]{}, matches);
    }
}
