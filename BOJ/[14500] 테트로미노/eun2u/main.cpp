//
//  main.cpp
//  14500
//
//  Created by 한은주 on 2020/08/06.
//  Copyright © 2020 한은주. All rights reserved.
//

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <cstring>
#include <utility>

using namespace std;
int m,n;
int board[500][500]; //입력으로 주어지는 수는 1,000을 넘지 않는 자연수
int tetromino[19][4][2]={
    {{0,0},{0,1},{0,2},{0,3}},
    {{0,0},{1,0},{2,0},{3,0}},
    {{0,0},{0,1},{1,1},{-1,1}},
    {{0,0},{0,1},{0,2},{-1,1}},
    {{0,0},{0,1},{0,2},{1,1}},
    {{0,0},{1,0},{2,0},{1,1}},
    {{0,0},{1,0},{2,0},{2,1}},
    {{0,0},{0,1},{0,2},{1,0}},
    {{0,0},{0,1},{1,1},{2,1}},
    {{0,0},{0,1},{0,2},{-1,2}},
    {{0,0},{1,0},{1,1},{2,1}},
    {{0,0},{0,1},{-1,1},{-1,2}},
    {{0,0},{0,1},{1,0},{1,1}},
    
    {{0,0},{0,1},{-1,1},{-2,1}},
    {{0,0},{1,0},{1,1},{1,2}},
    {{0,0},{0,1},{1,0},{2,0}},
    {{0,0},{0,1},{0,2},{1,2}},
    {{0,0},{0,1},{1,0},{-1,1}},
    {{0,0},{0,1},{1,1},{1,2}}
};
bool inRange(int y, int x){
    if(y<0 || y>=m) return false;
    if(x<0 || x>=n) return false;
    
    return true;
}
int countMax(int y, int x){
    
    int max_value=0;

    for(int dir=0;dir<19;dir++){
        int sum=0;
        
        for(int i=0;i<4;i++){
            int figure_y=y+tetromino[dir][i][0];
            int figure_x=x+tetromino[dir][i][1];
            
            if(!inRange(figure_y,figure_x)) continue;
            
            sum+=board[figure_y][figure_x];
        }
        max_value=max(max_value,sum);
    }
    return max_value;
    
}
int main() {

    ios_base :: sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin>>m>>n;
    
    for(int i=0;i<m;i++)
        for(int j=0;j<n;j++)
            cin>>board[i][j];
          
    int result=0;
    for(int i=0;i<m;i++)
        for(int j=0;j<n;j++)
            result=max(result,countMax(i,j));
    
    cout<< result<<endl;
    return 0;
}
