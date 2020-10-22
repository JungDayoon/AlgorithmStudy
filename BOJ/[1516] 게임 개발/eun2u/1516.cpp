#include <iostream>
#include <utility>
#include <queue>
#include <vector>
#include <cstring>
#include <algorithm>
using namespace std;
int N;
int inDegree[501];
vector<int> adj[501];
int times[501];
int answer[501];

vector<int> topologicalSort(){
    queue<int> q;
    vector<int> order;

    for(int i=1;i<=N;i++)
        if(inDegree[i]==0)
            q.push(i);
    
    while(!q.empty()){
        int here=q.front();
        q.pop();

        for(int j=0;j<adj[here].size();j++){
            int there=adj[here][j];
            inDegree[there]--;

            answer[there]=max(answer[there],answer[here]+times[there]);

            if(inDegree[there]==0)
                q.push(there);
        }
    }
    return order;
}
int main(){
    cin>>N;
    int t,pre;
    for(int i=1;i<=N;i++){
        cin>>t>>pre;
        answer[i]=t;
        times[i]=t;
        while(pre!=-1){
            adj[pre].push_back(i);
            inDegree[i]++;
            cin>>pre;
        }
    }
    vector<int> order=topologicalSort();

    for(int i=1;i<=N;i++)
        cout<<answer[i]<<'\n';

    return 0;
}