
/*
https://leetcode.com/problems/climbing-stairs/description/
70. Climbing Stairs

You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

Constraints:

1 <= n <= 45
 */
// package Problems;
import java.util.Scanner;
import java.util.Arrays;

public class ClimbingStairs70 {
    /*
     * space optimized approach
     * 
     * @param n
     * 
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 1)
            return n;
        int prev1 = 1;
        int prev2 = 1;
        for (int i = 2; i <= n; i++) {
            int curr = prev1 + prev2;
            prev1 = prev2;
            prev2 = curr;
        }
        return prev2;
    }

    /************************/

    /*
     * reurisve approach
     * 
     * @param N
     * 
     * @return
     */
    public int recursive_climbStairs(int N) {
        if (N <= 1)
            return 1;
        return recursive_climbStairs(N - 1) + recursive_climbStairs(N - 2);
    }

    /************************/

    /*
     * Memoization Approach
     * 
     * @param N
     * 
     * @return
     */
    public int memoization_climbStairs(int N) {
        int[] memo = new int[N + 1];
        Arrays.fill(memo, -1);
        return memoization_climbStairs(N, memo);
    }

    public int memoization_climbStairs(int i, int[] memo) {
        if (i <= 1)
            return 1;
        if (memo[i] != -1)
            return memo[i];
        memo[i] = memoization_climbStairs(i - 1, memo) + memoization_climbStairs(i - 2, memo);
        return memo[i];
    }

    /************************/

    /*
     * Tabulation Approach
     * 
     * @param N
     * 
     * @return
     */
    public int tabulation_climbStairs(int N) {
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }

    /************************/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClimbingStairs70 obj = new ClimbingStairs70();
        int N = sc.nextInt();
        System.err.println("LeetCode: " + obj.climbStairs(N));
        System.out.println("Recurisve Approach: " + obj.recursive_climbStairs(N));
        System.out.println("Memoization Approach: " + obj.memoization_climbStairs(N));
        System.out.println("Tabulation Approach: " + obj.tabulation_climbStairs(N));
        sc.close();
    }
}
