# 프로그래머스 Lv.2 1844. 게임 맵 최단거리

## 문제 유형

- 난이도: Lv.2
- 유형: 그래프 탐색, 최단거리, BFS
- 링크: <https://school.programmers.co.kr/learn/courses/30/lessons/1844>
- 핵심: `(0, 0)`에서 `(N - 1, M - 1)`까지 벽을 피해서 갈 수 있는 가장 짧은 칸 수를 구한다.

## 제출한 접근: 거리순 PriorityQueue

각 칸을 `Node(y, x, dist)`로 저장한다.

```java
PriorityQueue<Node> queue = new PriorityQueue<>();
queue.add(new Node(0, 0, 1));
visited[0][0] = true;
```

`Node.compareTo()`가 `dist`가 작은 노드를 먼저 꺼내도록 한다. 따라서 목표 칸을 처음 꺼냈을 때의 `dist`가 최단 거리다.

```java
@Override
public int compareTo(Node other) {
    return Integer.compare(this.dist, other.dist);
}
```

## 가장 중요한 방문 처리 순서

> 큐에 넣는 순간 `visited[y][x] = true`로 바꾼다.

```java
visited[nextY][nextX] = true;
queue.add(new Node(nextY, nextX, cur.dist + 1));
```

시작점도 마찬가지다.

```java
queue.add(new Node(0, 0, 1));
visited[0][0] = true;
```

이 순서면 같은 칸이 여러 경로에서 큐에 중복으로 들어가는 것을 막는다.

## 탐색 흐름

1. 시작점 `(0, 0, 1)`을 넣고 시작점을 방문 처리한다.
2. 가장 거리가 짧은 `cur`을 하나 꺼낸다.
3. 목표 지점이면 `cur.dist`를 즉시 반환한다.
4. 상·하·좌·우 중 지도 안에 있고, 길(`1`)이고, 미방문인 칸을 큐에 넣는다.
5. 큐가 비었는데 목표를 못 만났다면 `-1`을 반환한다.

## 제출 코드

```java
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
```

## 시간복잡도

| 방식 | 시간복잡도 | 공간복잡도 |
|---|---:|---:|
| 현재 풀이: PriorityQueue | `O(NM log(NM))` | `O(NM)` |
| 모든 이동 비용이 1일 때의 일반 BFS: ArrayDeque | `O(NM)` | `O(NM)` |

현재 풀이는 올바르다. 다만 이 문제는 모든 칸의 이동 비용이 `1`이므로, 다음에는 `Queue<Node> queue = new ArrayDeque<>();`를 쓰는 일반 BFS가 더 가볍다.

## 오늘의 불변식

> `visited[y][x]`가 `true`인 칸은 이미 큐에 넣었거나 처리한 칸이다. 따라서 다시 큐에 넣지 않는다.
