## 분류💁

위상정렬

## 접근법

- 작업을 그래프로 연결한 후 위상정렬을 한다. 
- 위상정렬 순서대로 선행노드 탐색하며 가장 큰 값을 알아낸다
      - 선행되어야하는 건 어떻게 알지? → in node
- 각 작업마다 작업이 끝나는 시간을 저장한다.


   
   
```python
def findTime():  
#선언은 생략 
    for i in range(2,len(order)+1):
            max=0
            if(len(innode[i])==0): #선행되야하는게 없음
                ret[i]=time[i]

            for j in innode[i]: #선행노드 탐색
                if(max<ret[j]):
                    max=ret[j]
                ret[i]=time[i] + max

            if(retVal < ret[i]): #결과값을 위한 retVal
               retVal=ret[i]

    return retVal
```


## Fail 이유
- 그래프가 연결이 안되는 경우도 생각해야한다. 이어져있지 않으면 선행노드 상관없이 처음부터 더한 값을 저장하면 된다

## 후기💡
- 위상정렬 문제를 새롭게 풀어보았다. 더 많이 풀어봐야겠다.
- 파이썬 입력받기 너무 어렵다..
