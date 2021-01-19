#include <iostream>
#include <utility>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;
int N,W,H;
int ans=987654321;
int map[16][16];
int cmap[16][16];
int dy[4]={0,-1,0,1};
int dx[4]={1,0,-1,0};
bool inRange(int y, int x){
    if(y<0 || y>H) return false;
    if(x<0 || x>W) return false;
    return true;
}
int countBricks(){
    int ret=0;
    for(int i=0;i<H;i++)
        for(int j=0;j<W;j++)
            if(cmap[i][j]!=0)
                ret++;

    return ret; 
}
void copy(int a[][16], int b[][16]){ //copy a to b;
    for(int i=0;i<H;i++)
        for(int j=0;j<W;j++)
            b[i][j]=a[i][j];
}
void shiftDown(){
    for(int j=0;j<W;j++){
        vector<int> tmp;
        for(int i=0;i<H;i++)
            if(cmap[i][j]!=0)
                tmp.push_back(cmap[i][j]);
        

        for(int i=0;i<H-tmp.size();i++)
            cmap[i][j]=0;
        int idx=0;
        for(int i=H-tmp.size();i<H;i++)
            cmap[i][j]=tmp[idx++];
    }
}
void bomb(int num, int y, int x){
    cmap[y][x]=0;
    if(num==1) return;

    for(int i=0;i<4;i++){
        for(int j=1;j<num;j++){
            int ny=y+dy[i]*j;
            int nx=x+dx[i]*j;

            if(!inRange(ny,nx)) break;
            if(cmap[ny][nx]==0) continue;

            bomb(cmap[ny][nx],ny,nx);
        }
    }
}
void shoot(vector<int> picked){
    //copy map to cmap;
    memset(cmap,0,sizeof(cmap));
    copy(map, cmap);

    for(int x: picked){
        for(int y=0;y<H;y++){
            if(cmap[y][x]!=0){ //맨위에 벽돌
                int num=cmap[y][x];
                bomb(num,y,x); 
                if(num!=1) // 1보다 클때 shiftDown
                    shiftDown();
                break;
            }
        }       
    }
    int result=countBricks();
    ans=min(ans,result);
}
void dfs(vector<int> picked, int W, int cnt){
    if(ans==0) return;
    if(cnt==N){
        shoot(picked);
        return;
    }
    for(int i=0;i<W;i++){
        picked.push_back(i);
        dfs(picked,W,cnt+1);
        picked.pop_back();
    }
}
int main(){
    int TC;
    cin>>TC;
    for(int tc=1;tc<=TC;tc++){
        cin>>N>>W>>H;
        
        memset(cmap,0,sizeof(cmap));
        memset(map,0,sizeof(map));
        for(int i=0;i<H;i++)   
            for(int j=0;j<W;j++)
                cin>>map[i][j];

        ans=987654321;
        vector<int> picked;
        dfs(picked,W,0);
        cout<<"#"<<tc<<" "<<ans<<'\n';
    }
    return 0;
}