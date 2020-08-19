//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串 
// 👍 1223 👎 0

 
package leetcode.editor.cn;
//Java：最长公共前缀
public class P14LongestCommonPrefix{
    public static void main(String[] args) {
        Solution solution = new P14LongestCommonPrefix().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：两两找出公共前缀，最终结果即为最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        // 取第一单词作为比较词
        String ans = strs[0];
        for (int i = 1; i < strs.length; ++i) {
            int j = 0;
            for (;j < ans.length() && j < strs[i].length(); ++j) {
                // 如果
                if (ans.charAt(j) != strs[i].charAt(j))
                    break;
            }
            ans = ans.substring(0, j);
            //如果查找过程中出现了 ans 为空的情况，则公共前缀不存在直接返回
            if (ans == "")
                return ans;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}