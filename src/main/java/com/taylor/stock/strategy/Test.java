package com.taylor.stock.strategy;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/7/13 0:29
 */
public class Test {
    public static int find(int a, int low, int high, int... A) {
        if (low > high || low > A.length) {
            return -1;
        }
        if (low == high) {
            if (a == A[low]) {
                return low+1;
            } else {
                return -1;
            }
        }
        if (low < high) {
            if (a == A[low + (high - low) / 2] && A[low + (high - low) / 2 - 1] < a) {
                return low + (high - low) / 2+1;
            } else if (A[low + (high - low) / 2] < a) {
                return find(a, low + (high - low) / 2 + 1, high, A);
            } else {
                return find(a, low, low + (high - low) / 2 - 1, A);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 4, 5};
        System.out.println(find(3, 0, A.length - 1, A));
    }
}
