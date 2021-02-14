# [BOJ]/[1074] Z

## *- Divide & Conquer -*

**Main**

* (`r`, `c`) 가 속하는 **'Z'**가 시작되는 좌표의 **방문순서**를 찾아 결과를 출력한다.
  * ![image](https://user-images.githubusercontent.com/33208360/107879313-7bfe5e00-6f1b-11eb-940f-164c0773c9d9.png)
  * (`r`, `c`) = (짝수, 짝수) : `0`번째 => `check = 0`로 저장
  * = (짝수, 홀수) : `1`번째 => `check = 1`로 저장한 후, **'Z'**시작점을 기준으로 방문순서를 찾아야 하니까 `c--`
  * = (홀수, 짝수) : `2`번째 => `check = 2`로 저장, `r--`
  * = (홀수, 홀수) : `3`번째 => `check = 3`로 저장, `r--`, `c--`
* `Recursive()`를 통해 구한 **'Z'**시작점의 방문순서에 `check`값을 더한 값을 출력한다.

**Func**

* `void Recursive(int N, int r, int c, int first)`
  * `int first` : **'Z'**의 시작점의 방문순서로, 재귀가 호출될 때마다 갱신된다.
  * `N`값에 따라 `map`의 길이의 절반인 `half`를 구한다.
    * `int half = (int) (Math.pow(2, N)) / 2;`
  * `half`와 `r`, `c`를 비교하여 위 **Main**과 같이 `check`를 구한다.
  * 구한 `check`값으로 `first`를 갱신한다.
    * `first += Math.pow(2, 2*(N-1)) * check;`
    * 전체를 `1/4`한 한 덩이의 크기가 `Math.pow(2, 2*(N-1))`
  * 위의 과정 후, `N-1`값과 갱신된 `first`값으로 `Recursive()`를 호출한다.
    * `check == 0`이면, 기존 `r`, `c`값 그대로
    * `check == 1`이면, 기존 `r`값과, `c-half`값으로
    * `check == 2`이면, `r-half`값과 기존 `c`값으로
    * `check == 3`이면, `r-half`값과 `c-half`값으로
  * `N`이 `1`이면 그 때의 `first`값을 최종 **'Z'**시작점 값인 `res`에 저장 후 종료한다.

## :speaking_head:

* 재귀함수를 호출할 때의 변수의 값을 각 `check`값에 맞게 처리해주어야하는데, 처음에 너무 짧게 생각해서 틀렸다 ㅜㅜ 큰 input값일 경우의 조건을 생각해서 풀자 !!