import math 

n=0
m=0
costs=[] #운영비용
minH=[] #손해안보는 최소 집 개수
houses=[] #집 정보
tot_house=0

def calculCost():
    global costs
    global minH

    costs.clear()
    minH.clear()
    costs.append(0)
    minH.append(0)
    k=1
    while(True):
        cost=k**2 +(k-1)**2
        h=math.ceil(cost/m)
        if(h > tot_house): 
            break
        costs.append(cost)
        minH.append(h)
        k+=1

    return k
        
def findMaxHouses(curK):
    global houses
    maxCnt=0
    for i in range(n):
        for j in range(n):
            cnt=0
            for h in houses:
                if(abs(h[0]-i) + abs(h[1]-j) <curK):
                    cnt+=1
            
            if(cnt>maxCnt):
                maxCnt=cnt
    
    return maxCnt
   
TC=int(input())
for tc in range(1,TC+1):
    city=[]
    city.clear()
    n,m=map(int,input().split())
    city=[list(map(int,input().split())) for _ in range(n)]
    tot_house=0
    houses.clear()

    for i in range(n):
        for j in range(n):
            if(city[i][j]==1):
                houses.append((i,j))
                tot_house+=1

    k =calculCost()-1
    while(1):
        ret=findMaxHouses(k)
        if(minH[k] > ret):
            k-=1
        else:
            print('#'+str(tc)+' '+ str(ret))
            break

