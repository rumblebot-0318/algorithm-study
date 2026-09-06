# SWEA 11446. 사탕 가방

## 문제 유형

- 난이도: D3
- 유형: 파라메트릭 서치, 이분 탐색
- 핵심: `mid`를 **가방 개수 후보**로 두고, 그 개수만큼 같은 구성의 가방을 만들 수 있는지 확인한다.

## 입력 해석

```text
T
N M
A1 A2 ... AN
```

| 값 | 의미 |
|---|---|
| `N` | 사탕 종류 개수 |
| `M` | 가방 하나에 들어가야 하는 사탕 총 개수 |
| `A[i]` | i번째 사탕의 총 개수 |

예를 들어:

```text
3 3
5 6 7
```

은 다음 뜻이다.

```text
사탕 종류는 3개
가방 하나에는 사탕 3개가 필요
1번 사탕 5개, 2번 사탕 6개, 3번 사탕 7개 보유
```

## 오늘 배운 핵심

파라메트릭 서치에서 `mid`는 보통 배열 인덱스가 아니라 **정답 후보**다.

이 문제에서는:

```text
mid = 만들 수 있다고 가정하는 가방 개수
```

따라서 `check(mid)`는 다음 질문이다.

```text
가방을 mid개 만들 수 있나?
```

## 가능 여부 확인

가방을 `mid`개 만들려면 각 사탕 종류를 `mid`개의 가방에 똑같이 나누어야 한다.

```java
candies[i] / mid
```

의 의미는:

```text
i번 사탕을 mid개 가방에 나눴을 때, 가방 하나에 넣을 수 있는 개수
```

예시:

```text
candies = [5, 6, 7]
M = 3
```

| 가방 후보 `mid` | 계산 | 가방 하나당 가능 개수 | 가능 여부 |
|---:|---|---:|---|
| 5 | `5/5 + 6/5 + 7/5` | `1 + 1 + 1 = 3` | 가능 |
| 6 | `5/6 + 6/6 + 7/6` | `0 + 1 + 1 = 2` | 불가능 |

`M = 3`이므로 가방 하나당 3개 이상 넣을 수 있으면 가능하다.

## 이분 탐색 방향

이 문제는 가능한 **최대 가방 개수**를 찾는다.

```text
가방 수:  1  2  3  4  5  6  7
가능?:   T  T  T  T  T  F  F
```

마지막 `T`를 찾아야 하므로:

```java
if (canMake(mid)) {
    answer = mid;
    left = mid + 1;
} else {
    right = mid - 1;
}
```

## Lower Bound / Upper Bound 감각

| 목표 | 가능 패턴 | 찾는 값 | `check(mid) == true`일 때 |
|---|---|---|---|
| 최소값 찾기 | `F F F T T T` | 첫 번째 `T` | `answer = mid`, `right = mid - 1` |
| 최대값 찾기 | `T T T F F F` | 마지막 `T` | `answer = mid`, `left = mid + 1` |

외우기 좋은 문장:

```text
가능하면 일단 저장한다.
최대 찾기면 오른쪽으로 간다.
최소 찾기면 왼쪽으로 간다.
```

## 핵심 코드

```java
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
```

## 제출 시 주의

SWEA 제출 시 로컬 입력 파일 설정은 제거한다.

```java
// System.setIn(new FileInputStream("./input.txt"));
```

이 줄이 살아 있으면 SWEA 서버에는 `input.txt`가 없어서 `FileNotFoundException` 런타임 에러가 날 수 있다.

또한 사탕 개수와 계산 누적값은 커질 수 있으므로 `long`을 사용한다.

## 시간복잡도

| 부분 | 복잡도 |
|---|---:|
| `canMake(mid)` | `O(N)` |
| 이분 탐색 | `O(log max(A[i]))` |
| 전체 | `O(N log max(A[i]))` |
