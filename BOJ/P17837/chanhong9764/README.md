# [BOJ 17837] 새로운 게임 2

접근법

- 2차원 ArrayDeque를 만들어서 시뮬레이션 구현
- 선택한 말의 다음 움직일 곳이 흰색이면 현재 있는 곳의 ArrayDeque에서 선택한 말이 나올 떄까지 pop
- 나온다면 다음 움직일 곳의 ArrayDeque에 삽입
- 나오지 않는 다면 현재 위치의 ArrayDeque에 삽입
- 선택한 말의 다음 움직일 곳이 빨간색이면 다음 움직일 곳의 ArrayDeque에 현재 있는 곳의 ArrayDeque의 가장 뒤에서부터 add하여 reverse를 구현
- 다음 움직일 곳이 파란색이라면 방향을 변경한 후, 다시 움직일 곳을 체크
- 변경한 위치도 파란색이거나 범위를 나간다면 중단
- 아니면 재귀를 통해 다시 진행