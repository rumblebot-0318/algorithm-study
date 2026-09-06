# 30일 알고리즘 워밍업 커리큘럼

NeetCode 쉬운 문제와 SWEA 기초 문제를 섞어서, 개념을 다시 데우는 30일 과정입니다.

## 운영 원칙

- 언어: Java
- 매일 1문제 중심, 여유 있으면 복습 1개 추가
- 목표: 어려운 A형 문제로 바로 가지 않고, 입력 해석 → 반복문 구조 → check 함수 → 자료구조 감각을 다시 만든다.
- 문제 풀이 후 반드시 정리할 것:
  - 입력에서 변수 의미
  - 핵심 함수 이름
  - 손으로 1회 추적
  - 실수한 포인트
  - 시간복잡도

## 30일 표

| Day | 플랫폼 | 문제 | 핵심 개념 | 오늘의 목표 |
|---:|---|---|---|---|
| 1 | SWEA | 11446 사탕 가방 | 파라메트릭 서치 | `mid = 가방 개수 후보` 감각 고정 |
| 2 | NeetCode | Contains Duplicate | HashSet | 중복 확인을 O(N)으로 줄이기 |
| 3 | SWEA | 1204 최빈수 구하기 | 카운팅 배열 | 값 범위가 작을 때 배열 인덱스로 세기 |
| 4 | NeetCode | Valid Anagram | 빈도 배열/HashMap | 문자열 빈도 비교 |
| 5 | SWEA | 1954 달팽이 숫자 | 구현, 방향 전환 | `canMove()`로 이동 조건 분리 |
| 6 | NeetCode | Two Sum | HashMap | 보수값 찾기 패턴 |
| 7 | 복습 | Week 1 Review | 입력/배열/Hash | 틀린 문제 다시 손추적 |
| 8 | SWEA | 2001 파리 퇴치 | 2D 완전탐색 | 모든 시작 좌표 → 함수로 합산 |
| 9 | NeetCode | Best Time to Buy and Sell Stock | 1-pass | 최소값 유지하며 최대 이익 갱신 |
| 10 | NeetCode | Valid Palindrome | Two Pointer | 양끝 포인터와 조건 스킵 |
| 11 | SWEA | 4839 이진탐색 | Binary Search | left/right/mid 이동 원리 |
| 12 | SWEA | 3074 입국심사 | 파라메트릭 서치 | `mid = 시간`, 최소 true 찾기 |
| 13 | NeetCode | Binary Search | Binary Search | 기본 이분탐색 코드 고정 |
| 14 | 복습 | Week 2 Review | Two Pointer/Binary Search | lower/upper 방향 정리 |
| 15 | NeetCode | Search Insert Position | Lower Bound | 첫 번째 `>= target` 위치 찾기 |
| 16 | NeetCode | Koko Eating Bananas | Parametric Search | `mid = 속도`, 최소 가능값 찾기 |
| 17 | SWEA | 1974 스도쿠 검증 | 검증 구현 | 행/열/박스 check 함수 분리 |
| 18 | NeetCode | Valid Parentheses | Stack | 열린 괄호 저장, 닫힐 때 검증 |
| 19 | SWEA | 1225 암호생성기 | Queue | poll/offer 시뮬레이션 |
| 20 | NeetCode | Min Stack | Stack 설계 | 상태를 함께 저장하는 자료구조 |
| 21 | 복습 | Week 3 Review | Stack/Queue/Parametric | check 함수 3개 다시 작성 |
| 22 | NeetCode | Flood Fill | DFS/BFS | 2D 방문 처리 기본 |
| 23 | SWEA | 1210 Ladder1 | 2D 시뮬레이션 | 방향 우선순위와 visited 감각 |
| 24 | NeetCode | Number of Islands | BFS/DFS | 격자 컴포넌트 탐색 |
| 25 | SWEA | 2805 농작물 수확하기 | 2D 규칙 구현 | 행별 범위 계산 |
| 26 | NeetCode | Maximum Depth of Binary Tree | DFS | 재귀 반환값 이해 |
| 27 | NeetCode | Invert Binary Tree | Tree DFS | 노드 단위 처리 |
| 28 | 복습 | Week 4 Review | DFS/BFS/Tree | 방문 배열과 재귀 구조 정리 |
| 29 | SWEA | 26793 게으름뱅이 | 파라메트릭+그리디 | `mid = 놀 수 있는 날짜`, 데드라인 정렬 |
| 30 | SWEA | 3813 그래도 수명이 절반이 되어서는... | 파라메트릭+그리디 | `mid = 허용 wear level`, 연속 구간 배치 |

## 난이도 흐름

```text
배열/입력 해석
→ Hash / Counting
→ 2D 완전탐색 / 구현
→ Binary Search
→ Parametric Search
→ Stack / Queue
→ DFS / BFS
→ Parametric Search + Greedy
```

## 파라메트릭 서치 복습 문장

```text
1. mid를 정답 후보로 둔다.
2. check(mid)를 true/false 질문으로 만든다.
3. 가능 패턴이 한쪽으로 몰리는지 본다.
4. 최대 true인지, 최소 true인지에 따라 방향을 정한다.
```

| 목표 | 가능 패턴 | true일 때 |
|---|---|---|
| 최소값 | `F F F T T T` | `ans = mid; right = mid - 1` |
| 최대값 | `T T T F F F` | `ans = mid; left = mid + 1` |

## 매일 기록 템플릿

```md
## Day N. 문제명

### 입력 해석

### 핵심 아이디어

### 손추적

### 코드 구조

### 실수/주의점

### 시간복잡도
```
