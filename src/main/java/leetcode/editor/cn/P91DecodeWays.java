//一条包含字母 A-Z 的消息通过以下方式进行了编码： 
//
// 'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 示例 1: 
//
// 输入: "12"
//输出: 2
//解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2: 
//
// 输入: "226"
//输出: 3
//解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
// Related Topics 字符串 动态规划 
// 👍 462 👎 0

 
package leetcode.editor.cn;
//Java：解码方法
public class P91DecodeWays{
    public static void main(String[] args) {
        Solution solution = new P91DecodeWays().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1. 动态规划
    // 状态定义：dp[i] 为s[0...i]的解码方法总和
    // 思路： 考虑到'0'无法被单独解码这种情况，所以需要分析不同情况
    // 1. s[i] = '0',那么s[i - 1]只可以为'1'或'2'，因为如果同时存在两个相邻的'0'，比如：'100'，此时整个字符串都无法被译码,return 0
    //    这时dp[i] = dp[i - 2], 即s[i]和s[i-1]只能同时被解码，所以不增加dp[i - 2]的解码总和数
    // 2. s[i-1] = '1', 那么s[i]可以单独被解码(dp[i] = dp[i-1])，或s[i]和s[i-1]同时被解码(dp[i] = dp[i-2])
    //    这时dp[i] = dp[i-1] + dp[i-2]
    // 3. s[i-1] = '2'并且'1'<=s[i]<='6'，同上，s[i]可以单独被解码，或s[i]和s[i-1]同时被解码
    //    这时dp[i] = dp[i-1] + dp[i-2]
    // 其余情况dp[i]不增加dp[i-1]的解码总和数，因此dp[i] = dp[i-1]
    // 这里可以看到dp[i] 仅可能与前两项有关，因此可以使用单变量代替dp数组
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;

        int pre = 1, curr = 1;//dp[-1] = dp[0] = 1

        for (int i = 1; i < s.length(); i++) {
            int temp = curr;
            if (s.charAt(i) == '0') {
                if (s.charAt(i-1) == '1' || s.charAt(i-1) == '2') {
                    curr = pre;
                } else {
                    return 0;
                }
            } else {
                if (s.charAt(i-1) == '1' || (s.charAt(i-1) == '2' && s.charAt(i) <= '6' && s.charAt(i) >= '1')) {
                    curr = curr + pre;
                }
            }
            pre = temp;
        }
        return curr;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}