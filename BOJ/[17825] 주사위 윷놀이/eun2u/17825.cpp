#include <iostream>
#include <algorithm>
#include <utility>
#include <cstring>
#include <vector>
using namespace std;

int answer=0;
int turns[10]; // 주사위 10개
int score[33]={0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,0,
                13,16,19,22,24,28,27,26,25,30,35};
vector<int > adj[45];
vector<int> orders;

bool existIn(int horse[], int loc){ 
    for(int i=0;i<4;i++){
        if(horse[i]==loc) return true;
    }
    return false;
}
int moveHorse(){
    
    int iter,ret=0;
    int horse[4]={0,0,0,0};

    for(int i=0;i<10;i++){
        int turn=turns[i];
        int hloc=horse[orders[i]];
        int cur=hloc;
        if(hloc==21) continue; //도착한 말 사용하려할 때

        if(cur==5 || cur==10 || cur==15){
            cur=adj[cur][1];
            iter=turn-1;
        }
        else    iter=turn;
        
        for(int j=0;j<iter;j++){
            if(cur==21) //도착했을 때
                break;
            cur=adj[cur][0];
        }

        if(cur!=21 && existIn(horse, cur)) // 이동하려는 칸이 도착 칸이 아니고, 말이 존재
            return 0;

        horse[orders[i]]=cur;  //말 위치 갱신
        ret+=score[cur]; //도착하면 점수 더함
    } 
    return ret;
}
void makeOrder(int cnt){
    if(cnt==10){
        int result=moveHorse();
        answer=max(answer,result);
        return;
    }
    for(int i=0;i<4;i++){
        orders.push_back(i);
        makeOrder(cnt+1);
        orders.pop_back();
    }
}
void init(){
    for(int i=0;i<=20;i++) adj[i].push_back(i+1);
    adj[5].push_back(22); adj[22].push_back(23); adj[23].push_back(24); adj[24].push_back(30);
    adj[10].push_back(25); adj[25].push_back(26); adj[26].push_back(30);
    adj[15].push_back(27); adj[27].push_back(28); adj[28].push_back(29); adj[29].push_back(30);
    adj[30].push_back(31); adj[31].push_back(32); adj[32].push_back(20); 
}
int main(){
  
    for(int i=0;i<10;i++)
        cin>> turns[i];

    init();
    makeOrder(0);

    cout<<answer<<'\n';

    return 0;
}