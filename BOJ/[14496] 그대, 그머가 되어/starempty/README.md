다익스트라

주어진 그래프가 양방향 그래프임을 나타내기 위하여 graph 딕셔너리에 양방향 정보를 모두 넣어준다.

다익스트라 방법으로 동일 진행 -> 도착 노드 weight(in dp 딕셔너리) 출력

(예외처리 필요)
```python
if b not in dp:
    print(-1)
else:
    print(dp[b])
```
<pre>
어제 의량이와 해킹 다익스트라 구현 시뮬레이션을 엄청 했더니 그 코드가 이해가 됐는지.. 이용해서 풀었다 뿌듯

처음에는 시작노드를 a로 한 것과 b로 한거 나눠서 계산하려고 했는데(방향이 상관없으니까)

애초에 graph에 방향성이 있어야 가능하다는 것을 다윤이 코드 보고 알게되어서 graph에 반대 방향도 추가해줬다.

92퍼센트에서 계속 틀렸습니다가 나와서 마음이 초조했는데 

dp 배열에 도착노드 정보가 없는 경우(연결이 안되있는 경우)를 빠뜨려서 예외처리를 해주니 바로 맞았습니다.되었다

</pre>