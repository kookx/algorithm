//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法 
// 👍 1186 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：括号生成
public class P22GenerateParentheses{
    public static void main(String[] args) {
        Solution solution = new P22GenerateParentheses().new Solution();
        // TO TEST
        solution.generateParenthesis(2);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<String> result;

    public List<String> generateParenthesis(int n) {
        // 解法1. 递归
        // 分解题意：左括号个数必须小于n，右括号个数必须小于左括号
        result = new ArrayList<>();

        recursion(0, 0, n, "");
        return result;
    }

    private void recursion(int left, int right, int n, String str){
        if (left == n && right == n) {
            result.add(str);
            return;
        }
        if (left < n) {
            recursion(left + 1, right, n, str + "(");
        }
        if (left > right) {
            recursion(left, right + 1, n, str + ")");
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}