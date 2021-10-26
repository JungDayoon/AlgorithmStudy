# [BOJ]/[19236] 청소년 상어

## 분류
dfs

## 접근법
4*4칸이기 때문에 상어가 최대 길이3만큼 움직일 수 있다.
## 조심
1. 2차원배열을 copy할 땐 deepcopy를 이용해야한다.
> dfs에서 list를 계속 변경하므로 copy해서 사용했다.
2. dfs return값
>물고기를 먹을 때 마다 size+를 해주었는데 그러면 다른 dfs를 돌고 있는 친구도 size가 +되기때문에
>temp_size를 두고 dfs를 빠져나온 친구의 size를 temp_size에 저장 후 젤 큰 값을 size에 저장해 준다.

## 후기 
어렵다.