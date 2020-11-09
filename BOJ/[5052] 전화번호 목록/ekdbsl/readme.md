# [BOJ 5052] 전화번호 목록 - Python

### :computer: Algorithm

> 트라이



### :computer: Logic

#### :memo: TRIE ?

트라이는 Tree 자료구조의 일종으로, 어떤 문자열을 검색하는 데에 있어서 아주 뛰어난 성능을 보이는 자료구조이다 !

문자열 검색 시간 복잡도는 O(m) (m: 문자열의 최대 길이)

#### :computer_mouse: 파이썬에서의 구현

1. Node 클래스 구현

   - key: 단어의 글자 하나를 담는 데 이용한다. 영어라면 알파벳 하나가 들어가게 된다.

   - data: 마지막 글자를 나타내주는 flag이다. boolean형으로 간단하게 True/False로 구분할 수도 있지만, 마지막 글자인 경우 data필드에 단어 전체를 저장해두면(마지막 글자가 아니면 None으로 표시) prefix 기준 search에 유용하게 사용될 수 있다.

     ex) Jack의 경우, 마지막 글자인 k노드의 data 필드에만 "Jack"이 들어가고, 나머지 필드는 None이 들어가게 된다.

2. Trie 클래스 구현

   - insert(string)
     - 트라이에 문자열 삽입
   - search(string)
     - 주어진 단어 string이 트라이에 존재하는 지 여부를 반환
   - starts_with(prefix)
     - 주어진 prefix로 시작하는 단어들을 BFS로 트라이에서 찾아 리스트 형태로 반환
     - BFS로 단어들을 찾을 때 결국 마지막에 반환되는 것은 Terminal 노드, 즉 마지막 글자이기 때문에 단어 자체를 알려면 BFS 경로를 역추적하는 작업이 추가로 필요하다. (data가 boolean 형일 때)
     - 하지만, 단어의 마지막 글자 data필드에 전체 단어를 넣으면 역추적 과정이 필요없다!

#### :heavy_heart_exclamation: 문제 적용

- 길이가 짧은 순으로 문자열들을 정렬한다.
- 각 문자열을 trie 자료구조에 추가하는데(insert 함수 내), 추가하려는 단어의 문자 노드의 data가 None이 아니라면(일관성 없음 만족) 바로 False를 return 한다.