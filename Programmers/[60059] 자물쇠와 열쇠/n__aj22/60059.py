import copy
def turn_right(key, M):
    new_key = []
    for i in range(M):
        new_list = []
        for j in range(M):
            new_list.append(key[M-1-j][i])
        new_key.append(new_list)
    return new_key
def increase_lock(length, lock):
    new_lock = [[1]*(len(lock)+length*2-2) for _ in range(length-1)]
    for i in range(len(lock)):
        new_list = []
        for j in range(length-1):
            new_list.append(1)
        for j in range(len(lock[i])):
            new_list.append(lock[i][j])
        for j in range(length-1):
            new_list.append(1)
        new_lock.append(new_list)
    for i in range(length-1):
        new_lock.append([1]*(len(lock)+length*2-2))
    
    return new_lock
def solution(key, lock):
    answer = False
    M, N = len(key), len(lock)
    start_index = M-1
    lock = increase_lock(M, lock)
    lock_sum = N*N

    for turn in range(4):
        for i in range(M+N-1):
            for j in range(M+N-1):
                temp = copy.deepcopy(lock)
                for k in range(M):
                    for l in range(M):
                        lock[i+k][j+l] += key[k][l]
                #확인
                flag = True
                total = 0
                for k in range(start_index, start_index+N):
                    for l in range(start_index, start_index+N):
                        if(lock[k][l] == 0 or lock[k][l] == 2):
                            flag = False
                            break
                        total+=lock[k][l]
                    if(flag == False):
                        break
                if(total == lock_sum):
                    return True
                lock = temp
        temp = copy.deepcopy(lock)
        key = turn_right(key, M)
    return False
if __name__ == "__main__":
    key = [[0, 0, 0], [1, 0, 0], [0, 1, 1]]
    lock = 	[[1, 1, 1], [1, 1, 0], [1, 0, 1]]
    print(solution(key, lock))