#include <iostream>
#include <utility>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;
int N;
int result=0;
int dy[4]={1,-1,0,0};
int dx[4]={0,0,-1,1};
struct atom{
    int x, y, dir, e;
};
struct dists{
    int a,b;
    float dist;
};
vector<atom> atoms;
bool visited[1000];

bool isConnected(atom a, atom b,float len){

    float ay=a.y+dy[a.dir]*len;
    float ax=a.x+dx[a.dir]*len;
    float by=b.y+dy[b.dir]*len;
    float bx=b.x+dx[b.dir]*len;
    if(ay==by && ax==bx) return true;
    else return false;
}
void deleteAtoms(){
  //visited true된 원자들 delete하기
    vector<atom> new_atoms;
    for(int i=0;i<atoms.size();i++)
        if(!visited[i])
            new_atoms.push_back(atoms[i]);
    
    atoms.clear();
    for(int i=0;i<new_atoms.size();i++)
        atoms.push_back(new_atoms[i]);

}
bool comp(dists a, dists b){    return a.dist < b.dist; }
void crushAtoms(vector<dists> crush){
    memset(visited,false,sizeof(visited));

    sort(crush.begin(), crush.end(), comp);//거리 내림차순 정렬
    double minDist=crush[0].dist;

    for(int i=0;i<crush.size();i++){
        if(crush[i].dist==minDist){
            //충돌해서 result값 더하고, vector에서 삭제
            int idx1=crush[i].a;
            if(!visited[idx1]){
                result+=atoms[idx1].e;
                visited[idx1]=true;
            }
            int idx2=crush[i].b;
            if(!visited[idx2]){
                result+=atoms[idx2].e;
                visited[idx2]=true;
            }
        }
        else break;
    }   
}
void emitEnergy(){

    while(1){
        if(atoms.size() < 2) break;

        vector<dists > crush;
        for(int i=0;i<atoms.size();i++){
            atom a=atoms[i];
            for(int j=i+1;j<atoms.size();j++){
                atom b=atoms[j];

                if(a.x ==b.x && isConnected(a,b,(float)abs(a.y-b.y) /2)){//세로로 만날 때
                    float dd=(float)abs(a.y-b.y) /2;
                    dists d={i,j, dd};
                    crush.push_back(d);
                }
                else if(a.y==b.y && isConnected(a,b,(float)abs(a.x-b.x)/2)){//가로로 만날 때
                    float dd=(float)abs(a.x-b.x)/2;
                    dists d={i,j,dd};
                    crush.push_back(d);
                }
                
                else if(abs(a.y-b.y)== abs(a.x-b.x) && isConnected(a,b, abs(a.y-b.y))){// 직각으로 만날 때
                    dists d={i,j, abs(a.y-b.y)};
                    crush.push_back(d);
                }
            }
        }
        if(crush.empty()) break;

        crushAtoms(crush);
        deleteAtoms();
    }

}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int TC;
    cin>>TC;
    for(int tc=1;tc<=TC;tc++){
        atoms.clear();
        result=0;
        cin>>N;
        int y,x,d,e;
        for(int i=0;i<N;i++){
            cin>>x>>y>>d>>e;
            atom a={x,y,d,e};
            atoms.push_back(a);
        }
        
        emitEnergy();
        cout<<"#"<<tc<<" "<<result<<'\n';
    }
    return 0;
}