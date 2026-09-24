import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> ans = new LinkedList<>();
        int currentDays = 0;
        int count = 0;

        for (int i = 0; i < progresses.length; i++) {
            int left = (100 - progresses[i] + speeds[i] - 1) / speeds[i];

            if (left <= currentDays) {
                count++;
            } else {
                if (count > 0) {
                    ans.add(count);
                }
                currentDays = left;
                count = 1;
            }
        }

        ans.add(count);

        int[] answer = new int[ans.size()];
        int idx = 0;
        for (int value : ans) {
            answer[idx++] = value;
        }

        return answer;
    }
}
