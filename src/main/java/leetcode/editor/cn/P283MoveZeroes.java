//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 652 👎 0

 
package leetcode.editor.cn;

import java.util.Arrays;

//Java：移动零
public class P283MoveZeroes{
    public static void main(String[] args) {
        Solution solution = new P283MoveZeroes().new Solution();
        // TO TEST
        int[] nums = new int[]{0,1,0,3,12};
        solution.moveZeroes(nums);
        for(int num:nums){
            System.out.println(num);
        }
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void moveZeroes(int[] nums) {
        // 解法1.依次交换0放到数组末尾
        // 解法2.增加一个指针记录非零位置

        //这里使用解法2
        int j=0;
        for(int i=0;i<nums.length;++i){
            if(nums[i]!=0){
                nums[j] = nums[i];
                if(i != j){
                    nums[i] = 0;
                }
                j++ ;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}