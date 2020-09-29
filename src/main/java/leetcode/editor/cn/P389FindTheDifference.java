//给定两个字符串 s 和 t，它们只包含小写字母。 
//
// 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。 
//
// 请找出在 t 中被添加的字母。 
//
// 
//
// 示例: 
//
// 输入：
//s = "abcd"
//t = "abcde"
//
//输出：
//e
//
//解释：
//'e' 是那个被添加的字母。
// 
// Related Topics 位运算 哈希表 
// 👍 156 👎 0

 
package leetcode.editor.cn;
//Java：找不同
public class P389FindTheDifference{
    public static void main(String[] args) {
        Solution solution = new P389FindTheDifference().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：位运算 异或
    // 思路：一个数和0做XOR运算等于本身：a⊕0 = a
    //      一个数和其本身做XOR运算等于 0：a⊕a = 0
    //      XOR 运算满足交换律和结合律：a⊕b⊕a = (a⊕a)⊕b = 0⊕b = b
    // 因此顺序异或运算最后得出的数即是多出的数
    public char findTheDifference(String s, String t) {
        char res = t.charAt(t.length() - 1);
        for (int i = 0; i < s.length(); ++i) {
            res ^= s.charAt(i);
            res ^= t.charAt(i);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}