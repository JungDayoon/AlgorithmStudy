## [SWEA]/[2383] 점심 식사시간

## *- Simulation & Combination -*

* `ArrayList<ArrayList<Integer>> C`: 사람을 두 계단으로 나눠서 처리하기 위한 조합을 담을 `ArrayList`.

* `int[][] stairs = new int[2][3]`: 두 계단의 x, y좌표, 높이

* `ArrayList<int []> q`: 사람의 위치 x, y좌표 저장

* *in `Main`*

  * `for`문을 돌면서 사람의 수로 `0`에서 `q.size()/2`까지 만큼으로 만들어 낼 수 있는 조합을 가지고, 한 집합은 A 계단으로, 다른 한 집합은 B 계단으로 내려갔을 때의 최소 시간을 구한다. (같은 조합집합들로 `A`,`B`반대로도 처리해준다.)

    ```java
    minTime = Math.min(minTime, distToStairs(a, C2, stairs, q)); //a가 0 ~ q.size()/2 개로 이루어진 조합 집합
    minTime = Math.min(minTime, distToStairs(C2, a, stairs, q)); //C2는 a에 속하지않는 원소로 이루어진 집합
    ```

  </br>

* `int distToStairs(ArrayList<Integer> C1, ArrayList<Integer> C2, int[][] stairs, ArrayList<int []> q)`

  :  `C1`에 속하는 사람은 `A`계단과의 거리를 `ArrayList<Integer> dist1`에, `C2`에 속하는 사람은 `B`계단과의 거리를 `ArrayList<Integer> dist2`에 저장한다.

  그 후, `calculation(dist1, dist2, stairs, q.size())`을 `return`.

  </br>

* `int calculation(ArrayList<Integer> dist1, ArrayList<Integer> dist2, int[][] stairs, int peopleN)`

  * `ArrayList<Integer> s1`: 계단 `A`에 있는 사람들의 정보

  * `ArrayList<Integer> s2`: 계단 `B`에 있는 사람들의 정보

  * `int sl1 = (-1) * stairs[0][2] -1`: `A`계단높이 * (-1) -1 => 계단 도착 뒤 1분 후에 내려갈 수 있으므로

  * `int sl2 = (-1) * stairs[1][2] -1`: `B`계단높이 * (-1) -1 => 위와 같은 이유

  * 시간 `time`을 늘려주며 이동완료한 사람의 수 `finish`가 전체 사람의 수`peopleN`가 될 때 까지 이동을 처리해준다.

    ```java
    while(finish != peopleN) {
    	time++;
    			
        if(d1N != 0)
    		d1N = move(dist1, s1, d1N, reald1N); 
    	if(d2N != 0)
    		d2N = move(dist2, s2, d2N, reald2N);
    			
    	if(s1.size() != 0)
    		finish = remove(s1, sl1, finish); 
    	if(s2.size() != 0)
    		finish = remove(s2, sl2, finish); 
    }
    ```

    </br>

* `int move(ArrayList<Integer> dist, ArrayList<Integer> s, int dN, int N)`

  : 사람 각각 계단까지의 거리를 `1`씩 줄여주고, 계단에 3명보다 적게 있다면 계단에 올려준다.

  * `dist` : 처리하려는 사람 집합의 계단까지의 거리
  * `s`: 해당 계단을 내려가고 있는 사람의 정보
  * `dN`: 계단까지 이동하고 있는 사람의 수
  * `N`: 현재 집합의 전체 사람의 수
  * 계단까지 도착하고, 계단에 오를 수 있으면 `dist`에서 없애고, `dN`을 감소시킨다. 이 `dN`값을 `return`한다.

</br>

* `int remove(ArrayList<Integer> s, int stairL, int finish)`

  : 각 계단에 있는 사람을 한 칸씩 내려주고, 만약 그 계단의 높이만큼 다 내려왔다면 계단에서 없애준다.

  * `stairL`: 해당 계단의 높이를 처리해준 수. (위의 `sl1`, `sl2`를 말함)
  * 계단을 다 내려온 사람의 수를 더해주고 그 값`finish`를 `return`한다.

</br>

## :speaking_head:

조합을 써야하고 해야하는 처리가 많아 푸는데 가장 오래걸린 문제이다 ..

그래도 뒷쪽 문제들을 먼저 풀어보면서 조합 구현을 할 수 있게 되었고, 친구들의 readme를 참고하면서 문제해결 방향을 차근차근 찾아서 마 침 내 해결할 수 있었다 ㅜ