//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。 
//
// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。 
//
// 
//
// 例如，给定三角形： 
//
// [
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
// 
//
// 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。 
//
// 
//
// 说明： 
//
// 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。 
// Related Topics 数组 动态规划 
// 👍 512 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：三角形最小路径和
public class P120Triangle{
    public static void main(String[] args) {
        Solution solution = new P120Triangle().new Solution();
        Integer[][] array = new Integer[][]{{2},{3,4},{6,5,7},{4,1,8,3}};
        List<Integer[]> list_array = Arrays.asList(array);
        List<List<Integer>> list_integer = new ArrayList<List<Integer>> ();
        for(int i = 0; i < list_array.size(); i++){
            list_integer.add(Arrays.asList(list_array.get(i)));
        }
        System.out.println(solution.minimumTotal(list_integer));
//        solution.minimumTotal(Arrays.asList(f));
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();
        int[][] f = new int[n][n];
        f[0][0] = triangle.get(0).get(0);
        for(int i=1;i<n;i++){
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
            for(int j=1;j<i;j++){
                f[i][j] = Math.min(f[i-1][j-1],f[i-1][j]) + triangle.get(i).get(j);
            }
            f[i][i] = f[i-1][i-1] + triangle.get(i).get(i);
        }
        int minTotal = f[n-1][0];
        for (int i=1;i<n;i++){
            minTotal = Math.min(minTotal,f[n-1][i]);
        }
        return minTotal;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}