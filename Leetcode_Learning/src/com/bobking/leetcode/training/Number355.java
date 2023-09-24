package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2023-02-20 20:45
 */
public class Number355 {

    // 参考：https://leetcode.cn/problems/design-twitter/solution/ha-xi-biao-lian-biao-you-xian-dui-lie-java-by-liwe/
    private class Twitter {

        // 用户 id 和推文（单链表）的对应关系
        private Map<Integer, Tweet> twitter;

        // 用户 id 和关注的用户列表的对应关系
        private Map<Integer, Set<Integer>> followings;

        // 全局使用的时间戳字段，用户每发布一条推文之前 + 1
        private int timestamp = 0;

        private PriorityQueue<Tweet> maxHeap;

        public Twitter() {
            followings = new HashMap<Integer, Set<Integer>>();
            twitter = new HashMap<Integer, Tweet>();
            maxHeap = new PriorityQueue<Tweet>((o1, o2) -> -o1.timestamp + o2.timestamp);
        }

        public void postTweet(int userId, int tweetId) {
            timestamp++;
            if (twitter.containsKey(userId)) {
                Tweet oldHead = twitter.get(userId);
                Tweet newHead = new Tweet(tweetId, timestamp);
                newHead.next = oldHead;
                twitter.put(userId, newHead);
            } else {
                twitter.put(userId, new Tweet(tweetId, timestamp));
            }
        }

        public List<Integer> getNewsFeed(int userId) {
            // 由于是全局使用的，使用之前需要清空
            maxHeap.clear();

            // 如果自己发了推文也要算上
            if (twitter.containsKey(userId))
                maxHeap.offer(twitter.get(userId));

            Set<Integer> followingList = followings.get(userId);
            if (followingList != null && followingList.size() > 0) {
                for (Integer followingId : followingList) {
                    Tweet tweet = twitter.get(followingId);
                    if (tweet != null)
                        maxHeap.offer(tweet);
                }
            }

            List<Integer> res = new ArrayList<Integer>(10);
            int count = 0;
            while (!maxHeap.isEmpty() && count < 10) {
                Tweet head = maxHeap.poll();
                res.add(head.id);

                // 这里最好的操作应该是 replace，但是 Java 没有提供
                if (head.next != null)
                    maxHeap.offer(head.next);
                count++;
            }
            return res;
        }

        public void follow(int followerId, int followeeId) {

            // 被关注人不能是自己
            if (followeeId == followerId)
                return;

            // 获取我自己的关注列表
            Set<Integer> followingList = followings.get(followerId);
            if (followingList == null) {
                Set<Integer> init = new HashSet<Integer>();
                init.add(followeeId);
                followings.put(followerId, init);
            } else {
                if (followingList.contains(followeeId))
                    return;
                followingList.add(followeeId);
            }
        }

        public void unfollow(int followerId, int followeeId) {

            if (followeeId == followerId)
                return;

            // 获取我自己的关注列表
            Set<Integer> followingList = followings.get(followerId);

            if (followingList == null)
                return;

            // 这里删除之前无需做判断，因为查找是否存在以后，就可以删除，反正删除之前都要查找
            followingList.remove(followeeId);
        }

        // 推文类，是一个单链表（节点视角）
        private class Tweet {

            // 推文 id
            private int id;

            // 发推文的时间戳
            private int timestamp;
            private Tweet next;

            public Tweet(int id, int timestamp) {
                this.id = id;
                this.timestamp = timestamp;
            }
        }
    }
}
