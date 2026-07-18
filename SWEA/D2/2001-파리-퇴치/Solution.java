import java.io.*;
import java.util.*;

class Solution {

    static int T;
    static int N;
    static int M;
    static int[][] map;
    static int max;

    public static void main(String[] args) throws Exception {
        // SWEA 제출 시 제거
        // System.setIn(new FileInputStream("./input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            max = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int y = 0; y <= N - M; y++) {
                for (int x = 0; x <= N - M; x++) {
                    max = Math.max(max, checkMap(y, x));
                }
            }

            sb.append("#").append(t).append(" ").append(max).append("\n");
        }

        System.out.print(sb);
    }

    static int checkMap(int y, int x) {
        int sum = 0;

        for (int i = y; i < y + M; i++) {
            for (int j = x; j < x + M; j++) {
                sum += map[i][j];
            }
        }

        return sum;
    }
}
