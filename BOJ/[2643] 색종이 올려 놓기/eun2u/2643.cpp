#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>
using namespace std;

int N;
vector<pair<int,int> > paper;
bool cmp(pair<int,int> a, pair<int,int> b){
    if(a.first ==b.first) return a.second<b.second;
    else return a.first<b.first;
}
bool compareSize(pair<int,int> a, pair<int,int> b){
    if(a.first <=b.first && a.second<=b.second)
        return true;
    return false;
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin>>N;
    int a,b, result=1;
    for(int i=0;i<N;i++){
        cin>>a>>b;
        if(a<b)    paper.push_back(make_pair(a,b));
        else paper.push_back(make_pair(b,a));
    }

    sort(paper.begin(), paper.end(),cmp);
    vector<int> len(N,1);
    len[0]=1;

    for(int cur=0;cur<N-1;cur++){
        for(int next=cur+1;next<N;next++){
            if(compareSize(paper[cur],paper[next])){
                len[next]=max(len[next],len[cur]+1);
                result=max(result,len[next]);
            }               
        }
    }
    
    cout<<result<<'\n';

    return 0;
}