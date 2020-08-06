//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 说明： 
//
// 
// 所有数字（包括目标数）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [10,1,2,7,6,1,5], target = 8,
//所求解集为:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,5,2,1,2], target = 5,
//所求解集为:
//[
//  [1,2,2],
//  [5]
//] 
// Related Topics 数组 回溯算法 
// 👍 325 👎 0

 
package leetcode.editor.cn;

import java.util.*;

//Java：组合总和 II
public class P40CombinationSumIi{
    public static void main(String[] args) {
        Solution solution = new P40CombinationSumIi().new Solution();
        // TO TEST
        solution.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1. 回溯
    // 思路：步骤同组合I，调整起点位置为上层起点+1
    List<List<Integer>> output;
    Deque<Integer> path;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        output = new ArrayList<>();
        path = new ArrayDeque<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return output;
    }

    private void dfs(int[] candidates, int residue, int begin) {
        if (residue == 0) {
            output.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            if (residue - candidates[i] < 0) {
                break;
            }

            // 如果当前元素和前一个元素相等，并且该元素处于同一层函数for的不同位置
            if (i > begin && candidates[i] == candidates[i - 1]) continue;

            path.addLast(candidates[i]);
            dfs(candidates, residue - candidates[i], i+1);
            path.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}