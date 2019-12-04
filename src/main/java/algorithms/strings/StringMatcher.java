package algorithms.strings;

interface StringMatcher {

    /**
     * find all occurrence positions of pattern in src string
     * @return all occurrence positions of pattern in src string
     */
    int[] findMatch(String src, String pattern);

}
