//有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。 
//
// 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。 
//
// 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方
//向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。 
//
// 最后返回经过上色渲染后的图像。 
//
// 示例 1: 
//
// 
//输入: 
//image = [[1,1,1],[1,1,0],[1,0,1]]
//sr = 1, sc = 1, newColor = 2
//输出: [[2,2,2],[2,2,0],[2,0,1]]
//解析: 
//在图像的正中间，(坐标(sr,sc)=(1,1)),
//在路径上所有符合条件的像素点的颜色都被更改成2。
//注意，右下角的像素没有更改为2，
//因为它不是在上下左右四个方向上与初始点相连的像素点。
// 
//
// 注意: 
//
// 
// image 和 image[0] 的长度在范围 [1, 50] 内。 
// 给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。 
// image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。 
// 
// Related Topics 深度优先搜索 
// 👍 107 👎 0

 
package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

//Java：图像渲染
public class P733FloodFill{
    public static void main(String[] args) {
        Solution solution = new P733FloodFill().new Solution();
        // TO TEST
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 思路. 本题类似岛屿问题，可以使用DFS和BFS
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        dfs(image, sr, sc, newColor);
        return image;
    }
    // 解法1. BFS（超时）
    // 思路：我们从给定的起点开始，进行广度优先搜索。每次搜索到一个方格时，
    // 如果其与初始位置的方格颜色相同，就将该方格加入队列，并将该方格的颜色更新，以防止重复入队。
    private void bfs(int[][] image, int sr, int sc, int newColor){
        // 定义遍历上下左右四个方向移动对BFS中坐标点的影响
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int currColor = image[sc][sc];
        if (currColor == newColor) {
            return;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = newColor;
        int n = image.length, m = image[0].length;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx >= 0 && mx < n && my >= 0 && my < m && image[mx][my] == currColor) {
                    queue.offer(new int[]{mx, my});
                    image[mx][my] = currColor;
                }
            }
        }
    }
    // 解法2. DFS
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, -0, -1, 1};
    private void dfs(int[][] image, int sr, int sc, int newColor){
        int currColor = image[sr][sc];
        if (currColor != newColor) {
            helper(image, sr, sc, currColor, newColor);
        }
    }
    private void helper(int[][] image, int sr, int sc, int color, int newColor){
        if (image[sr][sc] == color) {
            image[sr][sc] = newColor;
            for (int i = 0; i < 4; i++) {
                int mx = sr + dx[i], my = sc + dy[i];
                if (mx >= 0 && mx < image.length && my >= 0 && my < image[0].length) {
                    helper(image, mx, my, color, newColor);
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}