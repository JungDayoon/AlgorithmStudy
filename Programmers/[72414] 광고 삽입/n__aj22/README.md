# Programmers 72414번 : 광고 삽입

## Algorithm

## Description

**모든 시간은 초로 변환하여 계산한다, 마지막 결과만 hh:mm:ss 로 변환해 return 한다.**

1. **`string_to_sec(time_str)`** : string 형식(hh:mm:ss)의 시간을 int 형의 초로 변환해서 return

2. **`sec_to_string(time_sec)`** : int형의 초시간을 string 형식(hh:mm:ss)로 return

3. **`solution(play_time, adv_time, logs)`** : 결과값을 return 

    `all_time` : 시간별 누적 시청자 수를 저장하는 리스트

    1. logs에 저장된 시청 시작시간, 시청 끝 시간을 초로 변환하고 all_time[시작]에 +1, all_time[끝]에 -1을 한다.

    2. 구간별 시청자수 기록 : 1초 동안의 시청자수를 찾는다.

        all_time[i] = all_time[i]+all_time[i-1]
    
    3. 전체 누적 시청자수 기록 : 누적 시청자수를 구한다.

        all_time[i] = all_time[i]+all_time[i-1]
    
    위의 세 가지 단계를 완료하면 다음과 같다.
    ![Picture2](https://user-images.githubusercontent.com/33089715/112313306-0db37500-8ceb-11eb-9cac-b76d1e54a58a.png)

    **가장 시청자 수가 많은 구간 탐색** : 처음부터 끝까지 탐색하며 구간대비 시청자 수가 가장 많은 구간을 찾는다.

    + 전체 누적 시청자수 기록 all_time을 이용하면 누적 재생시간을 구할 수 있다.
        
        ```python
        if(max_play_time<all_time[i]-all_time[i-adv_time]):
            max_play_time = all_time[i]-all_time[i-adv_time]
            max_start_time = i-adv_time+1
        ```
    + 예를 들어 위의 예시에서 광고가 5초라고 하면, 4~8 구간의 누적 재생 시간은 all_time[8]-all_time[8-5] = all_time[8]-all_time[3] = 8 과 같다.
## Review

너무너무너무너무너무너무너무너누뭄너무어렵다!

