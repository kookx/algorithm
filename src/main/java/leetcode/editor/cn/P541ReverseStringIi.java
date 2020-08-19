//给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。 
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例: 
//
// 输入: s = "abcdefg", k = 2
//输出: "bacdfeg"
// 
//
// 
//
// 提示： 
//
// 
// 该字符串只包含小写英文字母。 
// 给定字符串的长度和 k 在 [1, 10000] 范围内。 
// 
// Related Topics 字符串 
// 👍 87 👎 0

 
package leetcode.editor.cn;
//Java：反转字符串 II
public class P541ReverseStringIi{
    public static void main(String[] args) {
        Solution solution = new P541ReverseStringIi().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：双指针
    // 解题思路：344题反转数组的进阶版，每2k为一组，前k个元素反转，后k个元素保持不变。
    //前k个元素的下标为（i, i + k - 1）,此处需判断是否超出数组范围，反转之后 i = i +2k,
    //进行下一组的反转。
    public String reverseStr(String s, int k) {
        char[] charArray = s.toCharArray();
        int len = charArray.length;
        for (int i = 0; i < len; i += 2*k) {
            int left = i;
            int right = (i + k - 1) < len ? (i + k - 1) : len - 1;
            while (left <= right) {
                char temp = charArray[left];
                charArray[left++] = charArray[right];
                charArray[right--] = temp;
            }
        }
        return String.valueOf(charArray);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}