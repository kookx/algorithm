//在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。 
//
// 示例: 
//
// 输入: 
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//输出: 4 
// Related Topics 动态规划 
// 👍 503 👎 0

 
package leetcode.editor.cn;
//Java：最大正方形
public class P221MaximalSquare{
    public static void main(String[] args) {
        Solution solution = new P221MaximalSquare().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1.暴力
    // 解法2.动态规划
    // 状态定义：dp(i, j)dp(i,j) 表示以 (i, j)(i,j) 为右下角，且只包含 11 的正方形的边长最大值
    // DP方程：dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
    //        如果 i 和 j 中至少有一个为 0，则以位置 (i, j) 为右下角的最大正方形的边长只能是 1，
    //        因此 dp(i, j) = 1dp(i,j)=1。
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                }
                maxSide = Math.max(dp[i][j], maxSide);
            }
        }

        return maxSide * maxSide;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}