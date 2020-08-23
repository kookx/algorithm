//给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。 
//
// 示例 1: 
//
// 输入: [5,7]
//输出: 4 
//
// 示例 2: 
//
// 输入: [0,1]
//输出: 0 
// Related Topics 位运算 
// 👍 180 👎 0

 
package leetcode.editor.cn;
//Java：数字范围按位与
public class P201BitwiseAndOfNumbersRange{
    public static void main(String[] args) {
        Solution solution = new P201BitwiseAndOfNumbersRange().new Solution();
        // TO TEST
        System.out.println(0&1);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1：暴力迭代（超时）
    // 解法2：位移操作，两个数与操作非全1位置会被0位填充，即求得公共前缀得长度后，
    // 再将最小数左移该长度
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        while (m < n) {
            m = m >> 1;
            n = n >> 1;
            shift++;
        }
        return m << shift;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}