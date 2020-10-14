Simulation, BFS

1. 터널의 정보를 미리 딕셔너리에 담아두어서 확인한다.
2. queue를 사용해서 터널이 연결되어 있으면 queue에 넣고 queue가 빌 때까지 진행한다.(BFS)
4. visit 여부를 체크하는 `visit = [[0]*m for _ in range(n)]`가 있다.
3. 제한 시간(L)전인 칸들만 카운트해준다.

새로 움직인 칸의 터널 정보 중 움직이기 전 칸 터널 정보가 있으면 연결되어있다는 것이 유용하게 쓰였다.

비교할 대상이 바뀌지 않으면 구조체로 정해두는 것이 편하다는 것을 알게되었다.

BFS는 큐가 빌 때까지 확인하면 된다.

<pre>
공부: Deque 스택과 큐의 기능을 모두 가진 객체

필수 라이브러리: `from collections import deque`

문자열을 이용해 deque를 만들면 각 문자의 요소로 된 리스트 형태의 deque가 만들어진다.

스택처럼 이용하는 경우: append(), pop() //오른쪽에서 입출력

큐처럼 이용하는 경우: appendleft(), popleft() //왼쪽에서 입출력

deque 확장하기: extend() 나의 코드에서는 그냥 덧셈으로 덧붙여주었다, extendleft()

리스트처럼 사용하기: insert(idx, insert_item), remove(items)
</pre>
