package algorithms.strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class KnuthMorrisPrattStringMatcher implements StringMatcher {
    private static Logger logger = LoggerFactory.getLogger(KnuthMorrisPrattStringMatcher.class);

    /**
     * prefix function will compute longest prefix of pattern is the proper suffix of pattern prefix itself.
     * f: {1,...., m} -> {0, 1,....,m-1}
     * input of f is the a prefix of pattern.
     * output of f is length of longest prefix of pattern is the proper suffix of this input prefix
     */
    private int[] computePrefixFunction(String pattern) {
        int[] res = new int[pattern.length()+1];
        res[1] = 0;
        int q = -1; // current match pattern index
        for (int i = 1; i < pattern.length(); i++) {
            // check whether next position matches
            while (q >= 0 && pattern.charAt(q+1) != pattern.charAt(i)) {
                // next possible match position, q is last pattern index matches, so current prefix length is q+1
                // q-1+1 is next position to try
                q = res[q+1];
            }
            // next char match
            if (pattern.charAt(q+1) == pattern.charAt(i)) {
                q++;
            }
            res[i+1] = q+1;
        }
        return res;
    }

    /**
     * O(n) to search, O(m) to pre process
     */
    public int[] findMatch(String src, String pattern) {
        List<Integer> res = new ArrayList();
        // compute prefix function
        int[] prefixFunction = computePrefixFunction(pattern);
        int q = -1; // current match pattern index
        for (int i = 0; i < src.length(); i++) {
            // check whether next position matches
            while (q >= 0 && pattern.charAt(q+1) != src.charAt(i)) {
                // next possible match position, q is last pattern index matches, so current prefix length is q+1
                // q is next position to try - 1
                q = prefixFunction[q+1] - 1;
            }
            // next char match
            if (pattern.charAt(q+1) == src.charAt(i)) {
                q++;
            }
            if(q == pattern.length()-1) {
                // find a match
                res.add(i - pattern.length() + 1);
                // q is next position to try - 1
                q = prefixFunction[q+1] - 1;
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
