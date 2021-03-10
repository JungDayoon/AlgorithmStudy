import copy
def make_column(answer, x, y): #기둥 설치 
    if(y == 0): #바닥 위 
        return True
    if([x, y-1, 0] in answer):#다른 기둥 위 
        return True
    if([x-1, y, 1] in answer): #왼쪽 보와 연결 
        return True
    if([x, y, 1] in answer): #오른쪽 보와 연결
        return True
    return False
def make_beam(answer, x, y):
    if([x, y-1, 0] in answer): #왼쪽이 기둥 위
        return True
    if([x+1, y-1, 0] in answer): #오른쪽이 기둥 위
        return True
    if([x-1, y, 1] in answer and [x+1, y, 1] in answer):
        return True
    return False
def remove(answer, x, y, a):
    temp = copy.deepcopy(answer)
    temp.remove([x, y, a])
    #아래에 기둥이 있으면 
    for a in temp:
        if(a[2] == 0):
            if(make_column(temp, a[0], a[1]) == False):
                return False
        else:
            if(make_beam(temp, a[0], a[1]) == False):
                return False
    return True 

def solution(n, build_frame):
    answer = []
    for frame in build_frame:
        x, y, a, b = frame
        if(b == 1): #설치
            if(a == 0):#기둥 
                if(make_column(answer, x, y)):
                    answer.append([x, y, a])

            elif(a == 1): #보
                if(make_beam(answer, x, y)):
                    answer.append([x, y, a])
        else:#삭제 
            if(remove(answer, x, y, a)):
                answer.remove([x, y, a])

    answer = sorted(answer, key = lambda x : (x[0], x[1], x[2]))
    return answer

if __name__ == "__main__":
    n = 5
    build_frame = [[[1,0,0,1],[1,1,1,1],[2,1,0,1],[2,2,1,1],[5,0,0,1],[5,1,0,1],[4,2,1,1],[3,2,1,1]], 
    [[0,0,0,1],[2,0,0,1],[4,0,0,1],[0,1,1,1],[1,1,1,1],[2,1,1,1],[3,1,1,1],[2,0,0,0],[1,1,1,0],[2,2,0,1]]]

    for i in range(2):
        print(solution(n, build_frame[i]))