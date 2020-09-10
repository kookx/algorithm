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
    // 解法：回溯
    // 思路：步骤同组合I，调整起点位置为上层起点+1
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, target, path, 0, res);
        return res;
    }
    private void dfs(int[] candidates, int target, Deque<Integer> path, int begin, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = begin; i < candidates.length; i++) {
            if (target - candidates[i] < 0) break;
            // 如果当前元素和前一个元素相等，并且该元素处于同一层函数for的不同位置
            if (i > begin && candidates[i] == candidates[i - 1]) continue;
            path.addLast(candidates[i]);
            dfs(candidates, target - candidates[i], path, i + 1, res);
            path.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}