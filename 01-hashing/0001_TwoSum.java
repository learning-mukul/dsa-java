/*
 * 1. Two Sum  (Easy)
 * https://leetcode.com/problems/two-sum/
 *
 * Pattern         : Hashing
 * Trigger -> Trick: "pair summing to target" -> HashMap of seen value -> index,
 *                   check the complement BEFORE putting the current number.
 *
 * Brute force : try every pair (i, j)          -> Time O(n^2), Space O(1)
 * Optimised   : one pass with a HashMap        -> Time O(n),   Space O(n)
 *
 * Why check before put? So a number is never paired with itself
 * (e.g. nums = [3, 2, 4], target = 6 must not return [0, 0]).
 */
import java.util.*;

class TwoSum {

    // Brute force: check every pair.
    static class SolutionBrute {
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) return new int[]{i, j};
                }
            }
            return new int[]{-1, -1};
        }
    }

    // Optimised: complement lookup in a HashMap (paste-ready for LeetCode).
    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int need = target - nums[i];
                if (map.containsKey(need)) return new int[]{map.get(need), i};
                map.put(nums[i], i);
            }
            return new int[]{-1, -1};
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().twoSum(new int[]{2, 7, 11, 15}, 9))); // [0, 1]
        System.out.println(Arrays.toString(new Solution().twoSum(new int[]{3, 2, 4}, 6)));      // [1, 2]
        System.out.println(Arrays.toString(new SolutionBrute().twoSum(new int[]{3, 3}, 6)));    // [0, 1]
    }
}
