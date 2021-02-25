Simulation

1. 채울 수 있는 배열의 크기는 100x100으로 한정되어 있다.
2. 숫자의 범위: 100보다 작거나 같은 자연수(1<=n<=100)
3. r연산은 col>=row 인 경우

node class를 선언하면 compareTo를 이용하여 숫자 정보를 정렬하기도 하고 다시 arr에 넣을때 용이하다.

[연산 예시]
```java
for(int x = 0; x < col; x++) {
	for(int y = 0; y < row; y++) {
		count[arr[x][y]]++;
		arr[x][y] = 0;
	}
	for(int i = 1; i < count.length; i++) {
		if(count[i] != 0) list.add(new node(i, count[i]));
	}
					
	Collections.sort(list);
					
	int idx = 0;
	for(node n: list) {
		arr[x][idx++] = n.n;
		arr[x][idx++] = n.cnt;
	}
	rsize = Math.max(rsize, idx);
					
	list.clear(); //node를 담고 있는 arrayList 초기화
	Arrays.fill(count, 0); //count배열 초기화
}
row = rsize;
```
- row나 col을 1초마다 새로운 숫자로 업데이트하는 것을 잊어서는 안된다.

- 1차원 count 배열을 두어서 arr속 숫자를 인덱스로 두고 카운팅을 해준다.(count 배열을 int[] count = new int[101]로 선언했다.)

- 카운팅 후 바로 0으로 바꿔줌으로써 arr 재사용 가능