#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<vector<int> > adj;
int n;
int bfs(int src,int dest){
    vector<int> distances(n,-1);
    queue<int> q;
    distances[src]=0;
    q.push(src);

    while(!q.empty()){
        int here=q.front();
        q.pop();

        for(int i=0;i<adj[here].size();i++){
            int there=adj[here][i];
            if(distances[there]==-1){
                q.push(there);
                distances[there]=distances[here]+1;
            }
        }
    }
    return distances[dest];
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int a, b,m;
    cin>>a>>b>>n>>m;
    adj.resize(n); //0부터 n-1
    for(int i=0;i<m;i++){
        int pair1,pair2;
        cin>>pair1>>pair2;
        adj[pair1-1].push_back(pair2-1); //양방향
        adj[pair2-1].push_back(pair1-1);
    }
    cout<< bfs(a-1,b-1)<<'\n';

    return 0;

}