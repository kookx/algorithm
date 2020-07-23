//给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 示例: 
//
// 输入:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 7
//解释: 因为路径 1→3→1→1→1 的总和最小。
// 
// Related Topics 数组 动态规划 
// 👍 558 👎 0

 
package leetcode.editor.cn;
//Java：最小路径和
public class P64MinimumPathSum{
    public static void main(String[] args) {
        Solution solution = new P64MinimumPathSum().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1. 动态规划
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length, column = grid[0].length;
        // 定义一个dp数组 保存前面的最优解：最小路径和
        int dp[][] = new int[row][column];

        dp[0][0] = grid[0][0];

        // 只含一列的情况
        for (int i = 1; i < row; ++i) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // 只含一行的情况
        for (int j = 1; j < column; ++j) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // 在m n中找
        for (int i = 1; i < row; ++i) {
            for (int j = 1; j < column; ++j) {
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[row -1][column -1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}