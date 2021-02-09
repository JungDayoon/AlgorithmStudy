#include <iostream>
#include <utility>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
int N;
vector<pair<int,int> > lec;
struct cmp{
    bool operator()(pair<int,int> a, pair<int,int> b){
		return a.second>b.second;
    }
};
int solution(){

    priority_queue<pair<int,int> ,vector<pair<int,int> >, cmp > pq;
    pq.push(lec[0]);
    int cnt=1;

    for(int i=1;i<lec.size();i++){
        pair<int,int> item=lec[i];
        if(pq.top().second <= item.first){
            pair<int,int> a=pq.top();
            pq.pop();
            pq.push(make_pair(a.first, item.second));
        }  
        else{
            pq.push(item);
            cnt++;
        }
    }

    return cnt;
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N;
    int s,t;
    for(int i=0;i<N;i++){
        cin>>s>>t;
        lec.push_back(make_pair(s,t));
    }
    sort(lec.begin(),lec.end());

    cout<< solution()<<'\n';

    return 0;
}