//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。 
//
// 示例 1: 
//
// 输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 说明： 
//
// 
// num1 和 num2 的长度小于110。 
// num1 和 num2 只包含数字 0-9。 
// num1 和 num2 均不以零开头，除非是数字 0 本身。 
// 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。 
// 
// Related Topics 数学 字符串 
// 👍 450 👎 0

 
package leetcode.editor.cn;
//Java：字符串相乘
public class P43MultiplyStrings{
    public static void main(String[] args) {
        Solution solution = new P43MultiplyStrings().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1.竖式相乘
    // 思路：从右至左模拟竖式相乘，每乘一位，就用竖式相加法计算结果，需要注意的是，nums2除末尾外，每一位都需要补零
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        String ans = "0";
        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            // 补零
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            int y = num2.charAt(i) - '0';
            int add = 0;
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int sum = x * y + add;
                add = sum / 10;
                curr.append(sum % 10);
            }
            if (add != 0) {
                curr.append(add % 10);
            }
            // 由于append是倒序的，反转传入
            ans = add(ans, curr.reverse().toString());
        }
        return ans;
    }

    private String add(String ans, String num) {
        int n = ans.length() - 1, m = num.length() - 1;
        int add = 0;
        StringBuffer curr = new StringBuffer();
        while (n >= 0 || m >= 0 || add != 0) {
            int x = n >= 0 ? ans.charAt(n) - '0':0;
            int y = m >= 0 ? num.charAt(m) - '0':0;
            int sum = x + y + add;
            add = sum / 10;
            curr.append(sum % 10);

            n--;
            m--;
        }
        return curr.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}