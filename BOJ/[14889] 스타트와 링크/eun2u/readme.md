## 분류💁

조합, DFS, 브루트 포스

## 접근법

- 조합 문제다. 


### 시간 초과 난 코드
```cpp
void pickS(vector<int> picked,int idx, int cnt, int toPick) {
	if (cnt == toPick/2) {
		calculAbility(picked);
		return;
	}
	for (int i = idx; i < toPick; i++) {
		if (visited[i]) continue;
		visited[i] = true;
		picked.push_back(i);
		pickS(picked, idx + 1, cnt + 1, toPick);
		visited[i] = false;
		picked.pop_back();
	}
}
```


## 후기💡

- 예전에 풀어봤던 문제인데, 새롭게 풀었더니 시간초과가 떴다. 일반적인 DFS 조합 코드는 시간초과가 떴다..

- 예를 들어, (1, 2, 3) (4, 5, 6)을 두번씩 확인해서 그런 것 같다. 근데 귀찮아서 조합을 다르게 짜서 시간을 줄였다. 




