class Trie():
    head = {}
    def __init__(self,check_list):
        self.head = {}
        for check in check_list:
            cur = self.head
            for c in check:
                if c not in cur:
                    cur[c]={}
                cur = cur[c]
            cur["*"]={}
        return
    
    def search(self,check):
        cur = self.head
        for c in check:
            if "*" in cur:
                return True
            cur = cur[c]
        if len(cur)==1:
            return False
        return True


T = int(input())
for t in range(T):
    N = int(input())
    numbook = [input() for _ in range(N)]
    trie = Trie(numbook)
    for i in numbook:
        if trie.search(i):
            print("NO")
            break
        if i==numbook[N-1]:
            print("YES")