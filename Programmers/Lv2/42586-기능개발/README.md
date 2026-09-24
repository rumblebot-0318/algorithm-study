# 프로그래머스 Lv.2 42586. 기능개발

## 문제 유형

- 난이도: Lv.2
- 유형: 구현, 배열, 배포 묶음
- 링크: <https://school.programmers.co.kr/learn/courses/30/lessons/42586>
- 핵심: 앞 기능이 배포될 때까지 뒤 기능은 먼저 끝나도 기다린다.

## 핵심 생각: 줄 서서 같이 배포하기

각 기능이 완료되는 날짜를 먼저 구한다.

```java
int left = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
```

`currentDays`는 **현재 배포 묶음의 맨 앞 기능이 완료되는 날짜**다.

- `left <= currentDays`: 현재 기능은 먼저 끝났거나 같은 날 끝난다. 하지만 앞 기능이 끝날 때까지 기다려야 하므로 같은 묶음이다. `count++`
- `left > currentDays`: 현재 기능은 기존 묶음보다 늦게 끝난다. 기존 묶음을 결과에 넣고 새 묶음을 시작한다.

## 예시 추적

`progresses = [93, 30, 55]`, `speeds = [1, 30, 5]`의 완료일은 `[7, 3, 9]`이다.

| 기능 | 완료일 | currentDays | 처리 | count |
|---:|---:|---:|---|---:|
| 1 | 7 | 0 | 첫 배포 묶음 시작 | 1 |
| 2 | 3 | 7 | 3일에 끝나도 7일까지 기다림 | 2 |
| 3 | 9 | 7 | 기존 `2`를 기록하고 새 묶음 시작 | 1 |
| 종료 | - | 9 | 마지막 `1` 기록 | - |

결과는 `[2, 1]`이다.

## 제출 코드

```java
import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> ans = new LinkedList<>();
        int currentDays = 0;
        int count = 0;

        for (int i = 0; i < progresses.length; i++) {
            int left = (100 - progresses[i] + speeds[i] - 1) / speeds[i];

            if (left <= currentDays) {
                count++;
            } else {
                if (count > 0) {
                    ans.add(count);
                }
                currentDays = left;
                count = 1;
            }
        }

        ans.add(count);

        int[] answer = new int[ans.size()];
        int idx = 0;
        for (int value : ans) {
            answer[idx++] = value;
        }

        return answer;
    }
}
```

## 불변식

> `currentDays`는 아직 결과에 넣지 않은 배포 묶음의 기준 완료일이고, `count`는 그 묶음에 포함된 기능 수다.

## 시간복잡도

| 시간 | 공간 |
|---:|---:|
| `O(N)` | `O(N)` |

## 주의할 점

- 완료일 계산은 나눗셈 결과를 올림해야 한다.
  - `(남은 작업량 + 하루 작업량 - 1) / 하루 작업량`
- 마지막 배포 묶음은 반복문 안에서 닫히지 않을 수 있으므로, 반복문 뒤에 `ans.add(count)`가 필요하다.
- `left`가 더 작아도 순서상 앞 기능을 지나칠 수 없으므로, 같은 묶음으로 처리한다.
