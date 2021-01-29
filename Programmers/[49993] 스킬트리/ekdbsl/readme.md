# [Programmers] 스킬 트리 - Python

### :computer: Algorithm

> 구현



### :computer: Logic

skill tree의 skill이 선행단계를 지키고 있는지를 확인하기 위해 degree라는 변수를 두었다.

맨 처음에 degree는 0이고, skill node가 skill에 해당하는 알파벳이면, 이때 해당하는 degree의 skill과 동일한지를 확인한다.

skill node가 skill에 포함되지 않으면 고려대상이 아니기 때문에 continue한다.

skill node가 skill에 포함되어 있지만 해당하는 degree의 skill과 동일하지 않다면, 이는 순서를 지키지 않은 것이므로 break 한다.