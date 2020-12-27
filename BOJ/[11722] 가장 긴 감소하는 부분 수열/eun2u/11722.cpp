#include <iostream>
#include <utility>
#include <string>
#include <cstring>
#include <queue>
#include <vector>
using namespace std;

vector<int > arr;
vector<int> cnt;
int main(){
    int N,num,result=1;
    cin>>N;
    cnt.resize(N+1,1);

    for(int i=0;i<N;i++){
        cin>>num;
        arr.push_back(num);
    }
    
    for(int i=0;i<N-1;i++){
        int target=arr[i];
    
        for(int next=i;next<N;next++){
            if(arr[next] < target && next > i){
                cnt[next]=max(cnt[next],cnt[i]+1);
                result=max(cnt[next],result);
            }
        }
    }
    cout<<result<<'\n';

    return 0;
}