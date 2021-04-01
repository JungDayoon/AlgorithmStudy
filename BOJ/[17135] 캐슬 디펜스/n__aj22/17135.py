import copy
import heapq
answer = 0
def distances(point1, point2):
    return abs(point1[0]-point2[0])+abs(point1[1]-point2[1])
def start_game(archer, enemy):
    temp = copy.deepcopy(enemy)
    deleted_num = 0
    while(temp):
        delete_list = []
        for one_archer in archer:
            queue = []
            for one_enemy in temp:
                e_y, e_x = one_enemy
                distance = distances(one_archer, one_enemy)
                if(distance<=D):
                    heapq.heappush(queue, [distance, e_x, e_y]) #거리가 작은거 먼저, 같다면 왼쪽 먼저
            
            if(len(queue)!=0):
                delete_enemy = [queue[0][2],queue[0][1]] #y, x
                if(delete_enemy not in delete_list):
                    delete_list.append(delete_enemy)
        
        #모든 궁수가 공격할 적이 정해졌다면, temp에서 삭제한다.
        deleted_num+=len(delete_list)
        for delete_enemy in delete_list:
            temp.remove(delete_enemy)
        delete_list = []
        for one_enemy in temp:
            one_enemy[0]+=1
            if(one_enemy[0] == N):
                delete_list.append(one_enemy)
        
        for delete_enemy in delete_list:
            temp.remove(delete_enemy)
        
    return deleted_num
def choose_archer(num, archer, index, enemy):
    if(num == 3):
        #게임 시작
        check_num = start_game(archer, enemy)
        #리턴값으로 제외된 적의 수
        #비교
        global answer
        answer = max(answer, check_num)
        return
    
    for i in range(index, M):
        archer.append([N, i])
        choose_archer(num+1, archer, i+1, enemy)
        archer.pop(-1)
    return
    

if __name__ == "__main__":
    N, M, D = map(int, input().split())
    arr = []
    enemy = []
    for i in range(N):
        new_arr = list(map(int, input().split()))
        for j in range(M):
            if(new_arr[j] == 1):
                enemy.append([i,j])
        arr.append(new_arr)
    
    choose_archer(0, [], 0, enemy)
    print(answer)