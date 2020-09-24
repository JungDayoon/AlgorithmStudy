# [BOJ 1992] 쿼드 트리 - Python

### :computer: Algorithm

분할정복

### :computer:Logic

`quadTree(x_start, x_end, y_start, y_end)` 재귀함수로 간단히 해결할 수 있다.

현재 탐색하는 범위 내의 원소들이 모두 같은 값을 가지는 지 확인하고, 다 같은 값을 가진다면 outStr에 그 값을 추가해준다.

다르다면, 네 개의 범위로 분할하여 똑같은 방법을 수행하면 된다.

 ```python
x_mid = (x_start+x_end)//2
y_mid = (y_start+y_end)//2
quadTree(x_start, x_mid, y_start, y_mid)
quadTree(x_mid, x_end, y_start, y_mid)
quadTree(x_start, x_mid, y_mid, y_end)
quadTree(x_mid, x_end, y_mid, y_end)
 ```

### :computer: Review

재귀함수를 사용하면 짧고 깔끔하게 풀 수 있다

1년전에 풀어봤던건데 그때보다 코드를 더 깔끔하게 짠 거 같아서 뿌듯하당 ~