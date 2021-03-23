def Max_BC(M,A, userA_move, userB_move, BC_list):
    userA=[1,1]; userB=[10,10]
    userA_BC_list=[]; userB_BC_list=[];
 
    for sec in range(M+1):
        # 1초마다 충전 가능한 BC list
        userA_cur_list=[]; userB_cur_list=[]; 
 
        # 현재 위치에서 충전 가능여부 검사 
        for idx, BC in enumerate(BC_list):
            if(is_distance_in(userA, BC)):
                userA_cur_list.append([idx, BC[3]]) #[몇번째 BC, 해당 BC의 성능]
            if(is_distance_in(userB, BC)):
                userB_cur_list.append([idx, BC[3]])
 
        # 성능 최대인 BC와 겹치는 BC 찾기 위해 성능 큰 BC 순으로 정렬
        userA_cur_list.sort(key=lambda x:-x[1])
        userB_cur_list.sort(key=lambda x:-x[1])
 
        # 합이 최대인 두 사용자의 BC 찾기
        userA_cur_cnt=len(userA_cur_list)
        userB_cur_cnt=len(userB_cur_list)
 
        # 두 사용자 모두 충전 가능 BC 가 없는 경우,
        if(userA_cur_cnt==0 and userB_cur_cnt==0):
            pass
        # 사용자A만 충전 가능 BC 가 없는 경우, 사용자B의 최대 성능 BC만 주입
        elif(userA_cur_cnt==0):
            userB_BC_list.append(userB_cur_list[0][1])
        # 사용자B만 충전 가능 BC 가 없는 경우, 사용자A의 최대 성능 BC만 주입
        elif(userB_cur_cnt==0):
            userA_BC_list.append(userA_cur_list[0][1])
        # 두 사용자 모두 충전 가능 BC가 있는 경우 
        else:
            # 둘 다 충전 가능 BC 1개인 경우
            if(userA_cur_cnt==1 and userB_cur_cnt==1):
                # 둘 다 충전 가능 BC 동일할 때 
                if(userA_cur_list[0][0]==userB_cur_list[0][0]):
                    userA_BC_list.append(userA_cur_list[0][1]//2)
                    userB_BC_list.append(userB_cur_list[0][1]//2)
                else: # 각각 다른 BC 일 때
                    userA_BC_list.append(userA_cur_list[0][1])
                    userB_BC_list.append(userB_cur_list[0][1])
            elif(userA_cur_cnt==1): # A는 1개, B 여러개 일 때 겹쳐도 A첫번째+B두번째가 최대
                if(userA_cur_list[0][0]==userB_cur_list[0][0]):
                    userA_BC_list.append(userA_cur_list[0][1])
                    userB_BC_list.append(userB_cur_list[1][1])
                else:
                    userA_BC_list.append(userA_cur_list[0][1])
                    userB_BC_list.append(userB_cur_list[0][1])
            elif(userB_cur_cnt==1): # A는 여러개, B 1개 일 때 
                if(userA_cur_list[0][0]==userB_cur_list[0][0]):
                    userA_BC_list.append(userA_cur_list[1][1])
                    userB_BC_list.append(userB_cur_list[0][1])
                else:
                    userA_BC_list.append(userA_cur_list[0][1])
                    userB_BC_list.append(userB_cur_list[0][1])
                 
            else: # A,B 모두 충전 가능 BC 여러개인 경우
                smaller=userA_cur_cnt if userA_cur_cnt<userB_cur_cnt else userB_cur_cnt
                is_same_already=False # 이전에 겹친 BC 가 있는지 확인 위한 변수 
                for idx in range(smaller):
                    # 두 사용자의 BC가 겹치는 경우 
                    if userA_cur_list[idx][0]==userB_cur_list[idx][0]:
                        # 이전에 겹친 BC 가 없다면 
                        if(is_same_already==False):
                            prev_same=userA_cur_list[idx][1] # 현재 성능(p) 저장 
                            is_same_already=True # 겹침 확인 변수 값 바꿈 
                            continue
                        # 이전에 겹친 BC 가 있다면(즉 두 사용자에게 겹치는 BC가 두 개나 있다는 뜻)  
                        else: # 0,1 번째 원소 모두 겹치면 일단 0번째를 userA에 넣을게요...
                            userA_BC_list.append(prev_same)
                            userB_BC_list.append(userB_cur_list[idx][1])
                            break
                    # 두 사용자의 BC가 겹치지 않는 경우 
                    else:
                        # 이전에 겹친 BC가 없다면 userA는 해당 BC의 성능으로, userB는 B대로 값 넣어줌
                        if(is_same_already==False):
                            userA_BC_list.append(userA_cur_list[idx][1])
                            userB_BC_list.append(userB_cur_list[idx][1])
      
                        # 이전에 겹친 BC가 있다면 
                        else:
                            # 현재 초에서 두 사용자 성능의 합이 최대가 되면 되니까 
                            # 맨 첫번째 BC가 겹쳐 첫번째 성능이 동일하다면 
                            # 두번째 인덱스의 BC가 큰 사용자가 두번째 BC를 가지고
                            # 다른 사용자가 첫번째 BC를 가지면 결국 합은 최대값이 된다.
                            if(userA_cur_list[idx][1]>=userB_cur_list[idx][1]):
                                userA_BC_list.append(userA_cur_list[idx][1])
                                userB_BC_list.append(prev_same)
                            else:
                                userA_BC_list.append(prev_same)
                                userB_BC_list.append(userB_cur_list[idx][1])
                        break
                         
        if(sec<M):
            userA=move_to(userA, userA_move[sec])
            userB=move_to(userB, userB_move[sec])
 
    return (sum(userA_BC_list)+sum(userB_BC_list))
 
# 사용자의 위치가 BC 충전 범위 안에 들어가는지 검사
def is_distance_in(user, BC): # user=[x,y] AP=[x,y,c,p]
    d=abs(user[0]-BC[0])+abs(user[1]-BC[1])
    return d<=BC[2]
 
def move_to(user, move): # user=[x,y] move=0 or 1 or 2 or 3 or 4
    if(move==0):
        return user
    elif(move==1):
        return [user[0], user[1]-1]
    elif(move==2):
        return [user[0]+1, user[1]]
    elif(move==3):
        return [user[0], user[1]+1]
    elif(move==4):
        return [user[0]-1, user[1]]
     
T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    M, A=map(int,input().split())
    userA_move=list(map(int, input().split()))
    userB_move=list(map(int, input().split()))
         
    BC_list=[]
    for _ in range(A):
        BC=list(map(int, input().split())) # [x,y, c, p]
        BC_list.append(BC)
        result=Max_BC(M,A, userA_move, userB_move, BC_list)
    print('#{} {}'.format(test_case, result))