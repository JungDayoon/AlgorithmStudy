# [Programmers]/[64064] 불량 사용자

## *- simulation -*

### solution

* `Map<String, ArrayList<String>> bannedID`

  : <제재 아이디, 제재 아이디에 해당되는 사용자 아이디 리스트>

1. `user_id`배열과 `bannedID`배열을 스캔하면서, `bannedID`를 완성시킨다.

2. 각 제재 아이디에 해당되는 사용자 아이디의 모든 조합의 개수를 구한다.

   `int CaseOfNum(int M, String[] banned_id, int idx, Map<String, ArrayList<String>> bannedID, ArrayList<String> idComb)`

   : 백트래킹을 이용하여 현재 확인하려는 제재아이디인 `banned_id[idx]`에 해당하는 사용자 아이디 리스트 중 하나씩 `idComb`에 넣고 재귀를 호출한다.

   * 모든 제재 아이디를 확인했다면,

     1. `idComb`를 오름차순 정렬 후, 하나의 String으로 만든다. 

     2. 그 String으로 중복 체크를 한 후, 없었던 조합이라면 `return 1`

        이미 확인한 조합이라면 `return 0`

</br>

## :speaking_head:

빙빙 둘러서 푼 문제 ㅜ 너무 어렵게 생각하지 말자

처음에 Trie를 써야하는 줄 알고 사용해서 풀었는데 계속 틀렸다 ..

그냥 간단하게 `for`문을 이용해서 가능한 사용자 아이디를 구했더니 통과하였다.