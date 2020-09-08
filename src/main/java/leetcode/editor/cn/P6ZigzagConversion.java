//将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下： 
//
// L   C   I   R
//E T O E S I I G
//E   D   H   N
// 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// string convert(string s, int numRows); 
//
// 示例 1: 
//
// 输入: s = "LEETCODEISHIRING", numRows = 3
//输出: "LCIRETOESIIGEDHN"
// 
//
// 示例 2: 
//
// 输入: s = "LEETCODEISHIRING", numRows = 4
//输出: "LDREOEIIECIHNTSG"
//解释:
//
//L     D     R
//E   O E   I I
//E C   I H   N
//T     S     G 
// Related Topics 字符串 
// 👍 806 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：Z 字形变换
public class P6ZigzagConversion{
    public static void main(String[] args) {
        Solution solution = new P6ZigzagConversion().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1：按行排序
    // 思路：从上到下拼接，遇到Z字拐点变换方向
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        List<StringBuilder> rows = new ArrayList<>();
        // 初始化行
        for (int i = 0; i < Math.min(numRows, s.length()); i++) rows.add(new StringBuilder());
        // 从第0行开始
        int curRow = 0;
        // 定义拐点
        boolean flag = false;
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            // 遇到Z字拐点变换方向
            if (curRow == 0 || curRow == numRows - 1) flag = !flag;
            curRow += flag ? 1 : -1;
        }
        StringBuilder res = new StringBuilder();
        // 按行输出
        for (StringBuilder row : rows) res.append(row);
        return res.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}