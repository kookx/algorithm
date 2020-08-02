//假设按照升序排序的数组在预先未知的某个点上进行了旋转。 
//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。 
//
// 请找出其中最小的元素。 
//
// 你可以假设数组中不存在重复元素。 
//
// 示例 1: 
//
// 输入: [3,4,5,1,2]
//输出: 1 
//
// 示例 2: 
//
// 输入: [4,5,6,7,0,1,2]
//输出: 0 
// Related Topics 数组 二分查找 
// 👍 226 👎 0

 
package leetcode.editor.cn;
//Java：寻找旋转排序数组中的最小值
public class P153FindMinimumInRotatedSortedArray{
    public static void main(String[] args) {
        Solution solution = new P153FindMinimumInRotatedSortedArray().new Solution();
        // TO TEST
        solution.findMin(new int[]{1,2});
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 二分查找
    // 思路：1.如果nums[mid] > nums[0] 在mid右边寻找变化点
    // 2.如果nums[mid] <= nums[0] 在mid左边寻找变化点
    // 3.如果nums[mid] > nums[mid + 1] mid + 1是变化点
    // 4.如果nums[mid] < nums[mid - 1] mid - 1是变化点
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0, right = nums.length - 1,mid;
        if (nums[right] > nums[0]) {
            return nums[0];
        }

        while (left <= right) {
            mid = left + (right - left)/2;
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}