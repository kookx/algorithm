//给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。 
//
// 示例: 
//
// 
//输入: [4, 6, 7, 7]
//输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7
//]] 
//
// 说明: 
//
// 
// 给定数组的长度不会超过15。 
// 数组中的整数范围是 [-100,100]。 
// 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。 
// 
// Related Topics 深度优先搜索 
// 👍 149 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：递增子序列
public class P491IncreasingSubsequences{
    public static void main(String[] args) {
        Solution solution = new P491IncreasingSubsequences().new Solution();
        // TO TEST
        System.out.println(solution.findSubsequences(new int[]{4, 6, 6, 9}));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：DFS回溯
    // 思路：针对每个元素，可以选与不选，类似《全排列》。
    //      判断重复的方法可以用本题中手动判断，或者用set判重
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> temp = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null) {
            return null;
        }
        dfs(0, Integer.MIN_VALUE, nums);
        return res;
    }

    private void dfs(int curIndex, int preValue, int[] nums) {
        if (curIndex >= nums.length) {
            if (temp.size() >= 2) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        // 如果选择当前元素
        if (nums[curIndex] >= preValue) {// 选择当前大于或等于前一个的数（保证是升序的），并向后遍历
            temp.add(nums[curIndex]);
            dfs(curIndex + 1, nums[curIndex], nums);
            temp.remove(temp.size() - 1);
        }
        // 如果不选择当前元素
        if (nums[curIndex] != preValue) {// 不遍历重复元素
            // 注意这里传的是preValue，表示跳过当前元素
            dfs(curIndex + 1, preValue, nums);// 将当前元素的后一个元素加入，并向后遍历
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}