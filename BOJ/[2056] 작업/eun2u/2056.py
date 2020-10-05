n=0
time=[] #시간 저장
adj=[] #인접리스트
innode=[] #선행되어야하는 노드
seen=[]
order=[]

def findTime():  
    global order

    ret=[0]*(n+1)
    ret[1]=time[1]
    retVal=ret[1]

    for i in range(2,len(order)+1):
        max=0
        if(len(innode[i])==0): #선행되야하는게 없음
            ret[i]=time[i]

        for j in innode[i]:
            if(max<ret[j]):
                max=ret[j]
            ret[i]=time[i] + max
       
        if(retVal < ret[i]):
           retVal=ret[i]

    return retVal

def dfs(here):
    global seen
    global order
    global adj

    seen[here]=True
    for i in range(len(adj[here])):
        there=adj[here][i]
        if(seen[there]==False):
            dfs(there)

    order.append(here)
    
def topologicalSort():
    global seen
    global order
    global adj

    seen=[False]*(n+1)
    for i in range(1,len(adj)):
        if(seen[i]==False):
            dfs(i)

    order.reverse()

def makeGraph():
    global adj
    adj=[list() for _ in range(n+1)]
    for i in range(1,n+1):
        for val in innode[i]:
            adj[val].append(i)

n=int(input())
time=[0]*(n+1)
innode.append([0])
for i in range(1,n+1):
    t,num, *pre = map(int, input().split())
    time[i]=t
    innode.append(pre)

makeGraph()   
topologicalSort()
ret=findTime()

print(ret)