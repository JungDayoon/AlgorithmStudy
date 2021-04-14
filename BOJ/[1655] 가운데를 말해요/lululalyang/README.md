# [BOJ]/[1655] 가운데를 말해요

## *- PriorityQueue -*

### solution

우선순위 큐 2개를 사용한다.

* `PriorityQueue<Integer> small` : 가운데 값보다 작은 수. 내림차순

  > :star: Java - `PriorityQueue` `Integer`형 내림차순 설정
  >
  > ```java
  > PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
  > ```
  >
  > `Collections.reverseOrder()`를 인자로 넣어준다.

* `PriorityQueue<Integer> big`: 가운데 값보다 큰 수. 오름차순

* `int mid` : 수를 입력받을 때마다의 가운데 값

------

1. 첫 번째 숫자를 `mid`로 설정하고, 그 수를 출력.

   두 번째 숫자부터 입력받으면서 아래의 과정을 수행한다.

2. 입력받은 수가 이전 `mid`값보다 크면 `big`에, 작으면 `small`에 추가한다.

3. 현재 인덱스 확인(두 번째숫자는 `1`로 간주 => 두번째숫자부터 반복을 시작하므로 인덱스 `1`부터 시작된다)

   * 인덱스가 홀수라면, 이때까지 입력받은 수가 짝수개인 것

     => 짝수개일 때는 가운데 두 숫자 중 작은 숫자를 출력해야하므로, `small`의 크기가 `big`의 크기보다 `1`작아야한다.

     * 크기 차이가 `1`이 아니라면, 크기가 큰 pq에서 작은 pq로 옮겨준다.

       `int MoveOddIdx(PriorityQueue<Integer> small, PriorityQueue<Integer> big, int mid, int flag)`

       : 홀수 인덱스일 때의 원소 이동 메소드.

       `flag`가 `0`이면 `small`에서 `big`으로, `1`이면 `big`에서 `small`로 이동시킨다.

       이동시킬 때는 `mid`값을 크기가 작은 pq로 옮기고, 크기가 큰 pq에서 뽑은 원소를 `mid`값으로 설정한다.

       크기차이가 `1`이 되었다면, 그때의 `mid`값을 리턴

   * 인덱스가 짝수라면, 이때까지 입력받은 수가 홀수개인 것

     => 홀수개일 때는 가운데 숫자를 출력해야하므로, `small`의 크기와 `big`의 크기가 같아야한다.

     * 둘의 크기가 같지 않다면, 크기가 큰 pq에서 작은 pq로 옮겨준다.

       `int MoveEvenIdx(PriorityQueue<Integer> small, PriorityQueue<Integer> big, int mid, int flag)`

       : 짝수 인덱스일 때의 원소 이동 메소드.

       `flag`가 `0`이면 `small`에서 `big`으로, `1`이면 `big`에서 `small`으로 이동시킨다.

       이동시킬 때는 `mid`값을 크기가 작은 pq로 옮기고, 크기가 큰 pq에서 뽑은 원소를 `mid`값으로 설정한다.

       크기가 같아졌다면, 그때의 `mid`값을 리턴

4. 두 pq를 정리한 후에, 그 때의 `mid`값이 이 때까지 입력받은 수 중 가운데 숫자가 된다.

   `mid`값을 출력

