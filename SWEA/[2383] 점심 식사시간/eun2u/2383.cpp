#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <string>

using namespace std;
const int MAX_VAL =987654321;
int n;
int result=MAX_VAL;
struct STAIR{
    int l,y,x;
};

vector< STAIR > stair;
vector< pair<int,int> > people;
int room[10][10];
int dist[2][10];

void calculDist(){

    for(int i=0;i<2;i++)
        for(int j=0;j<people.size();j++){
            int diff=abs(stair[i].y - people[j].first) + abs(stair[i].x-people[j].second);
            dist[i][j]=diff+1;
        }
}
vector<int> pickSt(vector<int> picked){

    vector<int> ret(people.size(),-1);

    for(int i=0;i<picked.size();i++) //picked에 포함되면 
        if(picked[i]>=0)
            ret[picked[i]]=0;

    for(int i=0;i<people.size();i++) 
        if(ret[i]!=0)
            ret[i]=1;

    return ret;
}
void lunchTime(vector<int> picked){
    vector<int>st =pickSt(picked); //사람들이 어떤 계단 사용할 지 
    vector< queue<int> > waiting(2);
    int snum[2]={0,0}; //현재 계단에 있는 사람 수
    vector<int> loc(st.size(),-1); //사람들 현재위치
    int time=0;

    while(1){
        for(int i=0;i<st.size();i++){// 시간에 맞춰 증가시켜주기
            if(loc[i]>0){
                if(loc[i] == stair[st[i]].l){
                    loc[i]=0;
                    snum[st[i]]--;
                }
                else
                    loc[i]++;
            }
        }

        bool end=true;
        for(int i=0;i<loc.size();i++)
            if(loc[i]!=0)
                end=false;
        if(end)//모두 0이면 break;
            break;

        for(int i=0;i<2;i++){   //대기하고 있는 사람
            while(!waiting[i].empty()){
                int who=waiting[i].front();
                if(snum[st[who]] < 3){
                    loc[who]=1;
                    snum[st[who]]++;
                    waiting[i].pop();
                }
                else break;
            }
        }

        for(int i=0;i<st.size();i++){     //처음 들어오는 사람
            if(dist[st[i]][i]==time){
                if(snum[st[i]]>= 3)
                    waiting[st[i]].push(i);
                else{
                    loc[i]=1;
                    snum[st[i]]++;
                }
            }
        }
        time++;
    }
    result=min(result,time);
}
void pickWho(int n, vector<int>& picked, int toPick){

    if(toPick==0){
        lunchTime(picked);
        return;
    }
    int smallest=picked.empty()? 0: picked.back()+1;

    for (int next= smallest; next < n; next++){
        picked.push_back(next);
        pickWho(n,picked,toPick-1);
        picked.pop_back();
    }
}
int main(){

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int tc;
    cin>>tc;
    for(int c=1;c<=tc;c++){
        cin>>n;
        stair.clear();
        people.clear();
        result=MAX_VAL;

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
                cin>>room[i][j];
                if(room[i][j]==1)
                    people.push_back(make_pair(i,j));
                else if(room[i][j]>1){
                    STAIR s;
                    s.l=room[i][j];
                    s.y=i;
                    s.x=j;
                    stair.push_back(s);
                }
            }
        
        calculDist();

        vector<int> picked;
        for(int i=0;i<=people.size();i++)
            pickWho(people.size(),picked,i);

        cout<<"#"<<c<<" "<<result<<'\n';
    }

    return 0;
}