# 백준 1113 : 수영장 만들기

## Algorithm

BFS, Simulation

## Description

1. 입력받은 arr 주위에 높이가 0인 공간을 추가한다.
```
ex) 입력 arr   ---> 변경된 arr
    16661           0000000
    61116           0166610
    16661           0611160
                    0166610
                    0000000

```

2. `check_pool_possible(arr, y, x)` 함수를 사용해 물을 추가하는 것을 시작할 수 있는 위치를 찾는다.

+ `check_pool_possible(arr, y, x)` : y, x 위치에 물을 추가하는 것이 가능한지 Boolean 값으로 return 하는 함수
    + (y, x)의 상, 하, 좌, 우를 확인하면서 하나라도 현재 높이보다 작은 곳이 있다면 물을 추가하는 것이 불가하다.(흘러내림) -> return False
    + 그렇지 않다면 return True

3. 위의 과정을 통해 True 인 위치를 찾으면 그 위치부터 시작해 `make_pool(visited, arr, y, x)` 함수를 통해 수영장을 만들 수 있는지 확인한다.
+ `make_pool(visited, arr, y, x)` : (y, x)를 시작으로 BFS를 하면서 시작 높이와 동일한 높이의 위치를 모두 찾고, 현재 위치보다 높은 곳의 높이 중 가장 작은 값을 구해 추가할 수 있는 물의 양을 찾는다.
+ BFS
    + 만약 BFS 도중 시작 높이보다 작은 곳이 발견된다면 물을 추가할 수 없으므로 return 0 
    + 시작 높이보다 높은 곳을 찾으면 min_top_height 와 비교해 작은 값을 저장한다.(높은 곳 중 작은 값을 찾기 위함)
    + 시작 높이보와 같은 곳을 찾으면 queue에 넣고 visited = True 로 변경한다. 물을 추가할 후보가 되는 candidate 리스트에 추가한다.
+ BFS 후, candidate 리스트에 포함된 위치를 모두 min_top_height 로 변경하고 추가한 물의 양을 return 한다. 

4. arr 의 높이의 변화가 없을 때 까지 반복하며 모든 물의 양의 합을 구한다. 








## Review

