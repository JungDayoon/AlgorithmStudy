def solution(jobs):
    answer = 0
    jobs.sort(key = lambda x:(x[1]))
    endx = 0
    job_num = len(jobs)
    while(jobs):
        for i in range(len(jobs)):
            if(jobs[i][0] <= endx):
                start, time = jobs.pop(i)
                endx += time
                answer+=(endx-start)
                break       
            if i == len(jobs) - 1:
                endx+=1

    return answer//job_num
if __name__ == "__main__":
    jobs = [[24, 10], [18, 39], [34, 20], [37, 5], [47, 22], [20, 47], [15, 2], [15, 34], [35, 43], [26, 1]]
    print(solution(jobs))