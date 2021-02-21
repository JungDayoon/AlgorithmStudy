N = int(input())
number = list(map(int, input().split()))
new_list = [0]
length = 0
for i in number:
    if(new_list[-1]<i):
        new_list.append(i)
        length+=1 #새로운 값이 추가될 때만(리스트가 길어질 때만) 길이를 늘려줌
    
    else:
    # lower bound 방식을 사용할 것
    # 찾고자 하는 값 이상이 처음 나타나는 위치
        start = 0
        end = len(new_list)
        while(start<end):
            mid = (start+end)//2
            if(i>new_list[mid]):
                start = mid + 1
            else:
                end = mid
        new_list[start] = i

print(length)