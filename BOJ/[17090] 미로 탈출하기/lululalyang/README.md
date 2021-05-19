# [BOJ]/[17090] 미로 탈출하기

## *- dfs -*

```java
boolean[][] map; // 주어진 미로 정보
boolean[][] escape; // 탈출 여부 
boolean[][] visited; // 방문 여부
```

* `visited`가 `true`고, 

  * `escape`가 `false`면 이미 탈출할 수 없는 칸으로 확인된 칸

  * `escape`가 `true`면 이미 탈출할 수 있는 칸으로 확인된 칸

1. 각 위치를 스캔하면서, `escape`가 `false`라면 `dfs()`를 호출해 그 리턴값을 현재 위치의 `escape`로 저장한다.

   ```java
   boolean dfs(int x, int y, boolean[][] escape, boolean[][] visited)
   ```

   1. 현재 위치 `(x, y)`가 미로 밖이라면,

      탈출한 것으로 `true`를 리턴.

   2. 방문하지 않았다면,

      방문 여부를 `true`로 바꿔주고,

      현재 위치의 `map`정보에 따른 다음 위치로 `dfs()`를 재귀호출.

      그 리턴값을 현재 위치의 `escape`값에 저장한다.

   3. 현 위치의 `escape`값 리턴.

2. 현재 위치의 `escape`값이 `true`라면,

   탈출 가능한 칸 개수인 `cnt`값을 `+1`해준다.

3. `cnt`가 최종 결과값으로, `cnt`를 출력