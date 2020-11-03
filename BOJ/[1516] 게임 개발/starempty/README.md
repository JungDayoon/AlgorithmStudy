위상정렬

위상정렬 문제는 각 노드를 우선적으로 두는 노드들의 리스트 + indegree 리스트(시작점은 0)을 만들어야한다. 

우선순위큐로 구현하면 시간별로 순서를 만들어 둘 수 있기때문에 효율성을 높일 수 있다. 하지만 `import copy copy.deepcopy([])`를 이용하면 효율성이 저해된다. 

파이썬 우선순위큐(`import heapq`) 사용법은 `[list].heapqpush([우선순위], [list에 넣는 value])` value는 다양한 형태로 삽입이 가능하다. 다윤 Thank you!

아래와 같이 설계해서 구현했지만 하나의 노드당 순서를 다시 만들어주고 제작 시간을 고려하지 않으므로 시간초과가 나왔다.

```python
check = []
for i in range(1, num+1):
    answer = time[i]
    if board[i]:
        check += board[i]
        while check[:]:
            #print(check)
            tmp = check[0]
            check = check[1:]
            if not tmp:
                break
            answer += time[tmp]
            #print(check, board[tmp])
            if check != board[tmp]:
                check += board[tmp]

    print(answer)
```

<pre>
위상정렬 문제가 세 번째임에도 불구하고 아직도 indgree 리스트 만드는데서 헤맸다. [추가 문제](https://www.acmicpc.net/problem/2252) 하나 더 풀어야겠다.
</pre>