//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串 
// 👍 394 👎 0

 
package leetcode.editor.cn;

import java.util.*;

//Java：字母异位词分组
public class P49GroupAnagrams{
    public static void main(String[] args) {
        Solution solution = new P49GroupAnagrams().new Solution();
        // TO TEST
        solution.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法：hash表
    public List<List<String>> groupAnagrams(String[] strs) {
       if (strs.length == 0) return new ArrayList<>();
       Map<String,List> map = new HashMap<>();
       for (String c : strs) {
           char[] s = c.toCharArray();
           Arrays.sort(s);
           String key = String.valueOf(s);
           if (!map.containsKey(key)) {
               map.put(key, new ArrayList());
           }
           map.get(key).add(c);
       }
       return new ArrayList(map.values());
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}