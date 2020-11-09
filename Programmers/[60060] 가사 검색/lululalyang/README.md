# [Programmers]/[60060] 가사 검색

## - 트라이 -

* `query`의 길이가 `word`의 길이와 같아야 매칭되므로 `word`의 길이 만큼 트라이를 만들어준다. ( 각 가사의 길이는 1이상 10000이하, 빈 문자열은 없음)

  `Trie[] trie = new Trie[10001]`;

* `words`에 있는 각 가사(`word`)를 트라이에 `insert`한다.

  * `trie`배열을 미리 초기화하지 않고, `word`길이에 해당하는 index만 초기화한다.
  * 기존에 `rootNode`에 insert한 것과 달리 `front`노드, `back`노드에 insert한다.
    * query는 와일드카드 문자인 `'?'`가 하나 이상 포함되어 있으며, 각 query의 접두사 아니면 접미사 중 하나로만 주어지므로 query의 접두사가 와일드카드 문자라면 query의 뒷쪽부터, query의 접미사가 와일드카드 문자라면 query의 앞쪽부터 확인한다. 
    * --> 이를 위해 각 `word`를 `front`에는 원래 순서대로 insert하고, `back`에는 반대 순서대로 insert한다.

* `TrieNode`는 자식노드맵과 그 자식노드의 개수를 가지고 있다.

  ```java
  class TrieNode{ //자식노드맵 & 현재 노드의 자식노드의 개수
  	Map<Character, TrieNode> childNodes = new HashMap();
  	int cnt = 0;
  }
  ```

  * `word`를 insert할 때 `cnt`를 하나씩 증가시킨다.

    이 때,  query가 '?????'와 같이 전체가 와일드카드 문자일 경우의 개수도 처리해야하므로 root Node 즉, `front`와 `back`node의 `cnt`도 증가시켜주어야 한다.

    :star:따라서, `thisNode`를 바꿔주기 전에 `thisNode`의 `cnt`를 증가시켜주어야 한다.

    ```java
    for(int i=word.length()-1; i>=0; i--) { //backinsert의 경우이다.
    	thisNode.cnt++; //thisNode바꿔주기전에 cnt++
    	thisNode = thisNode.childNodes.computeIfAbsent(word.charAt(i), c -> new TrieNode());
    }
    ```

    </br>

* `trie`를 완성시킨 후, `queries`의 각 `query`로 매칭되는 `word`를 확인한다.

  * `query`가 와일드카드문자 '`?`'로 시작한다면 `backcheck(query)`를,

    접미사가 와일드카드문자라면 `frontchech(query)` 를 수행한다.

    * `int backcheck(String query)`

      : `query`의 가장 뒤쪽 문자부터 확인한다.

      (그럼 이때의 rootNode는 `back`)

      - 현재 노드(`thisNode`)의 자식노드맵 중 현재 문자를 key로 갖는 맵이 있다면 그 자식노드맵을 현재노드로 바꿔주고 다음 문자를 확인한다.

      - 현재 문자를 key로 갖는 자식노드가 없다면 매칭되는 단어가 없는 것이므로 `0`을 return한다.

      - 현재 문자가 '`?`'라면, 해당 노드(`thisNode`)의 `cnt`를 return.

        (문자의 길이대로 `trie`를 만들었기 때문에 `?`를 만났다면 매칭되는 word이다.)

    * `int frontcheck(String query)`

      : `query`의 가장 앞쪽 문자부터 확인한다.

      (그럼 이때의 rootNode는 `front`)

      이후는 `backcheck()`와 동일하게 수행.

  * 각 return값을 `answer[]`에 넣고 return 한다.

</br>

## :speaking_head:

* 트라이의 큰 구조는 비슷하지만 `TrieNode`의 변수와 `Trie`의 `insert`, `contains`는 여러가지로 적용될 수 있다 ! 기본틀을 잘 알아두잣

