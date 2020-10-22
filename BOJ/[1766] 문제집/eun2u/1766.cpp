#include <iostream>
#include <utility>
#include <vector>
#include <queue>
#include <cstring>
#include <algorithm>
#define MAX_VAL 32001
using namespace std;
int N,M;
int inDegree[MAX_VAL];
vector<int> adj[MAX_VAL];

void topologicalSort(){
    priority_queue<int> q;

    for(int i=1;i<=N;i++)
        if(inDegree[i]==0)
            q.push(-i);
    
    while(!q.empty()){
        int here=-q.top();
        q.pop();
        cout<<here<<" ";

        for(int j=0;j<adj[here].size();j++){
            int there=adj[here][j];
            inDegree[there]--;

            if(inDegree[there]==0){
                q.push(-there);
            }
        }
    }
}
int main(){
    cin>>N>>M;

    int a,b;
    for(int i=0;i<M;i++){
        cin>>a>>b;//A번 문제는 B번 문제보다 먼저 푸는 것이 좋다는 의미
        adj[a].push_back(b);
        inDegree[b]++;
    }
    topologicalSort();
    return 0;
}