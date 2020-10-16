## 분류💁

브루트 포스, 시뮬레이션 

## 접근법

- 5번 선거구를 설정하면 나머지 1번, 2번, 3번, 4번 선거구는 조건에 맞게 설정하면 된다.



### 5번 선거구 설정
```cpp
    // 5번 선거구
	int left = 0, right = 0;
	bool turnLeft = true, turnRight = true;
	for(int row = x; row <= x + d1 + d2; row++) {
		for(int col= y + left; col<= y+ right; col++) {
			zone[row][col] = 5;
			pop[4] += A[row][col];
		}

		if(left == -d1) turnLeft = !turnLeft;
		if(right == d2) turnRight = !turnRight;
			
		if(turnLeft) left--;
		else left++;
			
		if(turnRight) right++;
        else right--;
	}
  ```



## 후기💡

- 어려운 문제는 아닌 것 같은데, 인덱스 설정하는게 어려워서 다른사람들이 푼 것들을 참고했다.

