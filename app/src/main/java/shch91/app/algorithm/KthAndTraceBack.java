package shch91.app.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class KthAndTraceBack {

    private static LinkedList<String> temp = new LinkedList<>();

    private static List<List<String>> result = new ArrayList<List<String>>();

    public static void main(String[] args) {

        // partition("a");
        //for (List<String> list : result) {
        //  for (String it : list) {
        //    System.out.print(it + "    ");
        //}
        //System.out.println();
        // }
        KthAndTraceBack s = new KthAndTraceBack();
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = s.findKthLargest(nums, 5);
        System.out.println(k);
    }

    public static List<List<String>> partition(String s) {
        if (s == "") {
            return result;
        }
        //开始搜索
        traceBack(s, 0);
        return result;
    }

    private static void traceBack(String str, int beg) {
        if (beg == str.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int end = beg; end < str.length(); end++) {
            if (isPalindrome(str, beg, end)) {
                String tempStr = str.substring(beg, end + 1);
                temp.addLast(tempStr);
                traceBack(str, end + 1);
                temp.removeLast();
            }
        }
    }

    public static boolean isPalindrome(String str, int s, int e) {
        while (s < e) {
            if (str.charAt(s++) != str.charAt(e--)) {
                return false;
            }
        }
        return true;
    }

    public int findKthLargest(int[] nums, int k) {
        return kth(nums, 0, nums.length - 1, k);
    }

    public int kth(int[] nums, int i, int j, int k) {
        int cur = pos(nums, i, j);
        if (k == j - cur + 1) {
            return nums[cur];
        } else if (j - cur + 1 > k) {
            return kth(nums, cur + 1, j, k);
        } else {
            return kth(nums, i, cur - 1, k - (j - cur + 1));
        }
    }


    public int pos(int[] nums, int i, int j) {

        int pivot = nums[i];
        while (i < j) {
            while (j > i && nums[j] >= pivot) {
                j--;
            }
            nums[i] = nums[j];
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            nums[j] = nums[i];
        }
        nums[i] = pivot;
        return i;
    }

}