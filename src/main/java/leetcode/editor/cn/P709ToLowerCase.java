//实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入: "Hello"
//输出: "hello" 
//
// 示例 2： 
//
// 
//输入: "here"
//输出: "here" 
//
// 示例 3： 
//
// 
//输入: "LOVELY"
//输出: "lovely"
// 
// Related Topics 字符串 
// 👍 129 👎 0

 
package leetcode.editor.cn;
//Java：转换成小写字母
public class P709ToLowerCase{
    public static void main(String[] args) {
        Solution solution = new P709ToLowerCase().new Solution();
        // TO TEST
        System.out.println(solution.toLowerCase("Hello"));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：利用ASCII值大小写之间相差32的性质，遇到大写的字母，加上32就是相应的小写字母
    // 参考char字符对应的ASCII码值（https://blog.csdn.net/baidu_36327010/article/details/83007046）
    public String toLowerCase(String str) {
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] >= 'A' && arr[i] <= 'Z') {//arr[i] >= 65 && arr[i] <= 90
                arr[i] += 'a' - 'A';//arr[i] += 32
            }
        }
        return String.valueOf(arr);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}