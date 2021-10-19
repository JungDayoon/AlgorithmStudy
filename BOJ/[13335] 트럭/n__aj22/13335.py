def move(arr):
    for i in range(len(arr)):
        arr[i][1]+=1
def move_total():
    t = 0
    queue = []
    total_weight = 0

    while(True):
        if(len(truck) == 0):
            break
        if(len(queue)>0):
            move(queue)
            if(queue[0][1] == w+1):
                vertex = queue.pop(0)
                total_weight -= vertex[0]
        if(total_weight+truck[0]<=L):
            queue.append([truck[0], 1])
            total_weight+=truck[0]
            truck.pop(0)
        t+=1
    if(len(queue)>0):
        t+= (w - queue[-1][1] + 1)
    return t
if __name__ == "__main__":
    n, w, L = map(int, input().split())
    truck = list(map(int, input().split()))
    print(move_total())