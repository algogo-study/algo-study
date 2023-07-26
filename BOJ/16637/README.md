# Review

- 단순 괄호에 포함되있는 지 아닌지를 계산 하면 어림잡아 계산해도 2^19 로 시간이 충분하다고 생각
  - 따라서 완전 탐색
- 8*3+5 와 같은 식에서 괄호를 씌울 수 있는 방법은 2가지
  - (8 * 3) + 5
    - 이전의 결과값을 현재의 값(3)과 계산하고 index 를 2 추가함
  - 8 * (3 + 5)
      - 현재의 값(3) 과 다음 숫자(5) 를 연산하고 그 결과(8)를 이전 숫자(8) 와 연산함
      - index 를 4 추가함
- 재귀를 사용하여 여러 답을 구하고 최대값을 구함