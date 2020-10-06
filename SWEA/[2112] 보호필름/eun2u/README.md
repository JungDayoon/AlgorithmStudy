## 분류💁

DFS, 백트래킹

## 접근법

- 약품 투입 없이 성능검사가 가능하면, 0을 출력한다


- 최대 k-1개 약품 투입으로 성능 검사가 불가능 하면 k를 출력한다


- k보다 작은 값으로 성능 검사가 가능하면, 그 값을 출력한다.





```python
def dfs(picked,start):
    global newfilm
    global minK

    if(checkPerform(newfilm)==True):
        if(picked < minK):
            minK=picked
        return

    if(picked==k-1):
        return
          
    for i in range(start, d):
        for val in range(2):
            if(checkPerform(newfilm)==False):
                newfilm[i]=[val for _ in range(w)]
                dfs(picked+1,i+1)
                newfilm[i]=copy.deepcopy(film[i])
 ```
  



## 후기💡
- 처음에 조합으로 약품을 투입할 행을 선택해서 풀려고 했는데, 그렇게 하니 0과 1 둘다를 어떤 순서로 칠해야 할지 생각하기 어려웠다.. 

- 그래서 처음부터 다시 백트래킹으로 풀었다. 

- 삽질한다고 시간이 꽤 걸렸다 

- dfs 함수 내에서 종료조건의 순서를 제대로 걸지 못해서 오답이 나왔었다. 
    - checkPerform 과 picked 를 or 로 검사 -> 성능검사 불가능인데 Picked조건으로만 종료
    
    - checkPerform 과 Picked 를 and 로 검사 -> k-1보다 작은 정답이 나오지 않게 됨..
    
    -> 지금 보니 당연한건데 코딩할 때는 깊게 생각하지 못했다... 흐름을 생각하며 짜자..
