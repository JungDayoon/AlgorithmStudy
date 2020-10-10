## 분류💁

DFS


## 시퀀스

1. 현재 모양에서 갈 수 있는 만큼 이동한다.

2. 이동하려는 곳이 빈칸인 경우 이동 가능하다. 
    
    2.1 ➡️ : 오른쪽 확인
    
    2.2 ⬇️ : 아래쪽 확인
    
    2.3 ↘️ : 오른쪽, 아래쪽, 오른쪽아래 세가지 빈칸인지 확인
    
3. 빈칸인 경우에 이동한다.

4. (N-1, N-1)에 도달하면 return

## DFS코드
```cpp
void movePipes(int y1, int x1, int y2, int x2,int shape){
    if(y2==N-1 && x2==N-1){
        result++;
         return;
    }

    int psize;
    if(shape==0 || shape==2) psize=2;
    else psize=3;
    for(int i=0;i<psize;i++){
        int dir=pos[shape][i];
        int ny=y2+dy[pos[shape][i]];
        int nx=x2+dx[pos[shape][i]];

        if(!inRange(ny,nx)) continue;
        if(isSpace(dir,ny,nx))
            movePipes(y2,x2,ny,nx,dir);
    }
}
```
## 후기💡
- SWEA [1953] 탈주범 검거와 비슷한 방식으로 방향을 이동했다.

- 1시간 걸렸다.


