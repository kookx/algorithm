//给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
// 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
//
//
// 返回滑动窗口中的最大值。 
//
// 
//
// 进阶： 
//
// 你能在线性时间复杂度内解决此题吗？ 
//
// 
//
// 示例: 
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7] 
//解释: 
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10^5 
// -10^4 <= nums[i] <= 10^4 
// 1 <= k <= nums.length 
// 
// Related Topics 堆 Sliding Window 
// 👍 453 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayDeque;

//Java：滑动窗口最大值
public class P239SlidingWindowMaximum{
    public static void main(String[] args) {
        Solution solution = new P239SlidingWindowMaximum().new Solution();
        // TO TEST
        solution.maxSlidingWindow2(new int[]{1,3,-1,-3,5,3,6,7},3);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        return maxSlidingWindow2(nums, k);
    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        // 解法1. 暴力法
        if (nums.length * k == 0) return new int[0];

        int[] ans = new int[nums.length - k + 1];
        for (int i = 0; i <= nums.length - k; ++i) {
            int max = nums[i];
            for (int j = i + 1; j < i + k; ++j) {
                max = max > nums[j] ? max : nums[j];
            }
            ans[i] = max;
        }
        return ans;
    }


    ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
    int [] nums;
    public void clean_deque(int i, int k) {
        // remove indexes of elements not from sliding window
        // 移除不在本窗口的队列元素。初始化窗口后，窗口每滑动一次，i增加1
        // i - k的差值就是不在当前窗口左边界的最大下标值，例如：k = 3，i = 5，i从下标3增加到了5，窗口滑动了3次，
        // 当前窗口的左边界下标为3，5 - 3 = 2，如果下标2还在队列头里，要将其移除队列，保证队列元素个数至多等于窗口大小。
        if (!deq.isEmpty() && deq.getFirst() == i - k)
            deq.removeFirst();

        // remove from deq indexes of all elements
        // which are smaller than current element nums[i]
        // 循环比较队尾的元素的nums值与当前nums值大小，如果当前值大，移除队尾，如果小在循环外添加到队尾
        // 这样始终保证了队列元素的nums值始终从大到小排列
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()])
            deq.removeLast();
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        // 解法2. 双端队列，前k个元素初始化一个双端队列，将其看成一个窗口，将窗口最大值的下标放到队首，
        // 每次滑动检查队首下标是否超出本窗口范围，再检查新元素是否大于队尾元素的nums值，如果大于则删除队尾，继续比较新队尾，
        // 如果小于则将该元素直接放到队尾，最后将队首的nums值设置为本次窗口的输出值，重复直到窗口滑倒n-k+1的位置
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        // init deque and output
        this.nums = nums;
        int max_idx = 0;
        for (int i = 0; i < k; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            // compute max in nums[:k]
            if (nums[i] > nums[max_idx]) max_idx = i;
        }
        int [] output = new int[n - k + 1];
        output[0] = nums[max_idx];

        // build output
        for (int i  = k; i < n; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}