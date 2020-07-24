//爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。 
//
// 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作： 
//
// 
// 选出任一 x，满足 0 < x < N 且 N % x == 0 。 
// 用 N - x 替换黑板上的数字 N 。 
// 
//
// 如果玩家无法执行这些操作，就会输掉游戏。 
//
// 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：2
//输出：true
//解释：爱丽丝选择 1，鲍勃无法进行操作。
// 
//
// 示例 2： 
//
// 输入：3
//输出：false
//解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= N <= 1000 
// 
// Related Topics 数学 动态规划 
// 👍 173 👎 0

 
package leetcode.editor.cn;
//Java：除数博弈
public class P1025DivisorGame{
    public static void main(String[] args) {
        Solution solution = new P1025DivisorGame().new Solution();
        // TO TEST
        solution.divisorGame(599);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean divisorGame(int N) {
        return dp(N);
    }

    // 解法1. 数学归纳法，如果N是偶数就赢，是奇数就输
    public boolean math(int N) {
        return N % 2 == 0;
    }

    // 解法2. 动态规划
    public boolean dp(int N) {
        // 防止数组越界，一般情况下后边遍历时会访问dp[N + 1],但是如果N = 1时，dp[2]就会报错了
        boolean[] dp = new boolean[N + 2];
        dp[1] = false;
        dp[2] = true;

        for (int i = 3; i <= N; ++i) {
            // 首先设置dp[i]为false，如果拿出的x是i的约数并且能让dp[i - x]为false，则修改dp[i]为true
            dp[i] = false;// 可以省略，初始化默认为false
            for (int x = 1; x < i; ++x) {
                if (i % x == 0 && !dp[i - x]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[N];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}