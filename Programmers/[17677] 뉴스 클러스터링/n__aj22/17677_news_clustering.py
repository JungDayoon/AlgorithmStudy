def Jacquard(dic1, dic2):#dic1이 dic2보다 크기가 크다.
    inser_num = 0
    union_num = 0
    for key in dic1:
        if(key in dic2.keys()):
            min_num = min(dic1[key], dic2[key])
            dic1[key]-=min_num
            dic2[key]-=min_num
            inser_num+=min_num
            union_num+=min_num
    for key in dic1:
        union_num+=dic1[key]
    for key in dic2:
        union_num+=dic2[key]

    if(union_num == 0):
        answer = 1
    else:
        answer = inser_num/union_num

    return int(answer*65536)
def find_dic(check_str):
    dic = {}
    for i in range(len(check_str)-1):
        two_alpa = check_str[i:i+2]
        if(two_alpa.isalpha()):
            if(two_alpa in dic.keys()):
                dic[two_alpa]+=1
            else:
                dic[two_alpa] = 1
    return dic

def solution(str1, str2):
    A_dic = find_dic(str1.lower())
    B_dic = find_dic(str2.lower())
    if(len(A_dic)<len(B_dic)):
        return Jacquard(B_dic, A_dic)
    else:
        return Jacquard(A_dic, B_dic)

if __name__ == "__main__":
    str1 = ["FRANCE", "handshake", "aa1+aa2", "E=M*C^2"]
    str2 = ["french", "shake hands", "AAAA12", "e=m*c^2"]

    for i in range(4):
        print(solution(str1[i], str2[i]))