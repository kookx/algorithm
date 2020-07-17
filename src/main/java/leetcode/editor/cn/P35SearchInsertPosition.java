//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，
// 返回它将会被按顺序插入的位置。
//
// 你可以假设数组中无重复元素。 
//
// 示例 1: 
//
// 输入: [1,3,5,6], 5
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [1,3,5,6], 2
//输出: 1
// 
//
// 示例 3: 
//
// 输入: [1,3,5,6], 7
//输出: 4
// 
//
// 示例 4: 
//
// 输入: [1,3,5,6], 0
//输出: 0
// 
// Related Topics 数组 二分查找 
// 👍 596 👎 0

 
package leetcode.editor.cn;
//Java：搜索插入位置
public class P35SearchInsertPosition{
    public static void main(String[] args) {
        Solution solution = new P35SearchInsertPosition().new Solution();
        // TO TEST
        System.out.println(solution.searchInsert(new int[]{1,3,5,6},7));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        // 解法1.暴力枚举
        // 解法2.二分查找，将该排序数组一份为二，判断两边端点值和目标值大小，逐步缩小搜索范围，时间复杂度为O(logn)

        int left = 0 , right = nums.length -1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            }else {
                right = mid -1;
            }
        }
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}