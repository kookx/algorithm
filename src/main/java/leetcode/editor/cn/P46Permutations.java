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
    int len;
    boolean[] used;
    public List<List<Integer>> permute(int[] nums) {
        output = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return output;
        }
        len = nums.length;
        used = new boolean[len];
        dfs(nums, new ArrayList<>());
        return output;
    }


    private void dfs(int[] nums, List<Integer> path) {
        if (path.size() == len){
            output.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            // 每次都从下标0开始搜索，并记录每个数有没有被使用过
            if (!used[i]){
                path.add(nums[i]);
                used[i] = true;

                dfs(nums, path);

                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}