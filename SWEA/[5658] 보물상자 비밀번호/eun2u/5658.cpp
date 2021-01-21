#include <iostream>
#include <set>
#include <vector>
#include <string>
using namespace std;
int N,K;
set<int> num;
void insertSet(string str){
    for(int i=0;i<N;i+=3){
        string token=str.substr(i,N/4);
        num.insert(stoi(token,nullptr,16));
    }
}
string shiftRight(string str){
    string ret;

    for(int i=1;i<str.size();i++)  
        ret.push_back(str[i]);

    ret.push_back(str[0]);
    return ret;
}
int findKthNum(){
    set<int>::iterator iter=num.end();
    int ret;
    if(num.size()==K)
        return *(num.begin());
        
    for(int i=0;i<=K;i++){
        ret=*iter;
        iter--;
    }
    return ret;
}
int main(){
    int TC;
    cin>>TC;
    for(int tc=1;tc<=TC;tc++){
        num.clear();
        cin>>N>>K;
        string str;
        cin>>str;
        
        insertSet(str);
        for(int i=0;i<N/4;i++){
            string ret=shiftRight(str);
            insertSet(ret);
            str=ret;
        }
        int ans=findKthNum();
        cout<<"#"<<tc<<" "<<ans<<'\n';
    }
    return 0;
}