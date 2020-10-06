import copy
d=0
w=0
k=0
INF=987654321
minK=0
film=[]
newfilm=[]

def checkPerform(film): #연속되는 구간 찾기 
    for j in range(w):
        cnt=1
        for i in range(d-1):
            if(cnt>=k):
                break
            if(film[i][j]==film[i+1][j]):
                cnt+=1
            else:
                cnt=1
        if(cnt<k):
            return False
    
    return True 

def dfs(picked,start):
    global newfilm
    global minK

    if(checkPerform(newfilm)==True):
        if(picked < minK):
            minK=picked
            minResult=newfilm
        return

    if(picked==k-1):
        return
          
    for i in range(start, d):
        for val in range(2):
            if(checkPerform(newfilm)==False):
                newfilm[i]=[val for _ in range(w)]
                dfs(picked+1,i+1)
                newfilm[i]=copy.deepcopy(film[i])

TC=int(input())
for tc in range(1,TC+1):
    minK=INF
    d,w,k=map(int,input().split())
    film=[list(map(int,input().split())) for _ in range(d)]
    newfilm=copy.deepcopy(film)

    if(checkPerform(film)==True):
        print('#'+str(tc)+' '+ str(0))
        continue

    dfs(0,0)
    if(minK==INF):
        print('#'+str(tc)+' '+ str(k))
    else:
        print('#'+str(tc)+' '+ str(minK))
