# [2020 카카오 BLIND RECRUITMENT] 가사 검색 - Python

### :computer: Algorithm

> 트라이



### :computer: Logic

와일드카드(?)가 앞이나 뒤에서부터 나올 수 있기 때문에 순방향 트라이와 역방향 트라이를 단어길이만큼 생성한다.

순방향 트라이에는 단어를 순방향으로 insert하고, 역방향 트라이에는 단어를 역순으로 insert한다.

count도 단어가 하나 들어올 때마다 각 글자하나 마다 올려준다.

그 후에 query 문이 들어오면 이 쿼리의 길이를 index로 가지는 TRIE에서 '?'가 나오기전까지의 node를 찾고, 이 node의 count가 원하는 답이 된다.



### :computer: Review

처음에 각 단어의 길이마다 트라이를 생성한 게 아니라 한 트라이 내에서 '?' 앞까지 node 탐색을 하고, 그 노드의 children들을 모두 queue에 넣은 뒤 queue에서 pop한 노드들의 children을 다시 queue에 넣는 작업을 ? 개수만큼 수행하였는데, 이렇게 하면 연산량이 많아져서 트라이를 사용해도 결국 시간초과가 난 것 같다..

TRIE 내에 count라는 변수를 두고, 이 count가 현재 노드로 시작하는 단어들의 개수를 저장하고 있으면 연산량을 많이 줄일 수 있다.