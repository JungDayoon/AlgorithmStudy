from itertools import combinations
def is_possible(candidate, col_list):
    for candi in candidate:
        same_count = 0
        for one in candi:
            if(one in col_list):
                same_count+=1
        if(same_count == len(candi)):
            return False
    return True
def is_candidatekey(relation, col_list):
    check_list = []
    for one in relation:
        new_list = []
        for i in col_list:
            new_list.append(one[i])
        if(new_list not in check_list):
            check_list.append(new_list)
    if(len(check_list) == len(relation)):
        return True
    return False

def solution(relation):
    answer = 0
    col = [i for i in range(len(relation[0]))]
    candidate_list = []
    for i in range(1, len(relation[0])+1):
        check_list = list(combinations(col, i))
        for one_check in check_list:
            one_check = list(one_check)
            if(is_possible(candidate_list, one_check)):
                if(is_candidatekey(relation, one_check)):
                    candidate_list.append(one_check)
    return len(candidate_list)

if __name__ == "__main__":
    relation = [["100","ryan","music","2"],
    ["200","apeach","math","2"],
    ["300","tube","computer","3"],
    ["400","con","computer","4"],
    ["500","muzi","music","3"],
    ["600","apeach","music","2"]]
    print(solution(relation))