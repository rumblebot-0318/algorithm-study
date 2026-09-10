import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int need = target - cur;

            if (map.containsKey(need)) {
                return new int[] { map.get(need), i };
            }

            map.put(cur, i);
        }

        return new int[] {};
    }
}
