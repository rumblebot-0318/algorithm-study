import java.util.*;

class Solution {

    static int questions;
    static char[] combinations;
    static String[] map;
    static char[][] mapToCharArray;
    static char[] letters = new char[]{'a', 'b', 'c'};
    static List<Node> questionsNodes;
    static int setCount;

    static int[][] dir = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    static int totalSize;
    static int height;
    static int width;

    public static void main(String[] args) {
        map = new String[]{"aa?"};

        height = map.length;
        width = map[0].length();
        setCount = 0;
        totalSize = 0;
        mapToCharArray = new char[height][width];

        for (int i = 0; i < map.length; i++) {
            char[] currentMap = map[i].toCharArray();
            for (int j = 0; j < currentMap.length; j++) {
                mapToCharArray[i][j] = currentMap[j];
            }
        }

        questions = 0;
        questionsNodes = new ArrayList<>();

        for (int i = 0; i < mapToCharArray.length; i++) {
            for (int j = 0; j < mapToCharArray[i].length; j++) {
                if (mapToCharArray[i][j] == '?') {
                    questionsNodes.add(new Node(i, j));
                    questions++;
                }
                totalSize++;
            }
        }

        combinations = new char[questions];

        for (int i = 0; i < letters.length; i++) {
            combinations[0] = letters[i];
            makeCombinations(1);
        }

        System.out.println(setCount);
    }

    private static void makeCombinations(int idx) {
        if (idx >= questions) {
            char[][] curTmp = new char[height][width];

            for (int i = 0; i < height; i++) {
                curTmp[i] = mapToCharArray[i].clone();
            }

            int combiIdx = 0;
            for (Node node : questionsNodes) {
                curTmp[node.y][node.x] = combinations[combiIdx++];
            }

            groupCheck(curTmp);
            return;
        }

        for (int i = 0; i < letters.length; i++) {
            combinations[idx] = letters[i];
            makeCombinations(idx + 1);
        }
    }

    private static void groupCheck(char[][] curTmp) {
        int sum = 0;

        for (char letter : letters) {
            Node startPoint = getPosition(letter, curTmp);
            if (startPoint != null) {
                sum += getCount(startPoint, curTmp);
            }
        }

        if (sum == totalSize) {
            setCount++;
        }
    }

    private static int getCount(Node cur, char[][] tmp) {
        int count = 0;
        boolean[][] visited = new boolean[height][width];
        Queue<Node> queue = new ArrayDeque<>();

        queue.offer(cur);
        visited[cur.y][cur.x] = true;
        char target = tmp[cur.y][cur.x];

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            count++;

            for (int[] d : dir) {
                int nextY = curNode.y + d[0];
                int nextX = curNode.x + d[1];

                if (nextY >= 0 && nextY < height
                        && nextX >= 0 && nextX < width
                        && !visited[nextY][nextX]
                        && tmp[nextY][nextX] == target) {
                    visited[nextY][nextX] = true;
                    queue.offer(new Node(nextY, nextX));
                }
            }
        }

        return count;
    }

    private static Node getPosition(char target, char[][] curTmp) {
        for (int i = 0; i < curTmp.length; i++) {
            for (int j = 0; j < curTmp[i].length; j++) {
                if (target == curTmp[i][j]) {
                    return new Node(i, j);
                }
            }
        }
        return null;
    }
}

class Node {
    int y;
    int x;

    Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
