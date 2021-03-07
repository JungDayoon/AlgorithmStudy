# Programmers 60058번 : 괄호 변환

## Algorithm

Simulation

## Description

### Logic

1. 열쇠의 크기를 확장해준다.(이 후 설명)

2. 열쇠와 키의 값을 더해준다.

3. 열쇠의 구역 내에 값 중에 0혹은 2가 존재한다면 키가 일치하지 않았단 말이므로 break

+ 열쇠의 구역 내의 값의 합이 열쇠의 크기와 일치한다면 키가 일치했다는 말이므로 return True

4. 키를 오른쪽으로 회전한 후 2번 단계부터 시작 

### 함수 설명 

**`increase_lock(length, lock)`** : 열쇠와 자물쇠의 값 비교가 쉽도록 자물쇠를 가로, 세로, 위, 아래로 M-1 만큼 확장한다.

+ 예를 들어 다음과 같은 자물쇠가 있다고 하자. 

    <img src="https://user-images.githubusercontent.com/33089715/110241001-eaba5e80-7f91-11eb-930c-bb69df6557a8.png" width="700">

+ 기존 자물쇠의 주위에 M-1(예제에서는 M이 3이므로 2만큼) 크기만큼 1을 추가해 확장해준다.

**`turn_right(key, M)`** : 열쇠를 오른쪽으로 돌린 값을 반환하는 함수

**`solution(key, lock)`** : 로직대로 실행하는 함수

+ 확장된 자물쇠와 열쇠의 값을 더해준다.

    <img src="https://user-images.githubusercontent.com/33089715/110241197-dc207700-7f92-11eb-9da4-1b547489f463.png" width="300"> <img src="https://user-images.githubusercontent.com/33089715/110241217-fb1f0900-7f92-11eb-82af-2f17b303013f.png" width="227">
    
+ 일치하는지 확인할 때는 기존 자물쇠의 영역만 확인한다.

    + 위의 예시에서는 2또는 0이 존재하므로 열쇠와 자물쇠가 일치하지 않는다는 의미이다.

+ 일치하는 경우는 다음과 같다.

    <img src="https://user-images.githubusercontent.com/33089715/110241455-3a018e80-7f94-11eb-88cf-fa04c6738138.png" width="500">

    + 자물쇠의 영역안이 모두 1로 합이 N*N인 9와 같기 때문에 자물쇠와 열쇠가 일치한다.
## Review

열쇠와 자물쇠가 일정 부분만 겹치는 경우를 고려해주기 위해 처음에는 둘 다 원래의 크기에서 잘라주는 것을 생각했는데 고려해주다가 outofbound 에러가 날거 같아서 아에 자물쇠 크기를 크게 만들어줬다. 번거롭지만 괜찮은 아이디어였다..!! 