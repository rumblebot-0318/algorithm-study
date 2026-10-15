# 프로그래머스 Lv.2 154540. 무인도 여행

- 링크: <https://school.programmers.co.kr/learn/courses/30/lessons/154540>
- 유형: 2차원 격자, 연결 요소, BFS/DFS
- 목표: `X`가 아닌 칸이 상·하·좌·우로 연결된 섬마다 식량의 합을 구한 뒤 오름차순 정렬한다.

## 제출한 BFS 접근

전체 맵을 훑다가 **숫자 칸이면서 아직 방문하지 않은 칸**을 만나면, 그 칸에서 BFS를 한 번 시작한다.

```text
전체 칸 순회
→ 새 섬 발견
→ 큐에 시작점을 넣고 즉시 방문 처리
→ 큐에서 하나씩 꺼내 식량을 더함
→ 상하좌우의 미방문 숫자 칸을 큐에 추가
→ 큐가 비면 그 섬의 식량 합 완성
```

### 핵심 불변식

> BFS에서 `visited[y][x] == true`인 칸은 이미 큐에 넣었거나 처리한 칸이다. 따라서 같은 섬의 칸은 한 번만 합산된다.

방문 처리는 `poll()`할 때보다 **큐에 넣는 순간** 하는 편이 명확하다.

```java
visited[nextY][nextX] = true;
queue.offer(new Node(nextY, nextX));
```

## BFS와 DFS: 무엇이 다른가?

이 문제처럼 **섬 하나를 전부 방문하고 합산하는 목적**이면 BFS와 DFS 모두 정답이다. 방문 처리도 영구적으로 유지한다.

```java
int dfsSum(int y, int x) {
    visited[y][x] = true;
    int sum = map[y][x];

    for (int[] d : dir) {
        int ny = y + d[0];
        int nx = x + d[1];
        if (ny < 0 || ny >= height || nx < 0 || nx >= width) continue;
        if (visited[ny][nx] || map[ny][nx] == 0) continue;
        sum += dfsSum(ny, nx);
    }
    return sum;
}
```

## DFS에서 되돌리기(백트래킹)가 필요한 때

`최장 경로`처럼 **현재 경로에서만 중복을 막고, 다른 경로에서는 같은 칸을 다시 시도해야 하는 문제**에서는 재귀 호출 뒤 방문을 원복한다.

```java
void dfsLongest(int y, int x, int length) {
    visited[y][x] = true;
    maxLength = Math.max(maxLength, length); // 현재 경로도 답 후보

    for (int[] d : dir) {
        int ny = y + d[0];
        int nx = x + d[1];
        if (ny < 0 || ny >= height || nx < 0 || nx >= width) continue;
        if (visited[ny][nx] || map[ny][nx] == 0) continue;
        dfsLongest(ny, nx, length + 1);
    }

    visited[y][x] = false; // 다른 경로를 위해 현재 칸 되돌리기
}
```

| 목적 | 방문 배열 처리 |
|---|---|
| 섬 개수/합, 연결 요소 | 방문 후 **원복하지 않음** |
| BFS 최단거리 | 큐에 넣는 순간 방문 후 **원복하지 않음** |
| DFS 최장 단순 경로 | 재귀 진입 시 방문 → 재귀 종료 시 **원복** |

> 핵심: DFS 자체가 항상 원복하는 것이 아니다. **“다른 경로에서도 이 칸을 다시 써봐야 하는가?”**가 원복 여부를 결정한다.

## 시간복잡도

각 칸은 최대 한 번 방문하고, 칸마다 4방향만 확인한다.

- 시간: `O(N × M)`
- 공간: `O(N × M)` (`visited`, `map`, BFS 큐)

## 제출 코드

[Solution.java](./Solution.java)
