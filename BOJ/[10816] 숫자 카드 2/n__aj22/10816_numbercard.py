N = int(input())
card1 = list(map(int, input().split()))
M = int(input())
card2 = list(map(int, input().split()))
card3 = {}
card4 = []
for i in range(N):
    if(card1[i] in card3.keys()):
        card3[card1[i]]+=1
    else:   
        card3[card1[i]]= 1

for i in range(M):
    if(card2[i] in card3.keys()):
        card4.append(str(card3[card2[i]]))
    else:
        card4.append(str(0))

print(" ".join(card4))


