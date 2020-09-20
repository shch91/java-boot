package shch91.app.algorithm;

import java.util.*;

class Tree {

    /**
     * 左右子树查找，找到任何一个节点都直接返回
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p == root || q == root || root == null) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right == null) {
            return left;
        } else if (left == null && right != null) {
            return right;
        } else if (left == null && right == null) {
            return null;
        }
        return root;
    }


    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        StringBuilder result = new StringBuilder("[");
        LinkedList<TreeNode> q = new LinkedList();
        q.add(root);
        TreeNode cur;
        int cnt = total(root);
        while (!q.isEmpty() && cnt > 0) {
            cur = q.poll();
            if (cur == null) {
                result.append("null,");
            } else {
                cnt--;
                result.append(cur.val + ",");
            }
            if (cur != null) {
                q.add(cur.left);
                q.add(cur.right);
            }
        }
        result.deleteCharAt(result.length() - 1);
        result.append("]");
        return result.toString();
    }

    /**
     * 该节点下的总结点数
     * @param root
     * @return
     */
    private int total(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = total(root.left);
        int right = total(root.right);
        return left + right + 1;
    }

    private int deep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = deep(root.left);
        int right = deep(root.right);
        return Math.max(left, right) + 1;
    }


    public static void main(String[] args) {
        Tree t = new Tree();
        //TreeNode root = t.deserialize("[1,2,null,3,null,4,null,5]");
        //System.out.println(t.serialize(root));
        int [][] build=new int[][]{
                {1,2,1},
                {1,2,2},
                {1,2,3}
        };
        t.getSkyline(build);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("[]".equals(data) || data == null || data.length() == 0) {
            return null;
        }
        String tree = data.substring(1, data.length() - 1);
        String[] node = tree.split(",");

        LinkedList<TreeNode> q = new LinkedList();
        TreeNode root = new TreeNode(Integer.valueOf(node[0])), cur, tmp;
        q.add(root);
        int size, index = 1;
        //左右子树
        boolean flag = true;
        while (index < node.length) {
            size = 2;
            cur = q.poll();
            while (size > 0 && index < node.length) {
                //左子树
                if (flag) {
                    if ("null".equals(node[index])) {
                        cur.left = null;
                    } else {
                        tmp = new TreeNode(Integer.valueOf(node[index]));
                        cur.left = tmp;
                        q.add(tmp);
                    }
                } else {
                    if ("null".equals(node[index])) {
                        cur.right = null;
                    } else {
                        tmp = new TreeNode(Integer.valueOf(node[index]));
                        cur.right = tmp;
                        q.add(tmp);
                    }
                }
                size--;
                index++;
                flag = !flag;
            }
        }
        return root;
    }

    /**
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {

        int builds = buildings.length;
        List<Faces> list=new ArrayList<Faces>(2*builds);
        //先起始
        for(int i=0;i<builds;i++){
            list.add(new Faces(buildings[i][0],buildings[i][2],0));
            list.add(new Faces(buildings[i][1],buildings[i][2],1));
        }
        //升序
        Comparator<Faces>cmp=new Comparator<Faces>() {
            @Override
            public int compare(Faces o1, Faces o2) {

                if(o1.x==o2.x){
                    //起始节点
                   if(o1.flag==o2.flag) {
                       return o1.flag==0? o2.h - o1.h : o1.h - o2.h;
                   }else{
                       return o1.flag-o2.flag;
                   }

                }else{
                    return o1.x-o2.x;
                }
            }
        };

        Collections.sort(list,cmp);
        //降序
        Comparator<Integer>p=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        };
        PriorityQueue<Integer> high = new PriorityQueue<Integer>(p);
        high.add(0);
        List<List<Integer>> result=new ArrayList<List<Integer>>();

        int h;
        for (Faces it:list){
            //起始节点
            if(it.flag==0){
                //当前最高点
                h=high.peek();
                high.add(it.h);
                if(it.h>h){
                    result.add(Arrays.asList(it.x,it.h));
                }
            }else{
                //当前最高点
                h=high.peek();

                //移除高度
                high.remove(it.h);
                //最大高度发生变化
                if(h!=high.peek()){
                    result.add(Arrays.asList(it.x,high.peek()));
                }

            }
        }
        return result;

    }

    private class Faces {
        //x坐标
        private int x;
        //高度
        private int h;
        //起始节点标记
        private int flag;
        public Faces(int x,int h,int flag){
            this.x=x;
            this.h=h;
            this.flag=flag;
        }
    }

}