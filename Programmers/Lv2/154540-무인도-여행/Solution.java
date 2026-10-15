import java.util.*;

class Solution {
    boolean[][] visited;
    int[][] map;
    final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int height;
    int width;

    public int[] solution(String[] maps) {
        height = maps.length;
        width = maps[0].length();
        visited = new boolean[height][width];
        map = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (maps[y].charAt(x) != 'X') {
                    map[y][x] = maps[y].charAt(x) - '0';
                }
            }
        }

        List<Integer> sums = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[y][x] != 0 && !visited[y][x]) {
                    sums.add(bfs(y, x));
                }
            }
        }

        if (sums.isEmpty()) return new int[]{-1};

        Collections.sort(sums);
        return sums.stream().mapToInt(Integer::intValue).toArray();
    }

    int bfs(int startY, int startX) {
        int sum = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(startY, startX));
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            sum += map[cur.y][cur.x];

            for (int[] d : dir) {
                int nextY = cur.y + d[0];
                int nextX = cur.x + d[1];

                if (nextY < 0 || nextY >= height || nextX < 0 || nextX >= width) continue;
                if (visited[nextY][nextX] || map[nextY][nextX] == 0) continue;

                visited[nextY][nextX] = true;
                queue.offer(new Node(nextY, nextX));
            }
        }
        return sum;
    }

    class Node {
        int y;
        int x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
