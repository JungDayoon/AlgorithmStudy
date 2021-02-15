# [BOJ]/[1497] 기타콘서트

## *-Backtracking-*

**Main**

* nC**0**부터 nC**n**까지의 조합을 만들기 위해 `Comb()`호출

**Func**

* `void Comb(int N, int r, int start, ArrayList<Integer> tmp)`
  * nCr의 조합을 만드는 함수 (`N`개의 기타 중에 `r`개의 기타를 고른다)
* `void CheckCanPlay(ArrayList<Integer> tmp)`
  * `tmp` : `r`개의 기타정보가 담겨있는 리스트
  * `tmp`에 들어있는 여러 기타가 연주할 수 있는 곡의 최대 개수를 구한다.
    * 그 개수가 이때까지의 최대 곡 수보다 많다면 곡 수와 기타 개수를 갱신
    * 그 개수가 최대 곡수와 같다면 적은 값의 기타 개수로 갱신

## :speaking_head:

* 조합은 이제 익숙해서 금방 해결했는데 무슨 생각인지 N/2까지의 조합만 구해서 틀렸다 .. 집중 딱 해서 풀좌 😂