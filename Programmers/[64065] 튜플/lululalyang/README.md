# [Programmers]/[64065] 튜플

## *- simulation -*

### solution

1. 가장 바깥의 괄호와 첫번째 원소의 앞 괄호와 마지막 원소의 뒷 괄호를 제거한다. 

   예) **{{**a}, {a, b}, {a, b, c, d}, {a, b, c**}}** (볼드체 부분)

   ```java
   s = s.substring(2, s.length()-2);
   ```

2. 한 집합씩 나누어 배열로 만든다.

   > `"},"`로 `split()`한 후, 1번째 인덱스부터 앞괄호 `{`를 제거해주었다.

3. 원소의 길이에 따라 오름차순으로 정렬한다.

   > :star: String배열 길이순 정렬하기
   >
   > ```java
   > String[] str = new String[n];
   > 
   > Arrays.sort(str, new Comparator<String>(){
   >     @Override
   >     public int compare(String s1, String s2){
   >         return s1.length() - s2.length;	// 앞 변수가 크면 양수값을, 작으면 음수값을 리턴 => 오름차순 정렬 (그 반대는 내림차순 정렬)
   >     }
   > })
   > ```

4. 정렬한 배열을 스캔하면서 아래 과정을 반복한다.

   * `int idx = 0` : 리턴배열인 `int[] answer`의 인덱스 처리, `0`으로 초기화
   * `Map<Integer, Boolean> chk` : 이때까지 튜플에 넣은 정수를 key로 갖는  맵

   1. 배열에 저장된 집합 내의 원소를 확인하면서 만약 `chk`에 없는 원소라면, 그 원소가 튜플의 `idx`번째 원소가 된다.
   2. `answer[idx]`에 그 원소를 저장하고, `chk`에 원소를 `put()`한다.
   3. `inx`를 `+1`



