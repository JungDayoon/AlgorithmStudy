import heapq
def make_room(arr):
    queue = []
    heapq.heappush(queue, [arr[0][1], arr[0][0]])
    room_num = 1

    for i in range(1, len(arr)):
        lecture_start, lecture_end = arr[i]
        room_end, room_start = queue[0]

        if(room_end>lecture_start):
            #현재 남은 강의중 가장 시작 시간이 빠른 것이,
            #강의실 중 가장 빠르게 끝나는 강의실보다 빨리 시작하면, 
            #이 강의실은 사용 불가
            room_num+=1
        else:
            #이 강의실을 사용할 수 있는 경우
            heapq.heappop(queue)
            #pop
        heapq.heappush(queue, [lecture_end, lecture_start])


    return room_num
if __name__ == "__main__":
    N = int(input())
    lecture = []
    for i in range(N):
        lecture.append(list(map(int, input().split())))
    lecture.sort()
    print(make_room(lecture))
