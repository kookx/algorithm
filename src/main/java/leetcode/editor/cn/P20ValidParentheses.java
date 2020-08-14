//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串 
// 👍 1694 👎 0

 
package leetcode.editor.cn;

import java.util.*;

//Java：有效的括号
public class P20ValidParentheses{
    public static void main(String[] args) {
        Solution solution = new P20ValidParentheses().new Solution();
        // TO TEST
        System.out.println(solution.isValid("(( )( ) ) "));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1. 栈+DFS
    // 思路：左括号入栈右括号出栈，栈为空表示有效，否则无效
    public boolean isValid(String s) {
        char[] stack = new char[s.length()];
        int head = 0;
        for (Character c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack[head++] = c;
                    break;
                case '}':
                    if (head == 0 || stack[--head] != '{') return false;
                    break;
                case ']':
                    if (head == 0 || stack[--head] != '[') return false;
                    break;
                case ')':
                    if (head == 0 || stack[--head] != '(') return false;
                    break;
            }
        }
        return head == 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}