# boj16926

### 접근방법

- 배열의 요소가 열 방향으로 이동하지 않는다면 행 방향으로 이동을 하는 요소로 생각했습니다.
- 열 방향으로 이동하는 요소들의 패턴은, 역삼각형과 삼각형이 엇갈린 모양으로 있습니다.
- 역삼각형은 요소가 왼쪽으로 이동하고, 삼각형은 요소가 오른쪽으로 이동합니다.
- 행 방향으로 이동하는 요소들의 패턴은 오른쪽 꼭짓점 삼각형과 왼쪽 꼭짓점 삼각형 모양으로 있고,
- 오른쪽 꼭짓점 삼각형은 요소가 아래로 이동하고, 왼쪽 꼭짓점 삼각형은 요소가 위로 이동합니다.
- 패턴을 그림으로 설명한 예시입니다.

![KakaoTalk_Photo_2023-07-26-20-56-21.jpeg](boj16926%20c55a846ee6e645949c305bcb431c2038/KakaoTalk_Photo_2023-07-26-20-56-21.jpeg)