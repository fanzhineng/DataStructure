package com.fzn2.day01;

/**
 * @program: DataStructure
 * 描述：      前缀和数组
 * @author: fzn
 * @create: 2022-10-19 12:58
 **/
public class Code03_PreSum {
    public static void main(String[] args) {
        int[] arr = {7,1,3,5,1,6,8,1,3,5,7,5,6};
        Code03_PreSum.RangeSum1 rangeSum1 = new RangeSum1(arr);
        Code03_PreSum.RangeSum2 rangeSum2 = new RangeSum2(arr);
        System.out.println(rangeSum1.rangeSum(1, 2));
        System.out.println(rangeSum2.rangeSum(1, 2));
        // 下一次就不需要计算 直接从数组中取
        System.out.println(rangeSum2.rangeSum(1, 2));
    }

    /**
     * 传统方法计算和
     */
    public static class RangeSum1 {

        private int[] arr;

        public RangeSum1(int[] array) {
            arr = array;
        }

        public int rangeSum(int L, int R) {
            int sum = 0;
            for (int i = L; i <= R; i++) {
                sum += arr[i];
            }
            return sum;
        }

    }

    /**
     * 利用前缀和数组计算和
     * 也可以构建二维数组保存和
     */
    public static class RangeSum2 {

        private int[] preSum;

        public RangeSum2(int[] array) {
            int N = array.length;
            preSum = new int[N];
            preSum[0] = array[0];
            for (int i = 1; i < N; i++) {
                preSum[i] = preSum[i - 1] + array[i];
            }
        }

        public int rangeSum(int L, int R) {
            return L == 0 ? preSum[R] : preSum[R] - preSum[L - 1];
        }

    }
}