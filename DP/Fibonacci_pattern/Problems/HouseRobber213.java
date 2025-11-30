/*
213 House Robber II
https://leetcode.com/problems/house-robber-ii/description/

You are a professional robber planning to rob houses along a street. Each house has a 
certain amount of money stashed. All houses at this place are arranged in a circle. 
That means the first house is the neighbor of the last one. Meanwhile, adjacent houses 
have a security system connected, and it will automatically contact the police if two 
adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return 
the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
Example 2:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 3:

Input: nums = [1,2,3]
Output: 3
 
Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000
 */

import java.util.Arrays;
import java.util.Scanner;

public class HouseRobber213 {

    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 0)
            return 0;
        if (n == 1)
            return nums[0];
        // Case 1: exclude last house
        int case1 = robHouse(Arrays.copyOfRange(nums, 0, n - 1));

        // Case 2: exclude first house
        int case2 = robHouse(Arrays.copyOfRange(nums, 1, n));

        return Math.max(case1, case2);
    }

    public int robHouse(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HouseRobber213 obj = new HouseRobber213();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println("Maximum amount of money: " + obj.rob(arr));
        sc.close();
    }

}