# [SWEA 1949] [모의 SW 역량테스트] 등산로 조성

접근법

- height 큐를 이용해서 가장 높은 봉우리 위치 저장
- 해당 위치부터 시작해서 재귀로 주변을 탐색
- 주변에 탐색 중, 해당 위치의 값보다 작은 위치가 존재하면 해당 위치로 이동
- 만약, 해당 위치의 값보다 크거나 같다면 공사를 진행
- isValidConstruction을 사용하여 공사 여부를 체크
- 공사를 진행하지 않은 상태라면 최대 K깊이 만큼 지형을 깎을 수 있으므로 1부터 K만큼 땅을 파보며 탐색 진행