//假设按照升序排序的数组在预先未知的某个点上进行了旋转。 
//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。 
//
// 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。 
//
// 你可以假设数组中不存在重复的元素。 
//
// 你的算法时间复杂度必须是 O(log n) 级别。 
//
// 示例 1: 
//
// 输入: nums = [4,5,6,7,0,1,2], target = 0
//输出: 4
// 
//
// 示例 2: 
//
// 输入: nums = [4,5,6,7,0,1,2], target = 3
//输出: -1 
// Related Topics 数组 二分查找 
// 👍 860 👎 0

 
package leetcode.editor.cn;
//Java：搜索旋转排序数组
public class P33SearchInRotatedSortedArray{
    public static void main(String[] args) {
        Solution solution = new P33SearchInRotatedSortedArray().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 二分查找
    // 思路：目标值在右边搜索的可能性只有三种
    // 1.左边升序，目标值大于中间，说明要找的target在右边的升序点
    // 2.左边升序，目标值小于最左边，说明要找的target在右边的旋转点
    // 3.左边存在旋转点，目标值介于中间位置和最左边之间，说明要找的target在右边的旋转点
    // 其余情况都在左边搜索
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1,mid;
        while (low <= high) {
            mid = (high - low)/2 + low;
            if (nums[mid] == target)
                return mid;
            if ((nums[mid] >= nums[0] && target > nums[mid]) ||
                    (nums[mid] >= nums[0] && target < nums[0]) ||
                    (nums[mid] < target && target < nums[0])) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}