Simulation, DFS(순열 구현)

파이썬은 permutations로 쉽게 순열 구현이 가능하다는 것은 알고 있지만 4번째 타자가 고정되어있다는 점과 DFS 보충학습을 고려하여 DFS로 구현해보았다. 

그 밖의 타자 움직임과 아웃, 득점은 시키는대로 구현하였다.

파이썬으로 구현하다보니 시간초과를 해결해야했는데 pypy3로 제출했으며 

앞으로 큰 인풋값은 `sys.stdin.readline()`으로 받고, 간단한 배열의 경우에는 변수로 처리하는 방법을 생각해야겠다.

특히 다른 분의 코드를 참고했는데, 각 루에 사람이 있는지를 판단하는 방법을 쉽고 직관적이게 짠 것 같다.

```python
                if i[order[j]] == 0:
                    out += 1
                elif i[order[j]] == 1:
                    score += b3
                    b1, b2, b3 = 1, b1, b2
                elif i[order[j]] == 2:
                    score += b3 + b2
                    b1, b2, b3 = 0, 1, b1
                elif i[order[j]] == 3:
                    score += b3+b2+b1
                    b1, b2, b3 = 0, 0, 1
                else:
                    score += b1+b2+b3+1
                    b1, b2, b3 = 0, 0, 0
```