/*
 * 49. Group Anagrams  (Medium)
 * https://leetcode.com/problems/group-anagrams/
 *
 * Pattern         : Hashing
 * Trigger -> Trick: "anagrams / same letters" -> sorted word as the HashMap key.
 *
 * n = number of words, k = max word length
 * Brute force : pairwise isAnagram + used[]     -> Time O(n^2 * k),      Space O(n)
 * Optimised   : sorted word -> list of words    -> Time O(n * k log k),  Space O(n * k)
 *
 * Follow-up: a 26-letter count array as the key (e.g. "1#0#2#...")
 * avoids sorting -> O(n * k).
 */
import java.util.*;

class GroupAnagrams {

    // Brute force: compare every unused word with every later word.
    static class SolutionBrute {
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> result = new ArrayList<>();
            boolean[] used = new boolean[strs.length];
            for (int i = 0; i < strs.length; i++) {
                if (used[i]) continue;
                List<String> group = new ArrayList<>();
                group.add(strs[i]);
                used[i] = true;
                for (int j = i + 1; j < strs.length; j++) {
                    if (!used[j] && isAnagram(strs[i], strs[j])) {
                        group.add(strs[j]);
                        used[j] = true;
                    }
                }
                result.add(group);
            }
            return result;
        }

        public static boolean isAnagram(String s1, String s2) {
            if (s1.length() != s2.length()) return false;
            int[] freq = new int[26];
            for (char c : s1.toCharArray()) freq[c - 'a']++;
            for (char c : s2.toCharArray()) if (--freq[c - 'a'] < 0) return false;
            return true;
        }
    }

    // Optimised: anagrams share the same sorted form, so use it as the key.
    static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> groups = new HashMap<>();
            for (String s : strs) {
                char[] ch = s.toCharArray();
                Arrays.sort(ch);
                String key = new String(ch);
                groups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
            }
            return new ArrayList<>(groups.values());
        }
    }

    public static void main(String[] args) {
        String[] in = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new Solution().groupAnagrams(in));      // [[eat, tea, ate], [bat], [tan, nat]] (any order)
        System.out.println(new SolutionBrute().groupAnagrams(in));
    }
}
