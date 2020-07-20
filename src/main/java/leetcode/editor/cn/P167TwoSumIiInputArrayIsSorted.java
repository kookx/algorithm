//给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。 
//
// 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。 
//
// 说明: 
//
// 
// 返回的下标值（index1 和 index2）不是从零开始的。 
// 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。 
// 
//
// 示例: 
//
// 输入: numbers = [2, 7, 11, 15], target = 9
//输出: [1,2]
//解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。 
// Related Topics 数组 双指针 二分查找 
// 👍 351 👎 0

 
package leetcode.editor.cn;
//Java：两数之和 II - 输入有序数组
public class P167TwoSumIiInputArrayIsSorted{
    public static void main(String[] args) {
        Solution solution = new P167TwoSumIiInputArrayIsSorted().new Solution();
        // TO TEST
        solution.twoSum(new int[]{2},9);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // 解法1.双指针夹逼，目标值大于两数之和，左指针右移，否则右指针左移
        if (numbers.length < 2)
            return new int[0];
        for (int i = 0, j = numbers.length - 1;i < j;) {
            if (target == numbers[i] + numbers[j]) {
                return new int[]{i + 1,j + 1};
            } else if (target > numbers[i] + numbers[j]) {
                i++;
            } else {
                j--;
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}