# 백준 1445 : 일요일 아침의 데이트

## Algorithm

다익스트라

## Description

**주의할 점**

1. 쓰레기가 있는 칸은 쓰레기 옆 길이더라도 쓰레기 가 있는 칸으로만 친다. 따라서 아래와 같은 길이 있다면 g가 있는 위치는 모두 쓰레기가 있는 칸으로 만 치고 쓰레기 옆길로 치지 않는다.
    ```
    .....
    ggggg
    .....
    ```
2. 꽃이 있는 칸은 쓰레기 옆 길로 치지 않는다. 즉 F의 위치가 g옆에 있더라도 이 위치는 쓰레기 옆 길이 아닌 꽃이 있는 칸이다.

**구현**
1. next_to_trash(trash) : 쓰레기 'g'의 위치를 담은 리스트 trash를 파라미터로 가져와, 해당 위치의 옆길을 True, 그렇지 않은 길은 False로 가지는 배열을 return

+ 단, 꽃이 위치한 길은 쓰레기 옆이더라도 쓰레기 옆 길이 아니기 때문에 마지막에 그 위치를 False로 바꿔줘야 한다.

2. dilkstra(starty, startx, check) : 시작위치 [starty, startx] 부터 모든 위치 까지의 최소 쓰레기길, 쓰레기 옆길의 개수를 확인하는 함수

+ check는 next_to_trash 함수를 호출해 나온 결과이다.(쓰레기 옆길이 표시되어 있음)

+ 일반 다익스트라와 거의 유사하지만 heapq에 [쓰레기 길, 쓰레기 옆 길, y, x] 순으로 담아준다.
    
    +[쓰레기 길, 쓰레기 옆 길] 순으로 정렬되어야 함

    ```python
    next_trash1, next_trash2 = trash1, trash2 #다음 위치에 일단 현재 위치의 trash1, trash2를 담아준다.
    if arr[nexty][nextx] == 'g':
        next_trash1+=1 #다음 위치가 쓰레기라면 next_trash1하나 증가
    elif check[nexty][nextx] :
        next_trash2+=1 #다음 위치가 쓰레기가 아니고, 쓰레기 옆길이라면 next_trash2 하나 증가
                
    if(distances[nexty][nextx][0]>next_trash1): 
        #새로운 next_trash1이 현재 distances에 담긴 trash1의 값 보다 작다면
        #값을 업데이트 하고, queue에 담아준다.
        distances[nexty][nextx] = [next_trash1, next_trash2]
        heapq.heappush(queue, [next_trash1,next_trash2,nexty, nextx])
    elif(distances[nexty][nextx][0]==next_trash1 and distances[nexty][nextx][1]>next_trash2):
        #새로운 next_trash1이 현재 distances에 담긴 trash1의 값과 같고
        #새로운 next_trash2이 현재 distances에 담긴 trash2의 값보다 작다면
        #값을 업데이트 하고, queue에 담아준다.
        distances[nexty][nextx][1] = next_trash2
        heapq.heappush(queue, [next_trash1,next_trash2,nexty, nextx])   

    ```
## Review


