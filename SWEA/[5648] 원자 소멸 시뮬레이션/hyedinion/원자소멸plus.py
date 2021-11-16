T = int(input())
D = [[1,0],[-1,0],[0,-1],[0,1]]#상하좌우
for t in range(T):
    answer = 0
    N = int(input())
    atom = {}
    for i in range(N):
        x,y,d,k = list(map(int,input().split()))
        atom[i] = [y*2,x*2,d,k]
    
    for i in range(1,4001):
        if len(atom)<2:
            break
        rm = []
        stack = {}
        check = {}
        for i,a in atom.items():
            a[0]+=D[a[2]][0];a[1]+=D[a[2]][1]
            if not -2000<=a[0]<=2000 or not -2000<=a[1]<=2000:
                rm.append(i)
            key = str(a[0])+" "+str(a[1])
            if key not in check.keys():
                check[key] = [i]
            else:
                check[key].append(i)
                if key not in stack.keys():
                    stack[key]=True
        for key in stack.keys():
            for i in check[key]:
                answer+=atom[i][3]
                del atom[i]
        for i in rm:
            del atom[i]
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