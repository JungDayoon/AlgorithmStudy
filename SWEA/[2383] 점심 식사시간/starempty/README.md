알고리즘: 시뮬레이션

문제점:
1. 모든 사람이 계단을 선택할 때에 거리순이라고 착각했다. > 최종결과에 따라 달라질 수 있음으로 사람들이 계단을 선택할 수 있는 모든 경우의 수를 고려했어야했음
2. 시간이기때문에 가장 늦게오는 사람의 시간만 리턴해주면된다고 생각했다. > 하지만 대기시간이 존재한다.
3. 본인이 dfs를 이해하지 못했다.
4. combinations와 permutations의 차이를 정확히 이해하지 못해 처음 이틀 다른 사람의 코드를 봐도 전혀 이해하지 못했다.

해결방법:
1. combinations 함수 돌려서 인덱싱가지고 모든 경우의 수를 만들었다.(다윤이 코드 참고하였음)
<pre><code>
answer = []
        for i in range(n):
            choose = list(combinations(range(0,len(person)), i))
            for j in choose:
                index = []
                first = []
                for k in j:
                    first.append(person[k][0]+stair[0])
                    index.append(k)
                second = arrange(index, person, stair)
                answer.append(calculate(first, second, stair))
</code></pre>
2. 대기시간을 고려하여야함으로 (가장 늦게 오는 사람으로부터 세 사람 전의 시간 + 내려가는시간)과 비교하여 max값을 반환함으로써 대기시간을 계산할 필요가 없었다.
<pre><code>
def calculate(first, second, stair):
    st = sorted(first)
    nd = sorted(second)
    time1 = 0
    time2 = 0
    for i in range(len(st)):
        if i < 3:
            time1 = st[i]
        else:
            time1 = max(st[i], st[i-3]+stair[0])
    for j in range(len(nd)):
        if j < 3:
            time2 = nd[j]
        else:
            time2 = max(nd[j], nd[j-3]+stair[1])

    return max(time1, time2)
</code></pre>
3. [백준 n과m 시리즈](https://www.acmicpc.net/problem/15649)를 모두 풀었습니다... 하지만 파이썬 내장함수를 사용했기때문에 재귀를 이용한 dfs공부가 더 필요하다..
4. 아직도 잘 모르겠지만.. 실제로 돌려서 아웃풋을 확인하는 방법으로 어찌어찌 답은 냈습니다..

공부:
1. dfs와 bfs의 차이
  깊이우선탐색(재귀,스택) 너비우선탐색(큐)
  
  하나의 배열을 전역으로 선언해두고 dfs함수에 들어가면 그 배열에 값을 넣고 인덱스 높여서 그 함수를 다시 호출하는 것까지는 이해했다.(하지만 이부분도 bfs같은데 왜 이게 dfs인지 모르겠다.)
  
  하지만 언제 전역 배열을 초기화하는건지는 아직 이해가 가지 않는다..
  
  어쨌든 같은 배열에 접근해서 넣어주는 것인데..
  특정 인덱스값에 넣어도 한계점을 넘어서 배열에 추가되는 모습을 보고 환장했다..
  
  사실 노드를 탐색하면서 배열은 하나인데 모든 가짓수의 노드가 하나의 배열에 들어가는 것이 문제라고 생각한다. 어떻게 해결해야할지 고민해봐야겠다..
  
2. combinations(순열), permutations(조합)
   
   순열은 주어진 리스트에서 주어진 값만큼 뽑는데 모든 가짓수를 출력한다.
   
   조합은 주어진 리스트에서 주어진 값만큼 뽑는데 순서를 고려하여 중복을 제거한 뒤 모든 가짓수를 출력한다.
   
   (어떤 문제는 순열써서 풀면 된다고 생각했는데, 이분탐색써야하고 어떤건 조합써야하고 완전 다르다.. 가짓수를 먼저 생각하고 무엇을 써야할지 정확하게 판단한 뒤 코드를 만드는 것이 좋다고 느낀다.)

  
