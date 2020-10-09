## 분류💁

시뮬레이션, BFS


## 시퀀스

1. 택시 위치와 사람들의 최단거리 구함

2. 최단거리에 위치한 사람으로 이동한다( Fuel-=이동거리)
     
     -> 최단 거리가 같으면, row값 가장 작은 사람. 같으면 col값 가장 작은사람 택한다.
     
3. 시작 위치에서 목적지까지 최단경로로 이동 (Fuel-=이동거리)

4. 목적지까지 도착하면 (Fuel+=이동거리*2)

5. 다시 1로 돌아가서 반복
    * 이동 중에 연료가 0보다 작아지면 -1 출력하고 종료.


### [cpp] 키 값으로 정렬하기
```cpp
bool cmp_Dist(pair<P,int> a, pair<P, int> b) { 
    if(a.second==b.second){
        if(a.first.sy==b.first.sy)
            return a.first.sx<b.first.sx;
        else 
            return a.first.sy< b.first.sy;
    }
    else return a.second<b.second;
}



sort(dists.begin(),dists.end(),cmp_Dist);

```
-> 키값을 기준으로 간편하게 정렬이 가능하다. 


## 후기💡
- 최단거리의 일반적인 유형이라 금방 풀었는데, 파이썬 코드 시간초과를 도저히 해결하지 못했다. 
- 그래서 저는 그냥 cpp 쓰겠습니다. 시간초과 해결하기 힘듭니다..


