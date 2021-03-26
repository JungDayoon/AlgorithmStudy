#정확성 26/26
#효율성 0/7
def solution(k, room_number):
    answer = []
    next_room = [0]
    for i in range(1, k+1):
        next_room.append(i)
    
    for num in room_number:
        if(next_room[num] == num):
            answer.append(num)
            if(num+1<=k):
                next_room[num]= next_room[num+1]
        else:
            chk_num = num
            while(True):
                if(next_room[chk_num] == chk_num):
                    answer.append(chk_num)
                    next_room[chk_num] = next_room[chk_num+1]
                    break
                next_chk_num = next_room[chk_num]
                next_room[chk_num] = next_room[chk_num+1]
                chk_num = next_chk_num
    return answer
if __name__ == "__main__":
    k = 1
    room_number = [1]
    print(solution(k, room_number))