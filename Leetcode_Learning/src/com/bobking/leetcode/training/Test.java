package com.bobking.leetcode.training;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.util.*;

/**
 * @author BobKing
 * @create 2021-03-13 20:39
 */
public class Test {

    public enum Season {
        SPRING() {
            @Override
            public Season getNextSeason() {
                return SUMMER;
            }
        }, SUMMER() {
            @Override
            public Season getNextSeason() {
                return AUTUMN;
            }
        }, AUTUMN() {
            @Override
            public Season getNextSeason() {
                return WINTER;
            }
        }, WINTER() {
            @Override
            public Season getNextSeason() {
                return SPRING;
            }
        };

        public abstract Season getNextSeason();


    }

    public static void main(String[] args) throws ParseException {

       // int[][] nums = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
       //
       // //对二维数组进行排序
       // Arrays.sort(nums, new Comparator<int[]>() {
       //
       //     @Override
       //     public int compare(int[] o1, int[] o2) {
       //
       //         return o1[0] - o2[0];
       //     }
       // });
       //
       // for (int[] num : nums) {
       //     System.out.println(num[0] + " " + num[1]);
       // }
       //
       // System.out.println(Double.MIN_VALUE);
       // System.out.println(Double.MAX_VALUE);
       // System.out.println(-Double.MAX_VALUE);
       //
       // System.out.println(-1 * (long) Integer.MIN_VALUE);
       // System.out.println((long) Integer.MIN_VALUE);
       // System.out.println(Integer.MIN_VALUE);
       // System.out.println(Integer.MAX_VALUE);
       //
       // StringBuilder sb = new StringBuilder();
       // sb.append('h');
       // sb.append('e');
       // sb.append('l');
       // sb.append('l');
       // sb.append('o');
       // sb.reverse();
       //
       // System.out.println(sb.toString());
       // System.out.println("-------");
       // sb.setLength(0);
       // System.out.println(sb.toString());
       //
       // System.out.println("-------");
       //
       // for (Season s : Season.values())
       //     System.out.println(s + ".ordinal() --> " + s.ordinal());
       //
       // System.out.println("-------");
       // System.out.println(Double.MIN_VALUE);
       //
       // ArrayList<Integer> al = new ArrayList();
       // al.add(5);
       // al.add(6);
       //
       // Iterator iter = al.iterator();
       //
       // int count = 1;
       // while (iter.hasNext()) {
       //     System.out.println(count);
       //     count++;
       //     Integer i = (Integer) iter.next();
       //     System.out.println(i);
       // }
       //
       // String s = "Mon Jul 26 09:40:35 GMT+08:00 2012";
       // SimpleDateFormat sf = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
       // Date date1 = sf.parse(s);
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // String result = sdf.format(date1);
       // System.out.println(result);
       //
       // System.out.println(Integer.MAX_VALUE);
       // System.out.println(Integer.MIN_VALUE);
       // System.out.println(Double.MAX_VALUE);
       // System.out.println(Double.MIN_VALUE);
       // System.out.println(Long.MAX_VALUE);
       // System.out.println(Long.MIN_VALUE);
       //
       // Queue<Integer> queue = new LinkedList<>();
       // queue.offer(1);
       // queue.offer(2);
       // System.out.println(queue.poll());
       //
       // System.out.println("-------");
       //
       // Set<String> set = new HashSet<>();
       //
       // String str = "a";
       //
       // System.out.println(str.substring(0, 0));
       // System.out.println(str.substring(1));
       //
       // System.out.println("-------");
       //
       // set.add(str.substring(0, 0) + str.substring(1));
       // System.out.println(set.size());
       //
       // System.out.println("-------");
       //
       //
       // Deque<Integer> stack = new ArrayDeque();
       // stack.addLast(1);
       // stack.addLast(2);
       // System.out.println("Hello World!");
       //
       // File newName = new File("E:/WebHost/faw3/app-api/resources/static/img/share/feedback-img/3.jpg");
       // boolean bool = newName.delete();
       // System.out.println(bool);
       //
       //
       //
       // ArrayList<String> list = new ArrayList<String>();
       // list.add("abc");
       // list.add("def");
       // list.add("ghi");
       //
       //
       // Iterator<String> iterator = list.iterator();
       //
       // while(iterator.hasNext()){
       //     String letter = iterator.next();
       //     if(letter.equals("abc"))
       //         list.remove(letter);
       // }
       //
       // for(String letter : list){
       //     System.out.println(letter);
       // }

        // Queue<Integer> queue = new LinkedList<>();
        // queue.offer(1);
        // queue.offer(2);
        // for(Integer i : queue)
        //     System.out.println(i);
        //
        //
        // Deque<Integer> d = new ArrayDeque<Integer>();
        // d.addLast(1);
        // d.addLast(2);
        // d.addLast(3);
        //
        // for(Integer i : d)
        //     System.out.println(i);

        // int multi1 = 5;
        // int righOne = multi1 & (~multi1 + 1);
        // System.out.println(righOne);

        // StringBuilder temp = new StringBuilder();
        // temp.append(1);
        // temp.append(2);
        //
        // for(int i = 0; i < temp.length(); i++){
        //     System.out.println(temp.charAt(i));
        // }

        // int[] arr = {1, 3, 5, 8, 10};
        // int index = Arrays.binarySearch(arr, 6);
        // System.out.println(index);
        //
        // Deque<Integer> d = new ArrayDeque<Integer>();
        //
        // d.addLast(1);
        // d.addLast(2);
        // d.addLast(3);
        //
        // while(!d.isEmpty())
        //     System.out.println(d.pollFirst());
        //
        // for(Integer i : d)
        //     System.out.println(i);

        // int[] arr = {1, 3, 5, 8, 10};
        //
        // int res = Arrays.binarySearch(arr, 6);
        //
        //
        // System.out.println(res);
        //
        // System.out.println(Integer.MAX_VALUE);

        int[] counts = new int[32];
        counts[1] = 1;
        counts[2] = 1;

        int res = 0;
        int m = 3;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            // 位或 |
            res |= counts[31 - i] % m;
        }

