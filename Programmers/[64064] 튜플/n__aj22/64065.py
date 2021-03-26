def solution(s):
    answer = []
    #{{}} 제거
    s = s.replace("{", " ") #왼쪽 괄호 공백으로 치환
    s = s.replace("}", " ") #오른쪽 괄호 공백으로 치환
    s = s.lstrip() #왼쪽 공백 제거
    s = s.rstrip() #오른쪽 공백 제거
    s = s.split(" , ") #공백,공백 기준 split
    s.sort(key = lambda x:len(x))

    for value in s:
        value_list = value.split(",")
        for num in value_list:
            num = int(num)
            if num not in answer:
                answer.append(num)
                break
    return answer

if __name__ == "__main__":
    s = ["{{2},{2,1},{2,1,3},{2,1,3,4}}",
    "{{1,2,3},{2,1},{1,2,4,3},{2}}",
    "{{20,111},{111}}",
    "{{123}}",
    "{{4,2,3},{3},{2,3,4,1},{2,3}}"]
    for i in s:
        print(solution(i))