def get_magnet_after_rotate(magnet, direction):
    if(direction==1):
        new_magnet=[magnet.pop()]
        new_magnet.extend(magnet)
    else:
        new_magnet=magnet[1:]
        new_magnet.append(magnet[0])
    return new_magnet

def get_total_sum(magnet_info, rotate_case):
    for magnet_num, direction in rotate_case:
        magnet_before_rotate=magnet_info[magnet_num-1]
        before_rotate_left=magnet_before_rotate[6]
        before_rotate_right=magnet_before_rotate[2]

        temp_dir=direction
        for idx in range(magnet_num-1, 0, -1): #회전할 자석의 왼쪽 편
            if(before_rotate_left==magnet_info[idx-1][2]):break
            else:
                before_rotate_left=magnet_info[idx-1][6]
                magnet_info[idx-1]=get_magnet_after_rotate(magnet_info[idx-1], (-1)*temp_dir)
                temp_dir*=-1    

        temp_dir=direction 
        for idx in range(magnet_num-1, 3): #회전할 자석의 오른쪽 편
            if(before_rotate_right==magnet_info[idx+1][6]): break
            else:
                before_rotate_right=magnet_info[idx+1][2]
                magnet_info[idx+1]=get_magnet_after_rotate(magnet_info[idx+1], (-1)*temp_dir)
                temp_dir*=-1
        
        magnet_info[magnet_num-1]=get_magnet_after_rotate(magnet_before_rotate, direction)

    total=0
    for i, magnet in enumerate(magnet_info):
        if(magnet[0]==1):total+=2**i
    return total
        
if __name__=="__main__":
    T=int(input())
    for i in range(1, T+1):
        magnets=[]
        cases=[]
        k=int(input())
        for _ in range(4):
            magnets.append(list(map(int, input().split())))
        for _ in range(k):
            cases.append(list(map(int, input().split())))
        total=get_total_sum(magnets, cases)
        print("#{} {}".format(i, total))
    
    