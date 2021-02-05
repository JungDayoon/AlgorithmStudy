# [BOJ]/[1062] 가르침

## *- Backtracking -*

**Main**

* `ArrayList<String> Remainder` : 필수 글자인 `a, n, t, i, c`를 제외한 나머지 글자를 담고있는 리스트

**Func**

* `int ComputeMaxCnt(int N, int K)`

  : 읽을 수 있는 단어의 최대 개수를 리턴한다

  * `K < 5` 이면 필수 단어인 `a, n, t, i, c`를 모름 

    -> 아무 단어도 읽을 수 없다 -> `0`을 리턴

  * `K == 26` 이면 알파벳 전체 아는 것

    -> 주어진 단어 다 읽을 수 있다 -> 단어 개수인 `N`을 리턴

  * 그 외의 경우에는 `Backtracking(21, _K,  0, tmp)`를 호출하여 최대 개수를 구한다.

    * `21` : 필수 글자를 제외한 나머지 글자 개수
    * `_K = K - 5` : 가르칠 수 있는 글자 개수 `K`에서 필수글자를 제외한 개수
    * `tmp` : 필수 글자 `a, n, t, i, c`를 담고있는 리스트

* `void Backtracking(int N, int r, int start, ArrayList<String> tmp)`

  : 백트래킹을 통해 가르칠 수 있는 글자를 `tmp`에 담아 `CheckReadable(tmp)`를 호출

* `void CheckReadable(ArrayList<String> tmp)`

  : 주어진 단어 `words`를 하나씩 스캔하면서 가르칠 수 있는 글자 `tmp`에 존재하는지 확인한 후 읽을 수 있는 단어라면 `cnt++`. 이 `cnt`값으로 `MaxCnt`를 갱신

  * 접두사/접미사는 `ComputeMaxCnt()`를 통해 이미 확인한 후 이므로, `words[i]`의 `4`번째 글자부터 `words[i].length-5`번째 글자까지만 확인한다





