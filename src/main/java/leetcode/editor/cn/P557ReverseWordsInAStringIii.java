//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。 
//
// 示例 1: 
//
// 
//输入: "Let's take LeetCode contest"
//输出: "s'teL ekat edoCteeL tsetnoc" 
// 
//
// 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。 
// Related Topics 字符串 
// 👍 214 👎 0

 
package leetcode.editor.cn;
//Java：反转字符串中的单词 III
public class P557ReverseWordsInAStringIii{
    public static void main(String[] args) {
        Solution solution = new P557ReverseWordsInAStringIii().new Solution();
        // TO TEST
        System.out.println(solution.reverseWords("Let's take LeetCode contest"));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：双指针
    public String reverseWords(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        int left = 0;
        while (left < len) {
            int distance = 0;
            // 单词长度
            while (left + distance < len && s.charAt(left + distance) != ' ') distance++;
            int right = left + distance - 1, pos = right;
            // 交换单词
            while (left < right) {
                char temp = chars[left];
                chars[left++] = chars[right];
                chars[right--] = temp;
            }
            // 跳过空格，从下个单词首位开始
            left = pos + 2;
        }
        return String.valueOf(chars);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}