//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics 动态规划 
// 👍 1000 👎 0

 
package leetcode.editor.cn;
//Java：打家劫舍
public class P198HouseRobber{
    public static void main(String[] args) {
        Solution solution = new P198HouseRobber().new Solution();
        // TO TEST
        solution.rob(new int[]{});
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rob(int[] nums) {
        // 解法.动态规划
        // 第一种：在一维数组的基础上增加一个纬度记录不偷或者偷的最大值
        //      状态定义：a[i][0,1]表示第i个房子不偷或偷的最大值
        //      状态方程：a[i][0] = Max(a[i-1][0], a[i-1][1]) 第i个房子不偷可以是第i-1个房子不偷和第i-1个房子偷
        //               a[i][1] = a[i-1][0] + nums[i] 第i个房子偷是第i-1个房子不偷加上第i个房子偷
        // 第二种优化：直接用一维数组记录第i个房子的最大值
        //      状态定义：a[i]表示第i个房子的最大值
        //      状态方程：a[i] = Max(a[i-1], a[i-2] + nums[i])
        // 第三种优化：节省一个数组，使用变量存储当前值和上一步值

        return rob3(nums);
    }

    // 第二种
    private int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int[] a = new int[n];
        a[0] = nums[0];
        a[1] = Math.max(nums[0], nums[1]);
//        int res = a[1];
        for (int i = 2; i < n; i++) {
            a[i] = Math.max(a[i - 1], a[i - 2] + nums[i]);
//            res = Math.max(res, a[i]);
        }
        return a[n - 1];
    }

    // 第三种
    private int rob3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int preMax = 0;
        int currMax = 0;
        for (int i = 0; i < n; i++) {
            int temp = currMax;
            currMax = Math.max(currMax, preMax + nums[i]);
            preMax = temp;
        }
        return currMax;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}