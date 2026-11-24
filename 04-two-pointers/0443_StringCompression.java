/*
 * 443. String Compression  (Medium)
 * https://leetcode.com/problems/string-compression/
 *
 * Pattern         : Two Pointers (read pointer + write pointer, in place)
 * Trigger -> Trick: "compress / modify in place, O(1) space" ->
 *                   read pointer j finds each CONSECUTIVE group (while chars[j] == c),
 *                   write pointer w writes the char, plus the count digits only if count > 1.
 *
 * Why is writing in place safe? A group's output is never longer than the group
 * ("a" -> "a", "aa" -> "a2", "aaaaaaaaaaaa" -> "a12"), so w never passes i.
 * Lesson: groups are CONSECUTIVE runs, not total frequency ("aabaa" -> "a2ba2").
 *
 * Version 1   : build the answer in a StringBuilder, copy back -> Time O(n), Space O(n)
 * Optimised   : read / write pointers in the same array       -> Time O(n), Space O(1)
 */
import java.util.*;

class StringCompression {

    // Version 1: StringBuilder, then copy back into chars.
    static class SolutionBuilder {
        public int compress(char[] chars) {
            StringBuilder sb = new StringBuilder();
            int n = chars.length;
            int i = 0;
            while (i < n) {
                char c = chars[i];
                int j = i;
                while (j < n && chars[j] == c) j++;
                int length = j - i;
                sb.append(c);
                if (length > 1) sb.append(length);
                i = j;
            }
            for (int t = 0; t < sb.length(); t++) chars[t] = sb.charAt(t);
            return sb.length();
        }
    }

    // Version 2 (optimised): in place with read (i, j) and write (w) pointers.
    static class Solution {
        public int compress(char[] chars) {
            int n = chars.length;
            int i = 0;
            int w = 0;
            while (i < n) {
                char c = chars[i];
                int j = i;
                while (j < n && chars[j] == c) j++;
                int count = j - i;
                chars[w++] = c;
                if (count > 1) {
                    for (char d : String.valueOf(count).toCharArray()) chars[w++] = d;
                }
                i = j;
            }
            return w;
        }
    }

    // Prints "length compressedPart", e.g. "6 a2b2c3".
    private static String show(char[] chars, int len) {
        return len + " " + new String(chars, 0, len);
    }

    public static void main(String[] args) {
        char[] a = "aabbccc".toCharArray();
        System.out.println(show(a, new Solution().compress(a)));          // 6 a2b2c3
        char[] b = "a".toCharArray();
        System.out.println(show(b, new Solution().compress(b)));          // 1 a
        char[] c = "abbbbbbbbbbbb".toCharArray();
        System.out.println(show(c, new Solution().compress(c)));          // 4 ab12
        char[] d = "aabaa".toCharArray();
        System.out.println(show(d, new SolutionBuilder().compress(d)));   // 5 a2ba2
    }
}
