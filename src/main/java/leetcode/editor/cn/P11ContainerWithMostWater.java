//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, 
//ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器，且 n 的值至少为 2。 
//
// 
//
// 
//
// 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 
//
// 示例： 
//
// 输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
// Related Topics 数组 双指针 
// 👍 1638 👎 0

 
package leetcode.editor.cn;
//Java：盛最多水的容器
public class P11ContainerWithMostWater{
    public static void main(String[] args) {
        Solution solution = new P11ContainerWithMostWater().new Solution();
        // TO TEST
        System.out.println(solution.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxArea(int[] height) {
        // 解法1. 暴力枚举，使用两个循环，依次从左至右找出最大的面积，时间复杂度是O(n²)
        // 解法2. 用双指针，左边一个指针，右边一个指针，同时向中间扫描，哪边低就继续往里扫，记录面积值，直到两个指针相遇

        // 这里使用解法2
        int area = 0;
        for (int i=0,j=height.length-1;i<j;){
            int minHeight = (height[i]>height[j])?height[j--]:height[i++];
            int max = minHeight * (j-i+1);
            area = Math.max(max,area);
        }
        return area;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}