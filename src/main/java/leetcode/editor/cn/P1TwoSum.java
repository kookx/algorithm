//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表 
// 👍 8672 👎 0

 
package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Java：两数之和
public class P1TwoSum{
    public static void main(String[] args) {
        Solution solution = new P1TwoSum().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：双指针
    public int[] twoSum(int[] nums, int target) {
        int[] copyNum = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copyNum);
        int left = 0, right = copyNum.length - 1;
        int[] indices = new int[2];
        while (left < right) {
            int sum = copyNum[left] + copyNum[right];
            if (sum == target) {
                indices[0] = left;
                indices[1] = right;
                break;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        // 找到拷贝数组求出的值在原数组中的位置
        for (int i = 0; i < nums.length; ++i) {
            if (copyNum[indices[0]] == nums[i]) {
                indices[0] = i;
                break;
            }
        }
        for (int i = nums.length - 1; i >= 0; --i) {
            if (copyNum[indices[1]] == nums[i]) {
                indices[1] = i;
                break;
            }
        }
        return indices;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}