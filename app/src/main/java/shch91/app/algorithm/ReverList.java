package shch91.app.algorithm;

public class ReverList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //交换值
        ListNode h = head, n = head.next, next;
        h.next = null;
        while (n != null) {
            //保存next
            next = n.next;
            n.next = h;

            //迭代
            h = n;
            n = next;
        }
        return h;
    }

    public void deleteNode(ListNode node) {
        ListNode next=node.next;
        node.val=next.val;
        node.next=next.next;
    }





    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
