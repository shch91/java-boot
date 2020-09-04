package shch91.app.algorithm;

class CopyLink {
    public static void main(String[] args) {
        CopyLink p = new CopyLink();

    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node h = head, n, reHead=null;
        //尾部添加新节点
        while (h != null) {
            n = h.next;
            Node tmp = new Node(h.val);
            if (h == head) {
                reHead = tmp;
            }
            h.next = tmp;
            tmp.next = n;
            //迭代下一个
            h = n;
        }
        //拷贝随机指针
        h = head;
        while (h != null) {
            if(h.random!=null) {
                h.next.random = h.random.next;
            }else{
                h.next.random=null;
            }
            h = h.next.next;
        }
        //拆开新旧指针
        h = head;
        while (h != null) {
            n = h.next;
            h.next = n.next;
            if (n.next != null) {
                n.next = n.next.next;
            }
            h = h.next;
        }
        return reHead;
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}