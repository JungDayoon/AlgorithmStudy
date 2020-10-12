# 백준 14500번 : 테트로미노

## Algorithm

DFS, Brute Force

## Description

+ 단순하게 모든 위치를 시작점으로 해서 블록을 설치하고 4개 블록 설치를 완료하면 그 때의 숫자 합을 최댓값과 비교해줬다.

+ 단, 그냥 단순한 DFS로 블록을 설치하게 되면 ㅗ 모양의 블록을 설치하지 못해 이 부분을 따로 더해줬다.
    + 만약 지금 설치된 블록이 2개 이하라면, 현재 방향과 그 다음 방향 두개 모두 설치해준다.([0,1][1,2][2,3])
    + 이때 DFS 를 호출할 때는 num+2 를 호출해야 한다!!
    
    
    ``` python
    for i in range(4):
        nextr = nowr + dr[i]
        nextc = nowc + dc[i]
        if(isIn(nextr, nextc)):
            num_list.append(arr[nextr][nextc])
            visited[nextr][nextc] = False
            DFS(nextr, nextc, num+1, num_list)
            if (i<3): #그 다음 블록도 설치해서 확인해준다.
                nextr2 = nowr + dr[i + 1]
                nextc2 = nowc + dc[i + 1]
                if (isIn(nextr2, nextc2) and num<3): # 현재 설치된 블록이 2개보다 크면, 즉 3개이면 2개를 더 설치하게 되면 5개가 되기 때문에 불가능
                    num_list.append(arr[nextr2][nextc2])
                    visited[nextr2][nextc2] = False
                    DFS(nextr, nextc, num+2, num_list)
                    DFS(nextr2, nextc2, num+2, num_list)
                    num_list.pop(-1)
                    visited[nextr2][nextc2] = True
            num_list.pop(-1)
            visited[nextr][nextc] = True
    
    ```

## Review

pypy 로 통과했다.. ! 뭔가 더 시간을 줄일 수 있을 것 같은데 생각하기 귀찮아서 일단 이렇게 풀었다..

하지만 더 효율적으로 다시 풀어봐야 할 것같다.
