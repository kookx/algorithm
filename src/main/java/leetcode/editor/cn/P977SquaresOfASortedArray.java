//给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。 
//
// 
//
// 示例 1： 
//
// 输入：[-4,-1,0,3,10]
//输出：[0,1,9,16,100]
// 
//
// 示例 2： 
//
// 输入：[-7,-3,2,3,11]
//输出：[4,9,9,49,121]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 10000 
// -10000 <= A[i] <= 10000 
// A 已按非递减顺序排序。 
// 
// Related Topics 数组 双指针 
// 👍 130 👎 0

 
package leetcode.editor.cn;
//Java：有序数组的平方
public class P977SquaresOfASortedArray{
    public static void main(String[] args) {
        Solution solution = new P977SquaresOfASortedArray().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1：暴力：计算平方后排序
    // 解法2：双指针
    public int[] sortedSquares(int[] A) {
        int N = A.length;
        int j = 0;
        while (j < N && A[j] < 0) {
            j++;
        }
        int i = j - 1;
        int[] ans = new int[N];
        int cur = 0;
        while (i >= 0 && j < N) {
            if (A[i] * A[i] < A[j] * A[j]) {
                ans[cur++] = A[i] * A[i];
                i--;
            } else {
                ans[cur++] = A[j] * A[j];
                j++;
            }
        }
        while (i >= 0) {
            ans[cur++] = A[i] * A[i];
            i--;
        }
        while (j < N) {
            ans[cur++] = A[j] * A[j];
            j++;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}