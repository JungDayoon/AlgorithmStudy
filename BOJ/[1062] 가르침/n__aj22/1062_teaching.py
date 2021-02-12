from collections import OrderedDict
import string
base = ["a", "n", "t", "i", "c"]
total = []
max_num = 0
visited = {}
def init_alpha():

    alpha_list = list(string.ascii_lowercase)
    for one in alpha_list:
        if(one in base):
            visited[one] = True
        else:
            visited[one] = False
        total.append(one)
def find_max():
    check_num = 0
    for word in origin_word_arr:
        flag = True
        for alpha in word:
            if(visited[alpha] == False):
                flag = False
                break
        if(flag == True):
            check_num+=1
    global max_num
    max_num = max(max_num, check_num)
    return 
def backtracking(choose_num, index):
    if(choose_num==K):
        find_max()
        return
    
    for i in range(index,26):
        if(visited[total[i]] == False):
            visited[total[i]] = True
            backtracking(choose_num+1, i)
            visited[total[i]] = False
    return
if __name__ == "__main__":
    N, K = map(int, input().split())
    origin_word_arr = []
    init_alpha()
    for i in range(N):
        now_string = input()
        origin_word_arr.append(now_string)
    if(K<5):
        print(0)
    elif(K == 26):
        print(N)
    else:
        K = K-5
        backtracking(0, 0)
        print(max_num)

