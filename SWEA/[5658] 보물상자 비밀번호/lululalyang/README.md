# [SWEA]/[5658] 보물상자 비밀번호

## - Simulation - 

**Main**

* `int term = N / 4;` 

  : 보물상자 한 변에 숫자 `N/4`개씩 놓이고, 중복되는 숫자가 만들어지기 전까지 회전하려면 `N/4`번 회전하게되므로 저장해준다.

* `Set<Integer> decimalSet = new TreeSet<Integer>();`

  : 중복된 비밀번호는 제외하고 순서를 계산해야하므로 중복이 불가능하고 오름차순으로 정렬되는 `TreeSet`을 사용한다.

* `term`번 회전하면서 `DivdeandTohex()` 와 `moveNum()`을 처리한다.

* `List<Integer> decimalList = new ArrayList<Integer>(decimalSet)`

  : `Set`은 index로 접근 불가능하므로 `List`로 바꾼 후 `K`번째로 큰 수를 찾아 출력한다.

------

**Func**

* `void DevideandTohex(String[] inputNum, Set<Integer> decimal, int term, int N)`

  : 한 변에 있는 숫자를 붙여 16진수 비밀번호를 만든 후, 그 비밀번호를 10진수로 변환하여 `decimal`에 추가한다.

  * 자바는 16진수를 표현하는 자료형 X -> `String`으로 표현한다.
  * 16진수 -> 10진수 예) `Integer.parseInt("1B3", 16);`

* `moveNum(String[] inputNum)`

  : 보물상자를 한 번 회전했을 때의 숫자 이동을 처리한다.