import sys
sys.stdin = open("/Users/najihye/algo2/SWEA/[5658] 보물상자 비밀번호/n__aj22/sample.txt", "r")

T = int(input())
def turn_arr(arr):
    final_alpa = arr.pop()
    arr.insert(0, final_alpa)
    return arr
def make_number_arr(arr, N):
    number = []
    for i in range(4):
        number.append(make_decimal("".join(arr[i*N:(i+1)*N])))
    
    return number

def make_decimal(num_str):
    return int(num_str, 16)
for test_case in range(1, T + 1):
    N, K = map(int, input().split())
    string = str(input())
    arr_string = list(string)
    turn = int(N/4)
    number = []
    for i in range(turn):
        arr_string = turn_arr(arr_string)
        number_list = make_number_arr(arr_string, turn)
        for i in range(len(number_list)):
            if(number_list[i] not in number):
                number.append(number_list[i])
    sorted_arr = list(sorted(number, reverse=True))
    print("#"+str(test_case)+" "+str(sorted_arr[K-1]))


    # print(make_decimal('1F7'))

