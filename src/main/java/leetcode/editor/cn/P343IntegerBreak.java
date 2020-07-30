//给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。 
//
// 示例 1: 
//
// 输入: 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1。 
//
// 示例 2: 
//
// 输入: 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。 
//
// 说明: 你可以假设 n 不小于 2 且不大于 58。 
// Related Topics 数学 动态规划 
// 👍 329 👎 0

 
package leetcode.editor.cn;
//Java：整数拆分
public class P343IntegerBreak{
    public static void main(String[] args) {
        Solution solution = new P343IntegerBreak().new Solution();
        // TO TEST
        solution.integerBreak(10);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int integerBreak(int n) {
        return recursion(n);
    }

    // 解法1. 动态规划
    // 状态定义： dp[i]表示将i拆分成至少2个正整数的和后，这些整数乘起来后得到的乘积
    // 状态转移： dp[n] = j*max(i - j,dp[i - j])
    public int dfs(int n) {
        int dp[] = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], j * Math.max(i - j, dp[i - j]));
            }
        }
        return dp[n];
    }

    // 解法2. 分治递归 记忆化
    int memo[];
    int ans;
    public int recursion(int n) {
        if (n < 2) {
            return n;
        }
        memo = new int[n + 1];
        return helper(n);
    }
    private int helper(int n) {
        if (n == 2) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        ans = -1;
        for (int i = 1; i <= n - 1; i++) {
            ans = Math.max(ans, Math.max(i * helper(n - i), i * (n - i)));
        }
        memo[n] = ans;
        return ans;
    }

    // 解法3. 数学归纳法
    // 分解题意，求n可以分解3的最多和2的最小个数
    public int math(int n) {
        int max = 1;
        if (n == 2 || n == 3) return n - 1;

        for (; n > 4; n -= 3) {
            max *= 3;
        }
        return max * n;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}