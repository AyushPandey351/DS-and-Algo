/*
https://leetcode.com/problems/house-robber/description/
198. House Robber

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can
rob tonight without alerting the police.

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400
*/

// package Problems;

import java.util.*;

public class HouseRobber198 {
    public int rob(int[] nums) {
        return 0;
    }

    /**************** Recursive Approch */
    public int recursive_rob(int[] nums, int i) {
        if (i < 0)
            return 0;
        return Math.max(recursive_rob(nums, i - 1), nums[i] + recursive_rob(nums, i - 2));
    }

    public int recursive_rob(int[] nums) {
        return recursive_rob(nums, nums.length - 1);
    }

    /**************** Recursive Approch */

    /**************** Memoization Approach */
    int memo[];

    public int memoization_rob(int[] nums) {
        memo = new int[nums.length + 1];
        Arrays.fill(memo, -1);
        return memoization_rob(nums, nums.length - 1);
    }

    public int memoization_rob(int[] nums, int i) {
        if (i < 0)
            return 0;
        if (memo[i] >= 0)
            return memo[i];

        memo[i] = Math.max(memoization_rob(nums, i - 1), nums[i] + memoization_rob(nums, i - 2));
        return memo[i];
    }

    /**************** Memoization Approach */

    /**************** Iterative Approach */
    public int iterative_rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }

        return dp[nums.length];
    }

    /**************** Iterative Approach */

    /**************** Space Optimized Approach */
    public int spaceOptimized_rob(int[] nums) {
        int prev1 = 0, prev2 = 0;
        for (int num : nums) {
            int temp = prev1;
            prev1 = Math.max(prev1, prev2 + num);
            prev2 = temp;
        }
        return prev1;
    }

    /**************** Space Optimized Approach */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HouseRobber198 obj = new HouseRobber198();
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Recursive: " + obj.recursive_rob(arr));
        System.out.println("Memoization: " + obj.memoization_rob(arr));
        System.out.println("iterative: " + obj.iterative_rob(arr));
        System.out.print("Space optimized approach: "+obj.spaceOptimized_rob(arr));
        sc.close();
    }
}
