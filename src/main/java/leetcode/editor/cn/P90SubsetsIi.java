//给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: [1,2,2]
//输出:
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//] 
// Related Topics 数组 回溯算法 
// 👍 284 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：子集 II
public class P90SubsetsIi{
    public static void main(String[] args) {
        Solution solution = new P90SubsetsIi().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1. 回溯算法
    // 相较于<子集I>，这里可能会出现重复元素，为了避免添加重复元素，
    // 可以先排序，然后判断后一个元素是否和前一个元素相同，是则跳过
    List<List<Integer>> output;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        output = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>());
        return output;
    }

    private void backtrack(int[] nums, int begin, List<Integer> temp) {
        output.add(new ArrayList<>(temp));
        for (int i = begin; i < nums.length; i++) {
            // 如果当前元素和前一个元素相等，并且该元素处于同一层函数for的不同位置
            if (i > begin && nums[i] == nums[i - 1]) continue;
            temp.add(nums[i]);
            backtrack(nums, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}