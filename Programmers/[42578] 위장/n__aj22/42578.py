from itertools import combinations
def solution2(clothes):
    answer = 0

    clothlist = {}
    for name, cloth_type in clothes:
        if cloth_type in clothlist.keys():
            clothlist[cloth_type].append(name)
        else:
            clothlist[cloth_type] = [name]
    
    type_num = len(clothlist)
    for i in range(1, type_num+1):
        comb = list(combinations(clothlist.keys(), i))
        
        for c in comb:
            mul = 1
            for v in c:
                mul*=len(clothlist[v])
            answer+=mul
    return answer
def solution(clothes):
    answer = 0

    clothlist = {}
    for name, cloth_type in clothes:
        if cloth_type in clothlist.keys():
            clothlist[cloth_type].append(name)
        else:
            clothlist[cloth_type] = [name]
    
    mul = 1
    for key in clothlist.keys():
        mul*=(len(clothlist[key])+1)
    answer = mul - 1
    return answer
if __name__ == "__main__":
    c = [[["yellowhat", "headgear"], ["bluesunglasses", "eyewear"], ["green_turban", "headgear"]],
    [["crowmask", "face"], ["bluesunglasses", "face"], ["smoky_makeup", "face"]]]

    for i in c:
        print(solution(i))