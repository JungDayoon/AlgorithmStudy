# [Programmers]/[42888] 오픈채팅방

## - Map -

#### Solution

1. `String[][] command = new String[record.length][3]`

   : 한 줄씩 연결되어있는 `record`를 이용기록, 유저 아이디, 닉네임으로 따로 구분하여 저장한다.

   * `Map<String, String> User` : <UID, 닉네임>
   * `command`에 저장할 때 만약 이용기록이 `Enter`이나 `Change`일 경우,
     1. 만약 `User`에 존재하는 UID라면 (=기존 유저라면) 삭제하고 현재의 닉네임으로 다시 `put()`
     2. `User`에 존재하지 않는 UID라면 (= 새로운 유저라면) `User`에 추가해준다.

2. `command`를 하나씩 스캔하면서 출력되는 문자열을 `ArrayList<String> res`에 `add()`해준다.

   1. `"Enter"`라면, 해당되는 UID를 이용해 `User`에서 가져온 닉네임(`String name`)으로, `name + "님이 들어왔습니다."`를 `add()`
   2. `"Leave"`라면, 해당되는 UID를 이용해 `User`에서 가져온 닉네임(`String name)`으로, `name + "님이 나갔습니다."`를 `add()`

3. List타입인 `res`를 리턴타입인 Array타입으로 변형시킨다.

   > **:star: Java**
   >
   > 1. **List -> Array** (`.toArray()`)
   >
   >    ```java
   >    ArrayList<String> arrlist = new ArrayList<>();
   >    ...
   >    String[] arr = arrlist.toArray(new String[arrlist.size()]);
   >    ```
   >
   > 2. **Array -> List** (`Arrays.asList()`)
   >
   >    ```java
   >    String[] arr = new String[3];
   >    ...
   >    ArrayList<String> arrlist = new ArrayList<>(Arrays.asList(arr));
   >    ```
   >
   >    

