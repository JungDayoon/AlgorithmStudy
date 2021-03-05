def solution(record):
    answer = []
    name_dic = {}
    command_dic = {'Enter':'님이 들어왔습니다.', 'Leave':'님이 나갔습니다.'}
    record_list = []
    for onerecord in record:
        command = onerecord.split(" ")
        if(command[0]!="Change"):
            record_list.append([command[1], command[0]])
        if(command[0] == "Enter" or command[0] == "Change"):
            name_dic[command[1]] = command[2]
    for record in record_list:
        answer.append(name_dic[record[0]]+command_dic[record[1]])
    return answer

if __name__ == "__main__":
    record = ["Enter uid1234 Muzi", 
    "Enter uid4567 Prodo",
    "Leave uid1234",
    "Enter uid1234 Prodo",
    "Change uid4567 Ryan"]
    print(solution(record))
