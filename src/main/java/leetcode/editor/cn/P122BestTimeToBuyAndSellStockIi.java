//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 7
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
// 
//
// 示例 2: 
//
// 输入: [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3: 
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 3 * 10 ^ 4 
// 0 <= prices[i] <= 10 ^ 4 
// 
// Related Topics 贪心算法 数组 
// 👍 795 👎 0

 
package leetcode.editor.cn;
//Java：买卖股票的最佳时机 II
public class P122BestTimeToBuyAndSellStockIi{
    public static void main(String[] args) {
        Solution solution = new P122BestTimeToBuyAndSellStockIi().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int maxProfit(int[] prices) {
        return dp1(prices);
    }

    // 解法1. 贪心算法
    // 后一天抛，前一天买
    private int greedy(int[] prices) {
        int res = 0;
        int len = prices.length;
        for (int i = 0; i < len - 1; ++i) {
            int diff = prices[i + 1] - prices[i];
            if (diff > 0) {
                res += diff;
            }
        }
        return res;
    }

    // 解法2. 动态规划
    // 状态定义：dp[i][0]表示第i天卖出股票的最大收益，dp[i][1]表示第i天持有股票的最大收益
    private int dp(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }

    // 解法3. 动态规划状态压缩
    private int dp1(int[] prices) {
        int len = prices.length;
        if (len < 2)
            return 0;
        int hold = -prices[0];
        int cash = 0;
        int preHold = hold;
        int preCash = cash;
        for (int i = 1; i < len; i++) {
            cash = Math.max(preCash, preHold + prices[i]);
            hold = Math.max(preHold, preCash - prices[i]);
            preCash = cash;
            preHold = hold;
        }
        return cash;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}