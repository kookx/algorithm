//给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。 
//
// 一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列
//，而 "AEC" 不是） 
//
// 题目数据保证答案符合 32 位带符号整数范围。 
//
// 
//
// 示例 1： 
//
// 输入：S = "rabbbit", T = "rabbit"
//输出：3
//解释：
//
//如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
//(上箭头符号 ^ 表示选取的字母)
//
//rabbbit
//^^^^ ^^
//rabbbit
//^^ ^^^^
//rabbbit
//^^^ ^^^
// 
//
// 示例 2： 
//
// 输入：S = "babgbag", T = "bag"
//输出：5
//解释：
//
//如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。 
//(上箭头符号 ^ 表示选取的字母)
//
//babgbag
//^^ ^
//babgbag
//^^    ^
//babgbag
//^    ^^
//babgbag
//  ^  ^^
//babgbag
//    ^^^ 
// Related Topics 字符串 动态规划 
// 👍 241 👎 0

 
package leetcode.editor.cn;
//Java：不同的子序列
public class P115DistinctSubsequences{
    public static void main(String[] args) {
        Solution solution = new P115DistinctSubsequences().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：动态规划
    // 状态定义：dp[i][j] 代表s[0...i]在t[0...j]之间出现的次数
    // 思路：如果s[i]!=t[j],说明s[i]必不参与组成子序列，因此dp[i][j] = dp[i-1][j]
    //      如果s[i]==t[j],说明s[i]可以参与组成子序列，也可以不参与组成子序列，因此dp[i][j] = dp[i-1][j-1]+dp[i-1][j]
    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        // t为空时，s[0...i]个子串的空字符串都等于t
        for (int i = 0; i <= n; ++i) dp[i][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = s.charAt(i - 1) == t.charAt(j - 1) ? (dp[i - 1][j - 1] + dp[i - 1][j]) : dp[i - 1][j];
            }
        }
        return dp[n][m];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}