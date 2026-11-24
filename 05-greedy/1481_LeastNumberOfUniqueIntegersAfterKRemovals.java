/*
 * 1481. Least Number of Unique Integers after K Removals  (Medium)
 * https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
 *
 * Pattern         : Greedy (+ Hashing for counts)
 * Trigger -> Trick: "remove k items to minimise the distinct count" ->
 *                   each number costs its frequency, so remove the SMALLEST
 *                   frequencies first (cheapest first).
 *                   Sort the frequencies, not the values.
 * Related: 347 Top K Frequent Elements (same counting, flipped).
 *
 * n = arr.length, u = number of distinct values
 * Optimised   : count map + sort frequencies, greedy    -> Time O(n + u log u), Space O(u)
 * Bonus       : bucket by frequency instead of sorting  -> Time O(n),           Space O(n)
 *
 * Bonus O(n) bucket variant:
 *   int[] numWithFreq = new int[n + 1];          // numWithFreq[f] = how many values appear f times
 *   for (int c : freq.values()) numWithFreq[c]++;
 *   int unique = freq.size();
 *   for (int f = 1; f <= n; f++) {
 *       if (numWithFreq[f] == 0) continue;
 *       int canRemove = Math.min(numWithFreq[f], k / f);
 *       unique -= canRemove;
 *       k -= canRemove * f;
 *       if (k < f) break;                         // can't afford any bigger frequency
 *   }
 *   return unique;
 */
import java.util.*;

class LeastNumberOfUniqueIntegersAfterKRemovals {

    // Greedy: count, sort frequencies ascending, remove whole numbers while k allows.
    static class Solution {
        public int findLeastNumOfUniqueInts(int[] arr, int k) {
            Map<Integer, Integer> freq = new HashMap<>();
            for (int x : arr) freq.put(x, freq.getOrDefault(x, 0) + 1);
            List<Integer> list = new ArrayList<>(freq.values());
            Collections.sort(list);
            int unique = list.size();
            for (int x : list) {
                if (k >= x) {
                    k -= x;
                    unique--;
                } else break;
            }
            return unique;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findLeastNumOfUniqueInts(new int[]{5, 5, 4}, 1));                // 1
        System.out.println(new Solution().findLeastNumOfUniqueInts(new int[]{4, 3, 1, 1, 3, 3, 2}, 3));    // 2
    }
}
