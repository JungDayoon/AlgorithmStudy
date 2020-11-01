//[14425] 문자열 집합 - set 풀이

#include <iostream>
#include <string>
#include <set>
using namespace std;
int N,M;
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);    cout.tie(NULL);

    cin>>N>>M;
    int result=0;
    string s;
    set<string> strings;
    for(int i=0;i<N;i++){
        cin>>s;
        strings.insert(s);
    }
    for(int i=0;i<M;i++){
        cin>>s;
        if(strings.find(s) != strings.end())
            result++;

    }
    cout<<result<<'\n';
    return 0;
}