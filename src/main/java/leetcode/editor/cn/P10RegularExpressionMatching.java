//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 
//
// '.' 匹配任意单个字符
//'*' 匹配零个或多个前面的那一个元素
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
//
// 说明: 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "a*"
//输出: true
//解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3: 
//
// 输入:
//s = "ab"
//p = ".*"
//输出: true
//解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4: 
//
// 输入:
//s = "aab"
//p = "c*a*b"
//输出: true
//解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5: 
//
// 输入:
//s = "mississippi"
//p = "mis*is*p*."
//输出: false 
// Related Topics 字符串 动态规划 回溯算法 
// 👍 1475 👎 0

 
package leetcode.editor.cn;
//Java：正则表达式匹配
public class P10RegularExpressionMatching{
    public static void main(String[] args) {
        Solution solution = new P10RegularExpressionMatching().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：动态规划
    // 状态定义：dp[i][j] 代表s[0...i]和p[0...j]之间是否匹配
    // 思路：考虑状态转移方程时注意将p的*字符和p的前一个字符当成一个整体：如a*，同时p的.字符可以匹配s的任意一个字符，然后寻找i至j之间的关系
    public boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 0; i < n + 1; ++i) {
            for (int j = 1; j < m + 1; ++j) {
                // 特殊考虑*字符
                if (p.charAt(j - 1) == '*') {//注意dp数组因为考虑空字符串增加了一位长度，原字符长度要小一位
                    // p往前挪两位，将a*看成一个整体，a*可以匹配0到n次，这里表示选择匹配0次（默认p的*字符前一位字母和s该位置字母不等）
                    dp[i][j] = dp[i][j - 2];
                    if (matches(s, p, i, j - 1)) {//如果p的*字符前一位字母和s该位置字母相等
                        // 匹配1次，s往前挪一位
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[n][m];
    }

    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        // .字符可以匹配任意字符
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}