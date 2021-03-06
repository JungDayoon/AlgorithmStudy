# [Programmers]/[17680] 캐시

## - Map -

#### Solution

* `TreeMap<Integer, String> cache` : 캐시 내의 데이터를 저장하는 맵. 
  * `<들어온 순서, 도시 이름>`
  * `TreeMap`으로 선언하여서 `Key`인 들어온 순서(`Integer`)를 기준으로 오름차순으로 정렬되어 저장된다.
* `Map<String, Integer> cityName` : 현재 캐시에 있는 도시들을 저장하는 맵.
  * `<도시 이름, 들어온 순서>`
  * `Map`은 `key`를 이용해서만 데이터를 제거할 수 있으므로 부가적인 처리(삭제)를 위해 `cityName`을 만들었다.

**:bulb:과정**

1. `cache`에 해당 도시 이름이 있다면,

   * cache hit이므로 `time += 1`

   * `cityName`에서 해당 도시의 순서(`int order`)를 가져오고, 그 데이터를 삭제한다.

     `cache`에서 `order`을 key로 가지는 데이터를 삭제하고, 현재 순서인 `i`와 도시이름으로 다시 `cache.put()`해준다.

     `cachel.put()`해주었던 <도시이름 ,`i`>로 `cityName`에도 `put()`해준다.

2. `cache`에 해당 도시 이름이 없다면,

   * cache miss이므로 `time += 5`

   1. `cacheSize`가 `0`이라면, 

      캐시에 로드할 수 없으므로 패스

   2. 캐시의 크기가 `cacheSize`보다 작다면, 

      = 캐시에 자리가 있다는 것

      * `cache`와 `cityName`에 현재 순서와 도시이름을 `put()`해준다.

   3. 캐시의 크기가 `cacheSize`보다 크거나 같다면,

      = 캐시에 자리가 없다는 것

      * 가장 오래된 데이터인, `cache`의 첫번째 데이터를 가져온다. (`String rmCity`)

        `rmCity`를 사용하여서 `cityName`에서 해당 도시의 순서를 가져오고, 그 데이터를 삭제한다.

        가져온 순서로 `cache`에서 해당 도시 데이터를 삭제하고, 새로운 순서와 현재 도시 이름을 `cache`와 `cityName`에 `put()`해준다.

3. `cities`배열에 있는 모든 도시를 처리해준 후 `time`을 리턴.

</br>

## :speaking_head:

* 금방 풀긴 했지만, 뭔가 비효율적으로 푼 것 같다. 다른 방법도 생각해보자 