//你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。 
//
// 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。 
//
// 示例 1: 
//
// 
//输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
//输出: [20,24]
//解释: 
//列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
//列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
//列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
// 
//
// 注意: 
//
// 
// 给定的列表可能包含重复元素，所以在这里升序表示 >= 。 
// 1 <= k <= 3500 
// -105 <= 元素的值 <= 105 
// 对于使用Java的用户，请注意传入类型已修改为List<List<Integer>>。重置代码模板后可以看到这项改动。 
// 
// Related Topics 哈希表 双指针 字符串 
// 👍 192 👎 0

 
package leetcode.editor.cn;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

//Java：最小区间
public class P632SmallestRangeCoveringElementsFromKLists{
    public static void main(String[] args) {
        Solution solution = new P632SmallestRangeCoveringElementsFromKLists().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 题意：1.返回数组区间至少有一个值能在所有k个列表中找到
    // 2.返回数组的区间要最小：长度相等时起点要最小
    // 解法：双指针+堆
    public int[] smallestRange(List<List<Integer>> nums) {
        int[] ans = new int[]{0, (int)1e8};
        Queue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int max = Integer.MIN_VALUE;

        // 初始化堆
        for (int i = 0; i < nums.size(); i++) {
            int[] c = new int[]{nums.get(i).get(0), i, 0};
            heap.add(c);
            max = Math.max(max, c[0]);
        }

        while (true) {
            int[] c = heap.remove();
            if (max - c[0] < ans[1] - ans[0]) {
                ans[0] = c[0];
                ans[1] = max;
            }
            c[2]++;
            if (c[2] >= nums.get(c[1]).size()) {
                break;
            }

            c[0] = nums.get(c[1]).get(c[2]);
            heap.add(c);
            max = Math.max(max, c[0]);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}