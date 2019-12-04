package algorithms.strings;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class RabinKarpStringMatcherTest {

    private RabinKarpMatcher stringMatcher = new RabinKarpMatcher();

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
        int [] matches = stringMatcher.findMatch("aaaa", "bbc");
        assertArrayEquals(new int[]{}, matches);
    }

}
