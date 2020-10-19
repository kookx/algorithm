//给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。 
//
// 注意：如果对空文本输入退格字符，文本继续为空。 
//
// 
//
// 示例 1： 
//
// 输入：S = "ab#c", T = "ad#c"
//输出：true
//解释：S 和 T 都会变成 “ac”。
// 
//
// 示例 2： 
//
// 输入：S = "ab##", T = "c#d#"
//输出：true
//解释：S 和 T 都会变成 “”。
// 
//
// 示例 3： 
//
// 输入：S = "a##c", T = "#a#c"
//输出：true
//解释：S 和 T 都会变成 “c”。
// 
//
// 示例 4： 
//
// 输入：S = "a#c", T = "b"
//输出：false
//解释：S 会变成 “c”，但 T 仍然是 “b”。 
//
// 
//
// 提示： 
//
// 
// 1 <= S.length <= 200 
// 1 <= T.length <= 200 
// S 和 T 只含有小写字母以及字符 '#'。 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？ 
// 
//
// 
// Related Topics 栈 双指针 
// 👍 202 👎 0

 
package leetcode.editor.cn;
//Java：比较含退格的字符串
public class P844BackspaceStringCompare{
    public static void main(String[] args) {
        Solution solution = new P844BackspaceStringCompare().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1：栈
    // 思路：将正常字符加入栈，遇到#弹出栈顶元素
    public boolean backspaceCompare(String S, String T) {
        StringBuilder s = new StringBuilder();
        StringBuilder t = new StringBuilder();

        for (char c : S.toCharArray()) {
            if (c == '#') {
                if (s.length() == 0) continue;
                s.deleteCharAt(s.length() - 1);
            } else {
                s.append(c);
            }
        }

        for (char c : T.toCharArray()) {
            if (c == '#') {
                if (t.length() == 0) continue;
                t.deleteCharAt(t.length() - 1);
            } else {
                t.append(c);
            }
        }
        return s.toString().equals(t.toString());
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}