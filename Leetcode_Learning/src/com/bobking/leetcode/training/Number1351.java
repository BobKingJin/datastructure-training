package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-03 10:49
 */
public class Number1351 {

    public int countNegatives(int[][] grid) {

        if(grid.length == 0 || grid[0].length == 0)
            return 0;
        /*
            [4,3,2,-1],
            [3,2,1,-1],
            [1,1,-1,-2],
            [-1,-1,-2,-3]
        */
        // 从右上角开始遍历，i = 0, j = grid[0].length - 1
        // 如果当前值大于等于 0，那么前面的值肯定都非负，那么直接跳过，进入下一行, 即 i++
        // 如果当前值小于 0，那么当前值以及同列下的值都是小于 0 的，那么直接添加，然后进行下一列，即 j--

        int count = 0;
        for(int i = 0, j = grid[0].length - 1; i < grid.length && j >= 0; ){
            if(grid[i][j] >= 0){
                i++;
            }else{
                count += grid.length - i;
                j--;
            }
        }
        return count;
    }
}
