# [BOJ]/[1525] 퍼즐

## *- BFS -*

* `Queue<Data> q`

  : `q`에는 현재 퍼즐의 상태를 `StringBuilder`로 바꾼 것과, `0`의 `x`, `y`좌표, 그리고 `0`이 움직인 횟수를 저장한다.

* `Map<String, boolean> visited`

  : 방문여부는 퍼즐의 상태로 고려해주는데, 이때에는 `HashMap`을 사용한다.

  * `String` : 퍼즐의 상태를 `String`으로 저장

* `q`가 빌 때까지, `0`을 상하좌우로 움직여가며 `bfs`를 진행한다.

  * 만약, 현재의 퍼즐 상태가 정리된 상태라면 그때의 `cnt`값을 리턴한다.

    ` boolean ChkDone(StringBuilder list)` 

    : `list`가 `"123456780"`상태라면 `true`를, 그렇지 않으면 `false`를 리턴한다.

</br>

## :speaking_head:

* bfs문제는 파악했지만 방문여부를 어떻게 해줘야할 지 모르겠어서 방법을 참고하였다.

  * 무조건 방문여부는 `boolean`배열이나 `int`배열로 처리해주었었는데, 다른 여러가지 방법도 적용할 수 있다는 것을 알았다 !!

* 퍼즐의 상태인 `int`배열을 `String`으로 바꿔서 `q`나 `visited`에서 사용해주었는데,

  * 이때, `String`과 `StringBuilder`의 메소드가 익숙하지 않아 한참 헤맸다.

  > **:star: Java**
  >
  > **<`String`의 `replace()`메소드>**
  >
  > : 자바 문자열은 불변(immutable)이기때문에, 어떤 연산에 의해서 원본 문자열이 바뀌지 않는다. 따라서 원본은 그대로 유지되고, 바뀐 문자열이 새로 생성되어서 반환된다. 
  >
  > => 바뀐 문자열을 반환값으로 받아야한다.
  >
  > ```java
  > String str = "103425786";
  > str = str.replace('0', '9'); // 이런식으로 다시 str에 받아주어야 함
  > ```
  >
  > > 이 부분때문에 ***메모리초과*** 가 난 것 같다.
  >
  > **<`StringBuilder`의 `replace()`메소드>**
  >
  > : StringBuilder는 문자열 수정이 가능하다. 
  >
  > => 즉, 처음 문자열 하나만 할당하면 그 할당된 메모리에서 변환
  >
  > => 메모리 사용량이 적어짐
  >
  > ```java
  > StringBuilder sb = new StringBuilder("103425786");
  > sb.replcae(0, 1, "0"); // 이런식으로 바꾸려는 문자열의 시작인덱스와 끝인덱스, 그리고 바꾸려는 문자열을 전달해줘야 함
  > ```

  > **:star:Java : Char을 String으로, String을 Char로**
  >
  > **<`Char`을 `String`으로>**
  >
  > ```java
  > // 1. Char를 String으로 변환
  > char fromChar = 'A';
  > String toStr = Character.toString(fromChar); // Character.toString사용
  > 
  > // 2.Char형 배열을 String으로 변환
  > char[] cArr = {'a', 'b', 'c', 'd', 'e'};
  > String Str = String.valueOf(cArr); // String.valueOf() 사용
  > ```

  > **:star:Java : Char을 int로 변환**
  >
  > ```java
  > char charNum = '1';
  > // 방법 1
  > int num1 = charNum - '0'; // char - '0'을 이용하면 int형으로 변환 가능
  > 
  > // 방법 2
  > int num2 = Character.getNumericValue(charNum); // 아스키코드값 이용
  > ```