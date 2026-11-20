/*
 * 238. Product of Array Except Self  (Medium)
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * Pattern         : Prefix Sum (prefix / suffix products)
 * Trigger -> Trick: "everything except me" / "left side vs right side" ->
 *                   prefix and suffix running products.
 *                   Start at 1 for multiply (0 for add). Use the running value
 *                   first, THEN include the current element.
 *                   No division: zeros break it.
 *
 * Brute force : for each i, multiply all nums[j], j != i   -> Time O(n^2), Space O(1)   (TLE)
 * Version 1   : left[] and right[] product arrays          -> Time O(n),   Space O(n)
 * Optimised   : ans[] holds left products, one running
 *               prodRight variable from the right          -> Time O(n),   Space O(1) extra
 *               (the output array does not count as extra space)
 *
 * Brute force (TLE, shown for reasoning):
 *   for (int i = 0; i < n; i++) {
 *       int p = 1;
 *       for (int j = 0; j < n; j++) if (j != i) p *= nums[j];
 *       ans[i] = p;
 *   }
 */
import java.util.*;

class ProductOfArrayExceptSelf {

    // Version 1: separate left and right product arrays.
    static class SolutionArrays {
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] left = new int[n];
            int[] right = new int[n];
            left[0] = 1;
            for (int i = 1; i < n; i++) left[i] = left[i - 1] * nums[i - 1];
            right[n - 1] = 1;
            for (int i = n - 2; i >= 0; i--) right[i] = right[i + 1] * nums[i + 1];
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) ans[i] = left[i] * right[i];
            return ans;
        }
    }

    // Version 2 (optimised): output array + one running variable.
    static class Solution {
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] ans = new int[n];
            ans[0] = 1;
            for (int i = 1; i < n; i++) ans[i] = ans[i - 1] * nums[i - 1];
            int prodRight = 1;
            for (int i = n - 1; i >= 0; i--) {
                ans[i] = ans[i] * prodRight;
                prodRight = prodRight * nums[i];
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().productExceptSelf(new int[]{1, 2, 3, 4})));             // [24, 12, 8, 6]
        System.out.println(Arrays.toString(new Solution().productExceptSelf(new int[]{-1, 1, 0, -3, 3})));        // [0, 0, 9, 0, 0]
        System.out.println(Arrays.toString(new SolutionArrays().productExceptSelf(new int[]{1, 2, 3, 4})));       // [24, 12, 8, 6]
    }
}
