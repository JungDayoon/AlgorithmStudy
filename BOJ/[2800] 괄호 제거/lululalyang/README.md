# [BOJ]/[2800] 괄호 제거

## *- Stack, Combination -*

* `Stack`을 사용해 여는 괄호('`(`')와 닫는 괄호('`)`')의 index를 저장한다.  

  * `ArrayList<Integer> open = new ArrayList<>(); //여는 괄호`
  * `ArrayList<Integer> close = new ArrayList<>(); //닫는 괄호`

  * 입력 받은 수식에서 한 문자씩 확인한다.

    * `)`가 아니면 `stack.push()`

    * `)`를 만나면 `push()`하지 않고 `(`를 만날 때까지 `stack.pop()`한다

      `pop()`하면서 그 횟수를 저장한다(`popcnt++`)

    * `(`를 만나면 `(`의 index를 `open`에, `)`의 index를 `close`에 `add()`해준다.

      추가로, 다음 괄호의 index를 편하게 처리하기 위해 `popcnt+2`만큼 `0`을 `push()`해준다.

* 1개부터 괄호쌍의 개수만큼 까지 어떤 괄호쌍을 지울지 조합으로 쌍을 만든다.

  => `ArrayList<ArrayList<Integer>> c`에 저장 (지워야하는 괄호쌍의 index)

* `c`를 돌면서, 그에 해당하는 괄호쌍을 지워주고(`void DeleteBracket()`) 그 결과값의 수식을 `treeSet`에 저장한다.

  * `void DeleteBracket(char[] cArr, ArrayList<Integer> ctmp, ArrayList<Integer> open, ArrayList<Integer> close)`

    : 현재 지워야하는 괄호의 index에 해당하는 `cArr`부분을 '`_`'로 바꿔준다.

     `cArr`를 다시 돌면서 '`_`'가 아닌 부분만을 `StringBuilder sb`에 담는다.

    `sb`를 `string`으로 바꾼 후 `treeSet`에 `add()`한다.

    > **:star:java**
    >
    > `StringBuilder`: `char` 배열을 `string`으로 바꿔주기 위해 사용한다.
    >
    > ​							그냥 `char`배열을 `.toString()`하면 한 글자씩 구분되어 따로 저장됨
    >
    > :heavy_plus_sign: `StringBuilder, StringBuffer`의 특징(공통점)
    >
    > : 문자열의 저장/변경을 위한 메모리 공간을 지닌 클래스.
    >
    >  문자열 데이터의 추가를 위해 `append`와 `insert`메소드를 지니고 있다.
    >
    > 이 둘의 메모리 공간을 `String`클래스와는 달리 변경 가능한 변수의 성격을 지닌다고 할 수 있다.
    >
    > :heavy_plus_sign: 차이점
    >
    > : `StringBuilder`는 단일 스레드 환경에서만 사용가능 (Synchronization을 허용하지 않음)
    >
    > `StringBuffer`는 멀티스레드 프로그래밍에서 사용 (동시에 처리하는 것을 허용함)

    </br>

    > **:star: java**
    >
    > `TreeSet`:  `Set` 인터페이스의 구현 클래스. (=> `Set`성질 그대로 상속받음)										
    >
    > ​				**객체를 중복해서 저장할 수 없다(중복 자동 제거)**. 하나의 `null`값만 저장가능
    >
    > ​				기본적으로 **오름차순으로 데이터를 정렬**한다.
    >
    > :heavy_plus_sign:`HashSet`: `  TreeSet`과 특징이 같고, **저장 순서가 유지되지 않는다.**

## :speaking_head:

처음에는 `stack`을 쓰지않고 그냥 배열을 돌면서 괄호의 index를 찾아주었는데, 런타임에러였다.

다시 지혜 말 듣고 `stack`써서 괄호 index찾는 부분만 바꿔줬더니 통과했다!

근데 `stack`말고도 `StringBuilder`라든지, `HashSet`, `TreeSet`이라든지 새롭게 써보는 것들이 많아서 엄청 오래걸리고 어려웠다 ㅜ 이번에 잘 알아두고 다음에 쓸 수 있도록 해야겠다



