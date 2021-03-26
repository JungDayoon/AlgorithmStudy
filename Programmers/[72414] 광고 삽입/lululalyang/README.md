# [Programmers]/[72414] 광고삽입

## *- Simulation -*

### solution

:star: *`String`으로 입력받은 시간을 초 단위(`int`)로 바꿔서 처리한다.*

1. 동영상 재생길이인 `play_time`을 초 단위로 바꾼다. 

   `long N = timeToN(play_time)`

   * `int`범위를 벗어나므로 자료형 `long`을 사용한다.
   * `long timeToN(String time)` : `time`에 맞는 초 단위의 값을 리턴한다.

2. 시간에 따른 시청자 수를 저장하기 위한 배열을 `N`을 이용해 생성한다.

   `long[] AllTime = new long[(int)(N+1)]`

3. `logs`를 스캔하면서, 시청 시작 시각과 종료 시각을 초 단위로 바꾸고, 그 인덱스에 맞게 시작시각에는 `+1`을, 종료 시각에는 `-1`을 해준다.

   * 시작할 때 시청자 한명 추가의 개념	`AllTime[(int)start]++`
   * 종료할 때 시청자 한명 제거의 개념   `AllTime[(int)end]--`

4. 해당 시각에 몇명이 보고 있는지 계산한다.

   `AllTime[i] = AllTime[i-1] + AllTime[i]`

   * 이 때의 `AllTime[i]`는 시각 `i`에 시청하고 있는 시청자 수를 의미한다.

5. `i`시각까지의 누적 시청자 수를 계산한다.

   `AllTime[i] = AllTime[i-1] + AllTime[i]`

   * 이 때의 `AllTime[i]`는 시각 `i`까지 몇명이나 봤는지를 의미한다.

6. 광고 길이인 `adv_time`에 맞게 최대 시청자수를 찾고 그 때의 시작 시간을 저장한다.

   * `int adv = timeToN(adv_time)` : 초 단위로 바꿔준다.
   * 예) `AllTime[b] - AllTime[a]`
     * `b`시각까지 본 시청자 수에서 `a`시각까지 본 시청자 수를 뺀 값
     * `a+1`부터 `b`까지 본 시청자 수를 의미한다.
   * `int nowTime = AllTime[i] - AllTime[i-adv]`
     * `i-adv+1`부터 `i`까지 본 시청자 수
   * `nowTime`이 최대 시청자 수보다 크다면 최대 시청자 수 갱신한다.
     * 그 때의 시작 시각도 갱신한다.

7. 최대 시청자 수의 초단위 형태의 시작 시각을 다시 `HH:MM:SS`형태로 변환시켜 리턴해준다.

   * `String NtoTime(long N)`

</br>

:speaking_head:

* 감이 안와서 지혜의 방법을 참고했다.

  * `HH:MM:SS`형태의 `String`을 초단위인 `Integer`로 바꿔서 처리
  * 누적 시청자수 계산

  위 두 가지가 중요한 것 같다. 여러 풀이 방식을 알아두자 다음에 써먹게 !!ㅜㅜ