//给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。每次一个玩家
//只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。 
//
// 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。 
//
// 
//
// 示例 1： 
//
// 输入：[1, 5, 2]
//输出：False
//解释：一开始，玩家1可以从1和2中进行选择。
//如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）
//可选。
//所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
//因此，玩家 1 永远不会成为赢家，返回 False 。
// 
//
// 示例 2： 
//
// 输入：[1, 5, 233, 7]
//输出：True
//解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
//     最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= 给定的数组长度 <= 20. 
// 数组里所有分数都为非负数且不会大于 10000000 。 
// 如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。 
// 
// Related Topics 极小化极大 动态规划 
// 👍 254 👎 0

 
package leetcode.editor.cn;
//Java：预测赢家
public class P486PredictTheWinner{
    public static void main(String[] args) {
        Solution solution = new P486PredictTheWinner().new Solution();
        // TO TEST
        solution.PredictTheWinner(new int[]{1,2,253,7});
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1：递归
    // 思路：判断先手选择的左右边得分减去后手选择的左右边得分的最大值是否大于等于0
    public boolean PredictTheWinner1(int[] nums) {
        return recursion(nums, 0, nums.length - 1) >= 0;
    }

    private int recursion(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int scoreLeft = nums[start] - recursion(nums, start + 1, end);//选择左边
        int scoreRight = nums[end] - recursion(nums, start, end - 1);//选择右边
        return Math.max(scoreLeft, scoreRight);
    }

    // 解法2：记忆化递归
    // 思路：重复计算：比如，你先选 1，我再选 7，和你先选 7，我再选 1，
    // 这两种情况带来的子问题是一样的，都是剩下[5, 233]
    public boolean PredictTheWinner2(int[] nums) {
        int len = nums.length;
        int[][] memo = new int[len][len];
        return helper(nums, memo, 0, len - 1) >= 0;
    }

    private int helper(int[] nums, int[][] memo, int start, int end) {
        if (start == end) {
            return memo[start][end] = nums[start];
        }
        int scoreLeft = nums[start] - helper(nums, memo, start + 1, end);
        int scoreRight = nums[end] - helper(nums, memo, start, end - 1);
        return memo[start][end] = Math.max(scoreLeft, scoreRight);
    }

    // 解法3：动态规划
    // 思路：dp[i][j]表示在数组[i:j]中先手赢过对手的分数
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; ++i) {
            dp[i][i] = nums[i];
        }
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                int scoreLeft = nums[i] - dp[i + 1][j];
                int scoreRight = nums[j] - dp[i][j - 1];
                dp[i][j] = Math.max(scoreLeft, scoreRight);
            }
        }
        return dp[0][len - 1] >= 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}