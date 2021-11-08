# [BOJ]/[17281] ⚾

## 분류
dfs (bruteforce)

## 접근법
처음에는 문제의 설명대로 base를 list로 설정 후 n루타면 각각의 인덱스를 n만큼 더해준후 그게 3보다 크면 점수를 더해주게 했다.<br>
그랬더니 시간초과가 나고, if문으로 b1,b2,b3을 각각 설정해줘야하는 문제였다.(시간초과 때문)<br>
검색해보니 전부다 permutation이랑 if문으로 풀었다.<br>
b1,b2,b3을 각각 할당해주고, answer에 해당베이스를 더해주는 아이디어가 시간복잡도 측면에서 효율이 좋은듯.. 나는 생각해내지 못했다 ㅜ<br>
python은 시간초과 때문에 이방법 밖에 없나보다..<br>

## dfs
```python
def dfs(depth,check,player_list):
    if depth==9:
        play_game(player_list)
        return
    for i in range(1,9):
        if check[i]:
            player_list[depth] = i
            check[i]=False
            if depth==2:
                dfs(depth+2,check,player_list)
            else:
                dfs(depth+1,check,player_list)
            player_list[depth] =0
            check[i]=True
    return
```
- player_list를 1부터 돌면서 각 순서의 타자번호를 저장해준다.(0은 4번으로 이미 지정)
- depth 3은 1번타자로 정해져있기 때문에 depth==2이면 depth=4로 넘어가 준다.
- depth==9이면 play_game한다.

## play_game
```python
def play_game(player_list):
    global answer
    temp_answer = 0
    current_num = 0
    for p in play_result:
        b1,b2,b3=0,0,0
        out = 0
        while True:
            player = p[player_list[current_num]]
            if player==0:
                out+=1
            elif player==1:
                temp_answer+=b3
                b1,b2,b3 = 1,b1,b2
            elif player==2:
                temp_answer+=b2+b3
                b1,b2,b3 = 0,1,b1
            elif player==3:
                temp_answer+=b1+b2+b3
                b1,b2,b3=0,0,1
            elif player==4:
                temp_answer+=b1+b2+b3+1
                b1,b2,b3 = 0,0,0
            current_num = (current_num+1) %9
            if out==3:
                break
    answer = max(answer,temp_answer)
    return
```
- `p` - 각이닝의 타자결과이다.
- `current_num` - 현재 번호
- `player_num[current_num]` - 현재 번호의 타자번호
- `player = p[player_list[current_num]]` - 현재번호의 타자의 결과
- player==0 이면 out+1 해주고 out==3이면 그 이닝을 종료한다.
- 안타부터 홈런까지는 위의 코드와 같다.

## 후기
다시 나와도 if문으로 안풀어서 시간초과 날듯..<Br>