# [5644] 무선 충전 - C++

## 분류

시뮬레이션

---


##접근법

1. 무선 충전 범위를 정한다
    - dfs를 사용함

```c++
void setRange(int k, int y, int x, int c,int p,int gone){
    
    if(gone==c) return;
    
    range[k][y][x]=p;
    visited[y][x]=true;
    
    for(int dir=1;dir<5;dir++){
        int ny=y+dy[dir];
        int nx=x+dx[dir];
        
        if(!inRange(ny,nx)) continue;

       //if(!visited[ny][nx]) --> 조건이 없어야함. 
        setRange(k,ny,nx,c, p,gone+1);
    }
}
```
visit은 검사하면 안된다. 습관처럼 사용하고 있었다.



2. 충전 범위는 3차원 배열로 구현
- 겹치는 것만 따로 저장 -> 처음 친구는 겹치는지 알 수 없음
- BC마다 따로 저장 -> 위치가 옮겨질때마다 탐색해야하는 시간이 오래걸림
- 3차원 배열 -> 선택


3차원 배열을 이용해 매 초마다 걸쳐지는 BC 탐색
- 두명이 같은 충전기에 접속하면, 균등하게 분배하고, 다른 가능성이 있으면 최대값이 되게해야함. 
- P가 최대가 될 수 있는 걸로 택함


---
## 후기
- 충전 범위가 겹치는 것을 어떻게 구현해야하는지 엄청 고민했다.... 



