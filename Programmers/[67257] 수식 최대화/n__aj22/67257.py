from itertools import permutations
import copy
def calculate(numbers, operands, order):
    for check_oper in order:
        number_stack = []
        oper_stack = []
        number_stack.append(numbers[0])
        for i in range(len(operands)):
            if(check_oper == operands[i]):
                front_num = number_stack.pop(-1)
                if(check_oper == '+'):
                    front_num += numbers[i+1]
                elif(check_oper == "-"):
                    front_num -= numbers[i+1]
                else:#곱
                    front_num *= numbers[i+1]     
                number_stack.append(front_num)
            else:
                oper_stack.append(operands[i])
                number_stack.append(numbers[i+1])
        operands = oper_stack
        numbers = number_stack

    return abs(numbers[0])
def solution(expression):
    answer = 0
    operand = [] #연산자

    for alpha in expression:
        if '-' == alpha:
            operand.append('-')
        elif('+' == alpha):
            operand.append('+')
        elif('*' == alpha):
            operand.append('*')

    oper_set = set(operand)#중복제거(연산자 종류만 추출)
    order = list(permutations(oper_set, len(oper_set))) #연산순서

    #연산자 제거 
    expression = expression.replace("-", " ")
    expression = expression.replace("+", " ")
    expression = expression.replace("*", " ")
    #숫자만 리스트로 변경 
    expression = list(map(int, expression.split(" ")))

    for i in order:
        numtemp = copy.deepcopy(expression)
        opertemp = copy.deepcopy(operand)
        a = calculate(numtemp, opertemp, i)
        answer = max(answer, a)

    return answer

if __name__ == "__main__":
    expressions = ["2-990-5+2+32","100-200*300-500+20"]
    for i in expressions:
        print(solution(i))