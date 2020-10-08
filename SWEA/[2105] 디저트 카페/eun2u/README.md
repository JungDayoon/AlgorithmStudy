## 분류💁

DFS


## 시퀀스

1. 꼭짓점 제외하고 전체 board를 탐색시작한다.

2. 같은 방향으로 갈 수 있으면 가고, 못가면 다음 방향으로 꺽는다

    2.1 board 경계 밖으로 나가지 않는지, 먹었던 디저트인지 확인한다.

3. 시작점이 같으면 사각형 완성으로 종료한다.


## 유의사항

- 같은 방향으로 갈 수 있을 만큼 최대한 가면 가장 큰 사각형이 됨.

- 각 꼭짓점은 시작점이 될 수 없음

- 디저트를 먹을 수 없으면 -1


<DFS 코드>

```cpp
void dfs(int y,int x,int dir, int picked){
    if(startY==y && startX==x && picked>0){
        result=max(result,picked);
        return;
    }
    
    for(int i=0;i<2;i++){
        int ny=y+dy[dir+i];
        int nx=x+dx[dir+i];

        if(!inRange(ny,nx) || eaten[board[ny][nx]])
            continue;

        if(!visited[ny][nx]){
            visited[ny][nx]=true;
            eaten[board[ny][nx]]=true;
            dfs(ny,nx,dir+i,picked+1);
            visited[ny][nx]=false;
            eaten[board[ny][nx]]=false;
        }
    }
}
```




## 후기💡
- 오랜만에 dfs 푸니까 오래걸렸다.감 잃었다..
- 처음에 너무 복잡하게 생각했다. 생각을 단순화 할 순 없을까? 
