백트래킹(DFS)

`bool check(int row, int col, int k)`

해당 row에, col에 k가 존재하는지, (row/3)*3, (col/3)*3으로 for문 작성하여 해당 박스에 k가 존재하는지 checked[3] 배열에 0 또는 1로 담아서 1이 하나라도 있으면 false 반환, 아니면 true 반환

처음에 checked를 3개로 고정하지않고 그냥 다 저장해서 vector로 선언했다가 시간초과때문에 굉장히 힘든 시간을 보냈다.

배열에 담는 것 보다 그냥 for문 하나씩 돌려서 리턴값 비교하는게 오히려 더 시간이 덜 든다는 것을 알게되었다.


`void dfs(int cnt)`

`if(cnt == list.size())` : 종료조건 만족하여 정답 출력완료 시 return이 아니라 exit(0)을 해주어야했다. 은주가 말해줬었지만 잠시 혼동하여 삽질 조금했다. 하지만 return과 exit()의 차이를 잘 알게되었다.

입력받을 때 0인 부분을 `vector <pair<int,int>> list`에 저장하여 탐색횟수를 줄일 수 있었다. (타인 코드 참고)

주요 함수 코드가 정말 간단해서 모두 첨부한다...

```python
void dfs(int cnt){
    if (cnt == list.size()){//파라미터를 잘 정해서 넘겨주어야 시간을 단축할 수 있다는 것을 알게되었다..
        print();
        exit(0);//return은 들어갔던 함수만 빠져나오기 exit는 프로그램 종료
    }
    for(int k = 1; k < 10; k++){
        int i = list[cnt].first;
        int j = list[cnt].second;
        if(check(i, j, k)){
            sdoku[i][j] = k; //넣어서
            dfs(cnt+1); //다음 거 확인한 뒤,
            sdoku[i][j] = 0; //백트래킹하여 빠져나온 뒤 초기화 해주는 것이야말로 백트래킹..
        }
    }
    return;
}
```

***

점심시간, 스도쿠 모두 꼬박 삼일씩 걸려서 했는데,,끝나니까 싱겁네요..

꾸준하게 노력하겠습니다.

c++도 새롭게 배우니 재밌네요.

