# 백준 17281번 : ⚾️

## Algorithm

DFS, Permutation

## Description

1. calculate_score(member_list) : 현재 선수 순서인 member_list 대로 야구를 진행했을 때의 점수를 계산

  + N루을 의미하는 b1, b2, b3 : 선수가 있음을 의미할 때는 1
  
  + 루타에 따라 b1, b2, b3 를 적절히 더해주면 그 때 상황에 따른 점수를 계산할 수 있다.
  
  
2. permutation(arr, start, end) : permutation 을 DFS 로 직접 구현한 코드, 구현은 했지만 이번 문제에서는 사용은 하지 않았다.

3. main : 1~8 의 숫자를 permutation 해서 선수 순열을 만든 후에 calculate_score를 호출해서 max 점수를 찾는다.

  + 0 번은 3번째에 넣어준다.
  
## Review

처음에 DFS 로 permutation 를 구현하고, 루상에 주자가 나간 경우를 base 리스트를 사용해서 사용했는데 결과는 맞게 나왔으나 시간초과가 떴다.

찾아보니까 최대한 시간을 줄이려면 리스트 선언 같은거도 다 줄여야 한다고 해서 base리스트를 각각 인트형 변수를 만들어서 사용했고, permutation도 있는거 사용했더니(이게 영향을 준지는 모르겠음) 통과했다.

인트형으로 선언한게 제일 큰 것 같음.. ^^ 무슨차인지,, 돌아가면 된거자나.. 
