//给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入："ab-cd"
//输出："dc-ba"
// 
//
// 示例 2： 
//
// 输入："a-bC-dEf-ghIj"
//输出："j-Ih-gfE-dCba"
// 
//
// 示例 3： 
//
// 输入："Test1ng-Leet=code-Q!"
//输出："Qedo1ct-eeLg=ntse-T!"
// 
//
// 
//
// 提示： 
//
// 
// S.length <= 100 
// 33 <= S[i].ASCIIcode <= 122 
// S 中不包含 \ or " 
// 
// Related Topics 字符串 
// 👍 58 👎 0

 
package leetcode.editor.cn;
//Java：仅仅反转字母
public class P917ReverseOnlyLetters{
    public static void main(String[] args) {
        Solution solution = new P917ReverseOnlyLetters().new Solution();
        // TO TEST
        System.out.println(solution.reverseOnlyLetters("?6C40E"));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：双指针
    public String reverseOnlyLetters(String S) {
        int n = S.length();
        char[] as = S.toCharArray();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !isWord(as[left])) left++;
            while (left < right && !isWord(as[right])) right--;
            if (left < right) {
//                char temp = as[left];
//                as[left++] = as[right];
//                as[right--] = temp;
                //位运算法
                as[left] ^= as[right];
                as[right] ^= as[left];
                as[left++] ^= as[right--];
            }
        }
        return new String(as);
    }
    private boolean isWord(char s) {
        return (s >= 'a' && s <= 'z') || (s >= 'A' && s <= 'Z');
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}