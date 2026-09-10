# NeetCode / LeetCode 1. Two Sum

## 문제 유형

- 난이도: Easy
- 유형: HashMap, 배열
- NeetCode: <https://neetcode.io/problems/two-integer-sum>
- LeetCode: <https://leetcode.com/problems/two-sum/>
- 핵심: 현재 숫자의 짝인 `target - nums[i]`가 이전에 나왔는지 HashMap으로 확인한다.

## 먼저 풀었던 방식: 완전탐색

처음 풀이는 모든 쌍을 직접 확인했다.

```java
for (int i = 0; i < nums.length; i++) {
    for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
            return new int[] { i, j };
        }
    }
}
```

이 방식은 이해하기 쉽지만, 모든 쌍을 보므로 시간복잡도는 `O(N^2)`이다.

## HashMap 접근

질문을 이렇게 바꾼다.

```text
현재 값 cur을 봤을 때,
이전에 need = target - cur 값이 나온 적 있는가?
```

HashMap에는 다음 정보를 저장한다.

| key | value |
|---|---|
| 숫자 값 | 그 숫자의 인덱스 |

예를 들어 `nums = [2, 7, 11, 15]`, `target = 9`이면:

| i | cur | need | map 상태 | 판단 |
|---:|---:|---:|---|---|
| 0 | 2 | 7 | `{}` | 7 없음 → `2:0` 저장 |
| 1 | 7 | 2 | `{2:0}` | 2 있음 → `[0, 1]` 반환 |

## 핵심 코드

```java
HashMap<Integer, Integer> map = new HashMap<>();

for (int i = 0; i < nums.length; i++) {
    int cur = nums[i];
    int need = target - cur;

    if (map.containsKey(need)) {
        return new int[] { map.get(need), i };
    }

    map.put(cur, i);
}
```

## 왜 먼저 검사하고 나중에 저장하는가?

현재 숫자를 먼저 저장하면 자기 자신을 두 번 쓰는 문제가 생길 수 있다.

예시:

```text
nums = [3]
target = 6
```

`3`을 먼저 넣고 `need = 3`을 찾으면 자기 자신을 짝으로 착각할 수 있다.

그래서 순서는 항상:

```text
1. need가 이전에 있었는지 확인
2. 없으면 현재 값 cur을 저장
```

이다.

## 오늘 배운 연결점

사탕 가방에서 `mid`가 정답 후보였던 것처럼, Two Sum에서는 `need`가 짝 후보이다.

```text
사탕 가방: mid개 만들 수 있나?
Two Sum: need가 이전에 나왔나?
```

둘 다 문제를 바로 풀기보다 **확인해야 하는 질문을 먼저 만드는 것**이 중요하다.

## 시간복잡도

| 풀이 | 시간복잡도 | 공간복잡도 |
|---|---:|---:|
| 이중 for문 | `O(N^2)` | `O(1)` |
| HashMap | `O(N)` | `O(N)` |

## 주의할 점

- `HashMap<Integer, Integer>`에서 key는 숫자, value는 인덱스이다.
- `containsKey(need)`로 먼저 확인한다.
- 정답을 찾으면 `map.get(need)`와 현재 인덱스 `i`를 반환한다.
- LeetCode는 정답이 반드시 하나 있다고 가정하므로 마지막 반환은 실제로 거의 도달하지 않는다.
