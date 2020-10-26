## 분류💁

문자열, 트라이

## 접근법

- 길이가 짧은 순서대로 sort 한다. -> 그래야 접두사가 있는지 확인할 수 있다.

- `insert()`를 할 때, 넣으려는 노드의 terminal 이 true 값이면 접두사가 존재하는 것이기 때문에, Result 값을 바꿔준다.

- 트라이노드 객체는 1. 자손 노드를 가리키는 포인터 목록과, 2. 이 노드가 종료 노드인지를 나타내는 bool값 변수로 구성된다





## 트라이
```cpp
struct TrieNode{
    TrieNode* children[NUM];
    bool terminal;
    TrieNode() : terminal(false){
	    memset(children, 0, sizeof(children));
    }
    ~TrieNode(){
		for(int i=0;i<NUM;i++)
		    if(children[i])
		        delete children[i];
    }
    void insert(const char* key){
	    if(*key == 0)
	        terminal=true;
        else{
	        int next=toNumber(*key);
	        if(children[next]==NULL)
		        children[next]=new TrieNode();
            if(children[next]->terminal ==true)
                result=true;
		    children[next]->insert(key+1);
        }
    }
};
```




## 후기💡

- 프로그래머스에 있는 전화번호 목록과 문제가 같아서 string::find() 함수를 사용하였는데, 시간초과가 떴다. 

- 그래서 새롭게 트라이를 공부했는데, 되게 새로웠다.

