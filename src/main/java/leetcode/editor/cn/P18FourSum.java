//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
// d 的值与 target 相等？找出所有满足条件且不重复的四元组。 
//
// 注意： 
//
// 答案中不可以包含重复的四元组。 
//
// 示例： 
//
// 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//满足要求的四元组集合为：
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics 数组 哈希表 双指针 
// 👍 563 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：四数之和
public class P18FourSum{
    public static void main(String[] args) {
        Solution solution = new P18FourSum().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：四指针h、i、j、k
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for (int h = 0; h < len - 3; ++h) {
            if (h > 0 && nums[h] == nums[h - 1]) {
                continue;
            }
            int curMin = nums[h] + nums[h + 1] + nums[h + 2] + nums[h + 3];
            if (curMin > target) {
                break;
            }
            int curMax = nums[h] + nums[len - 1] + nums[len - 2] + nums[len - 3];
            if (curMax < target) {
                continue;
            }
            for (int i = h + 1; i < len - 2; ++i) {
                if (i > h + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int j = i + 1;
                int k = len - 1;
                int curMin1 = nums[h] + nums[i] + nums[j] + nums[j + 1];
                if (curMin1 > target) {
                    break;
                }
                int curMax1 = nums[h] + nums[i] + nums[k] + nums[k - 1];
                if (curMax1 < target) {
                    continue;
                }
                while (j < k) {
                    int sum = nums[h] + nums[i] + nums[j] + nums[k];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[h], nums[i], nums[j], nums[k]));
                        j++;
                        while (j < k && nums[j] == nums[j - 1]) {
                            j++;
                        }
                        k--;
                        while (j < k && h < k && nums[k] == nums[k + 1]) {
                            k--;
                        }
                    } else if (sum < target) {
                        j++;
                    } else {
                        k--;
                    }
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}