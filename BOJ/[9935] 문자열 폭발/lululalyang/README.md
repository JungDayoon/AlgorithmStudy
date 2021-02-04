# [BOJ]/[9935] 문자열 폭발

## *- Stack, String -*

**Func**

* `String stringBomb(String S, String BOMB)`

  : 입력받은 문자열 `S`에서 폭발 문자열인 `BOMB`를 제거한 나머지 문자열을 return한다

  * 기존 문자열 `S`에 `BOMB`가 포함되어 있지 않다면 `S`그대로 return

  * Stack을 두 개 선언해준다.

    * `Stack<String> stack` : `S`를 담을 stack
    * `Stack<String> chk` : 폭발 문자열을 확인할 stack

  * `S`의 문자를 하나씩 `stack`에 넣어가면서 만약 그 문자가 폭발문자열의 마지막문자인  `bomb[bomb.length-1]`과 같으면 폭발문자열을 확인한다.

    * 폭발 문자열의 길이만큼 `stack`에서 `pop()`하면서 그 문자가 폭발문자열의 문자와 같은지 확인한다. 
    * 그 길이만큼 일치한다면 `chk.clear()`를 통해 폭발문자열을 버린다.
    * 일치하지 않는 문자가 있거나, `stack`이 비게 된다면 `chk`에 넣었던 문자들을 다시 `pop()`하면서 `stack`에 `add()`한다

  * `S`를 다 스캔한 후에, `stack`이 비었다면 `"FRULA"`를 return

  * 그렇지 않다면 `stack`에 있는 문자를 `pop()`하면서 `Stringbuilder sb`에 `append()`해주고 그 `sb`의 역순인 `sb.reverse()`를 return

    ( `stack`에서 `pop()`한 순서이기 때문에 결과의 역순이 `sb`에 담겨있다)

## :speaking_head:

* 처음에 폭발문자열을 지우고 다시 index처리해주는 것이 복잡할 거 같아서 Stack 2개를 써서 한 stack에서 담고 다른 stack으로 옮기고 .. 폭발 문자열의 첫 번째 문자와 동일한지 확인하는 식으로 풀었는데 출력초과 4번에 ,, 틀렸습니다 1번이나 난리였다.
* 지혜의 방법을 참고하여서 `s`의 문자를 확인할 때 폭발 문자열의 마지막 문자와 동일한지 확인하였는데 한 번에 통과되었다 .. 도저히 안풀릴 때는 다른 방법을 생각해볼 수 있도록 하자 ..