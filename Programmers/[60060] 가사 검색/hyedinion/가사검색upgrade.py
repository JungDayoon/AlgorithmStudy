class Node():
    def __init__(self):
        self.child = {}
        self.count = 0

class Trie():
    def __init__(self):
        self.head = Node()
    
    def add(self,word):
        cur = self.head
        for ch in word:
            if ch not in cur.child:
                cur.child[ch] = Node()
            cur.count+=1
            cur = cur.child[ch]
        cur.count+=1
        
    def search(self,word):
        cur = self.head
        for w in word:
            if w in cur.child:
                cur = cur.child[w]
            else:
                return 0
        return cur.count

def solution(words, queries):
    answer = []
    trie = {i: Trie() for i in range(1, 10001)}
    trie_opposite = {i: Trie() for i in range(1, 10001)}
    for w in words:
        trie[len(w)].add(w)
        trie_opposite[len(w)].add(w[::-1])
    for q in queries:
        if q=="?"*len(q):
            answer.append(trie[len(q)].head.count)
        elif q[0]=="?":#접두사, 뒤에서부터 탐색
            query = q[::-1].split("?")[0]
            answer.append(trie_opposite[len(q)].search(query))
        else:#접미사, 앞에서부터 탐색
            query = q.split("?")[0]
            answer.append(trie[len(q)].search(query))
    return answer

words = ["frodo", "front", "frost", "frozen", "frame", "kakao"]
queries = ["fro??", "????o", "fr???", "fro???", "pro?", "?????", "??", "f????", "???????"]
print(solution(words,queries))