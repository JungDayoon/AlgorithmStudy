#include <iostream>
#include <string>
#include <cstring>
using namespace std;
char arr[500][500];
int N, M;
int cache[500][500];
bool isin(int y, int x){
    if(0<=y && y<N && 0<=x && x<M) return true;
    return false;
}
int backtracking(int y, int x){
    if(!isin(y, x)){
        return 1;
    }
    int &ret = cache[y][x];
    if(ret!=-1) return ret;
    
    ret = 0;
    
    if(arr[y][x] == 'U'){
        ret = backtracking(y-1, x);
    }
    else if(arr[y][x] == 'R'){
        ret = backtracking(y, x+1);
    }
    else if(arr[y][x] == 'D'){
        ret = backtracking(y+1, x);
    }
    else if(arr[y][x] == 'L'){
        ret = backtracking(y, x-1);
    }
    return ret;
}
int main(){
    scanf("%d %d",&N, &M);
    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
            cin >> arr[i][j];
        }
    }
    memset(cache, -1, sizeof(cache));
    int answer = 0;
    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
            answer+=backtracking(i, j);
        }
    }
    cout<<answer<<endl;
    
}
