package com.danui.editdistance;

/**
 * https://en.wikipedia.org/wiki/Levenshtein_distance
 */
public class EditDistance {

    /**
     * @return Number of edits required to transform string 's' into 't'.
     */
    public static int solve(String s, String t) {
        return solve(s.toCharArray(), t.toCharArray());
    }

    /**
     * @return Number of edits required to transform char array 's' into 't'.
     */
    public static int solve(char[] s, char[] t) {
        int m = s.length;
        int n = t.length;
        if (m == 0) return n;
        if (n == 0) return m;
        int [][] d = new int[m+1][n+1];

        // Zero
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                d[i][j] = 0;
            }
        }

        // Editing 's' into an empty string requires s.length number of edits.
        for (int i = 1; i <= m; ++i) {
            d[i][0] = i;
        }

        // Likewise, editing an empty string into 't' requires t.length edits.
        for (int j = 1; j <= n; ++j) {
            d[0][j] = j;
        }

        for (int i = 1; i <= m; ++i) {
            char a = s[i-1];
            for (int j = 1; j <= n; ++j) {
                char b = t[j-1];
                int substitutionCost = (a==b) ? 0 : 1;
                d[i][j] = minimum(
                    d[i-1][j] + 1,                      // deletion
                    d[i][j-1] + 1,                      // insertion
                    d[i-1][j-1] + substitutionCost);    // substitution
            }
        }
        //DebugUtil.show(d);
        return d[m][n];
    }

    private static int minimum(int a, int b, int c) {
        return minimum(a, minimum(b, c));
    }

    private static int minimum(int a, int b) {
        if (a <= b) return a;
        return b;
    }
}
