이분탐색을 이용하였습니다.

첫 번째 시도는 직관적으로 이중 포문을 이용해 모든 수의 배열을 만들고 정렬한 뒤 입력받은 순서의 수를 반환했습니다.
메모리 초과로 실패했습니다.
<pre>
<code>
def main():
  n = int(input())
  k = int(input())
  
  ans = []
  for i in range(1, n+1):
    for j in range(1, n+1):
      ans.append(i*j)
  ans.sort()
  print(ans[k])

main()
</code>
</pre>

두 번째 시도는 이분탐색을 이용해야한다는 것을 인지한 후 count함수를 만들어서 기준수보다 작은 수를 카운트하는 함수를 이용하였습니다.
배열을 사용하지 않기때문에 메모리는 넘지 않지만 여전히 이중포문을 사용하기 때문에 시간이 오래걸렸습니다.
<pre>
<code>
def count():
  n = 3
  m = 4 #mid값으로 설정될 것,, m이하갯수를 반환..
  count = 0

  for i in range(1, n+1):
    for j in range(1, n+1):
      if i*j <= m:
        count += 1
      
  return count
count()
</code>
</pre>

세 번째 시도는 답을 찾아봤습니다... 도저히 제약조건을 떠올릴 수가 없어서 힌트만 얻고자 검색했는데 답을 봐버렸습니다.
<pre>
<code>
    for i in range(1, n+1):
        cnt += min(mid//i, n)
</code>
</pre>

핵심계산 식입니다. 비교대상을 i로 나눈 몫과 n중 더 작은 값이 각 행 중 비교대상보다 작은 수의 갯수임을 알게되었습니다...

비슷한 이분탐색 문제도 하나 더 풀었습니다.
백준 기타레슨: https://www.acmicpc.net/problem/2343

