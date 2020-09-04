package shch91.app.algorithm;

import java.util.*;

public class TopKFreq {
    public static void main(String[] args) {
        TopKFreq t = new TopKFreq();
        int[] nums = new int[]{1};
        int[] ret = t.maxSlidingWindow(nums, 1);
        for (int it : ret) {
            System.out.print(it + "\t");
        }


    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ret = new int[k];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        for (Map.Entry<Integer, Integer> it : list) {
            if (index < k) {
                ret[index++] = it.getKey();
            }
        }
        return ret;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ret = new int[nums.length - k + 1];
        int index = 0;
        for (int i = k - 1, j, max; i < nums.length; i++) {
            j = i - k + 1;
            max = nums[i];
            while (j < i) {
                max = Math.max(max, nums[j]);
                j++;
            }
            ret[index++] = max;
        }
        return ret;
    }
}
