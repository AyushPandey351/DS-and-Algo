
/*  
 * The Classic Problem
 * Calculate the nth Fibonacci number where the sequence is: 0, 1, 1, 2, 3, 5, 8, 13, 21.... 
 * Each number is the sum of the two preceding ones
*/
import java.util.Scanner;
import java.util.Arrays;

public class Fibonacci {
    static int[] dp;

    static int cal_naiveApproach(int N) {
        /*
         * Time Complexity: O(2^N) - Exponential
         * Space Complexity: O(n) - Recursion stack depth
         */
        if (N <= 1)
            return N;
        return cal_naiveApproach(N - 1) + cal_naiveApproach(N - 2);
    }

    static int cal_memoizationApproach(int N) {
        /*
         * Top-down approach
         * Time complexity: O(N) - Each value is computed once
         * Space complexity: O(N) - Memoiation Array + recursion stack
         * Here we easily avoid redundant calculations with caching
         */
        if (N <= 1)
            return N;
        if (dp[N] != -1)
            return dp[N];

        dp[N] = cal_memoizationApproach(N - 1) + cal_memoizationApproach(N - 2);
        return dp[N];
    }

    static int cal_tabulationApproach(int N) {
        /*
         * Time complexity = O(N) - Single loop itertaion
         * Space complexity = O(N) - DP array only
         * This iterative approach is more efficient as it avoids recursion overhead and
         * is more deterministic
         */
        if (N <= 1)
            return N;

        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }

    static int cal_spaceoptimized(int N) {
        /*
         * Time complexity = O(N)
         * Space complexity = O(1) - Only tw variables used.
         */
        if (N <= 1)
            return N;

        int prev = 0, curr = 1;
        for (int i = 2; i <= N; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }

        return curr;

    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.err.println("Please enter a number:");
        int N = sc.nextInt();

        int ans_naive = cal_naiveApproach(N);
        System.out.println("Recusrive Approach: " + ans_naive);

        dp = new int[N + 1];
        Arrays.fill(dp, -1);
        int ans_memoization = cal_memoizationApproach(N);
        System.err.println("Memoization Approach: " + ans_memoization);

        int ans_tabulation = cal_tabulationApproach(N);
        System.out.println("Tabulation Approach: " + ans_tabulation);

        int ans_spaceOptimized = cal_spaceoptimized(N);
        System.out.println("Space Optimized: " + ans_spaceOptimized);

        sc.close();
    }

}
