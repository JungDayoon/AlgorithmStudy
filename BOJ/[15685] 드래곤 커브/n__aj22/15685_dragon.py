N = int(input())
# x, y, d, g
dx = [1,0,-1,0]
dy = [0,-1,0,1]
dragon = []
def rotate(num):
    return (num+1)%4

for i in range(N):
    new_dragon = []
    x, y, d, g = map(int, input().split())
    new_dragon.append([x,y,d])
    dragon.append([x,y,d])

    for i in range(g+1):
        now_length = len(new_dragon)
        for j in range(now_length-3,-1,-1):
            now_x = new_dragon[-1][0]+dx[new_dragon[-1][2]]
            now_y = new_dragon[-1][1]+dy[new_dragon[-1][2]]
            now_d =rotate(new_dragon[j][2])
            new_dragon.append([now_x, now_y, now_d])
            dragon.append([now_x, now_y, now_d])

        now_x = new_dragon[-1][0] + dx[new_dragon[-1][2]]
        now_y = new_dragon[-1][1] + dy[new_dragon[-1][2]]
        now_d = rotate(new_dragon[-1][2])
        new_dragon.append([now_x, now_y, now_d])
        dragon.append([now_x, now_y, now_d])


for i in dragon:
    i.pop(-1)
minx = min(dragon)[0]
maxx = max(dragon)[0]
miny = min(dragon, key=lambda item: item[1])[1]
maxy = max(dragon, key=lambda item: item[1])[1]

num = 0
for i in range(minx, maxx):
    for j in range(miny, maxy):
        if[i,j] in dragon and [i+1, j] in dragon and [i,j+1] in dragon and [i+1, j+1] in dragon:
            num+=1
print(num)



