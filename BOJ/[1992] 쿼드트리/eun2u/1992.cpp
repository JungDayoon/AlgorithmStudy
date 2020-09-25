//1992. 쿼드트리

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;
int N;
int board[64][64];

int checkCompress(int n, int y, int x){
//n은 탐색 크기 y, x는 시작 좌표

   int compare=board[y][x];
   for(int i=y;i<y+n;i++)
       for(int j=x;j<x+n;j++)
           if(board[i][j] != compare)
                return -1;
       
   return compare;
}
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
void makeGraph(vector<string> input){
    
    for(int i=0;i<N;i++)
        for(int j=0;j<N;j++)
            board[i][j]=input[i].at(j)-'0';
}
int main(){

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin>>N;

    string s;
    vector<string> input;
    for(int i=0;i<N;i++){
        cin>>s;
        input.push_back(s);
    }
    makeGraph(input);
    quardTree(N,0,0);

    return 0;
}