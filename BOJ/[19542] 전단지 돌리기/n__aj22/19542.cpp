#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
using namespace std;

const int MAX = 100001;
typedef struct{
    int start, end, move;
}st;
int N, S, D;
vector<st> move_info;
vector<int> adj[MAX];
vector<int> child_adj[MAX];
int depth[MAX];
bool visited[MAX];
int check_depth(int n){//n-node, s-start node, l = length
    if(child_adj[n].size() == 0){//no child
        depth[n] = 0;
        return 0;
    }
    int n_depth = 0;
    for(int nextnode:child_adj[n]){
        n_depth = max(n_depth, check_depth(nextnode));
    }
    n_depth++;
    depth[n] = n_depth;
    return n_depth;
}
int go(int n){
    
    int count = 1;
    for(int nextnode:child_adj[n]){
        if(depth[nextnode]>=D)
            count+=go(nextnode);
    }
    return count;
    
}
void make_tree(int n)//n-node
{
    for(int node:adj[n]){
        if(!visited[node]){
            visited[node] = true;
            child_adj[n].push_back(node);
            make_tree(node);
        }
    }
    return;
}
int main(){
    scanf("%d %d %d", &N, &S, &D);
    S--;
    for(int i=0;i<N-1;i++){
        int u, v;
        scanf("%d %d",&u, &v);
        u--; v--;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    visited[S] = true;
    make_tree(S);
    
    check_depth(S);
    printf("%d", 2*(go(S)-1));

    return 0;
}
