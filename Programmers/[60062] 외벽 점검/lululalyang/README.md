# [Programmers]/[60062] 외벽 점검

## *- Permutation, Simulation -*

#### Solution

1. 이동 거리 배열 `dist`를 내림차순 정렬한다.

   * 보내야 하는 **친구 수** 최소값을 구해야 하는 문제로, 가장 이동거리가 긴 친구들로 골라 취약지점을 점검할 수 있는지 확인하는 것이 효율적이다. (어느 이동거리를 사용했는지는 필요하지 않으니까!)

   > **:star: Java  [int 배열 내림차순 정렬]**
   >
   > ```java
   > int[] dist = {1, 2, 3, 4};
   > Integer[] dist_ = Arrays.stream(dist).boxed().toArray(Integer[]::new);
   > Arrays.sort(dist_, Collections.revoerseOrder());
   > ```
   >
   > * `int`형 배열은 `String`타입 배열과 같이 쉽게 내림차순 정렬할 수 없다.
   >
   >   ```java
   >   String[] arr = {a, b, c, d};
   >   Arrays.sort(arr, Collections.reverseOrder());
   >   ```
   >
   > * 위와 같은 방법으로 정렬할 수 없어서 `int`형 배열을 `Integer`타입 배열로 변경해준 후, 정렬해주어야한다.

2. 친구수를 `1`명에서 `dist.length`명까지 늘려가며 확인한다.

3. 현재 친구 수만큼의 이동거리로 순열을 구한다.

   * 친구수가 `f`라고 하면, `f`개의 이동거리로 만들수 있는 모든 순서를 구하는 셈
   * nPr => n=`f`, r=`f`

   ```java
   void Perm(int N, int r, ArrayList<Integer> tmp, boolean[] visited){
       if(r == 0){
           // 이때의 tmp가 순열 집합 하나
           return;
       }
       
       for(int i=0; i<N; i++){
           if(!visited[i]){
               tmp.add(i);
               visited[i] = true;
               Per(N, r-1, tmp, visited);
   
               tmp.remove(tmp.indexOf(i));
               visited[i] = false;
           }
       }
   }
   ```

4. 만들어진 모든 순열집합을 이용해 모든 취약지점을 점검할 수 있는지 확인한다.

   `boolean CanCheckAll(ArrayList<Integer> dist, int[] weak, int n)`

   * `ArrayList<Integer> dist` : 이동거리의 순서

   * `dist`의 순서대로 각 취약지점을 첫 시작지점으로 해서 모두 점검할 수 있는지 확인한다.

   </br>

   * `boolean[] visited = new boolean[weak.length]` : 취약지점 점검 여부 배열
   * `int start` : 현재 이동거리 친구의 시작 인덱스 (시작은 취약지점에서 시작한다.)
   * `int now` : 친구의 현재 위치 (`weak[start]`에서 시작해 이동거리만큼 이동한다.)
   * `int next` : 점검해야하는 다음 취약지점의 인덱스 (시작 인덱스의 다음 인덱스로 초기화된다. )

   </br>

   * `now`에서 이동거리 `d`만큼 하나씩 이동

     * 이때, 현재 위치가 취약지점이라면 그 취약지점 점검처리 해준다. (`visited[next] = true`)
       * 다음 취약지점(`next`)을 갱신시켜준다. (만약, 마지막 인덱스라면 첫 인덱스인 `0`으로 바꿔주어야 함)
     * 만약, 현재 위치 `now`가 `n`이라면 `0`으로 바꿔주어야 한다. (외벽이 원형이기 때문)

   * `dist`에 있는 모든 이동거리를 이동시킨 후에 `visited`를 확인한다.

     `boolean Chkvisited(boolean[] visited)`

     : 모든 취약지점이 점검되었다면 (`visited[]`가 모두 `true`라면) `true`를 리턴

     그렇지 않다면 `false`를 리턴

5. 4번과정에서 `true`를 리턴 받으면 취약지점 모두를 `fcnt`명의 친구로 점검할 수 있는 것으로 그 때의 `fcnt`를 리턴한다.

   * 만약 친구 모두를 써도 점검할 수 없다면 `-1`로 초기화해둔 리턴값을 그대로 리턴해준다.

