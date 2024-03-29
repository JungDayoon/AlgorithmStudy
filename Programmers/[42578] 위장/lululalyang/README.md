# [Programmers]/[42578] 위장

## *- Hash, Map -*

```java
Map<String, Integer> map = new HashMap<>(); // <의상종류, 의상개수>
ArrayList<String> kinds = new ArrayList<>(); // 의상 종류 리스트
```

1. `clothes`배열을 스캔하면서 `map`과 `kinds`를 완성시킨다.

2. 전체 경우의 수를 구한다.

   예를 들어, 의상 종류가 {`상의`, `하의`}로 두 가지라면

   (`상의의상개수 + 안입는 경우`) * (`하의의상개수 + 안입는 경우`) 

   = (`상의 의상개수 + 1`) * (`하의 의상개수 + 1`)

   과 같이 구해준다.

   그리고, 하루에 최소 한 개의 의상은 입어야 하므로 아무것도 입지 않는 경우 `1`을 빼준 값이 결과값이된다.

</br>

## :speaking_head:

처음엔 의상 종류로 입을 수 있는 경우를 *조합* 을 이용해서 모두 구해준 후에 결과값을 구했는데, 테스트케이스 1번에서 계속 시간초과가 떴다.

아무리 바꿔봐도 시간초과여서 참고했는데, 보니까 아예 접근 방법이 틀렸다.

조합으로 모든 경우를 구하면 시간초과가 뜨고, 위의 방법과 같이 간단히 곱셈으로 접근해야 해결가능하다! 다양한 방법을 생각해보자