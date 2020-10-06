import sys
R,C,M = map(int, sys.stdin.readline().split())
shark = [[0]*(C+1) for _ in range(R+1)]
#print(shark)
dc = [0,0,0,1,-1]
dr = [0,-1,1,0,0]
for i in range(M):
    new_shark = list(map(int, sys.stdin.readline().split()))
    shark[new_shark[0]][new_shark[1]] = [new_shark[2],new_shark[3],new_shark[4]]


def find_shark(index):
    for i in range(1,R+1):
        if(shark[i][index] != 0):
            return i
    return 0
    
def change_loc(r,c,s,d,z):
    origins = s

    while(s>0):
        if(d == 1):
            if(r-s>=1):
                r -= s
                break
            minus = r - 1
            s -= minus
            r = 1
            d = 2
        if(d == 2):
            if(r+s<=R):
                r += s
                break
            plus = R - r
            s -= plus
            r = R
            d = 1
        elif(d == 3):
            if(c+s<=C):
                c += s
                break
            plus = C - c
            s -= plus
            c = C
            d = 4
        elif(d == 4):
            if(c-s>=1):
                c -= s
                break
            minus = c - 1
            s -= minus
            c = 1
            d = 3
    return r,c,origins,d,z




if __name__ == "__main__":
    catch_size = 0
    for i in range(1, C+1): #1부터 C까지 낚시왕 이동
        shark_index = find_shark(i)

        if(shark_index!=0):
            catch_size += shark[shark_index][i][2] #크기를 더해준다.
            shark[shark_index][i] = 0 #없애준다.
            
        
        shark_list = []
        for j in range(1,R+1):
            for k in range(1, C+1):
                if(shark[j][k]!=0):
                    shark_list.append([j,k,shark[j][k][0],shark[j][k][1],shark[j][k][2]])
                    shark[j][k]=0
        for j in shark_list:
            j[0],j[1],j[2],j[3],j[4] = change_loc(j[0],j[1],j[2],j[3],j[4])
            if(shark[j[0]][j[1]]==0):
                shark[j[0]][j[1]] = [j[2],j[3],j[4]]
            else:
                if(shark[j[0]][j[1]][2]<j[4]):
                    shark[j[0]][j[1]][0] = j[2]
                    shark[j[0]][j[1]][1] = j[3]
                    shark[j[0]][j[1]][2] = j[4]
        #print(shark)
    print(catch_size)
    
            
        
        

            

        
