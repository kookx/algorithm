//有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。 
//
// 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 
//N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。 
//
// 最初，除 0 号房间外的其余所有房间都被锁住。 
//
// 你可以自由地在房间之间来回走动。 
//
// 如果能进入每个房间返回 true，否则返回 false。 
//
// 
// 
//
// 示例 1： 
//
// 输入: [[1],[2],[3],[]]
//输出: true
//解释:  
//我们从 0 号房间开始，拿到钥匙 1。
//之后我们去 1 号房间，拿到钥匙 2。
//然后我们去 2 号房间，拿到钥匙 3。
//最后我们去了 3 号房间。
//由于我们能够进入每个房间，我们返回 true。
// 
//
// 示例 2： 
//
// 输入：[[1,3],[3,0,1],[2],[0]]
//输出：false
//解释：我们不能进入 2 号房间。
// 
//
// 提示： 
//
// 
// 1 <= rooms.length <= 1000 
// 0 <= rooms[i].length <= 1000 
// 所有房间中的钥匙数量总计不超过 3000。 
// 
// Related Topics 深度优先搜索 图 
// 👍 145 👎 0

 
package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Java：钥匙和房间
public class P841KeysAndRooms{
    public static void main(String[] args) {
        Solution solution = new P841KeysAndRooms().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1：DFS
    // 思路：我们可以使用深度优先搜索的方式遍历整张图，统计可以到达的节点个数，
    // 并利用数组 {vis} 标记当前节点是否访问过，以防止重复访问
    int num;
    boolean[] vis;
    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        int n = rooms.size();
        vis = new boolean[n];
        num = 0;
        dfs(rooms, 0);
        return num == n;
    }

    private void dfs(List<List<Integer>> rooms, int k) {
        vis[k] = true;
        num++;
        for (int it : rooms.get(k)) {
            if (!vis[it]) {
                dfs(rooms, it);
            }
        }
    }

    // 解法2：BFS
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size(), num = 0;
        boolean[] vis = new boolean[n];
        vis[0] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int x = queue.poll();
            num++;
            for (int it : rooms.get(x)) {
                if (!vis[it]) {
                    vis[it] = true;
                    queue.offer(it);
                }
            }
        }
        return num == n;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}