//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1131 👎 0

 
package leetcode.editor.cn;
//Java：爬楼梯
public class P70ClimbingStairs{
    public static void main(String[] args) {
        Solution solution = new P70ClimbingStairs().new Solution();
        // TO TEST
        System.out.println(solution.climbStairs(5));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int climbStairs(int n) {
        // 解法1.递归
        //       缺点：1.存在重复值，要保存重复值需要额外的空间。
        //            2.可能会导致堆栈溢出。
        //            3.时间复杂度高，为O(2^n)
        //       理解：任何计算机的指令最终都逃不过为if else ，for ，recursion，
        //            因此算法最终的形态都是转化为这种形式
        // 解法2.动态规划 a[n] = a[n-1] + a[n-2] 拆分子结构
        // 动态规划优化
        if(n<=2) return n;
        int first = 1,second = 2,sum = 0;
        while (n-- > 2){
            sum = first + second;
            first = second;
            second = sum;
        }
        return sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}