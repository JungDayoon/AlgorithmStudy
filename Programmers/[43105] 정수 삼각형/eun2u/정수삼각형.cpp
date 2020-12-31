#include <string>
#include <cstring>
#include <vector>
#include <algorithm>
#include <iostream>
using namespace std;

int solution(vector<vector<int>> triangle) {
    
    int maxTri[500][500];
    memset(maxTri,0,sizeof(maxTri));
    int answer = 0;
    int n= triangle.size();
    
    maxTri[0][0]=triangle[0][0];
    for(int i=1;i<n;i++){
        maxTri[i][0]=maxTri[i-1][0]+triangle[i][0];
        answer=max(answer,maxTri[i][0]);
        for(int j=1;j<i+1;j++){
            maxTri[i][j]=max(maxTri[i-1][j],maxTri[i-1][j-1])+triangle[i][j];
            answer=max(answer,maxTri[i][j]);
        }
    }

    return answer;
}