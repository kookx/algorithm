//给定一个可包含重复数字的序列，返回所有不重复的全排列。 
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法 
// 👍 368 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：全排列 II
public class P47PermutationsIi{
    public static void main(String[] args) {
        Solution solution = new P47PermutationsIi().new Solution();
        // TO TEST
        solution.permuteUnique(new int[]{1,1,2});
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1. 回溯
    // 思路：步骤同全排列I，剪枝重复出现的情况,用used记录每个数是否用过
    List<List<Integer>> output;
    int len;
    boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        output = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return output;
        }
        len = nums.length;
        used = new boolean[len];
        Arrays.sort(nums);
        dfs(nums, new ArrayList<>());
        return output;
    }

    private void dfs(int[] nums, ArrayList<Integer> temp) {
        if (temp.size() == nums.length) {
            output.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // 如果当前元素和前一个元素相等，并且该元素处于同一层函数for的不同位置
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            temp.add(nums[i]);
            used[i] = true;

            dfs(nums, temp);

            used[i] = false;
            temp.remove(temp.size() - 1);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}