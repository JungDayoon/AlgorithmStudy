## 분류💁

시뮬레이션 그 자체



## 시퀀스

1. 낚시왕은 오른쪽으로 한 칸 이동

2. 낚시왕이 서있는 col의 row값이 가장 작은 상어를 잡는다. 없으면 안 잡음

3. 상어가 속력만큼 이동한다. 경계 부딪히면 방향전환. 이동한 곳에 상어가 존재하면 크기가 큰 친구가 잡아먹음

## 시간초과 이유
- 상어가 속력만큼 이동하는 것을 매 초마다 움직이며 옮겼다. 0<= s <=1000, 0<= M <= R*C 이기 때문에 최악의 케이스는 시간초과가 뜬다


<시간초과난 코드>
``` cpp
for(int i=0;i<speed;i++){
        int ny=y+dy[dir];
        int nx=x+dx[dir];
        if(!inRange(ny,nx)){
            int rdir=reverseDir(dir);
            ny=y+dy[rdir];
            nx=x+dx[rdir];
            dir=rdir;
        }
        y=ny;
        x=nx
```

-> 시간초과를 단축하려면, 매 초마다 이동하는 대신 한 벽까지는 쭉 이동한다. 벽에 닿으면 방향을 바꿔 다음 벽에 닿을 때까지 이동한다.



## 후기💡
- [2382] 미생물 격리와 비슷하게 풀었다.
- 시간 초과때문에 애먹었다.. 흠.. 
- 그리고 너무 코드 실수가 많다. 제발 ~~ 실수 줄이자~~
- 파이썬 풀다가 cpp 쓰니까 너무 코드가 길어

