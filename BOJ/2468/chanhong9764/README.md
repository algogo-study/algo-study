# [BOJ 2468] 안전영역

접근법

- 가장 낮은 물 높이와 가장 높은 물 높이를 구함
- 모든 물 높이에서 비교해 봐야한다고 생각해서 완탐으로 구현
- 아무 지역도 물에 잠기지 않는 경우도 있으니 가장 낮은 물 높이의 -1부터 탐색을 시작하여 가장 높은 물 높이 -1까지 탐색
- 각 영역들의 대해서 DFS로 방문 처리 후 영역 COUNT +1 증가
- 각 높이의 영역 개수와 비교하여 최대값 추출