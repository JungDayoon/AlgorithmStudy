# [Programmers]/[12971] 스티커 모으기(2)

## *- dp -* 

```java
int[] dp1 = new int[N]; // 첫 번째 스티커 뜯은 경우
int[] dp2 = new int[N]; // 안 뜯은 경우
```

* 첫 번째 스티커와 마지막 스티커는 인접해있으므로, 첫 번째 스티커를 뜯은 경우에는 마지막 스티커를 뜯을 수 없다.

  => 그렇기 때문에, 두 경우를 나눠서 계산

## solution

1. `N`이 `1`이면, 그 스티커 값이 `answer`

2. `N`이 `2`이면, 두 스티커 값 중 큰 값이 `answer`

3. `N`이 `3`이상이면,

   * `dp1[0]` 과 `dp1[1]`은 첫 번째 스티커 값이다.

     `dp1`은 첫 번째를 뜯은 경우이므로, 두 번째 스티커는 뜯을 수 없기 때문

   * `dp2[1]`은 두 번째 스티커 값.

     `dp2`는 첫 번째를 뜯지 않은 경우로, 두 번째 스티커를 뜯을 수 있기 때문

   그 이후, index `2`부터는

   `dp[i-1]`값과 `dp[i-2] + i번째 스티커 값` 중 큰 값이 `dp`값이 된다.

   * `dp1`은 마지막 스티커를 뜯을 수 없으므로 주의

   * `dp1[N-1]`과 `dp2[N-1]`중 큰 값이 `answer`