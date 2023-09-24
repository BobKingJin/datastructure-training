package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-25 22:25
 */
public class Number1376 {

    // 参考：https://leetcode.cn/problems/time-needed-to-inform-all-employees/solution/1376-tong-zhi-suo-you-yuan-gong-suo-xu-d-7py4/
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {

        if (n == 1)
            return 0;

        int ans = 0;
        int[] time = new int[n];
        time[headID] = -1;

        for (int i = 0; i < n; i++){
            if (time[i] == 0)
                time[i] = getTime(manager, informTime, time, i);
            // 更新最长时间
            if (time[i] > ans)
                ans = time[i];
        }

        return ans;
    }

    private int getTime(int[] manager, int[] informTime, int[] time, int i){

        // 获取负责人坐标
        int aim = manager[i];

        // 如果是总负责人，就不在意他的时间
        if (time[aim] == -1)
            return informTime[aim];

        // 如果负责人的时间未获取，则先获取负责人的时间
        if (time[aim] == 0)
            time[aim] = getTime(manager, informTime, time, aim);

        return time[aim] + informTime[aim];
    }

}
