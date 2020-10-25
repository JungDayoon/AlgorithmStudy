import sys
sys.stdin = open("/Users/najihye/algo2/SWEA/[5648] 원자 소멸 시뮬레이션/n__aj22/input.txt", "r")
T = int(input())
atom = []
dx = [0,0,-0.5,0.5]
dy = [0.5,-0.5,0,0]
def isrange(nx, ny):
    if(-1000<=nx<=1000 and -1000<=ny<=1000):
        return True
    return False
for test_case in range(1, T + 1):
    N = int(input())
    atom = []
    delete_list = []
    for i in range(N):
        new_list = list(map(int, input().split()))
        atom.append([float(new_list[0]), float(new_list[1]), new_list[2], new_list[3]])
    atom.sort(key = lambda x :(x[0],x[1]))
    num = 0 
    total_energy = 0
    while(True):
        if(len(atom) == 0):
            break
        delete_atom = []
        for i in atom:
            i[0] = i[0] + dx[i[2]]
            i[1] = i[1] + dy[i[2]]
            if(isrange(i[0],i[1]) == False):
                delete_atom.append(i)
        while(delete_atom):
            atom.remove(delete_atom.pop(0))
        if(len(atom) == 0):
            break
        atom.sort(key = lambda x :(x[0],x[1]))
        flag = False
        delete_atom.append([atom[0][0], atom[0][1]])
        for i in range(1, len(atom)):
            if([atom[i][0], atom[i][1]] in delete_atom):
                flag = True
            else:
                if(flag == False):
                    delete_atom.pop(-1)
                delete_atom.append([atom[i][0], atom[i][1]])
                flag = False
        if(flag == False):
            delete_atom.pop(-1)
        if(len(delete_atom)!=0):
            for i in atom[:]:
                if([i[0],i[1]] in delete_atom):
                    total_energy+=i[3]
                    atom.remove(i)
    print("#"+str(test_case)+" "+str(total_energy))

