# [18223] 민준이와 마산 그리고 건우
## 분류💁

다익스트라

</br>

## 접근법

- 민준이가 건우를 도와주는 경로의 길이가 최단 경로의 길이보다 길어지지 않는다면, 건우를 도와주고 아니면 도와주러가지 않는 것이 문제의 핵심이다!

- 민준이->건우를 S, 건우-> 도착지를 P, 민준이->도착지를 D라고 했을 때
    - `S+P==D`이면 `SAVE HIM` 이고 `S+P!=D`이면 `GOOD BYE`를 출력한다.

- 다익스트라 알고리즘을 활용하여 민준이에서 시작하는 것을 `dest_fromsrc` , 건우에서 시작하는 것을 `dest_fromP`로 하여 그 사이 거리를 구한다.

- 처음에 건우 위치가 민준이와 동일하거나, 도착지에 있는 경우는 무조건 최단 거리로 건우를 데리고 갈 수 있기 때문에 바로 `SAVE HIM`이다.
</br>

## 후기💡

- 양방향 그래프인거 못봤다가 왜 틀렸는지 한참 찾고 있었다.. 으이그

