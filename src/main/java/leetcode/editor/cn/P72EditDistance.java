//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
// Related Topics 字符串 动态规划 
// 👍 1073 👎 0

 
package leetcode.editor.cn;
//Java：编辑距离
public class P72EditDistance{
    public static void main(String[] args) {
        Solution solution = new P72EditDistance().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：动态规划
    // 思路：类似1143.最长公共子序列
    // 状态定义：dp[i][j]为s1[0...i]和s2[0...j]的最短编辑距离
    // 状态方程：如果s1[i] == s2[j], dp[i][j] = dp[i-1][j-1]
    //         如果s1[i] != s2[j], dp[i][j] = 1 + min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        // 如果有一个为空串
        if (n * m == 0) {
            return n + m;
        }
        int[][] dp = new int[n + 1][m + 1];
        // base case
        for (int i = 0; i < n + 1; ++i) dp[i][0] = i;
        for (int i = 0; i < m + 1; ++i) dp[0][i] = i;
        // 自底向上求解
        for (int i = 1; i < n + 1; ++i) {
            for (int j = 1; j < m + 1; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][ j - 1];
                else dp[i][j] = 1 + Math.min(Math.min(
                        dp[i - 1][j],
                        dp[i][j - 1]),
                        dp[i - 1][j - 1]);
            }
        }
        return dp[n][m];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}