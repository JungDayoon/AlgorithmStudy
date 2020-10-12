## 분류💁

DFS, 브루트 포스


## 접근법
- 타순을 정하기 때문에 순열(permutation)을 사용해야 한다. 순열은 DFS로 한다.
``` cpp
void chooseOrder(vector<int> picked, int cnt){
    if(cnt==3){ //1번선수는 4번 타자
        visited[0]=true;
        picked.push_back(0);
        chooseOrder(picked,cnt+1);
        visited[0]=false;
        picked.pop_back();
    }
    if(cnt==9){
        playBaseball(picked);//정해진 순열로 야구 진행
        return;
    }
    for(int i=1;i<9;i++){
        if(!visited[i]){
            visited[i]=true;
            picked.push_back(i);
            chooseOrder(picked,cnt+1);
            visited[i]=false;
            picked.pop_back();
        }
    }
}
```

- 3아웃은 이닝이 종료되고, 다음 이닝에서 예전 순서 이어서 타자를 한다.

- 1번 선수는 무조건 4번째 타자이다.



## 시퀀스

1. 야구선수 타순(순열)을 DFS로 정한다.

2. 순서에 따라(홈, 1루, 2루, 3루)에 누가 있는지 저장한다.

    2.1 아웃이면 아웃카운트를 높여간다.
    
    2.2 홈에 들어오면 득점한다.
    
3. 3아웃으로 이닝이 종료되면 다음 이닝으로 예전 순서 이어서 타자를 한다.


구현하면, 


```cpp
void playBaseball(vector<int> picked){
    int result=0;
    int j=0;
    for(int i=0;i<N;i++){
        memset(where,false,sizeof(where));
        int out=0;
        while(out<3){
            if(j>=9) j%=9;
            int score=ball[i][picked[j]];
            if(score==0)    out++;
            else    result+=moving(j,score); //득점된 점수를 더해준다 
            j++;
        }
    }
    maxResult=max(result,maxResult);
}
```


## 후기💡

- 야구 룰을 몰라서 이해하는데 시간이 걸렸다.

- 1시간 15분 걸렸다.


