def solution(gems):
    answer = []
    gems_value = set(gems)
    hi_start = 0
    gem_set = set()
    gem_dic = dict()
    min_interval = float('inf')
    min_lo, min_hi = 0, 0
    for lo in range(0, len(gems)):
        found_flag = False

        for hi in range(hi_start, len(gems)):
            if gems[hi] in gem_dic.keys():
                gem_dic[gems[hi]]+=1
            else:
                gem_dic[gems[hi]] = 1
            gem_set.add(gems[hi])

            if(len(gem_set) == len(gems_value)):
                found_flag = True
                hi_start = hi
                if(abs(hi-lo)<min_interval):
                    min_interval = abs(hi-lo)
                    min_hi = hi
                    min_lo = lo
                break
        if not found_flag:
            break
        
        if gems[lo] in gem_dic.keys():
            if gem_dic[gems[lo]] == 1:
                del gem_dic[gems[lo]]
                gem_set.remove(gems[lo])
            else:
                gem_dic[gems[lo]]-=1
            
        if gems[hi_start] in gem_dic.keys():
            if gem_dic[gems[hi_start]] == 1:
                del gem_dic[gems[hi_start]]
                gem_set.remove(gems[hi_start])
            else:
                gem_dic[gems[hi_start]]-=1
        
    answer = [min_lo+1, min_hi+1]
    return answer
if __name__ == "__main__":
    gems_list = [["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"],
    ["AA", "AB", "AC", "AA", "AC"],
    ["XYZ", "XYZ", "XYZ"],
    ["ZZZ", "YYY", "NNNN", "YYY", "BBB"]]

    for gem in gems_list:
        print(solution(gem))