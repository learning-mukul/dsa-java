/*
 * 560. Subarray Sum Equals K  (Medium)
 * https://leetcode.com/problems/subarray-sum-equals-k/
 *
 * Pattern         : Prefix Sum + HashMap
 * Trigger -> Trick: "count subarrays with sum = k (negatives allowed)" ->
 *                   prefix sum + HashMap prefix -> count, seeded {0: 1}.
 *                   Check sum - k FIRST, then store sum. It is Two Sum on prefix sums.
 *
 * Intuition: bank balance. Money earned between two moments
 *            = later balance - earlier balance.
 *            So a subarray ending here sums to k  <=>  some earlier prefix == sum - k.
 * Why {0: 1}? It counts subarrays that start at index 0 (sum itself == k).
 * Why not sliding window? Negatives mean the sum does not grow as the window grows.
 *
 * Brute force : running sum extended from each start   -> Time O(n^2), Space O(1)
 * Optimised   : prefix sum + HashMap of counts         -> Time O(n),   Space O(n)
 */
import java.util.*;

class SubarraySumEqualsK {

    // Brute force: fix a start i, extend the end j, keep a running sum.
    static class SolutionBrute {
        public int subarraySum(int[] nums, int k) {
            int subArr = 0;
            for (int i = 0; i < nums.length; i++) {
                int sum = 0;
                for (int j = i; j < nums.length; j++) {
                    sum = sum + nums[j];
                    if (sum == k) subArr++;
                }
            }
            return subArr;
        }
    }

    // Optimised: how many earlier prefixes equal (sum - k)?
    static class Solution {
        public int subarraySum(int[] nums, int k) {
            Map<Integer, Integer> count = new HashMap<>();
            count.put(0, 1);
            int sum = 0;
            int result = 0;
            for (int x : nums) {
                sum += x;
                int need = sum - k;
                result += count.getOrDefault(need, 0);
                count.put(sum, count.getOrDefault(sum, 0) + 1);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().subarraySum(new int[]{1, 1, 1}, 2));           // 2
        System.out.println(new Solution().subarraySum(new int[]{1, 2, 3}, 3));           // 2
        System.out.println(new Solution().subarraySum(new int[]{1, -1, 0}, 0));          // 3
        System.out.println(new SolutionBrute().subarraySum(new int[]{1, -1, 0}, 0));     // 3
    }
}
