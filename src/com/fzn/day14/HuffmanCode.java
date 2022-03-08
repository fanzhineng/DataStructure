package com.fzn.day14;

import java.util.*;

/**
 * @program: DataStructure
 * 描述：  赫夫曼编码
 *              压缩和解压
 * @author: fzn
 * @create: 2022-02-14 13:14
 **/
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like you do you like i";
        byte[] contentBytes = content.getBytes();
        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是"+ Arrays.toString(huffmanCodeBytes)+" 长度为："+ huffmanCodeBytes.length);

        // 解压
        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原来的字符串："+ new String(sourceBytes));


        // System.out.println(contentBytes.length); // 40
        // List<Node> nodes = getNodes(contentBytes);
        // System.out.println(nodes);
        // System.out.println("==============");
        // // 创建的二叉树
        // Node huffmanTree = createHuffmanTree(nodes);
        // preOrder(huffmanTree);
        //
        // // 赫夫曼编码
        // getCodes(huffmanTree);
        // System.out.println("生成的赫夫曼编码表" + huffmanCodes);
        // System.out.println("生成的字符串");
        // zip(contentBytes, huffmanCodes);
        //
        // // 压缩后的
        // byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        // System.out.println(huffmanCodeBytes.length);
        // System.out.println(Arrays.toString(huffmanCodeBytes));
    }

    // 压缩数据的解码

    /**
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 处理后的字节数组
     * @return 返回的就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 先得到huffmanBytes对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            // 判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        // 把字符串按照指定的赫夫曼编码进行解码
        // 把赫夫曼编码表进行调换 因为反向查询 a->100 100->a
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        // 创建一个集合存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                // 递增取出key
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null){
                    // 没有匹配到
                    count++;
                }else {
                    // 匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count; // 直接移动到count的位置
        }
        // list中存放了所有的单个字符 把list中的数据放入到byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    // 完成数据的解压
    // 将Huffman字节数组转成二进制字符串
    // 二进制字符串对照赫夫曼编码转成正常的字符串
    /**
     * 将一个byte 转成一个二进制的字符串
     * @param flag 标志是否需要补高位如果是true 表示需要补高位如果是false表示不补 如果是最后一个不需要补高位
     * @param b 传入的byte
     * @return 是b对应的二进制的字符串 按照补码返回的
     */
    private static String byteToBitString(boolean flag, byte b){
        // 使用一个变量保存b
        int temp = b;
        // 如果是正数需要补位 补高位
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp); // 返回的是二进制对应的补码
        if (flag) {
            return str.substring(str.length() - 8);
        }else {
            return str;
        }
    }



    // 使用一个方法 封装起来 便于我们调用
    /**
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过赫夫曼编码处理后的字节数组（压缩后的数组）
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        // 根据nodes 创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        // 对应的赫夫曼编码(根据赫夫曼数)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        // 根据生成的赫夫曼编码 压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    // 将一个字符串对应的byte[] 数组 通过生成的赫夫曼编码表 返回一个赫夫曼编码 压缩后的byte[]
    /**
     *
     * @param bytes 这是原始的字符串对应的byte数组
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼处理后的byte[]
     */
    private static byte[] zip(byte[] bytes,Map<Byte, String> huffmanCodes) {
        // 利用编码表 将bytes 转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历bytes组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        // System.out.println(stringBuilder);
        // 将对应的字符串转成byte数组
        // 一句话 int len = (stringBuilder.length() + 7) / 8;
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        }else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 创建一个存储 压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; // 记录第一个byte
        for (int i = 0; i < stringBuilder.length(); i+=8) { // 因为每8位对应一个byte
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                // 不够8位
                strByte = stringBuilder.substring(i);
            }else {
                strByte = stringBuilder.substring(i, i + 8 );
            }
            // 将strByte 转换成一个byte[]
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    // 生成赫夫曼树对应的赫夫曼编码
    // 1. 将赫夫曼编码表存放在Map<Byte,String> 形式
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    // 2. 在生成赫夫曼编码表示 需要去拼接路径 定义一个StringBuilder 存储某一个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    // 为了调用方便 重载一把
    public static Map<Byte, String> getCodes(Node node) {
        if (node == null) {
            return null;
        }
        // 处理root的左子树
        getCodes(node.left,"0",stringBuilder);
        getCodes(node.right,"1",stringBuilder);
        return huffmanCodes;
    }
    /**
     * 功能 ： 将传入的node节点的所有叶子节点的赫夫曼编码得到 并放入到huffmanCodes中
     * @param node 传入结点
     * @param code 路径： 左子节点是0 右子节点是1
     * @param stringBuilder 是用于拼接路径
     */
    public static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 将传入的code 加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {
            // 如果node==null 不处理
            // 判断当前的node 是叶子节点还是非叶子节点
            if (node.data == null) { // 非叶子节点
                // 递归处理
                // 向左递归
                getCodes(node.left,"0",stringBuilder2);
                // 向右递归
                getCodes(node.right,"1",stringBuilder2);
            }else { // 说明是一个叶子节点
                // 就表示找到某一个叶子节点的最后
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }

    /**
     * 前序遍历
     */
    public static void preOrder(Node node) {
        if (node != null) {
            node.preOrder();
        }else {
            System.out.println("空树");
        }
    }

    /**
     *
     * @param bytes 接收字节数组
     * @return 返回list
     */
    public static List<Node> getNodes(byte[] bytes) {
        // 创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();
        // 遍历bytes 统计每一个byte出现的次数-> map[key,value]
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                // map 中还没有
                counts.put(b, 1);
            }else {
                counts.put(b, count +1 );
            }
        }

        // 把每个键值对转成一个Node对象 并加入nodes集合中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 通过list 创建对应的赫夫曼数
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 排序 从小到大
            Collections.sort(nodes);
            // 取出第一颗最小的
            Node leftNode = nodes.get(0);
            // 取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            // 创建一颗新的二叉树, 根节点 只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            // 将处理的两颗数移除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将新的二叉树数加入nodes
            nodes.add(parent);
        }
        // 返回 nodes 就是哈夫曼树的根节点
        return nodes.get(0);

    }
}

class Node  implements Comparable<Node> {
    Byte data; // 存放数据(字符)本身 a => 97
    int weight; //权值
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}