package algorithms.strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * String Automaton
 * only handle printable Ascii characters
 */
class StringAutomaton {
    private static Logger logger = LoggerFactory.getLogger(StringAutomaton.class);


    private String pattern;
    private char[] patternArray;
    private int[][] transitionMatrix;

    /**
     * for string automaton, states are char index+1: 0, 1, ..., pattern.length.
     * 0 is start state, pattern.length is accept state
     * transitionMatrix[q][a] is the state when next char is a and current automaton in stage q.
     * transition function delta(q,a)=theta(P(q)a), that is length of longest prefix of pattern is the suffix of P(q)a
     * when delta(q,a)=m, then it is a match
     */
    public StringAutomaton(String pattern) {
        this.pattern = pattern;
        this.patternArray = pattern.toCharArray();
        transitionMatrix = new int[pattern.length()+1][127];
        computeTransitionFunction();
    }

    public int transition(int state, char nextChar) {
        return transitionMatrix[state][nextChar];
    }

    /**
     * O(pattern.length()^3 * len(char set))
     */
    private void computeTransitionFunction() {
        for (int q = 0; q <= pattern.length(); q++) {
            // note, q=0 is start state, q=1 is first character index+1
            for (int c = 32; c < 127; c++) {
                // find longest prefix of P is suffix of P(q)c
                int k = Math.min(pattern.length() + 1, q + 2);
                String newSrc = pattern.substring(0, q) + (char)c;
                do {
                    k -= 1;
                } while (!isSuffix(newSrc, pattern.substring(0, k)));
                transitionMatrix[q][c] = k;
                logger.info("current str {}", newSrc);
                logger.info("state {} on char {} transit to state {}", q, c, k);
            }
        }

    }

    // use java string method, takes O(len(pattern))
    private boolean isSuffix(String src, String pattern) {
        return src.endsWith(pattern);
    }


}


public class FiniteAutomatonStringMatcher {
    private static Logger logger = LoggerFactory.getLogger(FiniteAutomatonStringMatcher.class);

    private String pattern;
    private StringAutomaton automaton;

    public FiniteAutomatonStringMatcher(String pattern) {
        this.pattern = pattern;
        automaton = new StringAutomaton(pattern);
    }

    /**
     * O(n)
     */
    public int[] findMatch(String src) {
        List<Integer> res = new ArrayList();
        int q = 0;
        for (int i = 0; i < src.length(); i++) {
            q = automaton.transition(q, src.charAt(i));
            if (q == pattern.length()) {
                res.add(i - pattern.length() + 1);
            }
            logger.info("cur index {} state {}", i, q);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
