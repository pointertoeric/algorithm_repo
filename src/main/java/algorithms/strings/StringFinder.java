package algorithms.strings;

interface StringFinder {

    /**
     * find all occurrence positions of pattern in src string
     * @return all occurrence positions of pattern in src string
     */
    int[] findString(String src, String pattern);

    String timeComplexity();
}
