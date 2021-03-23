#include <iostream>
#include <vector>
#include <cstring>
#include <cmath>

using namespace std;
const int INF = pow(2, 31)-1;
int N;
vector<pair<int, int>> matrix;
int cache[500][500];
int find_min(int start, int end){
    int &ret = cache[start][end];
    if(ret!=-1) return ret;
    if(start==end){
        return 0;
    }
    if(start+1 == end){
        return matrix[start].first*matrix[start].second*matrix[end].second;
    }
    
    ret = INF;
    for(int k=start;k<end;k++){
        ret = min(ret, find_min(start, k)+find_min(k+1, end)+matrix[start].first*matrix[k].second*matrix[end].second);
    }
    return ret;
    
}

int main(){
    scanf("%d", &N);
    
    for(int i=0;i<N;i++){
        int r,c;
        scanf("%d %d",&r, &c);
        matrix.push_back(make_pair(r, c));
    }
    memset(cache, -1, sizeof(cache));
    printf("%d", find_min(0, N-1));
    return 0;
}
