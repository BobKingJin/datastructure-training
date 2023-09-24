package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

public class Number388 {

    // 参考：https://leetcode-cn.com/problems/longest-absolute-file-path/solution/by-ac_oier-c55t/
    public int lengthLongestPath(String input) {

        // 对于每一个文件或文件夹而言，可以通过访问到结尾（\n）的方式取得，记为 cur，然后根据 cur 前面有多少个 \t 得知其所在的层级
        // 假设当前其所在层级为 level，那么它自然归属到最新一个层级为 level - 1 的文件夹中，因此可以使用哈希表记录每个层级
        // 最新的文件夹路径，通过字符串拼接的方式得到 cur 所在的完整路径 path，并在处理整个 s 过程中，统计长度最大的文件路径
        Map<Integer, String> map = new HashMap<Integer, String>();
        int n = input.length();
        String res = null;
        for (int i = 0; i < n; ) {
            int level = 0;
            while (i < n && input.charAt(i) == '\t' && ++level >= 0)
                i++;
            int j = i;
            boolean isDir = true;
            while (j < n && input.charAt(j) != '\n')
                if (input.charAt(j++) == '.')
                    isDir = false;

            String cur = input.substring(i, j);
            String prev = map.getOrDefault(level - 1, null);
            String path = prev == null ? cur : prev + "/" + cur;
            if (isDir){
                map.put(level, path);
            } else if (res == null || path.length() > res.length()) {
                res = path;
            }
            i = j + 1;
        }

        return res == null ? 0 : res.length();
    }
}
