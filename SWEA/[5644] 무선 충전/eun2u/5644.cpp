#include <iostream>
#include <string>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;
vector<int> movea; 
vector<int> moveb; 
vector< pair<int,int> > bcA;
vector< pair<int,int> > bcB;
int range[8][10][10];
int dy[5]={0,-1,0,1,0};
int dx[5]={0,0,1,0,-1};
int m, ap;
int maxVal=0;

int findMax(int ay, int ax,int by, int bx){
    bcA.clear();
    bcB.clear();
    //A,B가 속하는 BC를 넣음
    for(int i=0;i<ap;i++){
        if(range[i][ay][ax])
            bcA.push_back(make_pair(i,range[i][ay][ax]));
        if(range[i][by][bx])
            bcB.push_back(make_pair(i,range[i][by][bx]));   
    }

    int asize=bcA.size();
    int bsize=bcB.size();
    int ret=0;

    if(asize==0 && bsize==0)
        return 0;
    if(asize>0 && bsize==0) {
        for(int i=0;i<asize;i++)
            ret=max(ret,bcA[i].second);
        return ret;
    }
    if(bsize>0 && asize==0){
        for(int i=0;i<bsize;i++)
            ret=max(ret,bcB[i].second);
        return ret;
    }

    for(int i=0;i<asize;i++){
        for(int j=0;j<bsize;j++){
            if(bcA[i].first ==bcB[j].first)
                ret=max(ret,bcA[i].second);
            else
                ret=max(ret,bcA[i].second+bcB[j].second);
        }       
    }
    return ret;
}
void wirelessCharge(){
    int ay=0,ax=0;
    int by=9,bx=9;

    for(int t=-1;t<m;t++){
        if(t==-1){
            int ret=findMax(ay,ax,by,bx);
            maxVal+=ret;
            continue;
        }

        ay+=dy[movea[t]];
        ax+=dx[movea[t]];
        by+=dy[moveb[t]];
        bx+=dx[moveb[t]];

        int ret=findMax(ay,ax,by,bx);
        maxVal+=ret;
    }
}
bool inRange(int y, int x){
    if(y<0 || y>=10) return false;
    if(x<0 || x>=10) return false;
    return true;
}
void setRange(int k, int y, int x, int c,int p,int gone){
    
    if(gone==c) return;
    
    range[k][y][x]=p;
    
    for(int dir=1;dir<5;dir++){
        int ny=y+dy[dir];
        int nx=x+dx[dir];
        
        if(!inRange(ny,nx)) continue;

       //if(!visited[ny][nx]) --> 조건이 없어야함. 
        setRange(k,ny,nx,c, p,gone+1);
    }
}
void init(){
    for(int i=0;i<8;i++)
        for(int j=0;j<10;j++)
            for(int k=0;k<10;k++)
                range[i][j][k]=0;

    movea.clear();
    moveb.clear();
    maxVal=0;
}
int main(){
    int tc;
    cin>>tc;

    for(int i=0;i<tc;i++){
        cin>>m>>ap;
        init();
        int var;
        for(int j=0;j<m;j++){
            cin>>var;
            movea.push_back(var);
        }
        for(int j=0;j<m;j++){
            cin>>var;
            moveb.push_back(var);
        }
        
        int x,y,c,p;
        for(int k=0;k<ap;k++){
            cin>>x>>y>>c>>p;
            setRange(k, y-1, x-1, c, p, -1);
        }

        wirelessCharge();
        cout<<"\n#"<<i+1<<" "<<maxVal<<'\n';
    }
    
    return 0;
}