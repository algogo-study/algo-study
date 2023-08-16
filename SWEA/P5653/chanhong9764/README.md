# [SWEA 5653] [모의 SW 역량테스트] 줄기세포배양

접근법
- 세포가 점점 번식하므로, 배열의 크기를 임의로 큰 값인 [1000][1000]으로 설정
- 배열의 가장 중심에 초기 상태의 세포를 위치시켜 배양 용기를 넘어가는 것을 방지
- inActiveQ와 activeQ를 생성하여 비활성화된 세포와 활성화된 세포를 구분
- 초기 세포들을 inActiveQ에 삽입
- 비활성화 -> 활성화 -> 번식 -> 죽음 과정을 반복하므로 재귀로 구현
- 재귀 과정 중, inactiveQ와 activeQ의 변화가 있으므로 시간이 지난 직후의 상태들만 탐색하기 위해 size설정
- 시간이 1초 증가할 때마다, inActiveQ에 있는 세포들의 inActiveTime을 1씩 감소
- 만약, inActiveTime이 0이 될 경우, activeQ로 세포 이동
- activeQ에 있는 세포들을 가져와 activeTime 1씩 감소
- 번식되지 않은 세포일 경우 번식할 좌표의 값을 temp에 저장
- activeTime이 아직 남아있을 경우 다시 activeQ에 삽입
- temp에 있는 세포들을 확인하여 번식을 진행하는데, 번식을 할 위치에 생명력이 낮은 세포가 번식될 예정이면 해당 세포를 제거 후 삽입
- 시간이 K가 될 때, inactiveQ와 activeQ의 크기 반환