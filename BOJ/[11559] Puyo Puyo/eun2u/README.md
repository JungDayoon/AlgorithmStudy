## 분류💁

BFS, 시뮬레이션


## 시퀀스

1. 같은 색이 4개 이상 연결 되어 있으면 없앤다.

    - 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.


2. 없애면 중력의 영향을 받아 떨어진다.

    - 0을 제외한 값들로만 벡터를 만들어서 다시 board에 새롭게 채운다.
  
3. 없으면 종료 

## BFS
```cpp
void bfs(int y,int x){
    queue< pair<int,int> > q;
    vector< pair<int,int> > puyo;
    puyo.push_back(make_pair(y,x));
    q.push(make_pair(y,x));
    visited[y][x]=true;

    while(!q.empty()){
        pair<int,int> here= q.front();
        q.pop();

        for(int dir=0;dir<4;dir++){
            int ny=here.first+dy[dir];
            int nx=here.second+dx[dir];
            
            if(!inRange(ny,nx)) continue;
            if(!visited[ny][nx] && board[here.first][here.second]==board[ny][nx]){
                q.push(make_pair(ny,nx));
                puyo.push_back(make_pair(ny,nx));
                visited[ny][nx]=true;
            }
        }   
    }
    if(puyo.size() >=4) //크기가 4개 이상인 경우만 터진다.
        for(int i=0;i<puyo.size();i++)
            isPuyo[puyo[i].first][puyo[i].second]=true;
}
```

## 후기💡

- 1시간 정도 걸렸네요

- '터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.' 

    -> 이 조건을 확인하지 못해서 거의 새롭게 짰다..

- 진짜 문제는 꼼꼼히 읽자.. 제발.. 




