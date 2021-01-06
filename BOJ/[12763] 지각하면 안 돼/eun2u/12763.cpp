#include <iostream>
#include <queue>
#include <vector>
#include <utility>
using namespace std;
int N,T,M;
const int INF=987654321;
struct way{
    int v, t,m; //정점, 시간, 돈
};
struct cmp{
    bool operator()(way a, way b){
        return a.m > b.m;
    }
};

vector<vector<way> > adj;
int dijkstra(int src){
    vector<int> cost(N,INF);
    vector<int> times(N,INF);
    priority_queue<way, vector<way>, cmp> pq;
    cost[src]=0;
    times[src]=0;
    way w={src,0,0};
    pq.push(w);

    while(!pq.empty()){
        int c=pq.top().m;
        int here=pq.top().v;
        int t=pq.top().t;
        pq.pop();

        if(cost[here] < c && times[here] < t) continue;

        for(int i=0;i<adj[here].size();i++){
            int there=adj[here][i].v;
            int nextC=c+ adj[here][i].m;
            int nextT=t+adj[here][i].t;

            if(nextT > T || nextC > M) continue;
    
            if(cost[there] > nextC){
                cost[there]=nextC;
                times[there]=nextT;
                way a={there, nextT, nextC};
                pq.push(a);
            }
            else if(times[there] > nextT){
                times[there]=nextT;
                way a={there, nextT, nextC};
                pq.push(a);
            }
        }
    }

    return cost[N-1]==INF?-1: cost[N-1];
}
int main(){
    int L, a,b,m,t;
    cin>>N>>T>>M>>L;
    adj.resize(N);
    for(int i=0;i<L;i++){
        cin >>a>>b>>t>>m;
        way input={b-1,t,m};
        adj[a-1].push_back(input);
        input.v=a-1;
        adj[b-1].push_back(input);
    }
    
    int result=dijkstra(0);
    cout<<result<<'\n';
    return 0;
}