package algorithms.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * RabinKarpMatcher is implementation of Rabin-Karp algorithm
 * it is hash based algorithm.
 * 1. first compute hash of pattern with length m use Horner's rule within theta(m)
 * 2. for every m sub string of src text, calculate same hash and compare with pattern. if not equal they must not
 * match, if equal, double check again.
 * worst-case running time is theta((n-m+1)m), n is length of source string, m is length of pattern string
 */
public class RabinKarpMatcher implements StringMatcher {
    // assume for ascii characters
    private static final int RADIX = 256;
    // make sure RADIX * Q within a word, so hash value fall in word, make all the necessary
    // computations with single-precision arithmetic
    private static final int Q = 101;


    /**
     * calculate hash value for pattern
     * p=P[m-1] + d*(P[m-2] + d*(P[m-3] + ... + d*(P[1] + d*P[0])))
     */
    private int preProcessPattern(String pattern) {
        int p = 0;
        for (int i = 0; i < pattern.length(); i++) {
            p = (RADIX * p + pattern.charAt(i)) % Q;
        }
        return p;
    }

    private int preProcessFirst(String src, int len) {
        int t = 0;
        for (int i = 0; i < len; i++) {
            t = (RADIX * t + src.charAt(i)) % Q;
        }
        return t;
    }

    @Override
    public int[] findMatch(String src, String pattern) {
        List<Integer> res = new ArrayList();
        // h=pow(radix, m-1)mod(Q)
        int h = (int)(Math.pow(RADIX, pattern.length() - 1)) % Q;
        int p = preProcessPattern(pattern);
        // them match src string
        int t = preProcessFirst(src, pattern.length());
        for (int s = 0; s <= src.length() - pattern.length(); s++) {
            if ( t == p) {
                // double check they are match
                for (int j = 0; j < pattern.length(); j++) {
                    if (pattern.charAt(j) != src.charAt(s + j)) {
                        break;
                    }
                    if (j == pattern.length() - 1) {
                        res.add(s);
                    }
                }
            }
            // update t for next m sub string
            // t[s+1] = (d*(t[s]-T[s]*h) + T[s+m])mod(q)
            if (s < src.length() - pattern.length()) {
                t = (RADIX * (t - src.charAt(s) * h) + src.charAt(s + pattern.length())) % Q;
                if ( t < 0) {
                    t += Q;
                }
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
