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

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//Java：有效的括号
public class P20ValidParentheses{
    public static void main(String[] args) {
        Solution solution = new P20ValidParentheses().new Solution();
        // TO TEST
        System.out.println(solution.isValid("(( )( ) ) "));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private Map<Character,Character> mappings;

    public Solution() {
        this.mappings = new HashMap<>();
        this.mappings.put('{','}');
        this.mappings.put('(',')');
        this.mappings.put('[',']');
    }

    public boolean isValid(String s) {
        // 解法1.栈 + hash 解决，原理：遇到左括号压入栈，遇到右括号弹出栈顶元素，最后检查栈内元素是否为空
        Stack<Character> stack = new Stack<>();
        for (int i = 0;i<s.length();++i) {
            char c = s.charAt(i);
            if(this.mappings.containsKey(c)) {
                stack.push(c);
            } else {
                if(stack.empty() || this.mappings.get(stack.pop()) != c){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}