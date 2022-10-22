package com.fzn2.day03;

/**
 * @program: DataStructure
 * 描述：      K个一组翻转链表
 * 测试链接：https://leetcode.com/problems/reverse-nodes-in-k-group/
 * @author: fzn
 * @create: 2022-10-21 23:47
 **/
public class Code04_ReverseNodesInKGroup {
    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    // 反转一组
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);
        if (end == null) {
            return head;
        }
        // 第一组凑齐了！
        head = end;
        reverse(start, end);
        // 上一组的结尾节点
        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }

    // 得到 k 的值一组
    public static ListNode getKGroupEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    // 翻转 并指向下一组
    public static void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        ListNode next = null;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }
}