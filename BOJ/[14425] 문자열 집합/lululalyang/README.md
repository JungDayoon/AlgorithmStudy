# [BOJ]/[14425] 문자열 집합

## - map, HashMap -

> * `Map`
>
>   : 저장되는 데이터의 형식은 'key-value'쌍이다. (key-value는 매칭되어있음)
>
>   특정 데이터를 찾을 때는 key를 이용해 검색한다 (key는 중복이 안되기 때문)
>
>   key없는 value는 없다. value는 중복가능하다.
>
>   - 관련 메소드: `put()`, `get()`, `remove()`
>
> * `HashMap`
>
>   : 키나 값에 `null`값 저장이 가능. 여러 쓰레드에 안전X
>
>    데이터 순서 없다 (정렬X)
>
>   * 생성 예시
>
>     `Map<String, String> map1 = new HashMap<>();`
>
>   * 관련 메소드: `put()`, `get()`, `getKey()`, `getValue()`, `containseKey()`, `containsValue()`, `remove()`, `size()`
>     * `get()` : 해당하는 key가 없을 때는 `null`이 return.
>     * `put()`: 해당하는 key가 이미 존재한다면 value값을 덮어쓰기한다
>
> * `TreeMap`
>
>   : 데이터를 key를 기준으로 정렬한다. 
>
>   순서가 중요한 클래스 -> 순서와 관련된 메소드가 있다
>
>   * 생성 예시
>
>     `TreeMap<String, String> map2 = new TreeMap<>();`
>
>   * 관련 메소드: `firstKey()`, `lastKey()`, `higherKey()`, `lowerKey()` 등
>
>     * `higherKey()`: 해당 key 다음 순서의 key를 return
>     * `lowerKey()`: 해당 key 이전 순서의 key를 return

</br>

* `HashMap`의 `put()`과 `containsKey()`메소드를 사용하였다.

</br>

## :speaking_head:

트라이로 해보려고 했지만 ,, Map공부부터 했습니다...! 

좀 더 공부한 후에 트라이로 풀어보도록 하겠습니당 ! 😙