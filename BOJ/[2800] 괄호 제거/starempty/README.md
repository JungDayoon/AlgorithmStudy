Simulation, 조합

괄호짝을 찾아 idx 배열에 append

괄호인 부분을 미리 ''로 바꿔둔다.

- 정답을 담는 부분
```python
for i in range(len(idx)):
    for j in combinations(idx, i):  # idx 중 i개만 뽑는 경우의 수(따라서 0번째는 항상 모든 괄호를 제거한 string)
        P = string[:]
        for x, y in j:
            P[x] = '('
            P[y] = ')'
        answer.add(''.join(P)) # 띄어쓰기를 모두 없애주고(join) add to answer
```

사전 순 출력: sorted(answer) 사용

<pre>
- remove.py
    내가 처음 작성한 부분인데 그 마저도 괄호쌍을 구현하는 부분을 혼자 구현하지 못했다. 어쨌든 그 후 수작업으로 괄호를 빼줬다.

- removeREF
    py괄호를 빼뒀다가 넣는다는 발상이 좋아서 차용했다. 

    반대로 괄호를 빼는 경우, 일부 작동하나 제출해보면 틀렸습니다가 나온다 이유 분석을 해봐야겠다.. 

    그 밖에 sorted가 괄호까지 고려하는지 몰랐는데 손 쉽게 출력할 수 있는 방법이 있어서 신기했다. 

    이 코드에서도 중복제거를 고려해줬어야했기때문에 set을 사용했다.


부쩍 enumerate를 잘 쓰게 되었다.

한 쪽으로만 생각하지말고 반대로도 생각해보자..

오랜만의 시뮬레이션이라 그런지 어려웠다.
</pre>