//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。 
//
// 
//
// 
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。 
//
// 
//
// 示例: 
//
// 输入: [2,1,5,6,2,3]
//输出: 10 
// Related Topics 栈 数组 
// 👍 798 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

//Java：柱状图中最大的矩形
public class P84LargestRectangleInHistogram{
    public static void main(String[] args) {
        Solution solution = new P84LargestRectangleInHistogram().new Solution();
        // TO TEST
        solution.largestRectangleArea(new int[]{2,1,5,6,2,3});
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestRectangleArea(int[] heights) {
        // 解法2.单调栈+哨兵。维护一个单调栈，栈中总是保存递增元素的索引，当遇到比栈顶元素小的元素时，
        // 将栈顶元素依次出栈，每次都计算栈中的bar能围成的面积，直到栈顶元素小于当前元素就停止出栈。

        if (heights == null) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }

        int len = heights.length;
        int[] newHeights = new int[len + 2];
        for (int i = 0; i < len; ++i) {
            newHeights[i + 1] = heights[i];
        }
        heights = newHeights;
        len = len + 2;

        int ans = 0;

        Deque<Integer> stack = new ArrayDeque();
        stack.addLast(heights[0]);
        for (int i = 1; i < len; ++i) {
            while (heights[stack.peekLast()] > heights[i]) {
                int height = heights[stack.pollLast()];
                int width = i - stack.peekLast() - 1;
                ans = Math.max(ans,height * width);
            }
            stack.addLast(i);
        }
        return ans;
    }

    public int largestRectangleArea2(int[] heights) {
        // 解法1.暴力枚举，固定高度，用两层循环i,j，依次往后扫，找到左右小于自己的度的柱子停下，计算面积
        if (heights.length == 0){
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < heights.length; ++i){
            // 找左边右边小于height[i]的柱子
            int left = i, right = i;
            while(left > 0 && heights[left - 1] >= heights[i]) {
                left--;
            }
            while(right < heights.length - 1 && heights[right + 1] >= heights[i]) {
                right++;
            }

            sum = Math.max((right - left + 1) * heights[i],sum);

        }
        return sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}