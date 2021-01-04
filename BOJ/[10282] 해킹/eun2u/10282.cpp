#include <iostream>
#include <queue>
#include <vector>
#include <utility>
using namespace std;

int n,d,c;
const int INF=987654321;
int comps, result;
vector<vector<pair<int,int> > > adj;
 
void timetoInfect(vector<int> dist){
    comps=0,result=0;
    for(int i=0;i<dist.size();i++){
        if(dist[i]!=INF){
            result=max(result,dist[i]);
            comps++;
        }
    }
}
void dijkstra(int src){
    vector<int> dist(n,INF);
    dist[src]=0;
    priority_queue<pair<int,int> > pq;
    pq.push(make_pair(0,src));

    while(!pq.empty()){
        int cost=-pq.top().first;
        int here=pq.top().second;
        pq.pop();
        if(dist[here] < cost) continue;
        
        for(int i=0;i<adj[here].size();i++){
            int there=adj[here][i].first;
            int nextDist=cost+adj[here][i].second;
            if(dist[there]>nextDist){
                dist[there]=nextDist;
                pq.push(make_pair(-nextDist,there));
            }
        }
    }
    timetoInfect(dist);
}
int main(){
    int TC;
    cin>>TC;
    for(int tc=0;tc<TC;tc++){
        cin>>n>>d>>c;
        adj.clear();
        adj.resize(n);
        for(int i=0;i<d;i++){
            int a,b,s;
            cin>>a>>b>>s;
            adj[b-1].push_back(make_pair(a-1,s));
        }
        
        dijkstra(c-1);
        cout<<comps<<" "<<result<<'\n';
    }
    return 0;
}