def solution(skill, skill_trees):
    answer = 0

    for skill_tree in skill_trees:
        degree = 0
        for skill_node in skill_tree:
            if skill_node in skill and skill_node == skill[degree]:
                if degree + 1 < len(skill):
                    degree += 1
            elif skill_node not in skill:
                continue
            else:
                break
        else:
            answer += 1
    return answer


print(solution("CBD", ["BACDE", "CBADF", "AECB", "BDA"]))
