# [2688] 줄어들지 않아
## 분류💁

DP

</br>

## 접근법
> 줄어들지 않는 n자리 수의 개수. 어떤 숫자가 줄어들지 않는다는 것은 그 숫자의 각 자리 수보다 그 왼쪽 자리 수가 작거나 같을 때 이다.
- 10^64 까지 가능하기 때문에 Long을 사용한다.
- `long[][] digit` - i자릿수인 수가 j로 끝나는 경우의 수
- n==1 (자릿수 1개일 때)
    - digit[1][0]+digit[1][1] +... digit[1][9]
- n==2 (자릿수 2개일 때)
    - digit[2][0]=digit[1][0]
    - digit[2][1]=digit[1][0]+digit[1][1]
    - ...
    - digit[2][9]=digit[1][0]+digit[1][1] +... digit[1][9]

- 예를 들어, digit[2][1]은 마지막 값이 1보다 작거나 같은 0과 1에 (digit[1][0] digit[1][1]) leading zero만 추가하면 되기 때문에 `digit[2][1]=digit[1][0]+digit[1][1]`이다. 

## 점화식
```java
for(int d=2;d<=N;d++){
    if(digit[d][0]>0) continue;

    digit[d][0]=digit[d-1][0];
    for(int i=1;i<10;i++){
        digit[d][i]=digit[d][i-1]+digit[d-1][i];
    }
}
```


</br>

## 후기💡
- 처음에 가능한 전체 개수에서 줄어드는 수를 구해서 뺄려고 했는데 틀렸다. ㅜㅜ 그래서 새롭게 다시 짰다