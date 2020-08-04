//给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的数字可以无限制重复被选取。 
//
// 说明： 
//
// 
// 所有数字（包括 target）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1： 
//
// 输入：candidates = [2,3,6,7], target = 7,
//所求解集为：
//[
//  [7],
//  [2,2,3]
//]
// 
//
// 示例 2： 
//
// 输入：candidates = [2,3,5], target = 8,
//所求解集为：
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//] 
//
// 
//
// 提示： 
//
// 
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// candidate 中的每个元素都是独一无二的。 
// 1 <= target <= 500 
// 
// Related Topics 数组 回溯算法 
// 👍 788 👎 0

 
package leetcode.editor.cn;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

//Java：组合总和
public class P39CombinationSum{
    public static void main(String[] args) {
        Solution solution = new P39CombinationSum().new Solution();
        // TO TEST
        solution.combinationSum(new int[]{2, 3, 6, 7}, 7);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<Integer>> res;
    private int len;
    private Deque<Integer> path;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 回溯算法
        // 思路：求一个candidates数组里所有数总和为target的集合 -> 转化求candidates数组里所有数总和为target-candidates[i]的集合加上candidates[i]
        // 1.设置搜索点：考虑到返回集合中的可能存在的重复性
        // 2.剪枝：考虑到没必要的搜索，可以先排序数组，如果小的数都不能满足要求，那就不用考虑后面的数了
        res = new ArrayList<>();
        len = candidates.length;
        path = new ArrayDeque<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return res;
    }


    private void dfs(int[] candidates, int residue, int start) {
        if (residue == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < len; i++) {
            // 数组排序过，这里可以剪枝，提前终止搜索
            if (residue - candidates[i] < 0) {
                break;
            }

            path.addLast(candidates[i]);
            // 这里设置下一层搜索起点为当前i的原因
            //1.在搜索的时候，由于一个数可以使用多次，下一层的结点从这个搜索起点开始搜索
            //2.在搜索起点 i 之前的数因为以前的分支搜索过了，所以一定会产生重复, 所以不必从0开始了，直接从i开始
            dfs(candidates, residue - candidates[i], i);
            path.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}