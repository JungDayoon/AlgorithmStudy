#include <iostream>
#include <utility>
#include <algorithm>
#include <string>
#include <cstring>
#include <vector>
#include <queue>
using namespace std;
int N;
int ball[50][9];
bool visited[9];
int maxResult=0;
bool where[4];

int moving(int j, int score){
    bool moved[4];
    int ret=0;
    memset(moved,false,sizeof(moved));
    where[0]=true;
    for(int i=0;i<4;i++){
        if(where[i]){
            if(i+score>3) ret++;
            else
                moved[i+score]=true;
        }
    }
    for(int i=0;i<4;i++)
        where[i]=moved[i];

    return ret;
}
void playBaseball(vector<int> picked){
    int result=0;
    int j=0;
    for(int i=0;i<N;i++){
        memset(where,false,sizeof(where));
        int out=0;
        while(out<3){
            if(j>=9) j%=9;
            int score=ball[i][picked[j]];
            if(score==0)
                out++;
            
            else
                result+=moving(j,score);

            j++;
        }
    }
    maxResult=max(result,maxResult);
}
void chooseOrder(vector<int> picked, int cnt){
    if(cnt==3){ //1번선수는 4번 타자
        visited[0]=true;
        picked.push_back(0);
        chooseOrder(picked,cnt+1);
        visited[0]=false;
        picked.pop_back();
    }
    if(cnt==9){
        playBaseball(picked);//정해진 순열로 야구 진행
        return;
    }
    for(int i=1;i<9;i++){
        if(!visited[i]){
            visited[i]=true;
            picked.push_back(i);
            chooseOrder(picked,cnt+1);
            visited[i]=false;
            picked.pop_back();
        }
    }
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin>>N;
    for(int i=0;i<N;i++)
        for(int j=0;j<9;j++)
            cin>>ball[i][j];

    memset(visited,false,sizeof(visited));
    vector<int> picked;
    chooseOrder(picked,0);
    
    cout<<maxResult<<'\n';
}