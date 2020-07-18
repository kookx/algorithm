//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。 
//
// 
//
// 说明: 
//
// 
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。 
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。 
// 
//
// 
//
// 示例: 
//
// 输入:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//输出: [1,2,2,3,5,6] 
// Related Topics 数组 双指针 
// 👍 562 👎 0

 
package leetcode.editor.cn;
//Java：合并两个有序数组
public class P88MergeSortedArray{
    public static void main(String[] args) {
        Solution solution = new P88MergeSortedArray().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 解法1.双指针迭代,从前向后扫，用一个新数组拷贝nums1，比较nums1和nums2的元素大小，
        //      注意避免插入数组的操作，因为插入操作需要数组挪移，消耗大量时间
        // 解法2.双指针迭代，从后向前扫，不使用新数组
        int i = m - 1, j = n - 1;
        int p = m + n - 1; // 该指针记录nums1的末尾下标
        while (i >= 0 && j >= 0){
//            if (nums1[i] >= nums2[j]){
//                nums1[p] = nums1[i--];
//            } else {
//                nums1[p] = nums2[j--];
//            }
//            p--;
            // 简化写法
            nums1[p--] = (nums1[i] >= nums2[j]) ? nums1[i--] : nums2[j--];
        }
        // 将nums2的剩余元素拷贝到nums1前面
        System.arraycopy(nums2,0,nums1,0,j + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}