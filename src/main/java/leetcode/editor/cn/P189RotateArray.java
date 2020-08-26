//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: [1,2,3,4,5,6,7] 和 k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 输入: [-1,-100,3,99] 和 k = 2
//输出: [3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 说明: 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 要求使用空间复杂度为 O(1) 的 原地 算法。 
// 
// Related Topics 数组 
// 👍 629 👎 0

 
package leetcode.editor.cn;
//Java：旋转数组
public class P189RotateArray{
    public static void main(String[] args) {
        Solution solution = new P189RotateArray().new Solution();
        // TO TEST
        solution.rotate(new int[]{1,2,3,4,5,6,7},3);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void rotate(int[] nums, int k) {
        cycle(nums, k);
    }

    // 解法1：反转
    // 思路：移动的次数 n = k % nums.length，代表有n个数会被反转到后面
    private void reverse(int[] nums, int k) {
        k %= nums.length;
        // 反转数组
        helper(nums, 0, nums.length - 1);
        // 反转前nums.length - k 个元素（恢复前面部分）
        helper(nums, 0, k - 1);
        // 反转后k个元素（恢复后面部分）
        helper(nums, k, nums.length - 1);
    }

    private void helper(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    // 解法2：环状替换
    private void cycle(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}