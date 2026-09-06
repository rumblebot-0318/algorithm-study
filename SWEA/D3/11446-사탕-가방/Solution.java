import java.io.*;
import java.util.*;

class Solution {

    static int T;
    static int N;
    static long M;
    static long[] candies;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Long.parseLong(st.nextToken());

            candies = new long[N];
            long max = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                candies[i] = Long.parseLong(st.nextToken());
                max = Math.max(max, candies[i]);
            }

            long left = 1;
            long right = max;
            long answer = 0;

            while (left <= right) {
                long mid = (left + right) / 2;

                if (canMake(mid)) {
                    answer = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static boolean canMake(long bagCount) {
        long candyPerBag = 0;

        for (int i = 0; i < N; i++) {
            candyPerBag += candies[i] / bagCount;
            if (candyPerBag >= M) {
                return true;
            }
        }

        return false;
    }
}
