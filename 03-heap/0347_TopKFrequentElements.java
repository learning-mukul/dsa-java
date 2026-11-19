/*
 * 347. Top K Frequent Elements  (Medium)
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Pattern         : Heap (+ Hashing for counts)
 * Trigger -> Trick: "top / most frequent k" -> count map + MIN-heap of size k
 *                   (or bucket sort by count for O(n)).
 *
 * n = nums.length, u = number of distinct values
 * Version 1   : count map + sort keys by count       -> Time O(n + u log u), Space O(u)
 * Optimised   : count map + min-heap of size k       -> Time O(n + u log k), Space O(u + k)
 *
 * Why a MIN-heap? It keeps the k biggest: whenever size > k we throw out
 * the smallest count, so only the top k survive.
 */
import java.util.*;

class TopKFrequentElements {

    // Version 1: count, then sort distinct keys by count (descending).
    static class SolutionSort {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
            List<Integer> keys = new ArrayList<>(map.keySet());
            keys.sort((a, b) -> map.get(b) - map.get(a));
            int[] ans = new int[k];
            for (int i = 0; i < k; i++) ans[i] = keys.get(i);
            return ans;
        }
    }

    // Version 2 (optimised): min-heap ordered by frequency, capped at size k.
    static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> freq = new HashMap<>();
            for (int x : nums) freq.put(x, freq.getOrDefault(x, 0) + 1);
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> freq.get(a) - freq.get(b));
            for (int x : freq.keySet()) {
                pq.offer(x);
                if (pq.size() > k) pq.poll();
            }
            int[] ans = new int[k];
            for (int i = 0; i < k; i++) ans[i] = pq.poll();
            return ans;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));     // [2, 1] (any order)
        System.out.println(Arrays.toString(new SolutionSort().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2))); // [1, 2]
    }
}
