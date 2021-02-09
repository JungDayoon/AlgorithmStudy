#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>
#include <cstring>
using namespace std;
int N,M,H,result=0;
int map[11][11];
bool visited[11][11];
pair<int,int> home;
vector<pair<int,int> > mincho;

int calculDist(int y1, int x1, int y2, int x2){
    return abs(y1-y2) + abs(x1-x2);
}
void eatMintChoco(int sy, int sx, int power,int cnt){

    for(int i=0;i<mincho.size();i++){
        int my=mincho[i].first;
        int mx=mincho[i].second;
        if(visited[my][mx]) continue;

        int len=calculDist(my,mx, sy, sx);
        if(len > power) continue;
        
        visited[my][mx]=true;
        eatMintChoco(my,mx,power-len+H,cnt+1);
        visited[my][mx]=false;
    }

    if(calculDist(sy,sx,home.first,home.second) <=power){
        result=max(result, cnt);
    }
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin>>N>>M>>H;
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            cin>>map[i][j];
            if(map[i][j]==1)
                home=make_pair(i,j);
            else if(map[i][j]==2)
                mincho.push_back(make_pair(i,j));
        }
    }

    eatMintChoco(home.first,home.second,M,0);
    cout<<result<<'\n';
    return 0;   
}