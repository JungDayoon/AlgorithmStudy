from operator import itemgetter
from itertools import combinations
import bisect
application_map = {}
def make_map(info):
    split_info = split_query_or_info(info)
    #0
    string = ''.join(split_info[0:4])
    if string in application_map.keys():
        application_map[string].append(split_info[4])
    else:
        application_map[string] = [split_info[4]]

    comb_num = [0, 1, 2, 3]
    #1
    for i in range(1, 5):
        comb_list = list(combinations(comb_num, i))
        for comb in comb_list:
            string = ""
            for index in range(4):
                if index not in comb:
                    string+=split_info[index]
                else:
                    string+="-"
            if string in application_map.keys():
                application_map[string].append(split_info[4])
            else:
                application_map[string] = [split_info[4]]

def split_query_or_info(line):
    line = line.split(" ")
    word_list = []
    for word in line:
        if(word != "and"):
            if(word.isdigit()):
                word = int(word)
            word_list.append(word)
        
    return word_list
def search(arr, score):
    size = len(arr)
    return size - bisect.bisect_left(arr, score, lo=0, hi=size)
def solution(total_info, total_query):
    answer = []
    for info in total_info:
        make_map(info)
    for key in application_map.keys():
        application_map[key].sort()

    for query in total_query:
        now_query = split_query_or_info(query)
        clue_string = ''.join(now_query[0:4])
        if clue_string in application_map.keys():
            score_list = application_map[clue_string]
            answer.append(search(score_list, now_query[4]))
        else:
            answer.append(0)
    return answer
if __name__ == "__main__":
    info = ["java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"]
    query = ["java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"]
    print(solution(info, query))