//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划 
// 👍 2583 👎 0

 
package leetcode.editor.cn;

import java.util.Arrays;

//Java：最长回文子串
public class P5LongestPalindromicSubstring{
    public static void main(String[] args) {
        Solution solution = new P5LongestPalindromicSubstring().new Solution();
        // TO TEST
        System.out.println(solution.longestPalindrome("babad"));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1：暴力解法(超时)
    public String longestPalindrome1(String s) {
        int n = s.length();
        int max = 0;
        String ans = "";
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String temp = s.substring(i, j);
                char[] chs = temp.toCharArray();
                if (isPalindrome(chs) && chs.length > max) {
                    ans = temp;
                    max = chs.length;
                }
            }
        }
        return ans;
    }

    private boolean isPalindrome(char[] s) {
        int len = s.length;
        for (int i = 0; i < len / 2; i++) {
            if (s[i] != s[len - i - 1])
                return false;
        }
        return true;
    }

    // 解法2：动态规划
    // 思路：定义状态dp[i][j] 表示[i,j]区间是否回文串
    // dp[i][j]=dp[i+1][j-1]
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int maxLen = 1;
        int begin = 0;
        char[] arr = s.toCharArray();
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                dp[i][j] = arr[i] == arr[j] && (j - i < 3 || dp[i + 1][j - 1]);
                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}