# [BOJ]/[5052] 전화번호 목록

## *- 문자열, 트라이 -*

>:star: **Trie**
>
>: 일반 트리 자료구조 중 하나, 문자열을 저장하고 탐색하는데 유용한 자료구조.
>
>- 각 노드는 <key, Value>맵을 자식노드로 가지고 있다. 
>
>  Key는 하나의 알파벳이 되고, Value는 그 Key에 해당하는 자식노드가 된다.
>
>- 루트를 제외한 노드의 자손들은 해당 노드와 공통 접두어를 가진다.
>
>  - 루트노드는 특정문자를 의미하지 않고 자식노드만 가짐
>
>- **노드들은 부모노드나 자신이 어떤 Key에 해당하는 노드인지를 가지고 있는 것이 아님!**

</br>

> :star: **Trie in JAVA**
>
> : 자료구조인 `Trie`와 이를 구성할 `TrieNode`클래스가 각각 필요하다.
>
> </br>
>
> * **`TrieNode.java `** 
>
>   : `TrieNode`는 자식노드 맵과 현재 노드가 마지막 글자인지 여부에 대한 정보를 가지고 있다.
>
>    `Map<Character, TrieNode> childNodes`,
>
>    `boolean isLashChar`
>
>   -> 이에 접근하기 위한 `Getter()`, `Setter()`필요.
>
>   <br>
>
> * **`Trie.java`** 
>
>   : `Trie`는 기본적으로 빈 문자열을 가지는 루트노드만 가지고 있다. 
>
>   -> 생성자를 통해 `rootNode`생성.
>
>   -> 이후 insert메소드를 통해 단어를 넣으면서 자식노드가 생성됨.
>
>   * `public void insert(String word)`
>
>     : 해당 계층 문자의 자식노드가 존재하지 않을 때에만 자식노드를 생성.
>
>     (예: 이미 Trie에 'DEV'가 있는데 'DEAR'을 넣으려한다면, 'DE-'는 중복이므로 'D-E-'노드 아래에 '-A-R'만 추가로 자식노드 생성.)
>
>     마지막 글자는 `setIsLastChar(true);`로 표시해준다.
>
>     > ```java
>     > public void insert(String word) {
>     >     TrieNode thisNode = this.rootNode; //루트노드부터 탐색
>     >     for(int i=0; i<word.length(); i++) {
>     >         thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
>     >     }
>     >     thisNode.setIsLastChar(true);
>     > }
>     > ```
>     > * 해당 계층 문자의 자식노드가 존재하지 않을 때만 자식노드를 생성하는데, 이때 **람다식**을 사용한다.
>     >
>     >   * `tmpMap.computeIfAbsent(key, mappingFunction)`
>     >
>     >     : `HashMap`의 메소드. (코드가 간단하고 짧아진다)
>     >
>     >     `tmpmap`에 `key`가 있으면 `Value`를 반환하고, 그렇지 않다면 `mappingFunction`으로 `value`계산해 `tmpmap`에 추가한 후 그 `value`를 반환한다.
>     >
>     >   > * **람다식**: 식별자 없이 실행가능한 함수(코드 한줄에 함수를 써서 그것을 호출하는 방식)
>     >   >
>     >   >   (매개변수, ...) -> {실행문 ...}
>     >   >
>     >   >   (매개변수, ...) : 오른쪽 중괄호 { }를 실행하기 위해 필요한 값 제공 역할. (이름 자유롭게 지정, 인자타입 명시X)
>     >   >
>     >   >   -> 기호 : 매개변수를 이용해 중괄호 { }를 실행한다는 뜻
>
>     </br>
>
>   * `public boolean contains(String word) `
>
>     : 특정 단어가 Trie에 존재하는지 확인
>
>     조건 1) 루트노드부터 순서대로 알파벳이 일치하는 자식노드들이 존재할 것.
>
>     조건 2) 해당 단어의 마지막 글자에 해당하는 노드의 `isLastChar`이 `true`일 것.
>
>     ​			(= 해당 글자를 마지막으로 하는 단어가 있다는 뜻)

</br>

* 우선, 모든 전화번호를 입력받아 길이 오름차순으로 `sort`한다.

  > ```java
  > Arrays.sort(str, (a, b)->Integer.compare(a.length(), b.length()));
  > ```
  >
  > `int`형 비교시 `Integer.compare(int x, int y)`

* 정렬된 전화번호 배열 `str[]`을 `trie`에 `insert()`하기 전에 `contains()`로 현재 `trie`에 존재하는 지 확인해준다. 

  ```java
  TrieNode thisNode = this.rootNode;
  		
  for(int i=0; i<word.length(); i++) {
  	char character = word.charAt(i);
      // 조건 1)
  	TrieNode  node = thisNode.getChildNodes().get(character);
  			
  	if(node == null) //현재 str의 길이를 다 탐색하기 전에 node가 null이면 
  		return false; //현재 trie에 존재하지 않는 것.
  			
  	thisNode = node; //있으면 이노드를 현재노드로 하고 이 노드의 자식노드를 확인
  	
      if(thisNode.isLastChar()) //현재 str의 길이를 다 탐색하기 전에
  		return true; //현재 node가 마지막 node라면 이때까지의 node들이 접두어
  }					// 이므로 true를 반환.
  
  return thisNode.isLastChar(); //조건 2)
  ```

</br>

## :speaking_head:

* 트라이는 처음이라 자료구조 공부부터 했다 .. 필요한 메소드를 사용해보면서 익혔지만 아직도 어렵다 !!!!ㅠ 
* 람다식, `HashMap`의 메소드, `int`형 `sort` 등 몰랐던 부분 익혀두자 ! 🙋‍♀️

