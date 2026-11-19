/*
 * 128. Longest Consecutive Sequence  (Medium)
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Pattern         : Hashing (HashSet)
 * Trigger -> Trick: "consecutive, O(n), no sort" -> put all in a HashSet,
 *                   only START counting at x where x-1 is missing.
 *
 * Brute force : sort + scan (skip duplicates)    -> Time O(n log n), Space O(1) extra*
 * Optimised   : HashSet, count from run starts   -> Time O(n),       Space O(n)
 *   (*ignoring the sort's own stack space)
 *
 * Why O(n) even with a while loop inside a for loop? Each number is visited
 * by the while loop only once, from the start of its own run.
 */
import java.util.*;

class LongestConsecutiveSequence {

    // Version 1: sort, then count runs.
    static class SolutionSort {
        public int longestConsecutive(int[] nums) {
            if (nums.length == 0) return 0;
            Arrays.sort(nums);
            int best = 1, cur = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) continue;          // duplicate: ignore
                if (nums[i] == nums[i - 1] + 1) cur++; else cur = 1;
                best = Math.max(best, cur);
            }
            return best;
        }
    }

    // Version 2 (optimised): HashSet, start only where x-1 is missing.
    static class Solution {
        public int longestConsecutive(int[] nums) {
            Set<Integer> hs = new HashSet<>();
            for (int x : nums) hs.add(x);
            int best = 0;
            for (int x : hs) {
                if (hs.contains(x - 1)) continue;              // not a run start
                int len = 1;
                while (hs.contains(x + len)) len++;
                best = Math.max(len, best);
            }
            return best;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));               // 4
        System.out.println(new SolutionSort().longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));   // 9
    }
}
