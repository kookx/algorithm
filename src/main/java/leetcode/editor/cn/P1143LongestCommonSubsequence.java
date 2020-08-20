//给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。 
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。 
//例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
// 
//
// 若这两个字符串没有公共子序列，则返回 0。 
//
// 
//
// 示例 1: 
//
// 输入：text1 = "abcde", text2 = "ace" 
//输出：3  
//解释：最长公共子序列是 "ace"，它的长度为 3。
// 
//
// 示例 2: 
//
// 输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc"，它的长度为 3。
// 
//
// 示例 3: 
//
// 输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= text1.length <= 1000 
// 1 <= text2.length <= 1000 
// 输入的字符串只含有小写英文字符。 
// 
// Related Topics 动态规划 
// 👍 209 👎 0

 
package leetcode.editor.cn;
//Java：最长公共子序列
public class P1143LongestCommonSubsequence{
    public static void main(String[] args) {
        Solution solution = new P1143LongestCommonSubsequence().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：动态规划
    // 状态定义：dp[i][j] 为s1[1...i]和s2[1...j]的最长公共子序列
    // 状态方程：s[i] == s[j]时 dp[i][j] = 1 + dp[i - 1][j - 1],s[i] != s[j]时 dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
    // 表示至少有一个字符不在lcs中，不需要考虑两个都不在lcs中的情况，即dp[i][j] = max(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]),
    // 因为dp[i - 1][j - 1]永远是三个中最小的，不需要判断
    // 关键：用两个指针 i 和 j 从后往前遍历 s1 和 s2，如果 s1[i]==s2[j]，那么这个字符一定在 lcs 中；
    // 否则的话，s1[i] 和 s2[j] 这两个字符至少有一个不在 lcs 中，需要丢弃一个
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        // base case
        // 可以省略，初始化默认为0
//        for (int i = 0; i < m; ++i) dp[0][i] = 0;
//        for (int i = 0; i < n; ++i) dp[i][0] = 0;
        for (int i = 1; i < n + 1; ++i) {
            for (int j = 1; j < m + 1; ++j) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // 找到一个 lcs 的元素，长度即为前一个lcs的长度+1
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    //谁能让 lcs 最长，就听谁的
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[n][m];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}