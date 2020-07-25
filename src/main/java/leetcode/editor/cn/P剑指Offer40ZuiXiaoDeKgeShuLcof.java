//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
// 
//
// 示例 2： 
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0] 
//
// 
//
// 限制： 
//
// 
// 0 <= k <= arr.length <= 10000 
// 0 <= arr[i] <= 10000 
// 
// Related Topics 堆 分治算法 
// 👍 110 👎 0

 
package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

//Java：最小的k个数
public class P剑指Offer40ZuiXiaoDeKgeShuLcof{
    public static void main(String[] args) {
        Solution solution = new P剑指Offer40ZuiXiaoDeKgeShuLcof().new Solution();
        // TO TEST
        solution.getLeastNumbers(new int[]{3,2,1}, 2);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }

        return counterSort(arr, k);
//        return quickSearch(arr, 0, arr.length - 1,k - 1);
    }

    // 解法1. 排序
    public int[] sorted(int[] arr, int k) {
        // 排序
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, 0, k);
    }

    // 解法2. 快排
    public int[] quickSearch(int[] nums, int lo, int hi, int k) {
        // 排序
        int j = partition(nums, lo, hi);
        if (j == k) {
            return Arrays.copyOf(nums, j + 1);
        }
        return j > k?quickSearch(nums, lo, j - 1, k):quickSearch(nums, j + 1, hi, k);
    }

    private int partition(int[] nums, int lo, int hi) {
        int v = nums[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (++i <= hi && nums[i] < v);
            while (--j >= lo && nums[j] > v);
            if (i >= j) {
                break;
            }
            int t = nums[j];
            nums[j] = nums[i];
            nums[i] = t;
        }
        nums[lo] = nums[j];
        nums[j] = v;
        return j;
    }

    // 解法3. 计数排序,由于题目显示0 <= k <= arr.length <= 10000，0 <= arr[i] <= 10000，数据范围有限且不大，可以用空间换时间
    public int[] counterSort(int[] arr, int k) {
        int[] counter = new int[10001];

        // 统计数组的值从0到100000出现的次数
        for (int num : arr) {
            counter[num]++;
        }

        int[] res = new int[k];
        int idx = 0;
        for (int num = 0; num < counter.length; ++num) {
            while (counter[num]-- > 0 && idx < k) {
                res[idx++] = num;
            }
            if (idx == k) {
                break;
            }
        }

        return res;
    }

    // 解法4. 堆
    public int[] heap(int[] arr, int k) {
        // 默认是小根堆，实现大根堆需要重写一下比较器。
        Queue<Integer> pq = new PriorityQueue<Integer>((v1, v2) -> v2 - v1);

        for (int num : arr) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (num < pq.peek()) {
                pq.poll();
                pq.offer(num);
            }
        }

        int[] res = new int[pq.size()];
        int index = 0;
        for (int num : pq) {
            res[index++] = num;
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}