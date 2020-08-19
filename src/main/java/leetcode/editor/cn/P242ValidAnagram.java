//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 
// 👍 218 👎 0

 
package leetcode.editor.cn;

import com.sun.deploy.util.StringUtils;

//Java：有效的字母异位词
public class P242ValidAnagram{
    public static void main(String[] args) {
        Solution solution = new P242ValidAnagram().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：计数表
    // 思路：统计每个字母出现的次数，s加，t减
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] table = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            table[s.charAt(i) - 'a']++;
            table[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < table.length; ++i) {
            if (table[i] != 0) return false;
        }
        return true;
    }
    // 或者我们可以先用计数器表计算 s，然后用 t 减少计数器表中的每个字母的计数器
    public boolean isAnagram(String s, String t) {
        // 解法：计数表
        // 思路：统计每个字母出现的次数，s加，t减
        if (s.length() != t.length()) return false;
        int[] table = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); ++i) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}