if __name__ == "__main__":
    target_string = input()
    key_string = input()

    lastChar = key_string[-1]
    stack = []
    length = len(key_string)
    for alpha in target_string:
        stack.append(alpha)
        if alpha == lastChar and ''.join(stack[-length:]) == key_string:
            del stack[-length:]
    if(stack == []):
        print("FRULA")
    else:
        print(''.join(stack))

