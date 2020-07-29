//给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。 
//
// 示例 1: 
//
// 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//输出: false 
// Related Topics 字符串 动态规划 
// 👍 270 👎 0

 
package leetcode.editor.cn;
//Java：交错字符串
public class P97InterleavingString{
    public static void main(String[] args) {
        Solution solution = new P97InterleavingString().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public boolean isInterleave(String s1, String s2, String s3) {

        return dfs(s1, s2, s3);
    }

    // 解法1. 动态规划，参照官方
    public boolean dp(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if( n + m != t){
            return false;
        }

        boolean[][] f = new boolean[n + 1][m + 1];

        f[0][0] = true;

        for (int i = 0; i <= n; ++i) {
            for (int j = 0;j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    f[i][j] = f[i][j] || (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return f[n][m];
    }

    // 解法2. 回溯 + 记忆化
    public boolean dfs(String s1, String s2, String s3) {
        if (s1 == null) s1 = "";
        if (s2 == null) s2 = "";
        if (s3 == null) s3 = "";

        return helper(s1, s2, s3, 0, 0, 0, new Boolean[s1.length() + 1][s2.length() + 1]);
    }

    private boolean helper(String s1, String s2, String s3, int i, int j, int k, Boolean[][] memo) {
        if (memo[i][j] != null) return memo[i][j];
        if (i == s1.length() && j == s2.length() && k == s3.length()) {
            return true;
        }

        if (k >= s3.length()) return memo[i][j] = false;

        if (i < s1.length() && s1.charAt(i) == s3.charAt(k) && helper(s1, s2, s3, i + 1, j, k + 1, memo)) {
            return true;
        }
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k) && helper(s1, s2, s3, i, j+ 1, k + 1, memo)) {
            return true;
        }

        return memo[i][j] = false;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}