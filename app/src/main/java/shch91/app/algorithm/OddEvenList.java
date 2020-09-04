package shch91.app.algorithm;

public class OddEvenList {


    public int titleToNumber(String s) {
        if(s==null){
            return 0;
        }
        int ret=0,base=26;
        for(int i=0;i<s.length();i++){
            char a=s.charAt(i);
            ret=ret*base+a-'A'+1;
        }
        return ret;
    }
    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        OddEvenList p = new OddEvenList();
        System.out.println(p.titleToNumber("AA"));
        ListNode head = new ListNode(1, null);
        ListNode h = head;
        for (int i = 1; i < 2; i++) {
            h.next = new ListNode(i + 1, null);
            h = h.next;
        }
        p.isPalindrome(head);
    }

    /**
     * 插入
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode o = head, eHead = head.next, e = head.next, start = head.next.next;

        //当前位置奇偶标记
        boolean flag = true;
        while (start != null) {
            //奇数
            if (flag) {
                o.next = start;
                flag = !flag;
                o = o.next;
            } else {
                e.next = start;
                flag = !flag;
                e = e.next;
            }
            start = start.next;
        }
        e.next = null;
        o.next = eHead;
        return head;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        int len = 0, half;
        ListNode h = head, mid = head;
        while (h != null) {
            len++;
            h = h.next;
        }
        half = len >> 1;
        while (half > 0) {
            mid = mid.next;
            half--;
        }
        mid = reverseList(mid);
        h = head;
        half=len>>1;
        while (half>0) {
            if (mid.val != h.val) {
                return false;
            }
            half--;
            mid=mid.next;
            h=h.next;
        }
        return true;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode rev = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return rev;
    }
}
