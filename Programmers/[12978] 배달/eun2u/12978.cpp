#include <iostream>
#include <utility>
#include <vector>
#include <queue>
using namespace std;
const int INF=987654321;
vector<pair<int,int> > adj[51];

vector<int> dijkstra(int N,int src){
    vector<int> dist(N+1,INF);
    priority_queue<pair<int,int> > pq;//cost, V
    pq.push(make_pair(0,src));
    dist[src]=0;

    while(!pq.empty()){
        int cost=-pq.top().first;
        int here=pq.top().second;
        pq.pop();
        
        if(dist[here] < cost) continue;
        
        for(int i=0;i<adj[here].size();i++){
            int there=adj[here][i].first;
            int nextDist=cost+adj[here][i].second;
            
            if(nextDist < dist[there]){
                dist[there]=nextDist;
                pq.push(make_pair(-nextDist, there));
            }
        }
    }
    
    return dist;
}
int solution(int N, vector<vector<int> > road, int K) {
    int answer = 0;

    for(int i=0;i<road.size();i++){
        vector<int> edge=road[i];
        
        adj[edge[0]].push_back(make_pair(edge[1],edge[2]));
        adj[edge[1]].push_back(make_pair(edge[0],edge[2]));
    }
    
    vector<int> dist=dijkstra(N,1);
    for(int i=1;i<N+1;i++){
        if(dist[i]<=K){
            cout<<dist[i] <<" ";  
            answer++;
        }  
    }
    return answer;
}