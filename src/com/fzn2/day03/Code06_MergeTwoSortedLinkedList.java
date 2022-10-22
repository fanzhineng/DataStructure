package com.fzn2.day03;

/**
 * @program: DataStructure
 * 描述：  // 测试链接：https://leetcode.com/problems/merge-two-sorted-lists
 * 两个有序的链表合并
 * @author: fzn
 * @create: 2022-10-22 11:59
 **/
public class Code06_MergeTwoSortedLinkedList {
    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        ListNode head = head1.val <= head2.val ? head1 : head2;
        ListNode cur1 = head.next;
        ListNode cur2 = head == head1 ? head2 : head1;
        ListNode pre = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        // 剩谁就连剩
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
    }
}