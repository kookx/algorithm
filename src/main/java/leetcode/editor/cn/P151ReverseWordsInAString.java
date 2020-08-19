//给定一个字符串，逐个翻转字符串中的每个单词。 
//
// 
//
// 示例 1： 
//
// 输入: "the sky is blue"
//输出: "blue is sky the"
// 
//
// 示例 2： 
//
// 输入: "  hello world!  "
//输出: "world! hello"
//解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 
//
// 示例 3： 
//
// 输入: "a good   example"
//输出: "example good a"
//解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
// 
//
// 
//
// 说明： 
//
// 
// 无空格字符构成一个单词。 
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。 
// 
//
// 
//
// 进阶： 
//
// 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。 
// Related Topics 字符串 
// 👍 209 👎 0

 
package leetcode.editor.cn;
//Java：翻转字符串里的单词
public class P151ReverseWordsInAString{
    public static void main(String[] args) {
        Solution solution = new P151ReverseWordsInAString().new Solution();
        // TO TEST
        System.out.println(solution.reverseWords("  hello world!  "));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1：简单解法(面试时不建议使用），先将字符串分割成数组，然后逆序输出
    public String reverseWords1(String s) {
        String[] strs = s.trim().split(" ");
        StringBuffer buffer = new StringBuffer();
        for (int i = strs.length - 1; i >= 0; i-- ) {
            if (strs[i].equals("")) continue;
            buffer.append(strs[i]).append(" ");
        }
        return buffer.toString().trim();
    }
    // 解法2：手写
    public String reverseWords(String s) {
        s.trim();// 删除首尾空格
        int len = s.length();
        int j = len - 1, i = j;
        String res = "";
        while (i >= 0) {
            while (i >=0 && s.charAt(i) != ' ') i--;//搜索首个空格
            res += s.substring(i + 1, j + 1) + " ";// 添加单词
            while (i >= 0 && s.charAt(i) == ' ') i--;//搜索下个单词末尾
            j = i;
        }
        return res.trim();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}