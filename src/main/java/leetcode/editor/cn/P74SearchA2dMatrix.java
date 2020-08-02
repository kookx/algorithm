//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性： 
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 示例 1: 
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 13
//输出: false 
// Related Topics 数组 二分查找 
// 👍 215 👎 0

 
package leetcode.editor.cn;
//Java：搜索二维矩阵
public class P74SearchA2dMatrix{
    public static void main(String[] args) {
        Solution solution = new P74SearchA2dMatrix().new Solution();
        // TO TEST
        int[] i1 = new int[]{1,   3,  5,  7};
        int[] i2 = new int[]{10, 11, 16, 20};
        int[] i3 = new int[]{23, 30, 34, 50};
        int[][] arr = new int[3][4];
        arr[0] = i1;
        arr[1] = i2;
        arr[2] = i3;
        System.out.println(solution.searchMatrix(arr, 13));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 标准二分查找：一次二分
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        int left = 0, right = m*n - 1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            // 获取行号列号的方法：n为二维数组每行的个数，mid/n代表整数倍的n，即行数，取模就是列数
            int midVal = matrix[mid / n][mid % n];
            if (target == midVal) return true;
            if (midVal < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}