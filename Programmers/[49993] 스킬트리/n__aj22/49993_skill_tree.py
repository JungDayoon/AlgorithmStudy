def solution(skill, skill_trees):
    answer = 0
    
    for i in skill_trees:
        list_of_skill = list(skill)
        if(check_possibility(list_of_skill, i)):
            answer+=1
    return answer

def check_possibility(skill, arr): #
    arr = list(arr)
    for i in arr:
        if(i in skill):
            if(i == skill[0]):
                skill.pop(0)
            else:
                return False

    return True

if __name__ == "__main__":

    skill = "CBD"
    skill_trees = ["BACDE", "CBADF", "AECB", "BDA"]
    print(solution(skill, skill_trees))
