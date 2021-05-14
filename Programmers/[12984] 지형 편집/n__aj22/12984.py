import itertools

def solution(land, P, Q):
    answer = -1
    land = list(itertools.chain.from_iterable(land))
    land.sort()
    length = len(land)
    
    answer = (sum(land) - land[0]*length)*Q
    before_land = answer
    for i in range(1, len(land)):
        if land[i] == land[i-1] :
            continue
        before_land = before_land + ((land[i]-land[i-1])*(i)*P) - ((land[i]-land[i-1])*(length-i)*Q)

        if(before_land<answer):
            answer = before_land
        else:
            break
        
    return answer
if __name__ == "__main__":
    land = [[[1, 2], [2, 3]],[[4, 4, 3], [3, 2, 2], [2, 1, 0]]]
    P = [3, 5]
    Q = [2, 3]

    for i in range(2):
        print(solution(land[i], P[i], Q[i]))