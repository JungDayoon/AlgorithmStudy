def check_balance(w):
    stack = []
    for i in w:
        if(i == '('):
            stack.append('(')
        else:
            if(len(stack) == 0):
                stack.append(')')
            else:
                if(stack[-1] == '('):
                    stack.pop(-1)
                else:
                    stack.append(')')
    if(len(stack) == 0):
        return True
    return False
def recursive(w):
    if(w==""):
        return ""
    leftcount = 0
    rightcount = 0
    u, v = '',''
    for i in range(len(w)):
        if(w[i]=='('):
            leftcount+=1
        else:
            rightcount+=1
        if(leftcount == rightcount):
            u = w[0:i+1]
            v = w[i+1:]
            break
    if(check_balance(u)):
        return ''.join(u)+recursive(''.join(v))
    else:
        empty = "("
        empty+= recursive(''.join(v))
        empty+= ")"
        for i in u[1:len(u)-1]:
            if(i == '('):
                empty+= ")"
            else:
                empty+= "("
        return empty
def solution(p):
    
    return recursive(p)

if __name__ == "__main__":
    words = ["(()())()", ")(", "()))((()"]
    for word in words:
        print(solution(word))