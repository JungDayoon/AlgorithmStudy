N = int(input())
number = [int(input()) for _ in range(N)]
new_list = [0]
length = 0
for num in number:
    if new_list[-1]<num:
        new_list.append(num)
        length+=1
    else:
        start = 0
        end = len(new_list)
        while(start<end):
            mid = (start+end)//2
            if new_list[mid]<num:
                start = mid + 1
            else:
                end = mid
        new_list[start] = num

print(N-length)