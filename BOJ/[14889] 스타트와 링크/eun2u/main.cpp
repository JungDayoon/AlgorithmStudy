//
//  main.cpp
//  14889. 스타트와 링크

#include <iostream>
#include <string>
#include <queue>
#include <vector>
#include <algorithm>
#include <utility>
using namespace std;
int adj[20][20];
vector<vector<int>> pTeams;
int n;

void pickTeam(vector<int>& picked, int toPick){
    
    if(toPick==0){
        pTeams.push_back(picked);
        return;
    }
    int smallest=picked.empty()? 0 : picked.back()+1;

    for(int next=smallest;next<n;next++){
        picked.push_back(next);
        pickTeam(picked,toPick-1);
        picked.pop_back();
    }
}
int teamAbility(vector<int> teams){

    int ret=0;
    for(int i=0;i<teams.size();i++){
        for(int j=i+1;j<teams.size();j++){
            ret+=adj[teams[i]][teams[j]];
            ret+=adj[teams[j]][teams[i]];
        }
    }
  
    return ret;
}
vector<int> pickDTeams(vector<int> teams){
    
    bool visited[20];
    for(int i=0;i<n;i++)
        visited[i]=false;
    
    for(int i=0;i<n;i++)
        for(int j=0;j<n/2;j++)
            if(i==teams[j])
                visited[i]=true;

        
    vector<int> ret;
    for(int i=0;i<n;i++)
        if(!visited[i])
            ret.push_back(i);

    return ret;
}

int main() {
    cin>>n;
    
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            cin>>adj[i][j];
    
    vector<int> picked;
    pickTeam(picked,n/2);
    
    int minDiff=987654321;
    for(int i=0;i<pTeams.size();i++){
        int start=teamAbility(pTeams[i]);
        vector<int> dTeams=pickDTeams(pTeams[i]);
        int link=teamAbility(dTeams);
        
        minDiff=min(minDiff,abs(start-link));
    }
    
    cout<< minDiff<<endl;

    return 0;
}