        System.out.println(res);

        System.out.println(4 | 6);


        int[][] num = {{668, 991}, {7, 338}, {7, 38}};

        Arrays.sort(num, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[] cardPoints = {1, 2, 120};
        System.out.println(Arrays.stream(cardPoints).sum());

        System.out.println(lowbit(8));

        System.out.println((1 >> 1) << 2);

        int a = 1;
        int b = 2;

        if(a == 1){
            System.out.println("a = 1");
        }else if(b == 2){
            System.out.println("b = 2");
        }

        int[] nums = {1, 2 ,3 ,4, 5};
        int[] r = solve(nums, 2);
        System.out.println("----------------------");
        for(int i : r){
            System.out.println(i);
        }


        System.out.println("Get JDK Default GC for jdk"
                + System.getProperty("java.version") + " - "
                + System.getProperty("java.vm.name") + ":");
        for (GarbageCollectorMXBean gcBean : ManagementFactory.getGarbageCollectorMXBeans())
            System.out.println(gcBean.getName());


        int bit = Integer.highestOneBit(8) >> 1;
        System.out.println(bit);

        System.out.println("" + 6);















    }

    List<Long> window = new LinkedList<Long>();

    public boolean slideWindow(long requestTime, int count, long slideWindow){

        // System.currentTimeMillis();
        // 请求	timestamp	Y/N?
        // R1	1.5s	Y
        // R2	1.7s	Y
        // R3	2.1s	Y
        // R4	2.3s	N
        // R5	2.6s	Y
        // R6	2.65s	N
        // R7	2.8s	Y

        if(requestTime <= 0){
            throw new RuntimeException("The requestTime input is illegal! The requestTime needs to be postive!");
        }

        if(count <= 0){
            throw new RuntimeException("The count input is illegal! The count needs to be postive!");
        }

        if(slideWindow <= 0){
            throw new RuntimeException("The slideWindow input is illegal! The slideWindow needs to be postive!");
        }

        if(slideWindow == 0){
            slideWindow = 1000;
        }

        if(window != null && window.size() < count){
            window.add(0, requestTime);
            return true;
        }

        if(window == null){
            throw new RuntimeException("Please restart the server!");
        }

        long previousTime = window.get(0);

        if(requestTime - previousTime <= slideWindow){
            return false;
        }else{
            window.remove(window.size() - 1);
            window.add(0, requestTime);
            return true;
        }
    }










    private static int lowbit(int x) {
        return x & -x;
    }

    public static int[] solve(int[] nums, int k){

        // k = 12 ->  k = 2
        // k = 7 -> k = 2
        // k = N * nums.length

        // eg. 1 2 3 4 5 -> 4 5 1 2 3
        if(nums == null || k < 1){
            return null;
        }

         k = k % nums.length;

        int[] res = new int[nums.length];

        int[] arr = new int[2 * nums.length];
        // 1 2 3 4 5 1 2 3 4 5
        for(int i = 0; i < nums.length; i++){
            arr[i] = nums[i];
            arr[i + nums.length] = nums[i];
        }

        for(int i = 0; i < nums.length; i++){
            res[i] = arr[i + k + 1];
        }

        return res;
    }

    // 1 2 3 4 5 6 -> 3 2 1 4 5 6
    // 1 2 3 4 5 6 7 -> 4 3 2 1 5 6 7
    public ListNode solve2(ListNode head){

        // 1.空节点，单节点或者双节点
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }

        Stack<ListNode> stack = new Stack<ListNode>();
        int length = getLength(head);
        int max = ((length & 1) == 1) ? (length / 2) : (length / 2 - 1);

        ListNode copy = head;
        for(int i = 0; i <= max; i++){
            stack.push(copy);
            copy = copy.next;
        }

        ListNode next = copy;
        ListNode neweHead = stack.peek();
        ListNode pre = stack.pop();
        ListNode node = null;
        while(!stack.isEmpty()){
            node = stack.pop();
            // neweHead = (neweHead == null) ? node : neweHead;
            pre.next = node;
        }

        node.next = next;
        return neweHead;
    }

    private int getLength(ListNode head){

        int len = 0;
        while(head != null){
            len++;
            head = head.next;
        }

        return len;
    }


}
