# [Programmers]/[64064] 불량 사용자 - python

## 분류
시뮬레이션, dfs

## 접근법
- 먼저 길이를 key값으로 dictionary를 만든다.
- banned_id의 길이에 해당하는 dictionary로 찾아가 dfs를 통해 불량사용자의 조합을 찾아낸다.

## 제약조건
- 불량사용자가 두번 들어가면 안됨.

## 코드설명
`answerList[]` : 가능한 user의 조합
`userDict{}` : user의 길이를 key값으로 하는 dictionary
`banList[]` : 각각의 `banned_id`에 해당하는 user_id를 list로 저장
`find_id()` : `lis`안에 `banned_id`에 해당하는 user_id를 모두 모아 list로 return
`find_comb()` : `banList`안에 있는 id들의 조합을 찾음. dictionary를 통해 key의 갯수가 banList의 길이가 같으면 그 dictionary가 answerList에 있는지 확인 후 deepcopy한 뒤 append한다.
`solution()`: answerList의 갯수를 return


## 후기
- 불량 사용자의 list까지는 만들었는데 어떻게 조합을 해야할지 몰랐다.
- 지헤의 코드를 참조하니 재귀함수를 통해 dfs를 해주면 되는것... 새삼 공부를 헛했다고 느꼈다.
- 조합은 dfs! 조합은 dfs!