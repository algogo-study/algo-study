# [BOJ 20187] 종이접기

접근법

- 종이를 다 접은 후, 펼칠 때 구멍 값을 정해주는 식으로 구현
- getHole 함수는 접었을 때, 반대편에 구멍이 생기는 값이 반환
- recursive 함수의 k는 명령한 개수, rowStart, rowCol은 접은 종이의 행 시작 점과 끝점, colStart, colEnd도 접은 종이의 열 시작 점과 끝점
- 지속적으로 디버깅을 해보며 패턴을 파악해 2중 for문으로 각 종이에 대한 구멍의 값을 정해줌