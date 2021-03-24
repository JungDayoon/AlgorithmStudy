def string_to_sec(time_str):
    split_time = time_str.split(":")
    return int(split_time[0])*60*60+int(split_time[1])*60+int(split_time[2])
def sec_to_string(time_sec):
    hh = str(time_sec//3600).zfill(2)
    mm = str(time_sec%3600//60).zfill(2)
    ss = str(time_sec%60).zfill(2)
    return hh+":"+mm+":"+ss

def solution(play_time, adv_time, logs):
    adv_time = string_to_sec(adv_time)
    play_time = string_to_sec(play_time)
    all_time = [0]*(play_time+1)
    for log in logs:
        log_split = log.split("-")
        start, end = string_to_sec(log_split[0]), string_to_sec(log_split[1])
        all_time[start]+=1
        all_time[end]-=1
    for i in range(1, play_time+1):
        all_time[i]+=all_time[i-1]
    for i in range(1, play_time+1):
        all_time[i]+=all_time[i-1]
    max_play_time = all_time[adv_time-1]
    max_start_time = 0

    for i in range(adv_time, play_time):
        if(max_play_time<all_time[i]-all_time[i-adv_time]):
            max_play_time = all_time[i]-all_time[i-adv_time]
            max_start_time = i-adv_time+1
    return sec_to_string(max_start_time)

if __name__ == "__main__":
    play_time = ["02:03:55", "99:59:59", "50:00:00"]
    adv_time = ["00:14:15", "25:00:00", "50:00:00"]
    logs = [["01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"],
    ["69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"],
    ["15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"]]
    for i in range(3):
       print(solution(play_time[i], adv_time[i], logs[i]))