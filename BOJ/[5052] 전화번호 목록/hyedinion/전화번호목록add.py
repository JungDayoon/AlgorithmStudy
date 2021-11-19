class Trie():
    head = {}

    def add(self,check):
        cur = self.head
        for c in check:
            if "*" in cur:
                return False
            if c not in cur:
                cur[c]={}
            cur = cur[c]
        if len(cur)>0:
            return False
        cur["*"]={}
        return True

T = int(input())
for t in range(T):
    N = int(input())
    numbook = [input() for _ in range(N)]
    trie = Trie()
    trie.head = {}
    for i in numbook:
        if not trie.add(i):
            print("NO")
            break
        if i==numbook[N-1]:
            print("YES")