#include <iostream>
#include <vector>
using namespace std;

const int MAX = 1000001;
int dp[MAX][2], N;
vector<int> graph[MAX];
bool visited[MAX];

void start(int node){
    visited[node] = true;
    dp[node][0] = 1;
    
    for(int child:graph[node]){
        if(!visited[child]){
            start(child);
            dp[node][1]+=dp[child][0];
            dp[node][0]+=min(dp[child][0], dp[child][1]);
        }
    }
    return;
}
int main(){
    scanf("%d",&N);
    for(int i=0;i<N-1;i++){
        int u, v;
        scanf("%d %d", &u, &v);
        graph[u].push_back(v);
        graph[v].push_back(u);
    }
    start(1);
    printf("%d", min(dp[1][0], dp[1][1]));
    return 0;
}