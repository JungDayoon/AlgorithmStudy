target_str = "123456780"
visited={}
def BFS(start_str):
    queue =[]
    queue.append([start_str, 0])
    visited[start_str] = 0

    while(queue):
        string, count = queue.pop(0)
        if(string==target_str):
            return count
        arr = list(string)
        index = arr.index("0")
        j = index%3
        i = index//3

        if(i>0):#위로 이동 
            arr[index], arr[index-3] = arr[index-3], arr[index]
            if("".join(arr) not in visited):
                nextstep = "".join(arr)
                queue.append([nextstep, count+1])
                visited[nextstep] = count+1
            arr[index], arr[index-3] = arr[index-3], arr[index]
        if(i<2):#아래로 이동
            arr[index], arr[index+3] = arr[index+3], arr[index]
            if("".join(arr) not in visited):
                nextstep = "".join(arr)
                queue.append([nextstep, count+1])
                visited[nextstep] = count+1
            arr[index], arr[index+3] = arr[index+3], arr[index]
        if(j<2):#오른쪽으로 이동
            arr[index], arr[index+1] = arr[index+1], arr[index]
            if("".join(arr) not in visited):
                nextstep = "".join(arr)
                queue.append([nextstep, count+1])
                visited[nextstep] = count+1
            arr[index], arr[index+1] = arr[index+1], arr[index]
        if(j>0):#왼쪽으로 이동 
            arr[index], arr[index-1] = arr[index-1], arr[index]
            if("".join(arr) not in visited):
                nextstep = "".join(arr)
                queue.append([nextstep, count+1])
                visited[nextstep] = count+1
            arr[index], arr[index-1] = arr[index-1], arr[index]
    return -1
if __name__ == "__main__":
    arr = ""
    for i in range(3):
        arr+="".join(map(str, input().split()))
    print(BFS(arr))