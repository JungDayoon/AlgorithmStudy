Simulation

문제1. 미생물군이 같은 셀에 모이는 경우, 순차적으로 비교하면 안된다는 것을 알면서도 이중포문을 이용하여 순간순간만 비교했다.

해결1. check 이중 포문
```cpp
            //check begin
            for(int s = 0; s < k; s++){
                int col_tmp = col[s];
                int row_tmp = row[s];
                int max = kk[s];
                int sum = kk[s];
                int cway = way[s];
                for(int b = s+1; b < k; b++){
                    if(col_tmp==col[b] && row_tmp==row[b]){
                        if(max < kk[b]){
                            sum += kk[b];
                            max = kk[b];
                            kk[b] = 0;
                            cway = way[b];
                        }
                        else{
                            sum += kk[b];
                            kk[b] = 0;
                        }
                    }
                    
                    kk[s] = sum;
                    way[s] = cway;
                }
                
                
            }
            //check end
```
            

생각보다 쉬웠는데 생각보다 어려웠다.

다음부터는 함수를 활용해서 짜봐야겠다.

