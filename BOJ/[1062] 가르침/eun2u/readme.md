# [1062] 가르침
## 분류💁

브루트 포스, 조합

</br>

## 접근법
> 남극언어의 모든 단어는 "anta"로 시작되고, "tica"로 끝난다. 남극언어에 단어는 N개 밖에 없다고 가정한다. K개의 글자만 알고있는 학생들이 읽을 수 있는 단어의 최댓값을 구하는 프로그램을 작성하시오.
- "anta"와 "tica"가 무조건 포함되기 때문에 a,n,t,i,c 5글자는 무조건 알아야한다
    - K가 5보다 작으면 무조건 0개
- `teachWords()`에서 알파벳 26개 중에서 K개를 뽑는다. 하지만 5개를 이미 알고 있으니 21Ck 가 가능하다.
- `countWords()`에서 알고있는 알파벳으로 읽을 수 있는 단어 개수를 리턴한다.

</br>

## 후기💡
- 처음에 각 단어들을 읽을 수 있는지로 백트래킹을 시도했다. 매번 재귀호출때마다 `bool alpha[]`를 들고 가고, 매번 새롭게 배워야하는 알파벳 숫자를 구해서 그런지 시간초과였다.
- 아예 새롭게 단어가 아닌 알파벳으로 조합을 통해 문제를 풀었다. 왜 이걸 먼저 생각하지 못했을까 