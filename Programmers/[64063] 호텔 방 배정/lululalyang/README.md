# [Programmers]/[64063] 호텔방 배정

## *- Recursion -*

### solution

* `Map<Long, Long> bigger` 

  : `<a, b>` = `a`번 호텔방 보다 큰 번호인 비어있는 방 중 가장 번호가 작은 방 `b`번

1. 만약 손님이 원하는 방 번호 `guest`번방이 빈 방이라면,

   * 그 방을 배정한다. (`answer[i] = guest`, `i`번째 손님)

   * `guest`번보다 큰 번호의 비어있는 방을 구해 `bigger`에 넣어준다.

     ```java
     long next = dfs(bigger, guest+1); // next가 guest번 보다 큰 번호의 빈 방 
     bigger.put(guest, next);
     ```

2. 빈 방이 아니라면,

   * `guest`번 방보다 큰 번호의 비어있는 방을 배정한다.

     ```java
     answer[i] = dfs(bigger, bigger.get(guest));
     ```

   * 배정한 방보다 큰 번호의 비어있는 방을 구해 `bigger`에 넣어준다.

   * 앞서 구한 큰 번호의 빈 방으로 `guest`번 방보다 큰 번호 방을 갱신한다.

     ```java
     long next = dfs(bigger, answer[i]+1); // 배정한 방보다 큰 번호의 빈 방
     bigger.replace(guest, next); // guest번방도 위에서 구한 큰 번호방으로 갱신
     bigger.put(answer[i], next); 
     ```

* `long dfs(Map<Long, Long> bigger, long guest)`

  : `guest`방보다 크거나 같은 번호의 빈 방 번호를 리턴한다.

  1. `guest`번 방이 빈 방이라면, `guest`를 리턴.

     ```java
     if(!bigger.containsKey(guest)) // bigger에 guest가 없으면 빈 방이다.
     ```

  2. 빈 방이 아니라면, `guest`보다 큰 번방을 찾아 `guest`의 값을 갱신하고, 그 값을 리턴한다.

     ```java
     bigger.replace(guest, dfs(bigger, bigger.get(guest))); // bigger.get(guest)가 guest보다 큰 번호의 빈 방
     return bigger.get(guest);
     ```

</br>

## :speaking_head:

이 때까지 `Map`에 `.replace()`메소드가 있는지 몰랐다 !! 매번 `remove()`하고 변경값으로 다시 `put()`해주었었는데 `replace()`로 연산을 줄일 수 있었다.

또, 처음에는 빈 방도 `bigger`에 자기 자신으로 다 넣어줬는데 *시간초과* 가 나서, 보다 큰 방이 생길 때에만 추가해주었더니 통과했다.