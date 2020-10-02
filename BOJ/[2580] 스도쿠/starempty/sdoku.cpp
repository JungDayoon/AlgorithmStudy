//
//  main.cpp
//  sdoku
//
//  Created by knuprime019 on 2020. 10. 2..
//  Copyright © 2020년 knuprime019. All rights reserved.
//
#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

int sdoku[9][9] = {0};
vector<pair<int,int>> list;

void print(){
    for(int i = 0; i < 9; i++){
        for(int j=0; j < 9; j++){
            cout << sdoku[i][j];
            if (j != 8)
                cout << " ";
        }
        cout << endl; //줄바꿈
    }
}

bool check(int row, int col, int k){
    //print();
    //cout << endl;
    bool flag = true;
    int checked[3] = {0,0,0};
    
    for (int i = 0 ; i < 9; i++){
        if(sdoku[row][i] == k) checked[0] = 1;
        if(sdoku[i][col] == k) checked[1] = 1;
    }
    int trow = (row/3)*3;
    int tcol = (col/3)*3;
    
    for (int i = trow; i < trow+3; i++){
        for(int j = tcol; j < tcol+3; j++){
            if(sdoku[i][j] == k){
                checked[2] = 1;
                break;
            }
        }
    }
    for(int i = 0; i < 3; i++ ){
        if(checked[i] == 1){
            flag = false;
            break;
        }
    }
    return flag;
}
void dfs(int cnt){
    if (cnt == list.size()){
        print();
        exit(0);
    }
    for(int k = 1; k < 10; k++){
        int i = list[cnt].first;
        int j = list[cnt].second;
        if(check(i, j, k)){
            sdoku[i][j] = k;
            dfs(cnt+1);
            sdoku[i][j] = 0;
        }
    }
    return;
}

int main(int argc, char** argv)
{
    for(int i=0; i<9; i++){
        for(int j = 0; j < 9; j++){
            cin >> sdoku[i][j];
            if(sdoku[i][j] == 0){
                list.push_back(make_pair(i,j));
            }
        }
    }
    //print();
    dfs(0);
    
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}