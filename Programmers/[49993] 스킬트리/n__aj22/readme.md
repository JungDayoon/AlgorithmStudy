# Programmers 49993번 : 스킬트리

## Algorithm

Simulation

## Description

+ `check_possibility(skill, arr)` : 

    skill : 스킬의 순서를 list 로 가지고 있다.

    arr : 확인해야하는 스킬을 string 으로 가져온다.
    ``` python
    for i in arr: # string 을 list 로 변경하고, 하나씩 확인한다.
        if(i in skill): # 만약에 현재 수행하는 스킬이 스킬의 순서에 포함된다면 스킬의 순서가 맞게 되었는지 확인해야함
            if(i == skill[0]): #첫번째 스킬인지 아닌지 확인한다.
                skill.pop(0)# 확인 완료한 스킬은 list 에서 없애주기 때문에 무조건 조건문은 첫번째 스킬인지만 확인하면 된다.
            else: #그렇지 않으면 스킬 순서가 맞지 않다는 뜻이기 때문에 False 를 return 해준다.
                return False
    ```

## Review

간단해 보이지만 아이디어를 생각하는데는 좀 시간이 걸렸다.

