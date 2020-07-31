//给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。 
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 5
//输出:
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//] 
// Related Topics 数组 
// 👍 335 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：杨辉三角
public class P118PascalsTriangle{
    public static void main(String[] args) {
        Solution solution = new P118PascalsTriangle().new Solution();
        // TO TEST
        solution.generate(5);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> generate(int numRows) {
        return dp(numRows);
    }

    // 解法1. 动态规划
    // 思路：下一行的第一位和最后一位为1，下一行第二位是上一行的第一二位的和
    private List<List<Integer>> dp(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numRows == 0) return ans;

        // 初始化第一组数
        ans.add(new ArrayList<>());
        ans.get(0).add(1);

        for (int row = 1; row < numRows; row++) {
            List<Integer> pre = ans.get(row - 1);
            List<Integer> curr = new ArrayList<>();

            curr.add(1);

            for (int j = 1; j < row; j++) {
                curr.add(pre.get(j - 1) + pre.get(j));
            }

            curr.add(1);

            ans.add(curr);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}