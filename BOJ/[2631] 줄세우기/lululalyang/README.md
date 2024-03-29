# [BOJ]/[2631] 줄세우기

## *- dp -*

> `1`부터 `N`까지 순서대로 세워야 하고, 최소한의 움직임만으로 처리해야하므로,
>
> 아이들의 순서 중 *증가하는 부분 수열의 가장 긴 부분* 은 남겨두고, 나머지 부분을 옮기는 방식이 가장 적은 움직임으로 순서대로 세울 수 있는 방법이다.
>
> => LIS

```java
int[] dp; // dp[i]: i번째 원소를 마지막으로 하는 수열 중 가장 긴 증가하는 부분수열의 길이
```

* 모두 `1`로 초기화. (자기자신만을 가지는 수열의 길이가 LIS 최솟값)

* `i`번째 원소보다 앞에 있는 원소(`j`번째)를 확인하면서,

  `j`번째 숫자보다 `i`번째 숫자가 큰 수라면, `j`번째의 `LIS`길이에 `1`더한 값으로 `i`번째 LIS길이를 갱신한다. (기존의 `i`번째 LIS길이가 더 길다면 갱신하지 않음)

* `dp`값 중 가장 큰 값이 주어진 수열의 `LIS`길이가 된다.

총 수 `N`에서 `LIS`값을 뺀 값이 '번호 순서대로 줄을 세우는데 옮겨지는 아이들의 최소 수'가 된다.

</br>

## :speaking_head:

이전에 비슷한 문제를 풀었던게 기억나서 쉽게 풀었다