keypad = [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9'],['*', '0', '#']]
def find_position(num):
    for i in range(4):
        for j in range(3):
            if(keypad[i][j] == str(num)):
                return i, j
def solution(numbers, hand):
    answer = ''
    lefty, leftx = 3, 0
    righty, rightx = 3, 2
    left_possible = [1, 4, 7]
    right_possible = [3, 6, 9]


    for num in numbers:
        y, x = find_position(num)
        if(num in left_possible):
            answer+="L"
            lefty, leftx = y, x
        elif(num in right_possible):
            answer+="R"
            righty, rightx = y, x
        else:
            left_distance = abs(lefty-y)+abs(leftx-x)
            right_distance = abs(righty-y)+abs(rightx-x)
            if(left_distance<right_distance):
                answer+="L"
                lefty, leftx = y, x
            elif(right_distance<left_distance):
                answer+="R"
                righty, rightx = y, x
            else:
                if hand == "right":
                    answer+="R"
                    righty, rightx = y, x
                else:
                    answer+="L"
                    lefty, leftx = y, x
    return answer

if __name__ == "__main__":
    numbers = [[1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5],[7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2],[1, 2, 3, 4, 5, 6, 7, 8, 9, 0]]
    hand = ["right", "left", "right"]

    for i in range(3):
        print(solution(numbers[i], hand[i]))