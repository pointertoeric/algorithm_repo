package algorithms.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Complexity O((src.length() - pattern.length() + 1) * pattern.length())
 */
public class NativeStringMatcher implements StringMatcher {

    public int[] findMatch(String src, String pattern) {
        String srcString = Objects.requireNonNull(src, "src string cannot be null");
        String patternString = Objects.requireNonNull(pattern, "patternString string cannot be null");
        if (srcString.length() < patternString.length()) {
            return new int[]{};
        }

        List<Integer> res = new ArrayList();
        for (int i = 0; i <= srcString.length() - patternString.length(); i++) {
            for (int j = 0; j < patternString.length(); j++) {
                if (patternString.charAt(j) != srcString.charAt(i + j)) {
                    break;
                }
                if (j == pattern.length() - 1) {
                    res.add(i);
                }
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
