package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023/12/3 11:21
 * @Author: BobKing
 * @Description:
 */
public class Number1352 {

    // 参考: https://leetcode.cn/problems/product-of-the-last-k-numbers/solutions/98179/java-o1jie-fa-by-henrylee4/
    class ProductOfNumbers {

        private List<Integer> products;

        public ProductOfNumbers() {
            this.products = new ArrayList<Integer>();
            products.add(1);
        }

        public void add(int num) {
            if (num == 0) {
                products = new ArrayList<Integer>();
                products.add(1);
            } else {
                products.add(products.get(products.size() - 1) * num);
            }
        }

        public int getProduct(int k) {
            if (products.size() <= k)
                return 0;

            return products.get(products.size() - 1) / products.get(products.size() - 1 - k);
        }
    }
}
