# [1992] 쿼드트리 - C++

## 분류

분할정복



## 제약조건
N은 2의 제곱수
1≤N ≤64(2*6)
   
## 접근법

일반적인 분할정복 문제이다   

```c++
void quardTree(int n,int y, int x){
    
    int ret=checkCompress(n,y,x);
    if(ret != -1){
        cout<<ret;
        return;
    }
    
    cout<< "(";
    for(int i=y; i<y+n;i+=n/2){
        for(int j=x;j<x+n;j+=n/2){
            quardTree(n/2,i,j);
        }
    }
    cout<<")";
    
}
```
   



## 후기
- 디버깅에 의존하지 말고 생각하고 푸는 습관을 기르자..!!



