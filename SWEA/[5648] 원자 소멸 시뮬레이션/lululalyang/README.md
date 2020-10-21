# [SWEA]/[5648] 원자 소멸 시뮬레이션

## *- Simulation -*

* `static int map[][] = new int[4001][4001];`

  `A.add(new atom(` **`2*Integer.parseInt(s[0]), 2*Integer.parseInt(s[1])`** `, Integer.parseInt(s[2]), Integer.parseInt(s[3])))`;

  : `1.5`시간에 만나는 원자들을 쉽게 처리하기 위해 원자의 각 좌표에 `*2`를 해주고, 전체 좌표도 두배로 늘려준다. (범위 `-1000<=x,y<=1000`에서 `-2000<=x,y<=2000`으로)

* `static ArrayList<atom> A = new ArrayList<atom>();`

  : 원자의 정보(x,y좌표, 이동시간, 에너지)는 `Class atom`을 만들어 저장.

  각 원자를 `ArrayList`를 이용해 저장하였다.

* *in `void colliAtom()`*

  * `while(A.size() != 0)`

    : 원자가 범위내에 존재하지 않을 때까지 수행한다.

  * 각 원자의 위치를 이동방향(`A[i].d`)에 따라 이동시킨다.

    * 그 위치가 범위를 벗어나면 해당 원자를 삭제한다. 

      ```java
      A.remove(i); //리스트에서 지우고
      i--; //다음 원자를 확인하려면 index가 그대로니까 i-- 한다
      ```

    * 범위 내에 있으면 원자의 위치에 해당하는 `map`의 값을 `+1`한다.

      `map`값이 `2`이상이면 현재 위치에서 원자들의 충돌이 일어난 것 ==> 이 위치 `check`에 넣어준다

      ```java
      static ArrayList<int[]> check = new ArrayList<>();
      ```

      ```java
      map[A.get(i).y+2000][A.get(i).x+2000]++;
      if(map[A.get(i).y+2000][A.get(i).x+2000] >= 2) {
      	check.add(new int[] {A.get(i).y, A.get(i).x});
      }
      ```

  * 다시 각 원자를 돌면서 원자의 위치가 `check`에 존재하면 충돌이 일어난 거니까 현재 원자의 에너지(`A[i].k`)를 `result` 에 더해주고, `map`의 값을 `-1`하고 현재 원자를 `A`에서 제거한다. 

## :speaking_head:

계속 런타임에러에 메모리에러가 떠서 swea에서 실행도 못했는데 .....

알고보니까 class이름을 'Solution'으로 해줘야되는데 Main'으로 해주고 있 었 따 ㅜ 

오랜만에 풀었더니 이것도 까먹어부렸다😭😭 이제는 헷갈리지 않기 ......................