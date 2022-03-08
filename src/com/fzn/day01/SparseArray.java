package com.fzn.day01;

/**
 * @program: DataStructure
 * 描述：  稀疏数组
 *
 * @author: fzn
 * @create: 2022-01-12 15:46
 **/
public class SparseArray {
    public static void main(String[] args) {
        // 创建一个原始的二维数组 11*11
        // 0: 表示没有棋子 1表示黑子 2 表示篮子
        int[][] chessArr1  = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        // 输出原始的二维数组
        System.out.println("========输出原始的二维数组========");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        System.out.println("========将二维数组转稀疏数组========");
        // 将二维数组转稀疏数组
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        /*
         * 二维数组转稀疏数组的思路：
         * 1.遍历原始的数组，得到有限的数据的个数sum
         * 2.根据sum就可以创建稀疏数组spareArr       int[sum+1][3]
         * 3.将二维数组的有效数据存入到稀疏数组
         */
        // 创建对应的稀疏数组
        int[][] sparseArr = new int[sum+1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        // 遍历二维数组 将数组中的值存入到稀疏数组中  sparseArr
        // count 用于记录是第几个非零数据
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        // 输出稀疏数组
        System.out.println("得到的稀疏数组为~~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        /*
         * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的 chessArr2 = int[11][11]
         * 2.再读取稀疏数组后几行的数据，并赋给原始的二维数组即可。
         */
        // 将稀疏数组恢复成二维数组
        // 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        // 2.再读取稀疏数组后几行的数据，并赋给原始的二维数组即可。
        for (int i = 1; i <sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] =  sparseArr[i][2];
        }
        // 恢复后的二维数组
        System.out.println("========恢复后的二维数组:========");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}