def solution(s):
    answer = float('inf')
    string_len = len(s)
    half_len = len(s)//2

    for target_length in range(1, half_len+1):
        zip_string = ""
        index = target_length
        count = 1
        cmp_string = s[0:target_length]
        while(True):
            if(index>=string_len):
                if(count != 1):
                    zip_string+=str(count)
                zip_string+=cmp_string
                answer = min(answer, len(zip_string))
                break
            if(cmp_string==s[index:index+target_length]):
                count+=1
            else:
                if(count != 1):
                    zip_string+=str(count)
                zip_string+=cmp_string
                count = 1
                cmp_string = s[index:index+target_length]
                
            index += target_length
    return answer

if __name__ == "__main__":
    string = ["aabbaccc","ababcdcdababcdcd","abcabcdede", "abcabcabcabcdededededede","xababcdcdababcdcd"]

    for i in string:
        print(solution(i))