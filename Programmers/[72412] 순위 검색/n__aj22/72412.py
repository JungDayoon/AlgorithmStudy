from operator import itemgetter
def split_query_or_info(line):
    line = line.split(" ")
    word_list = []
    for word in line:
        if(word != "and"):
            if(word.isdigit()):
                word = int(word)
            word_list.append(word)
        
    return word_list
def compare(info, query):#info[index], now_query 호출
    for i in range(4):
        if(query[i]!='-'):
            if(query[i]<info[i]):
                return 0
            elif(query[i]>info[i]):
                return 1
    if(query[4]>info[4]):
        return 1
    elif(query[4]<info[4]):
        return 0
    else:
        return 2
def compare2(info, query):#info[index], now_query 호출
    for i in range(4):
        if(query[i]!='-'):
            if(query[i]<info[i]):
                return 0
            elif(query[i]>info[i]):
                return 1
    if(query[4]>info[4]):
        return 3
    elif(query[4]<info[4]):
        return 4
    else:
        return 2

def solution(infos, querys):
    answer = []
    info_word_list = []

    for info in infos:
        info_word_list.append(split_query_or_info(info))
    for query in querys:
        now_query = split_query_or_info(query)
        query_index_list = []
        for index in range(len(now_query)):
            if(now_query[index]!='-'):
                query_index_list.append(index)
        
        # print(now_query)
        # print(query_index_list)
        info_word_list.sort(key=itemgetter(*query_index_list))
        # print("---------sort------------")
        # for info in info_word_list:
        #     print(info)

        # print()
        #find down index
        left, right = 0, len(info_word_list)
        while(left<right):
            mid = (left+right)//2
            compare_result = compare(info_word_list[mid], now_query)
            if(compare_result==0 or compare_result == 2): #info>now_query
                right = mid
            elif(compare_result==1):
                left = mid+1

        down_index = left

        left, right = 0, len(info_word_list)
        while(left<right):
            mid = (left+right)//2
            compare_result = compare2(info_word_list[mid], now_query)
            if(compare_result == 0): #info>now_query
                right = mid
            elif(compare_result==1 or compare_result == 2 or compare_result == 3 or compare_result == 4):
                left = mid+1

        up_index = left

        answer.append(up_index-down_index)


    return answer
if __name__ == "__main__":
    info = ["java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"]
    query = ["java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"]
    print(solution(info, query))