# swea1873

### 구현방법

- 맵을 char형 2차원 배열로 담고, 현재 위치 인덱스 정보도 다른 변수에 저장하였습니다.
- ‘U’, ‘D’, ‘L’, ‘R’ 이 인풋되면, 먼저 전차의 방향을 각각 요구에 맞게 바꿔줍니다.
- 이후에 전차가 바라보는 방향대로 생각해서, 앞에 잔디가 있으면 맵에서 현재 위치의 값을 잔디로 바꾸고 →
    
    앞쪽 인덱스로 현재 인덱스를 수정 → 맵에서 현재 인덱스의 값을 전차로 바꾸는 과정을 거칩니다.
    
- 만약 ‘S’가 입력된다면, 바라보는 방향대로 생각해서 ‘#’ 나 ‘*’를 만날때 까지, 혹은 해당 방향의 인덱스 끝까지 탐색하여 만약 ‘*’인 경우 ‘.’로 바꿔줍니다.