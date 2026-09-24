import java.util.*;

class Solution {
    int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    boolean[][] visited;

    public int solution(int[][] maps) {
        int answer = -1;
        visited = new boolean[maps.length][maps[0].length];

        int height = maps.length - 1;
        int width = maps[0].length - 1;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0, 0, 1));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.y == height && cur.x == width) {
                return cur.dist;
            }

            for (int i = 0; i < dir.length; i++) {
                int nextY = cur.y + dir[i][0];
                int nextX = cur.x + dir[i][1];

                if (nextY >= 0 && nextY <= height && nextX >= 0 && nextX <= width) {
                    if (!visited[nextY][nextX] && maps[nextY][nextX] == 1) {
                        visited[nextY][nextX] = true;
                        queue.add(new Node(nextY, nextX, cur.dist + 1));
                    }
                }
            }
        }

        return answer;
    }
}

class Node implements Comparable<Node> {
    int y;
    int x;
    int dist;

    Node(int y, int x, int dist) {
        this.y = y;
        this.x = x;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.dist, other.dist);
    }
}
