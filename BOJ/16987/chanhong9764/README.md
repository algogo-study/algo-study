# [BOJ 16987] 계란으로 계란치기

접근법

- 계란을 치는 과정이 반복되므로 재귀 형식으로 구현
- 계란을 들어서 깨지지 않은 모든 계란을 쳐보기 위해 백트래킹 구현
- 깨지지 않은 다른 계란을 쳤을 때, 각 계란의 내구도를 변경
- 이후 손에 있는 계란 오른쪽에 위치한 계란으로 이동
- 손에 든 계란이 깨졌을 경우, 오른쪽에 위치한 계란으로 이동
- 깨지지 않은 다른 계란이 없을 경우, 오른쪽에 위치한 계란으로 이동