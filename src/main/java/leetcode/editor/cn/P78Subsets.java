//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
// Related Topics 位运算 数组 回溯算法 
// 👍 675 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：子集
public class P78Subsets{
    public static void main(String[] args) {
        Solution solution = new P78Subsets().new Solution();
        // TO TEST
        solution.subsets(new int[]{1,2,3});
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        return recursion(nums);
    }

    // 解法1. 回溯
    // 思考：每个元素都可以选或不选，每次做出选择都将当前集合保存，然后撤销选择
    //      遍历保存子集的顺序就是[],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]
    // 文字描述回溯过程：初始选择，选择1，递归选择2，递归选择3，撤销选择32再选3，撤销选择31再选2，递归选择3，撤销选择32再选3，结束
    // 步骤：1.确定当前解决方案
    // 2.制定下一步解决方案
    // 3.修改当前解决方案
    List<List<Integer>> res;
    private List<List<Integer>> recursion(int[] nums) {
        res = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>());
        return res;
    }

    private void backtrack(int i, int[] nums, ArrayList<Integer> tmp) {
        // 每次保存当前集合
        res.add(new ArrayList<>(tmp));

        //结束条件，子集的大小等于全集的大小了，由于后面的层级是递增的，到底了自然就无法继续下去了，这里可以省略
//        if(tmp.size() == nums.length){
//            return ;
//        }
        // 依次遍历元素
        for (int j = i; j < nums.length; j++) {
            // 做出选择
            tmp.add(nums[j]);
            // 将当前位置和集合传入下层
            backtrack(j + 1, nums, tmp);
            // 撤销选择
            tmp.remove(tmp.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}