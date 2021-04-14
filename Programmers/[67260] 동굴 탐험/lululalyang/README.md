# [Programmers]/[67260] 동굴탐험

## - Stack -

### 전역변수

* `ArrayList<Integer>[] adj` : 인접리스트

### solution

* `boolean[] visited` : 방문 여부
* `int[] before` : 이전에 방문해야하는 노드 번호
* `int[] after` : 이후에 방문해야하는 노드 번호

1. `path`배열에 따라 인접리스트 `adj`를 완성한다.

2. `order`배열에 따라 `before`배열을 완성한다.

   * `order[i][0] = a`, `order[i][1] = b` : `b`번 방문 전 `a`번을 방문해야한다는 의미로,
   * `before[order[i][1]] = order[i][0]` : `before[b] = a` 는 `b`번 방문전 `a`를 방문해야한다는 것을 의미

   **:star: `before`배열 완성 후, `before[0] = 0`이 아니라면, `false`를 리턴.**

   ​	 `0`번 방이 유일한 입구므로, `0`번 이전에 방문해야할 방이 있다면 모든 방을 방문하는 것은 불가능하다.

3. `0`번방 방문여부를 `true`로 바꿔주고(`visited[0] = true` ), `stack`에 `0`번방과 연결되어있는 방을 추가한다.

4. `stack`이 빌 때까지 아래의 과정을 반복한다.

   * `int now` : `stack`에서 `pop()`한 원소.

   1. `now`가 방문했던 방이면 `continue`.

   2. 방문하지 않았지만, `now`이전에 방문해야하는 방을 방문하지 않았다면, 

      '이전에 방문해야하는 방' 이후에 `now`를 방문할 수 있도록 `after`에 저장하고 `continue`.

      `after[before[now]] = now;` (`before[now]` : `now`이전에 방문해야하는 방)

   3. 두 경우가 아니라면, 현재 방의 방문여부를 `true`로 바꿔준 후, `now`와 연결된 방 중 방문하지 않은 방을 `stack`에 추가한다.
   4. `now`다음 방문해야하는 방인 `after[now]`도 `stack`에 추가해준다.

5. `stack`으로 확인을 끝낸 후에 모든 방의 방문여부를 확인한다.

   * 방문하지 못한 방이 있다면 `false`를 리턴.
   * 모두 방문했다면 `true`를 리턴.

</br>

## :speaking_head:

너모 어렵당. .

아무리 봐도 감을 못잡겠어서 지혜의 방법을 참고해서 `dfs`를 이용해서 풀었는데, `n`크기가 커서 stack overflow때문에 런타임 오류가 있었다. 그래도 그래프 내의 사이클을 확인하는 방법을 익힐 수 있었다.

> **:star: 그래프 내의 Cycle 확인**
>
> * `boolean[] visited` : 이번 경로에서 방문했는지의 여부
> * `boolean[] check` : 이미 자식노드를 다 확인했다면 `true`, 그렇지 않으면 `false`
>
> 위 두가지 배열을 사용하여 재귀를 통해 cycle여부를 확인한다.
>
> 1. 재귀 함수를 시작할 때, 아래의 두 조건을 확인한다.
>
>    ```java
>    if(visited[v])	return false;
>    ```
>
>    `visited`는 확인하고 있는 경로에 포함되는지의 여부이므로, `true`라면 이미 이번 경로에 포함된 노드로 사이클이 존재하는 것! => `false`를 리턴한다.
>
>    ```java
>    if(check[v])	return true;
>    ```
>
>    `check`는 해당 노드의 자식노드를 다 확인했다면 `true`를 가지므로, 확인을 반복하지 않기 위해서 바로 `true`를 리턴한다.
>
> 2. 위 두 경우에 포함되지 않는다면, `visited`와 `check` 모두를 `true`로 바꿔주고, `v`의 자식노드를 확인한다.
>
>    * 자식노드로 재귀함수를 처리했을 때, 리턴값이 `false`면 해당 자식노드를 사용하는 경로가 cycle을 만든다는 의미이므로 `false`를 리턴.
>    * 자식노드 모두가 리턴값이 `true`로 모두 스캔했다면, 현재 노드를 경로에서 제외시킨다. 즉 `visited[v] = false`
>
> 3. 모든 과정을 다 끝냈다면 `true`를 리턴

결국 다른 사람의 풀이를 참고해서 `stack`을 사용해서 풀었다. 

> **:star: Java - Stack의 push() vs add()**
>
> * `push()`는 stack에서 제공하는 메소드이다. 리턴값은 `<E>`
> * `add()`는 List에서 제공하는 메소드이다. 리턴값은 `Boolean`

> **:heavy_plus_sign: Java - Queue의 메소드 차이점**
>
> * 추가
>   * `add()` : 데이터를 추가할 때, 이미 큐가 꽉 차있다면 **예외 발생**
>   * `offer()` : 추가 실패를 의미하는 **`false`를 리턴**
> * 삭제
>   * `remove()` : 예외 발생
>   * `poll()` : 값 리턴
> * 검사
>   * `element()` : 예외 발생
>   * `peek()` : 값 리턴