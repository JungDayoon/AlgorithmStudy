## 분류💁

DFS, 백트래킹


## 시퀀스

1. 가장 높은 봉우리들을 저장해놓고, 거기서 시작한다.

2. 상하좌우 탐색을 한다. 

    2.1 방문하지 않았고, 경계안에 존재하고, 높이가 낮으면 재귀 호출을 한다.
    
    2.2 같거나 높고 또한 k값이 존재하면, 현재 높이와 1차이 만큼만 깍는다.
    
3. max길이를 저장하고 1로 반복한다.


```cpp
void makeRoad(int y, int x,int cnt){
    //종료조건.. 상하좌우 모두 갈 수 없으면 종료
    int flag=0;
    for(int dir=0;dir<4;dir++){
        int ny=y+dy[dir];
        int nx=x+dx[dir];
        if(!inRange(ny,nx) || visited[ny][nx] || map[y][x] <= map[ny][nx]-nowk) continue;
        flag=1;
    }
    if(flag==0){
        result=max(result, cnt);
        return;
    }
    

    for(int dir=0;dir<4;dir++){
        int ny=y+dy[dir];
        int nx=x+dx[dir];

        if(!inRange(ny,nx)) continue;

        if(!visited[ny][nx]){
            if(map[ny][nx] < map[y][x]){
                visited[ny][nx]=true;
                makeRoad(ny,nx,cnt+1);
                visited[ny][nx]=false;
            }
            else{
                if(nowk>0 && (map[ny][nx]-nowk < map[y][x]) ){//nowk가 존재하고, 깍아서 높이가 낮아지면
                    int diff=map[ny][nx]-map[y][x];
                    preVal=map[ny][nx];

                    map[ny][nx]-=diff+1;
                    visited[ny][nx]=true;
                    nowk=0;
                    makeRoad(ny,nx, cnt+1);
                    map[ny][nx]=preVal;
                    visited[ny][nx]=false;
                    nowk=K;
                }
            }
        }
    }
}
```

### 유의사항
- 가장 높은 봉우리에서만 시작한다.

- 반드시 높은 곳에서 낮은 곳으로 가고. 같아서는 안되고 대각선도 안된다.

- 딱 한 곳 에서만 최대 k만큼 공사한다.



## 후기💡
- 종료 조건이 상하좌우 갈 수 없으면으로 설정을 해서 flag를 두고 값이 바뀌면 종료했다.

- 1시간 10분 걸렸다.

- 초기화 안해준 게 있어서 그거 찾느라 시간이 꽤 걸렸다.

- 초기화는 바로바로!! 무조건!! 하기.

