#include <iostream>
#include <vector>
#include <queue>
#include <utility>
#include <algorithm>
using namespace std;
int N,D;
vector<vector< pair<int,int> > > adj;
const int INF=987654321;
int dijkstra(int src){
    vector<int> dist(D+1,INF);
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

            if(dist[there] > nextDist){
                dist[there]=nextDist;
                pq.push(make_pair(-nextDist,there));
            }
        }
    }
    return dist[D]==INF?N:dist[D];
}
int main(){

    cin>>N>>D;
    adj.resize(D+1);
    for(int i=0;i<D+1;i++)
        adj[i].push_back(make_pair(i+1,1));

    for(int i=0;i<N;i++){
        int src,dest,len;
        cin>>src>>dest>>len;
        if(src > D || dest> D) continue;
        adj[src].push_back(make_pair(dest,len));
    }    
    cout << dijkstra(0)<<'\n';
    return 0;
}
