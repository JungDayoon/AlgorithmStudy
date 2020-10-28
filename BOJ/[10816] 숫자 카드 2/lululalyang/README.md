# [BOJ]/[10816] 숫자 카드 2

## *- Binary Search -*

> * **이진탐색**
>
>   : **중복되지 않은 자료** 값이 주어질 때 그 데이터 내에 **특정 값**이 존재하는지 검색
>
> * **이진탐색 - 상/하한선(lower bound, upper bound)**
>
>   : **중복된 자료**가 있을 때 유용하게 탐색. 
>
>   * lower bound: 특정 값과 **같거나 큰 값이 처음** 나오는 index를 return
>   * upper bound: 특정 값보다 **처음으로 큰 값**이 나오는 index를 return

</br>

* `Arrays.sort(arr);`

  : 우선 이진탐색을 위해 숫자카드를 정렬한다

* `int lowerBound(int search, int[] arr)`

  : 찾는 값과 **같거나 큰 값**이 **처음** 나오는 index를 return.

  * `int left = 0;`

  * `int right = arr.length;`

    : `arr`의 마지막 숫자보다 큰 숫자를 찾는다면 `arr.length-1`이 아니라 `arr.length`를 return해야하므로 `right`를 이렇게 설정해준다

  ```java 
  while(left < right) {
  	int mid = (left + right) / 2;
  	if(arr[mid] >= search) { // 같거나 큰 값을 찾으면 그 값이 처음으로 나오는 
  		right = mid;		// index를 찾는 거니까 right=mid로 범위를 좁혀줌
  	}else { 
  		left = mid+1; // 찾는 값보다 작은 값이라면 큰 범위에서 찾아준다
  	}
  }
  ```

* `int upperBound(int search, int[] arr)`

  : 찾는 값보다 **큰 값**이 **처음**으로 나오는 index를 return

  * `int left, right`는 `lowerBound()`와 동일하게 설정
  * 찾는 값보다 크다면 `lowerBound()`와 동일하게 `right=mid`로 설정해주면서 범위를 좁혀준다. 
  * 찾는 값보다 작거나 같다면 마찬가지로 `left=mid+1`로 설정해 큰 범위에서 찾아준다.

* 이렇게 찾은 찾는 값의 "시작 index"와 "끝 index+1"를 이용해 해당 숫자의 카드 개수를 구한다.

</br>

## :speaking_head:

* 중복된 자료가 있을 때는 하한선, 상한선 위치를 찾는 방식으로 이분탐색을 해야한다는 것을 알게 되었다! 원래 이분탐색 방식에서 조금만 변형하면 되니까 잘 익혀두고 활용해야겠당

* *시간초과*  진짜 10번 넘게 보고나서 문제점을 알았따 ... 🤦‍♀️🤷‍♀️

  > :star: Java - [ `StringBuilder` vs `System.out.println` ]
  >
  > * `System.out.println`
  >
  >   : 내부적으로 확인해보면 `Synchronized block`으로 씌여져 있다.
  >
  >   ```java
  >   public void println(String x) { 
  >       synchronized (this) { 
  >           print(x);
  >           newLine(); 
  >       } 
  >   }
  >   ```
  >
  >   `synchronized`는 말 그대로 **동기화**를 말한다. 하나의 프로세스에는 하나 이상의 스레드가 존재하는데 스레드를 통해 같은 프로세스에서 데이터 공유가 가능하다. 때로는 공유 데이터가 작업 중인 스레드가 마칠 때까지 다른 스레드에서 접근하지 못하도록 하기 위한 것이 **동기화**이다. 이는 작업중인 스레드가 마칠 때까지 다른 스레드들이 **대기시간이 발생**한다는 단점이 있다. -> 작더라도 **오버헤드** 발생!
  >
  >   (오버헤드: 어떤 처리를 하기 위해 들어가는 간접적인 처리시간/메모리 등)
  >
  > * `StringBuilder`
  >
  >   : `String`과 동일하지만 문자열을 보다 쉽게 조작할 수 있는 클래스이다.
  >
  >   출력할 데이터들을 모아두고 한 번의 `System.out.println`를 이용해 출력할 수 있다.
  >
  > :arrow_forward: 반복해서 출력해야하는 경우가 많다면 `StringBuilder`를 사용하자!
  >
  > ​	둘 사이 속도차이가 매우 크다 ... 입출력 시에도 시간초과가 날 수 있다는 것 주의하기!!

   

