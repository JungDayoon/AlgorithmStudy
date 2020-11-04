//
//  main.cpp
//  sadari
//
//  Created by knuprime019 on 2020. 11. 4..
//  Copyright © 2020년 knuprime019. All rights reserved.
//

#include <iostream>
using namespace std;
int n, m, h;
int board[31][11];
int answer = 4;

bool check(){
    for(int start = 0; start < n; start++){
        int k = start;
        for(int j = 0; j < h; j++){
            if(board[j][k]){
                k += 1;
            }
            else if(board[j][k-1]){
                k -= 1;
            }
        }
        if(start != k){
            return false;
        }
    }
    return true;
}

void dfs(int cnt, int x, int y){
    if (check()){
        answer = min(answer, cnt);
        return;
    }
    else if(cnt == 3 || answer <= cnt){
        return;
    }
    else{
        
        for(int i = x; i < h; i++){
            int ny = 0;
            if(i==x){
                ny = y;
            }
            for(int j = ny; j < n-1; j++){
                if (!board[i][j] and !board[i][j+1]){
                    board[i][j] = 1;
                    dfs(cnt+1, i, j+2);
                    board[i][j] = 0;
                }
                
            }
        }
        
    }
}
int main(int argc, const char * argv[]) {
    int a,b;
    
    cin >> n >> m >> h;
    
    for(int i = 0; i < h; i++){
        for(int j = 0; j < n; j++){
            board[i][j] = 0;
        }
    }
    
    for(int i = 0; i < m; i++){
        cin >> a >> b;
        board[a-1][b-1] = 1;
    }
//    cout << '\n';
//    for(int i = 0; i < h; i++){
//        for (int j = 0; j < n; j++){
//            cout << board[i][j];
//        }
//        cout << '\n';
//    }
    dfs(0,0,0);
    if(answer < 4){
        cout << answer;
    }
    else{
        cout << -1;
    }
    
    
}
