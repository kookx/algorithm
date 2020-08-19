//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。 
//
// 示例 1: 
//
// 
//输入: "aba"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "abca"
//输出: True
//解释: 你可以删除c字符。
// 
//
// 注意: 
//
// 
// 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。 
// 
// Related Topics 字符串 
// 👍 247 👎 0

 
package leetcode.editor.cn;
//Java：验证回文字符串 Ⅱ
public class P680ValidPalindromeIi{
    public static void main(String[] args) {
        Solution solution = new P680ValidPalindromeIi().new Solution();
        // TO TEST
        System.out.println(solution.validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：双指针，参考125
    // 思路：遇到不等的情况，左指针右移或右指针左移检查
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        char[] arr = s.toCharArray();
        while (left < right) {
            if (left < right && arr[left] != arr[right]) {
                // 遇到不同的，选一条路走下去，即隐含了'最多删除一个字符'的意思
                return isPalindrome(arr, left + 1, right) || isPalindrome(arr, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isPalindrome(char[] arr, int left, int right) {
        while (left < right) {
            if (arr[left++] != arr[right--])
                return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}