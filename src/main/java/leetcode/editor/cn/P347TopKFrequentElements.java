//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。 
// 你可以按任意顺序返回答案。 
// 
// Related Topics 堆 哈希表 
// 👍 407 👎 0

 
package leetcode.editor.cn;

import java.util.*;

//Java：前 K 个高频元素
public class P347TopKFrequentElements{
    public static void main(String[] args) {
        Solution solution = new P347TopKFrequentElements().new Solution();
        // TO TEST
        solution.topKFrequent(new int[]{1,1,1,2,2,2,3,3,3}, 2);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 解法1. 堆heap 先统计每次数字出现的次数放入一个Map中，将map元素插入到堆里，保证堆里只有k个元素
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }

        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num,counter.getOrDefault(num, 0) + 1);
        }

        // 定义一个小顶堆
        Queue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((v1, v2) -> v1.getValue() - v2.getValue());

        // 将元素放入堆里
        for (Map.Entry<Integer,Integer> item : counter.entrySet()) {
            if (maxHeap.size() < k) {
                maxHeap.offer(item);
            } else if (item.getValue() > maxHeap.peek().getValue()) {
                maxHeap.poll();
                maxHeap.offer(item);
            }
        }

        int[] ans = new int[k];

        for (int i = 0; i < k; ++i) {
            ans[i] = maxHeap.poll().getKey();
        }

        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}