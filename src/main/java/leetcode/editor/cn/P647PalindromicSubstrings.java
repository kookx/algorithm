//给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。 
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。 
//
// 示例 1: 
//
// 
//输入: "abc"
//输出: 3
//解释: 三个回文子串: "a", "b", "c".
// 
//
// 示例 2: 
//
// 
//输入: "aaa"
//输出: 6
//说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
// 
//
// 注意: 
//
// 
// 输入的字符串长度不会超过1000。 
// 
// Related Topics 字符串 动态规划 
// 👍 294 👎 0

 
package leetcode.editor.cn;
//Java：回文子串
public class P647PalindromicSubstrings{
    public static void main(String[] args) {
        Solution solution = new P647PalindromicSubstrings().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countSubstrings(String s) {
        /**
         * 解法1. 动态规划
         *
         * 状态：dp[i][j] 表示字符串s在[i,j]区间的子串是否是一个回文串。
         * 状态转移方程：当 s[i] == s[j] && (j - i < 2 || dp[i + 1][j - 1]) 时，dp[i][j]=true，否则为false
         * 这个状态转移方程是什么意思呢？
         *
         * 当只有一个字符时，比如a自然是一个回文串。
         * 当有两个字符时，如果是相等的，比如aa，也是一个回文串。
         * 当有三个及以上字符时，比如ababa这个字符记作串1，把两边的a去掉，也就是bab记作串2，可以看出只要串2是一个回文串，那么左右各多了一个a的串1必定也是回文串。所以当s[i]==s[j]时，自然要看dp[i+1][j-1]是不是一个回文串。
         */
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int ans = 0;

        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ans++;
                }
            }
        }

        return ans;
    }
    // 解法2. 中心扩散
    // 思路：回文串分为两种，第一种是奇数长度，中间字母不管，关于中间字母是对称的，另一种是偶数长度，关于中间线对称
    //于是，我们依次对单个字母和两个字母进行扩散出去，来算出每种情况的回文子串的个数，最后相加就可以了
    public int countSubstrings2(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            ans += check(s, i, i);
            ans += check(s, i, i + 1);
        }
        return ans;
    }

    private int check(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            count++;
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}