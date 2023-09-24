package com.bobking.leetcode.training;

public class Number1952 {

    public boolean isThree(int n) {

        // 对于除 1 之外的数，都有 1 和自己为除数，也就是说只要从 1 到自己内只有一个除数，那它就是三除数

        boolean flag = false;

        for(int i = 2; i < n; ++i){
            if(n % i == 0){
                if(flag)
                    return false;
                flag = true;
            }
        }

        return flag;
    }
}
