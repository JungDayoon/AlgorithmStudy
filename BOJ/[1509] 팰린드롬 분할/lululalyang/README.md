# [BOJ]/[1509] 팰린드롬 분할

## *- DP -*

* **팰린드롬**: 앞에서 읽으나, 뒤에서 읽으나 항상 똑같이 읽어지는 문자열.
  * 예) "여보안경안보여", "ababa" 등

**`int MinPalindrome(String[] str)`** 

1. `str`로 만들 수 있는 모든 문자열이 팰린드롬이 되는지 확인한다.

   * `boolean[][] isPal`: `isPal[i][j]`는 `i`부터 `j`까지의 문자열이 팰린드롬이면 `true`를, 그렇지 않으면 `false`를 가진다.

   * `void computeIsPal(String[] str, int len, boolean[][] isPal)`

     : 두 개의 for문을 반복하는 데, 그 중 하나인 `i`는 확인하려는 문자열의 길이를 의미(**`i+1`:** 문자열 길이), 다른 하나 `j`는 확인하려는 문자열의 시작 인덱스를 의미한다.

     1. 길이가 `1`인 문자열이라면 (`i == 0`)

        모든 문자열이 팰린드롬이다. 예) `A` 

        따라서, 모든 `j`에 대해 `isPal[j][j] = true`

     2. 길이가 2인 문자열이라면 (`i == 1`)

        시작 인덱스와 끝 인덱스의 문자가 같다면 팰린드롬, 그렇지않으면 팰린드롬이 아니다. 

        예) `AA`: true / `AB`: false

        따라서, `str[j] == str[j+1]`일 때만 `isPal[j][j+1]= true`, 조건을 만족하지 않으면 `isPal[j][j+1] = false`

     3. 길이 3부터 입력문자열의 길이까지의 문자열이라면 (`3 <= i <= str.length-1`)

        양 끝 문자열을 제외한 나머지 문자열이 팰린드롬이고, 양 끝 문자가 같다면 팰린드롬, 그렇지 않다면 팰린드롬이 아니다.

        예) 길이가 5일 때, 

        `ABCDA`: false (양 끝 문자는 같지만, 양 끝을 제외한 나머지 문자열이 팰린드롬이 아님)

        `ABCBD`: false (양 끝을 제외한 나머지 문자열은 팰린드롬이지만, 양 끝 문자가 다름)

        `ABCBA`: true (조건을 만족)

2. `isPal`을 이용해 팰린드롬 분할 수의 최솟값을 구한다.

   * `int[] dp` : `dp[i]`는 `0`부터 `i`까지 문자열에서의 팰린드롬 분할 수의 최솟값

   * `dp[0] = 1`로 초기화. (`0`번째 문자는 길이 1로 팰린드롬이다)

   * `i=1`부터 `i=len-1`까지 반복한다.

     1. `0`부터 `i`까지의 문자열이 팰린드롬이라면 (`isPal[0][i] == true`)

        `dp[0][i] = 1`이다. (이 자체가 팰린드롬 문자열이라면 팰린드롬 분할 수의 최솟값은 자기 자신 하나로 `1`)

     2. 그렇지 않으면, 우선 `dp[i]`의 최대값인 `dp[i-1]+1`로 초기화한다.

        그리고 `j=1`부터 `j=i`까지 반복하면서 `dp[i]`값을 최소값으로 갱신한다.

        최소값은 <u>만약 `j`부터 `i`까지의 문자열이 팰린드롬이라면</u>, 

        **(`j-1`까지 문자열의 팰린드롬 분할 수의 최솟값 + `j`부터 `i`까지의 팰린드롬 문자열 1개) = (`dp[j-1] + 1`)** 이 된다.

3. `dp[len-1]`값이 입력 문자열의 팰린드롬 분할 수의 최솟값

## :speaking_head:

* 참고했다 !! 잘 알아두자!