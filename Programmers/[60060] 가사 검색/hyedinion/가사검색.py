class Trie():
    head = {}
    
    def add(self,word,i):
        cur = self.head
        for ch in word:
            if ch not in cur:
                cur[ch] = {}
            cur = cur[ch]

        if "*" in cur:
            cur["*"].append(i)
        else:
            cur["*"] = [i]
        
    def search(self,word):
        answer = []
        queue = {0 : [self.head]}
        
        for i,ch in enumerate(word):
            if len(queue[i])==0:
                return [-1]
            queue[i+1] = []
            for cur in queue[i]:
                if "?" in cur:
                    queue[i+1].append(cur["?"])
                if ch in cur:
                    queue[i+1].append(cur[ch])

        for cur in queue[len(word)]:
            if "*" in cur:
                answer+=cur["*"]

        if len(answer)==0:
            return [-1]
        return answer

def solution(words, queries):
    answer = [0 for _ in range(len(queries))]
    trie = Trie()
    for i in range(len(queries)):
        trie.add(queries[i],i)
    for w in words:
        indexs = trie.search(w)
        if indexs[0]!=-1:
            for i in indexs:
                answer[i]+=1
    return answer

words = ["frodo", "front", "frost", "frozen", "frame", "kakao"]
queries = ["fr???", "fr???","?????"]
print(solution(words,queries))