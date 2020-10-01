board=[]
mc=[]
dy=[-1,1,0,0]
dx=[0,0,-1,1]
n=0
m=0
k=0

def inDrug(y,x):
    if(y==0 or x==0):
        return True
    if(y==n-1 or x==n-1):
        return True
    return False

def reverseDir(dir):
    if(dir==0):
        return 1
    if(dir==1):    
        return 0
    if (dir==2):    
        return 3
    if (dir==3):    
        return 2

def moveMicrobes():
    global mc
    mc.sort(reverse=True, key = lambda x: x[2])
    board=[[(0,0)]*n for _ in range(n)] # 튜플형식의 2차원 배열

    for i in range(len(mc)):
        ny=mc[i][0]+dy[dir]
        nx=mc[i][1]+dx[dir]            
        num=mc[i][2]
        dir=mc[i][3]
                
        if(inDrug(ny,nx)==True): #약품 위에 존재
            newnum=(int)(num/2)
            newdir=reverseDir(dir)
            board[ny][nx]=(newnum,newdir)
        else:
            if(board[ny][nx][0]==0): #빈칸일 때
                board[ny][nx]=(num,dir)
            else:
                if(board[ny][nx][0] > num):
                    board[ny][nx]=(num+board[ny][nx][0], board[ny][nx][1])
                else:
                    board[ny][nx]=(num+board[ny][nx][0],dir)

    mc.clear()
    for i in range(n):
        for j in range(n):
            if(board[i][j]!=(0,0)):
                mc.append([i,j,board[i][j][0],board[i][j][1]])


def printResult(tc):
    global mc
    result=0

    for i in range(len(mc)):
        result+=mc[i][2]
    
    print("#"+str(tc)+" "+str(result))


TC=int(input())
for tc in range(1,TC+1):  
    n,m,k=map(int,input().split())
    mc.clear()
    for i in range(k):
        y,x,num,dir=map(int,input().split())
        mc.append([y,x,num,dir-1])

    for time in range(m):
        moveMicrobes()

    printResult(tc)
    