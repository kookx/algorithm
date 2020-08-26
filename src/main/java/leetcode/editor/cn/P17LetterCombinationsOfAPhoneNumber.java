//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法 
// 👍 851 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：电话号码的字母组合
public class P17LetterCombinationsOfAPhoneNumber{
    public static void main(String[] args) {
        Solution solution = new P17LetterCombinationsOfAPhoneNumber().new Solution();
        // TO TEST
        solution.letterCombinations("23");
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：回溯
    private String[] phoneNumber = {
            " ",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };
    private List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }
        backtrack(digits, 0, "");
        return res;
    }

    private void backtrack(String digits, int index, String s) {
        if (s.length() == digits.length()) {
            res.add(s);
            return;
        }
        char key = digits.charAt(index);
        String letters = phoneNumber[key - '0'];
        for (int i = 0; i < letters.length(); i++) {
            backtrack(digits, index + 1, s + letters.charAt(i));
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}