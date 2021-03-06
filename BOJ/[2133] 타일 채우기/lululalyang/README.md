# [BOJ]/[2133] 타일 채우기

## *- DP -*

* ***dp*** - bottom up 방법 사용

</br>

* `int Bottom_up(int N, int[] dp)`

  * `N`이 홀수이면 (3xN)을 채우는 경우의 수는 `0`

  * `dp[2] = 3` : (3x2)를 채우는 경우의 수는 `3`가지![image](https://user-images.githubusercontent.com/33208360/108836625-67ede700-7614-11eb-9ced-c7637ea01da6.png)

    

  * `N >= 4`는 `dp[2]`처럼 2칸씩 나누는 경우외에 특수한 경우가 2가지 더 존재한다.

    * 예를 들어 `N = 4`라면	 ![image](https://user-images.githubusercontent.com/33208360/108837090-172abe00-7615-11eb-91b5-49a9a27a9829.png)

  * 위의 조건을 고려하여 규칙을 세운다. 예를 들어 `N=6`이라면 3가지 조합으로 생각해볼 수 있다.

    1. [3x**2**]&(3x**4**) => `{(3x2)일 때 특수한 경우} * dp[4] = dp[2] * dp[4]` 
    2. [3x**4**]&(3x**2**) => `{(3x4)일 때 특수한 경우} * dp[2] = 2 * dp[2] `
    3. [3x**6**]&(3x**0**) => `{(3x4)일 때 특수한 경우} * dp[0] = 2 * 1` (`dp[0] = 1`: 계산의 편의성을 위해 1로 저장)

    위의 3가지 조합을 모두 더한 값이 `N=6`일 때의 경우의 수가 된다.

  * 이처럼, `4`에서부터 `N`까지 `dp[]`값을 구한다.

</br>

## :speaking_head:

* dp 너무 어렵다 .. 규칙을 찾아도 이걸 코드로 어떻게 옮겨야 할 지 잘 모르겠다ㅜ 연습 많이 하자 ..
