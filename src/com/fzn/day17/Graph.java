package com.fzn.day17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @program: DataStructure
 * 描述：      图
 *          深度优先遍历和广度优先遍历
 * @author: fzn
 * @create: 2022-02-16 13:46
 **/
public class Graph {

    public static void main(String[] args) {
        // 节点的个数
        int n = 5;
        String[] vertexValue = {"A", "B", "C", "D", "E"};
        // 创建图对象
        Graph graph = new Graph(n);
        // 循环添加顶点
        for (String value : vertexValue) {
            graph.insertVertex(value);
        }
        // 添加边
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        // 显示
        graph.showGraph();

        // 深度优先遍历
        System.out.println("=======");
        // graph.dfs();

        // 广度优先遍历
        graph.bfs();
    }

    // 存储顶点的集合
    private ArrayList<String> vertexList;
    private int[][] edges; // 存储图对应的临结矩阵
    private int numOfEdges; // 表示边的数目
    // 定义数组boolean[] 记录某一个节点是否被访问
    private boolean[] isVisited;

    public Graph(int n) {
        // 初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        // 边默认为0
        numOfEdges = 0;
        isVisited = new boolean[5];
    }
    // 得到第一个邻接节点的下标

    /**
     * 如果存在返回对应的下标不存在返回-1
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }
    // 根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }
    // 深度优先遍历算法
    public void dfs(boolean[] isVisited, int i) {
        // 首先访问该节点
        System.out.print(getValueByIndex(i) + "->");
        // 将结点设置为已访问
        isVisited[i] = true;
        // 查找节点i的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果w节点已经被访问过 去查找邻接节点的下一个
            w = getNextNeighbor(i, w);
        }
    }

    // 对dfs进行一个重载 遍历我们所有的节点并进行dfs
    public void dfs() {
        for (int i = 0; i < getNUmOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    // 对一个节点进行广度优先遍历
    public void bfs(boolean[] isVisited, int i) {
        int u; // 表示队列的头节点的下标
        int w; // 邻接节点w
        // 队列 节点访问的顺序
        LinkedList queue = new LinkedList<>();
        // 访问节点 输出节点信息
        System.out.print(getValueByIndex(i) + "=>");
        // 标记为已访问
        isVisited[i] = true;
        // 将节点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            // 取出队列的头节点下标
            u = (Integer) queue.removeFirst();
            //  得到第一个邻节点的下标w
            w = getFirstNeighbor(u);
            while (w != -1) {
                // 判断是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    // 标记已经访问
                    isVisited[w] = true;
                    // 入队列
                    queue.addLast(w);
                }
                // 以u为前驱结点 找w后面的下一个邻接节点
                w = getNextNeighbor(u, w); // 体现广度优先
            }
        }
    }
    // 遍历所有节点 都进行广度优先搜索
    public void bfs() {
        for (int i = 0; i < getNUmOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    // 图中常用的方法
    // 返回节点的个数
    public int getNUmOfVertex() {
        return vertexList.size();
    }
    // 得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }
    // 返回节点i（下标）对应的数据 0->A 1->B
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    // 返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }
    // 显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    // 插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }
    // 添加边
    /**
     *
     * @param v1 表示点的下标即是第几个顶点 “A”-"B" "A"->0 “B”->1
     * @param v2 第二个顶点对应的下标
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}