T = int(input())
D = [[1,0],[-1,0],[0,-1],[0,1]]#상하좌우
for t in range(T):
    answer = 0
    N = int(input())
    atom = {}
    for i in range(N):
        x,y,d,k = list(map(int,input().split()))
        atom[str(y*2)+" "+str(x*2)] = [d,k]
    
    for i in range(1,4001):
        if len(atom)<2:
            break
        rm = []
        stack = {}
        check = {}
        for k,v in atom.items():
            I,J = map(int,k.split());newI=I+i*D[v[0]][0];newJ=J+i*D[v[0]][1]
            if not -2000<=newI<=2000 or not -2000<=newJ<=2000:
                rm.append(k)
            key = str(newI)+" "+str(newJ)
            if key not in check.keys():
                check[key] = [k]
            else:
                check[key].append(k)
                if key not in stack.keys():
                    stack[key]=True
        for key in stack.keys():
            for k in check[key]:
                answer+=atom[k][1]
                del atom[k]
        for k in rm:
            del atom[k]
    print("#{} {}".format(t+1,answer))

'''
2
4
-1000 0 3 5
1000 0 2 3
0 1000 1 7
0 -1000 0 9
4
-1 1 3 3
0 1 1 1
0 0 2 2
-1 0 0 9


1
2
1000 1000 1 1
-1000 -1000 3 10
'''