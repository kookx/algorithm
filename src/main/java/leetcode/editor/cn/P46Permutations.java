//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法 
// 👍 827 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：全排列
public class P46Permutations{
    public static void main(String[] args) {
        Solution solution = new P46Permutations().new Solution();
        // TO TEST
        solution.permute(new int[]{1, 2, 3});
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：回溯
    List<List<Integer>> output;
    public List<List<Integer>> permute(int[] nums) {
        output = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>());
        return output;
    }

    private void dfs(int[] nums, int begin, List<Integer> temp) {
        if (temp.size() == nums.length){
            output.add(new ArrayList<>(temp));
            return;
        }

        for (int i = begin; i < nums.length; i++) {
            // 这里contains需要消耗O(N)的复杂度，可以考虑优化
            if (!temp.contains(nums[i])) {
                temp.add(nums[i]);
                dfs(nums, 0, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}