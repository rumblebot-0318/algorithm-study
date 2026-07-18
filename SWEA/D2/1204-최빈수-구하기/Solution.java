import java.io.*;
import java.util.*;

class Solution {

    static int T;
    static int testCaseNumber;
    static int[] count;
    static int maxCount;
    static int answer;

    public static void main(String[] args) throws Exception {
        // SWEA 제출 시 제거
        // System.setIn(new FileInputStream("./sample.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            testCaseNumber = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            count = new int[101];

            for (int i = 0; i < 1000; i++) {
                int score = Integer.parseInt(st.nextToken());
                count[score]++;
            }

            maxCount = -1;
            answer = -1;

            for (int score = 0; score <= 100; score++) {
                if (count[score] >= maxCount) {
                    maxCount = count[score];
                    answer = score;
                }
            }

            sb.append("#").append(testCaseNumber).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }
}
