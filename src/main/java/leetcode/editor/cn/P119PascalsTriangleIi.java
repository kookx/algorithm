//给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。 
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 3
//输出: [1,3,3,1]
// 
//
// 进阶： 
//
// 你可以优化你的算法到 O(k) 空间复杂度吗？ 
// Related Topics 数组 
// 👍 166 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：杨辉三角 II
public class P119PascalsTriangleIi{
    public static void main(String[] args) {
        Solution solution = new P119PascalsTriangleIi().new Solution();
        // TO TEST
        solution.getRow(3);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> getRow(int rowIndex) {

        return math(rowIndex);
    }

    // 解法1.动态规划
    // 思路：最直观想法，根据杨辉三角118题思路，先计算出k行的全部数据，然后取k行
    // 优化：空间多余：只要保留上层的结果
    public List<Integer> dp(int rowIndex) {
        int pre = 1;
        List<Integer> curr = new ArrayList<>();
        curr.add(1);
        for (int row = 1; row <= rowIndex; row++) {
            for (int j = 1; j < row; j++) {
                int temp = curr.get(j);
                curr.set(j,pre + temp);
                pre = temp;
            }
            curr.add(1);
        }
        return curr;
    }

    // 解法2.公式法
    // 思路：杨辉三角即使组合数，n行m列的数即为C(n,m) = n*(n-1)*(n-2)...(n-m+1)/m! = C(n,m-1) * (n-m+1)/m
    public List<Integer> math(int rowIndex) {
        int n = rowIndex;
        long pre = 1;
        List<Integer> ans = new ArrayList<>();
        ans.add(1);
        for (int m = 1; m <= n; m++) {
            pre = pre * (n - m + 1)/m;
            ans.add((int)pre);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}