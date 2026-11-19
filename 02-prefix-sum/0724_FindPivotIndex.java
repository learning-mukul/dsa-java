/*
 * 724. Find Pivot Index  (Easy)
 * https://leetcode.com/problems/find-pivot-index/
 *
 * Pattern         : Prefix Sum
 * Trigger -> Trick: "left side vs right side sums" -> compute total once,
 *                   rightSum = total - leftSum - nums[i].
 *
 * Brute force : for each i, loop to sum left and right   -> Time O(n^2), Space O(1)
 * Optimised   : total + running leftSum                  -> Time O(n),   Space O(1)
 */
import java.util.*;

class FindPivotIndex {

    // Brute force: re-sum both sides for every index.
    static class SolutionBrute {
        public int pivotIndex(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                int left = 0, right = 0;
                for (int j = 0; j < i; j++) left += nums[j];
                for (int j = i + 1; j < nums.length; j++) right += nums[j];
                if (left == right) return i;
            }
            return -1;
        }
    }

    // Optimised: one pass after computing the total.
    static class Solution {
        public int pivotIndex(int[] nums) {
            int total = 0;
            for (int x : nums) total += x;
            int leftSum = 0;
            for (int i = 0; i < nums.length; i++) {
                int rightSum = total - leftSum - nums[i];
                if (leftSum == rightSum) return i;
                leftSum += nums[i];
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));   // 3
        System.out.println(new Solution().pivotIndex(new int[]{1, 2, 3}));            // -1
        System.out.println(new SolutionBrute().pivotIndex(new int[]{2, 1, -1}));      // 0
    }
}
