//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。 
//
// 
//
// 提示： 
//
// 
// num1 和num2 的长度都小于 5100 
// num1 和num2 都只包含数字 0-9 
// num1 和num2 都不包含任何前导零 
// 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式 
// 
// Related Topics 字符串 
// 👍 238 👎 0

 
package leetcode.editor.cn;
//Java：字符串相加
public class P415AddStrings{
    public static void main(String[] args) {
        Solution solution = new P415AddStrings().new Solution();
        // TO TEST
        solution.addStrings("123","1234");
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1.双指针从后往前模拟加法运算
    public String addStrings(String num1, String num2) {
        int p = num1.length() - 1, q = num2.length() - 1, carry = 0;
        StringBuffer ans = new StringBuffer();
        while (p >= 0 || q >= 0 || carry !=0) {
            // 当前位置是否没有位数了,没有的补0
            int x = p>=0?num1.charAt(p) - '0':0;
            int y = q>=0?num2.charAt(q) - '0':0;
            int temp = x + y + carry;
            carry = temp / 10;
            ans.append(temp % 10);
            p--;
            q--;
        }
        ans.reverse();
        return ans.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}