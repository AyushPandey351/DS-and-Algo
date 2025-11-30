
/*
Problem Statement: Given an array of N positive integers, we need to return the maximum
sum of the subsequence such that no two elements of the subsequence are 
adjacent elements in the array.

Note: A subsequence of an array is a list with elements of the array where some elements 
are deleted (or not deleted at all) and the elements should be in the same order in the 
subsequence as in the array.

Input: nums = [1, 2, 4]
Output: 5
Explanation: 
Subsequence {1,4} gives maximum sum.

Input:  [2, 1, 4, 9]
Output: 11
Explanation: 
Subsequence {2,9} gives maximum sum
*/
import java.util.*;

public class MaxSumNonAdjacentElements {
    /**
     * Recurisve approach
     * 
     * @param arr
     * @return
     */
    int rec_findMaxSum(int[] arr) {
        int n = arr.length;
        return rec_findMaxSum(n - 1, arr);
    }

    int rec_findMaxSum(int ind, int[] arr) {
        if (ind <= 0)
            return 0;
        if (ind == 0)
            return arr[ind];

        int pick = arr[ind] + rec_findMaxSum(ind - 2, arr);
        int notPick = rec_findMaxSum(ind - 1, arr);

        return Math.max(pick, notPick);
    }

    /***************************/
    /**
     * Memoization approach
     * 
     * @param arr
     * @return
     */
    int memo_findMaxSum(int[] arr) {
        int n = arr.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return memo_findMaxSum(n - 1, arr, memo);
    }

    int memo_findMaxSum(int ind, int[] arr, int[] memo) {
        if (ind < 0)
            return 0;
        if (ind == 0)
            return arr[ind];
        if (memo[ind] != -1)
            return memo[ind];

        int pick = arr[ind] + memo_findMaxSum(ind - 2, arr, memo);
        int notPick = memo_findMaxSum(ind - 1, arr, memo);

        return memo[ind] = Math.max(pick, notPick);
    }

    /***************************/

    /**
     * Tabulation approach
     * 
     * @param arr
     * @return
     */
    int tab_findMaxSum(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return arr[0];

        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(arr[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[n - 1];
    }

    /***************************/
    /**
     * Space optimized approach
     * 
     * @param arr
     * @return
     */
    int spaceOptimized_findMaxSum(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return 0;

        int prev1 = 0;
        int prev2 = arr[0];
        for (int i = 1; i < n; i++) {
            int include = arr[i] + prev1;
            int exclude = prev2;
            int curr = Math.max(include, exclude);
            prev1 = prev2;
            prev2 = curr;
        }
        return prev2;
    }

    /***************************/

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        MaxSumNonAdjacentElements obj = new MaxSumNonAdjacentElements();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println("Recursive Approach: " + obj.rec_findMaxSum(arr));
        System.out.println("Memoization Approach: " + obj.memo_findMaxSum(arr));
        System.out.println("Tabulation Approach: " + obj.tab_findMaxSum(arr));
        System.out.println("Tabulation Approach: " + obj.spaceOptimized_findMaxSum(arr));
        sc.close();
    }
}
