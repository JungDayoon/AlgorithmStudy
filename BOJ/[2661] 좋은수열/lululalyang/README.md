# [BOJ]/[2661] 좋은 수열

## *- BackTracking -*  

```java
void solution(ArrayList<Integer> num, int len, int N) // 백트래킹 메소드
```

`num` : 이 때까지 만든 수열

`len` : 수열의 길이

1. `num`에 `1`부터 `3`까지 순서대로 하나씩 추가한다.

2. 그 때의 수열이 좋은 수열인지 확인한다.

   ```java
   boolean ChkValid(ArrayList<Integer> num, int len)
   ```

   1. 방금 추가한 마지막 숫자와 그 전 숫자가 같다면 나쁜 수열 => `return false`

   2. 현재 수열의 길이 내에서 가능할 때까지 마지막에 인접된 두 수열을 확인한다.

      같은 부분 수열이 연속되는 경우가 있다면 => `return false`

      그런 경우가 없다면 좋은 수열이므로 => `return true`

3. 만족하는 수열을 찾았는지의 여부인 `flag`가 `true`라면 `return`

4. 그렇지 않다면 추가한 마지막 숫자를 삭제하고 다음 숫자를 추가한다.

메소드를 시작할 때  수열의 길이가 `N`이라면 `flag`를 `true`로 바꿔주고 `return`한다.

