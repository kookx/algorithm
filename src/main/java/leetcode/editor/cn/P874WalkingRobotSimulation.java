//机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令： 
//
// 
// -2：向左转 90 度 
// -1：向右转 90 度 
// 1 <= x <= 9：向前移动 x 个单位长度 
// 
//
// 在网格上有一些格子被视为障碍物。 
//
// 第 i 个障碍物位于网格点 (obstacles[i][0], obstacles[i][1]) 
//
// 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。 
//
// 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。 
//
// 
//
// 示例 1： 
//
// 输入: commands = [4,-1,3], obstacles = []
//输出: 25
//解释: 机器人将会到达 (3, 4)
// 
//
// 示例 2： 
//
// 输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
//输出: 65
//解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4) 处
// 
//
// 
//
// 提示： 
//
// 
// 0 <= commands.length <= 10000 
// 0 <= obstacles.length <= 10000 
// -30000 <= obstacle[i][0] <= 30000 
// -30000 <= obstacle[i][1] <= 30000 
// 答案保证小于 2 ^ 31 
// 
// Related Topics 贪心算法 
// 👍 101 👎 0

 
package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

//Java：模拟行走机器人
public class P874WalkingRobotSimulation{
    public static void main(String[] args) {
        Solution solution = new P874WalkingRobotSimulation().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //题意：在一个无限大的坐标轴里求行走最大边长，即x2+y2。
    //注意：障碍点就是坐标，遇到障碍点本次移动会停住，需要进行下一次移动或换方向（如果遇到不换方向继续走，就永远被卡住）
    //思路：1.取得当前机器人的方向，开始移动
    //2.每移动一步前判断该位置是否为障碍物，如果存在障碍物，结束当前路径的移动
    //3.若无障碍物，移动，更新当前位置，然后与当前的最远距离比较，如果大于最远距离则更新最远距离。
    //4.移动完毕后取得最远距离
    public int robotSim(int[] commands, int[][] obstacles) {
        // 定义原点，朝向：0123代表北东南西, command为-1代表向右转即direction+1，-2向左转即direction+3，
        // 为了避免绕圈超过预定值，需要同时再对4取模即可
        int ans = 0, x = 0, y = 0, direction = 0;
        // 设定每个朝向每走一步在x，y轴上的位移，这里根据顺序为北东南西
        int[][] directions = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};

        // 将障碍点转化为坐标点存在Hash表里，方便O(1)时间查询
        Set<String> obstacleSet = new HashSet<>();
        for (int[] coordinate : obstacles) {
            obstacleSet.add(coordinate[0] + ", " + coordinate[1]);
        }

        //开始执行命令
        for (int command : commands) {
            if (command > 0) {
                int next_x, next_y;
                for (int i = 0; i < command; ++i) {
                    // 获取下一步坐标
                    next_x = x + directions[direction][0];
                    next_y = y + directions[direction][1];
                    // 判断下一步的坐标点是否是障碍物
                    if (obstacleSet.contains(next_x + ", " + next_y)) {
                        break;
                    }
                    x = next_x;
                    y = next_y;
                    // 更新结果
                    ans = Math.max(ans,x*x + y*y);
                }
            }else {
                direction = command == -1 ? (direction + 1) % 4 : (direction + 3) % 4;
            }
        }

        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}