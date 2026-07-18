import java.io.*;
import java.util.*;

class Solution {

    static int T;
    static int N;
    static int[][] map;

    // 오른쪽 -> 아래 -> 왼쪽 -> 위
    static int[][] dir = {
        {0, 1},
        {1, 0},
        {0, -1},
        {-1, 0}
    };

    public static void main(String[] args) throws Exception {
        // SWEA 제출 시 제거
        // System.setIn(new FileInputStream("./sample.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            fillSnail();

            sb.append("#").append(t).append("\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }

    static void fillSnail() {
        int y = 0;
        int x = 0;
        int dirIdx = 0;
        int num = 1;

        map[y][x] = num++;

        while (num <= N * N) {
            int ny = y + dir[dirIdx][0];
            int nx = x + dir[dirIdx][1];

            if (canMove(ny, nx)) {
                y = ny;
                x = nx;
                map[y][x] = num++;
            } else {
                dirIdx = (dirIdx + 1) % 4;
            }
        }
    }

    static boolean canMove(int y, int x) {
        return y >= 0 && y < N
            && x >= 0 && x < N
            && map[y][x] == 0;
    }
}
