# [1300] k번째 수 - C++

## 분류

이분탐색



## 제약조건

N은 10^5보다 작거나 같은 자연수이다. 둘째 줄에 k가 주어진다. k는 min(109, N2)

⇒ 범위가 매우 크기 때문에, 배열에 담을 수도 정렬할 수도 없다.   

   
## 접근법

이분 탐색을 적용해야 하는건 쉽게 알 수 있다. 
하지만 풀었더니 시간초과다.   

```c++
long long binarySearch(long long mid){
    long long cnt=0;

    for(int i=1;i<=N;i++){
        //cnt+=min(mid/i,N);
         for(int j=1;j<=N;j++){
             if(i*j <= mid)
                 cnt++;
         }
    }

    return cnt;
}
```
   


이중 for문 도는게 문제임
= > 그래서 새로운 방법을 찾아야 함

if(i*j ≤ mid) 해서 구하는 것과 cnt+=min(mid/i,N) 는 같다

즉, mid 보다 작거나 같은 수의 개수와 8을 1부터 N까지 나눈 값을 더한 값과 같다.   



## 후기
- long long으로 설정 안해서 시간초과 계속 떴다..
- cnt+=min(mid/i, N) 생각해내기 너무 어렵다.. 그래서 참고했다.. 
- 이분탐색은 풀면 풀수록 어려운 것 같다



