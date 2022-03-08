package com.fzn.day06;

/**
 * @program: DataStructure
 * 描述：
 * @author: fzn
 * @create: 2022-01-24 20:21
 **/
public class MiGong {
    public static void main(String[] args) {
        // 创建一个二维数组 模拟迷宫
        int[][] map = new int[8][7];
        // 使用1 表示墙
        // 上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部变为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        // map[1][2] = 1;
        // map[2][2] = 1;

        // 输出
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }


        // 使用递归回溯给小球找路
        setWay(map,1,1);
        System.out.println("-----------------");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    // 使用递归回溯来给小球找路

    /**
     *如果小球能到map[6][5] 位置 则说明通路找到
     * 约定：当map[i][j] 为0表示该点没有走 当为1 表示墙 当为2时表示通路可以走 3表示该点已经走过但是走不通
     * 规定走路的策略：     下右上左 如果该点走不通就回溯
     *
     * @param map 表示地图
     * @param i 从哪个位置开始找
     * @param j
     * @return 如果找到通路就返回true 否则就返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            // 通路已经找到 ok
            return true;
        }else {
            // 如果该点还没有走过
            if (map[i][j] == 0) {
                map[i][j] = 2; // 假定该点可以走通
                if (setWay(map,i+1 ,j)) {
                    // 向下走
                    return true;
                }else if (setWay(map,i,j+1)){
                    // 向右走
                    return true;
                }else if (setWay(map,i-1,j)){
                    // 向上走
                    return true;
                }else if (setWay(map,i,j-1)){
                    // 向左走
                    return true;
                }else {
                    // 说明该点走不通是死路
                    map[i][j] = 3;
                    return false;
                }
            }else {
                // 如果map[i][j]!=0 可能是1可能是2也可能是3
                return false;
            }
        }
    }
}