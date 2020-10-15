new_string_list = []
string_list = list(input())
def left(delete_list):
    new_delete_list = []
    global string_list

    stack = []
    for i in range(len(string_list)):
        if(string_list[i] == ')'):
            minus_num = 0
            while(True):
                now_char = stack.pop()
                minus_num += 1
                if(now_char == '('):
                    minus_num+=1
                    if (i in delete_list):
                        new_delete_list.append(len(stack))
                    for k in range(minus_num):
                        stack.append(0)
                    break
        else:
            stack.append(string_list[i])
    new_list = delete_list + new_delete_list
    new_string_list.append(''.join(list(string_list[i] for i in range(len(string_list)) if(i not in new_list))))

    return

def right(target,now,delete_list, prev):
    if(target == now):
        left(delete_list)
        return
    for i in range(prev, -1, -1):
        if(string_list[i] == ')'):
            delete_list.append(i)
            right(target, now+1, delete_list,i-1)
            delete_list.pop(-1)

if __name__ == "__main__":
    left_num = 0
    right_num = 0
    for i in string_list:
        if(i == '('):
            left_num+=1
        elif(i == ')'):
            right_num+=1

    check_num = min(left_num, right_num)

    for i in range(1, check_num+1):
        right(i, 0, [], len(string_list)-1)
    new_string_list.sort()
    new_list = []
    for i in new_string_list:
        if(i not in new_list):
            new_list.append(i)
    for i in new_list:
        print(str(i))