//给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。 
//
// 示例 1: 
//
// 
//输入: "abab"
//
//输出: True
//
//解释: 可由子字符串 "ab" 重复两次构成。
// 
//
// 示例 2: 
//
// 
//输入: "aba"
//
//输出: False
// 
//
// 示例 3: 
//
// 
//输入: "abcabcabcabc"
//
//输出: True
//
//解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
// 
// Related Topics 字符串 
// 👍 274 👎 0

 
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：重复的子字符串
public class P459RepeatedSubstringPattern{
    public static void main(String[] args) {
        Solution solution = new P459RepeatedSubstringPattern().new Solution();
        // TO TEST
        System.out.println(solution.repeatedSubstringPattern("abcabc"));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1. 暴力法
    public boolean repeatedSubstringPattern1(String s) {
        if(s.length() == 0||s.length()==1) return false;
        List<Integer> indexes =new ArrayList<>();
        indexes.add(0);
        //计算出所有有可能的子串长度
        for (int i = 1; i <s.length() ; i++) {
            //和第0个位置的字符相等，且总字符串的长度能够整除当前子串长度
            if(s.charAt(i)==s.charAt(0)
                    &&s.length()%i==0){
                indexes.add(i);
            }
        }
        for (int i = 1; i < indexes.size() ; i++) {
            int length =indexes.get(i);  //当前考虑的子串长度单位
            String str = s.substring(0,length);   //子串
            int j = length;
            //以当前考虑的长度单位进行遍历，如果每隔length的子串都等于str，
            //并且遍历到了字符串末尾，说明结果为true，反之跳出考虑下一种子串长度
            for (; j <s.length() ; j=j+length) {
                if(!s.substring(j,j+length).equals(str))
                    break;
            }
            if(j==s.length())
                return true;
        }
        return false;
    }
    // 解法2：字符串匹配
    public boolean repeatedSubstringPattern2(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }
    // 解法3：字符串匹配法2
    // 思路：S包含一个重复的子字符串，那么通过移动重复字符串长度的次数，使其与原始字符串匹配。
    // 可以创建一个新的字符串str,它等于原来的字符串S再加上S自身，
    // 这样其实就包含了所有移动的字符串，只要去除该字符串首尾字符后能匹配原字符串，返回true
    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}