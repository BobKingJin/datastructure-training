package com.bobking.leetcode.training;

import java.util.HashSet;

/**
 * @author BobKing
 * @create 2023-01-09 14:46
 */
public class Number2062 {

    public int countVowelSubstrings1(String word) {

        int cnt = 0;
        int len = word.length();
        if (len < 5)
            return 0;

        for (int i = 0; i < len; i++) {
            HashSet<Character> character = new HashSet<Character>();
            for (int j = i; j < len; j++) {
                if (!vowel(word.charAt(j)))
                    break;
                character.add(word.charAt(j));
                if (character.size() == 5)
                    cnt++;
            }
        }
        return cnt;
    }

    private boolean vowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i'
                || ch == 'o' || ch == 'u';
    }

    public int countVowelSubstrings2(String word) {

        clearCnt();
        int l = 0;
        int prev = 0;
        int ans = 0;

        for(int r = 0; r < word.length(); ++r){
            char c = word.charAt(r);
            if(vowels.contains(c)){
                ++cnt[c];
                // 若子串合法，计算以当前右指针所在字符结束的合法子串数
                if(isLegal()){
                    if(prev == 0)
                        // 若以上一个字符结束无合法子串，则初始子串数为 1，否则为上一个字符结束的合法子串数
                        prev = 1;
                    // 尝试右移左指针增加以当前右指针结束的合法子串数
                    while(tryDelete(word.charAt(l))){
                        ++prev;
                        ++l;
                    }
                    ans += prev;
                }
            }else{
                // 若右指针指向的字符非元音字符，则重置左指针至下一个字符，并重置子串数和计数器
                l = r + 1;
                prev = 0;
                clearCnt();
            }
        }
        return ans;
    }

    // 用于判断字符是否为元音字符
    private HashSet<Character> vowels = new HashSet<Character>(){{
        add('a');
        add('e');
        add('i');
        add('o');
        add('u');
    }};

    // 用于统计当前双指针内元音字符出现的次数以便判断子串是否合法以及左指针是否可以右移
    int[] cnt = new int[256];
    private void clearCnt(){
        cnt['a'] = 0;
        cnt['e'] = 0;
        cnt['i'] = 0;
        cnt['o'] = 0;
        cnt['u'] = 0;
    }

    // 判断当前子串是否合法
    private boolean isLegal(){
        return cnt['a'] > 0 && cnt['e'] > 0 && cnt['i'] > 0 && cnt['o'] > 0 && cnt['u'] > 0;
    }

    // 判断左指针左移后子串是否合法，若合法则左移
    private boolean tryDelete(char c){
        if(cnt[c] < 2)
            return false;
        --cnt[c];
        return true;
    }

}
