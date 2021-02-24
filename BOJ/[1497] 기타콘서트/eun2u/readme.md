# [1497] 기타콘서트
## 분류💁

비트마스킹, 백트래킹

</br>

## 접근법

> 최대한 많은곡을 연주해야할 때, 필요한 기타의 최소개수를 구해야한다!
- 연주가능한 최대 곡이 50이기 때문에 long long을 사용해야한다.
- 선택한 기타들이 연주할 수 있는 곡은 `|`(OR 연산)을 이용하면, 전체 연주가능한 곡을 알 수 있다. 
- 기본적인 로직은 해당 기타를 선택한다 / 선택안한다로 재귀호출을 한다.
- `if (scnt > maxSong)` : 곡 개수 > 최대곡개수
    * `maxSong`(최대곡) 과 `result`(최소기타)를 갱신한다
- `else if(scnt == maxSong)` : 곡개수 == 최대곡개수
    * `result`(최소기타) 개수만 갱신한다

```cpp
void playGuitar(int idx, int gcnt, long long songs){
    int scnt=countSong(songs);
    if(scnt > maxSong){
        maxSong=scnt;
        result=gcnt;
    }
    else if(scnt==maxSong){
        result=min(result,gcnt);
    }
    if(idx>=N) return;

    playGuitar(idx+1, gcnt+1, songs|guitar[idx]);
    playGuitar(idx+1, gcnt, songs);
}
```


## bitset STL
```cpp
    #include <bitset>

    //1의 개수를 반환 (연주가능한 곡의 개수)
    int countSong(long long snum){
        bitset<50> ret(snum);
        return ret.count();
    }

    ...
    //1LL은 long long 에서의 1이다.
    guitar[i] |= (1LL << M-j-1);
```

</br>

## 후기💡
- 연주 가능여부를 y/n으로 알 수 있기 때문에 비트마스킹으로 풀 수 있다. 
- 처음으로 비트마스킹으로 풀어보았다. 