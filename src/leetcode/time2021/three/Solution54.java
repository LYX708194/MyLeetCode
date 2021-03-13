package leetcode.time2021.three;

import java.util.ArrayList;
import java.util.List;

/**
 * 54 顺时针打印矩阵
 * @author lyx
 * @date 2021/3/13 12:48
 */
public class Solution54 {


    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        int m = 0,n = 0;
        if (matrix == null || (m = matrix.length) == 0 || (n = matrix[0].length) == 0)  return order;
        boolean[][] visited = new boolean[m][n];
        int direct = 0;
        int[][] directtions = {{0,1},{1,0},{0,-1},{-1,0}};
        int row = 0,col = 0;
        int total = m * n;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][col]);
            visited[row][col] = true;
            int newRow = row + directtions[direct][0],newCol = col + directtions[direct][1];
            if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n || visited[newRow][newCol]){
                //越界或者已经被访问，变换方向
                direct = (direct + 1) % 4;
            }
            row += directtions[direct][0];
            col += directtions[direct][1];
        }
        return order;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

}
