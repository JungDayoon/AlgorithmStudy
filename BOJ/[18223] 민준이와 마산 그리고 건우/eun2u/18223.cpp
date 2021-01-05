#include <iostream>
#include <queue>
#include <utility>
#include <vector>
using namespace std;
int V,E,P;
const int INF=987654321;
vector<vector<pair<int,int> > > adj; //정점, 가중치

vector<int> dijkstra(int src){
    vector<int> dist(V,INF);
    priority_queue<pair<int,int> > pq;
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

            if(nextDist< dist[there]){
                pq.push(make_pair(-nextDist,there));
                dist[there]=nextDist;
            }
        }
    }
    return dist;
}
void checkP(){
    if(P-1==0 ||P-1==V){
        cout<<"SAVE HIM"<<'\n';
        exit(0);
    }
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin>>V>>E>>P;
    checkP();

    adj.resize(V);
    int a,b,c;
    for (int i = 0; i < E; i++){
        cin>>a>>b>>c;
        adj[a-1].push_back(make_pair(b-1,c));
        adj[b-1].push_back(make_pair(a-1,c));
    }

    vector<int> dist_fromsrc=dijkstra(0);
    int srctoP=dist_fromsrc[P-1];
    int srctoDest=dist_fromsrc[V-1];

    vector<int> dist_fromp=dijkstra(P-1);
    int ptoDest=dist_fromp[V-1];
    
    if(srctoP + ptoDest == srctoDest)
        cout<<"SAVE HIM"<<'\n';
    else 
        cout<<"GOOD BYE"<<'\n';

    return 0;
}
