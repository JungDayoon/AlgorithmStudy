# [BOJ]/[2565] 전깃줄

## *- DP -*

* A 전봇대의 전깃줄 번호로, 오름차순으로 sorting한 후,  

  그에 따라 정렬된 B 전봇대의 전깃줄 번호 중 **최장 증가 부분 수열 (LIS)의 길이**를 구해 총 전깃줄 개수에서 그 길이를 뺀 값이 없애야 하는 전깃줄의 최소 개수가 된다.

  * A 전봇대의 전깃줄을 정렬했기 때문에, 겹치지 않는 전깃줄의 B 전봇대의 전깃줄 또한 오름차순으로 정렬된다. 

  * 만약 오름차순 정렬에 맞지 않다면 이는 겹치는 전깃줄 !

    => 없애야 하는 전깃줄의 **최소** 개수를 구해야하므로, 겹치지 않는 전깃줄의 최대 개수를 구해야한다

    => 따라서, **B 전봇대의 전깃줄의 최장 증가 부분 수열(LIS)의 길이**를 구한다.

* 전깃줄의 최대 개수가 `100`이므로 DP를 이용해 LIS를 구한다.

  * 해당 인덱스의 LIS길이는 `dp[]`
  * 우선, 모든 원소의 LIS 길이는 `1`로 초기화.
    * 그 후 `i`번째 원소의 LIS길이는 `i`보다 작은 인덱스인 `j`번째 (`j < i`) 원소와 `i`번째 원소를 비교한다.
    * 만약 `j` 번째 원소보다 `i`번째 원소가 크다면, `dp[i]`를 갱신한다.
      * `dp[j] + 1`과 `dp[i]` 중 큰 값으로 갱신.
  * 모든 원소의 `dp[]`값을 구한 후, `dp[]`배열의 최대 값이 해당 집합의 LIS의 길이가 된다.

> **:star: JAVA**
>
> * `int`형 2차원 배열 정렬하기 -> `Comparable`클래스 사용
>
>   ```java
>   Arrays.sort(arr, new Comparable<int[]>{
>   	@Override
>   	public int compare(int[] a1, int[] a2){
>   		return a1[0] - a2[0]; // 0번째 인덱스를 기준으로 오름차순 정렬
>   	}
>   })
>   ```
>
> * 직접 생성한 객체 동일한지 비교 ->  `equals()`메소드 오버라이드
>
>   ```java
>   class Made{
>       ...
>   	@Override
>       public boolean equals(Object o){
>           Made m = (Made)o;
>           ... // 같은 객체일 조건 명시
>       }
>   }
>   ```
>
>   

## :speaking_head:

* 처음에는 전깃줄 각각 겹치는 전깃줄을 저장하고, 그 개수를 저장하여서 가장 많은 전깃줄이 겹치는 전깃줄부터 제거하고, 겹치는 전깃줄이 없을 때까지 반복하는 방법으로 접근하였다.

  * 이 방법은 겹치는 전깃줄의 수가 같을 때, 어떤 전깃줄을 먼저 제거하냐에 따라서 결과값이 다르게 나오기 때문에 오답이었다.

  * 반례

    10

    1 6

    2 8

    3 2

    4 9

    5 5

    6 10

    7 4

    8 1

    9 7

    10 3

    => 답: 6 / 오답: 7

* 최장 증가 부분 수열을 이용해야한다!

  * LIS를 구하는 알고리즘이 여러가지 있었는데 DP와 다른 방법으로도 풀어보도록 하자



