package shch91.app.algorithm;

import java.util.ArrayList;
import java.util.List;

public class SearchAndSort {

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "0";
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i == nums.length - 1) {
                return "0";
            }
        }

        /**
         * 冒泡排序
         */
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i; j++) {
                if (cmp(nums[j], nums[j + 1]) < 0) {
                    int t = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = t;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
        }
        return sb.toString();
    }

    private int cmp(int x, int y) {
        int lenx = base(x), leny = base(y);
        int xy, yx, tx = x, ty = y;
        while (leny > 0) {
            tx = tx * 10;
            leny--;
        }
        xy = tx + y;
        while (lenx > 0) {
            ty = ty * 10;
            lenx--;
        }
        yx = ty + x;
        return xy - yx;
    }


    private int base(int v) {
        int t = v, ret = 0;
        do {
            t /= 10;
            ret++;
        } while (t != 0);
        return ret;
    }

    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        //中位数
        int mid = kSmall(nums, 0, nums.length - 1, (nums.length + 1) / 2);

        // 3-way-partition-to-wiggly in O(n) time with O(1) space.
        int i = 0, j = 0, k = nums.length - 1;
        while (j <= k) {
            if (nums[index(j, nums.length)] > mid) {
                swap(nums, index(i++, nums.length), index(j++, nums.length));
            } else if (nums[index(j, nums.length)] < mid) {
                swap(nums, index(j, nums.length), index(k--, nums.length));
            } else {
                j++;
            }
        }
    }

    /**
     * 快速选择第k小元素
     *
     * @param arr
     * @param k
     * @return
     */
    private int kSmall(int[] arr, int s, int e, int k) {
        int t = quick(arr, s, e);
        if (t == k - 1) {
            return arr[t];
        } else if (t > k - 1) {
            return kSmall(arr, s, t - 1, k);
        } else {
            return kSmall(arr, t + 1, e, k);
        }
    }

    public static void main(String[] args) {
        SearchAndSort t = new SearchAndSort();
        int[] arr = new int[]{5,2,6,1};
        //System.out.println((3-2)>>1);
        //System.out.println(t.findPeakElement(arr));
        //int de = t.findDuplicate(arr);
        //System.out.println(de);
        List<Integer>s= t.countSmaller(arr);
        for(Integer it:s){
            System.out.println(it);
        }
//        t.wiggleSort(arr);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i]);
//        }
    }


    public void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 地址转换
     *
     * @param i
     * @param len
     */
    private int index(int i, int len) {
        return (1 + 2 * (i)) % (len | 1);
    }


    /**
     * 一次快排找中位数
     */
    private int quick(int[] arr, int beg, int end) {
        int pivot = arr[beg];
        int i = beg, j = end;
        while (i < j) {
            while (i < j && arr[j] >= pivot) {
                j--;
            }
            arr[i] = arr[j];
            while (i < j && arr[i] <= pivot) {
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = pivot;
        return i;
    }

    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        if (nums.length == 2) {
            return nums[0] > nums[1] ? 0 : 1;
        }
        int i = 0, j = nums.length - 1, mid;
        while (i <= j) {
            mid = i + (j - i) / 2;
            if ((mid == 0 || nums[mid] > nums[mid - 1]) && (mid == nums.length - 1 || nums[mid] > nums[mid + 1])) {
                return mid;
            } else if (mid > 0 && nums[mid] < nums[mid - 1]) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 查找元素的 index
     *
     * @param arr
     * @param key
     * @return
     */
    public int binSearch(int[] arr, int key) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int i = 0, j = arr.length - 1, mid = -1;
        while (i <= j) {
            mid = i + (j - i) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] > key) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return mid;
    }


    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 1) {
            return -1;
        }
        int fast = nums[0], slow = nums[0];
        do {
            fast = nums[fast];
            fast = nums[fast];
            slow = nums[slow];
        } while (fast != slow);
        //重新表头
        fast = nums[0];
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums == null) {
            return null ;
        }
        List<Integer> ret = new ArrayList<>(nums.length);

        for (int i = nums.length-1; i >=0; i--) {
            int cnt = 0;

            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    cnt++;
                }
            }
            ret.add(cnt);
        }
        return ret;
    }
}
