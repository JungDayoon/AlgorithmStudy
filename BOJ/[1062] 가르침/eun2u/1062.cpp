#include <iostream>
#include <utility>
#include <vector>
#include <string>
#include <cstring>
#include <algorithm>
using namespace std;
int N,K;
int result=0;
bool alpha[26];
vector<string> words;

int countWords(){
    int ret=0;
    for(int i=0;i<words.size();i++){
        string s=words[i];
        bool matched=true;
        for(int j=0;j<s.size();j++){
            if(!alpha[s[j]-'a']){
                matched=false;
                break;
            }
        }
        if(matched) ret++;
    }

    return ret;
}
void teachWords(int start,int cnt ){
    if(cnt==K){
        result=max(result,countWords());
        return;
    }
    for(int i=start;i<26;i++){
        if(alpha[i]) continue;
        alpha[i]=true;

        teachWords(i,cnt+1);
        alpha[i]=false;
    }
}
int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin>>N>>K;
    alpha['a'-'a']=true;    alpha['n'-'a']=true;
    alpha['t'-'a']=true;    alpha['i'-'a']=true;    alpha['c'-'a']=true;

    string s;
    words.resize(N);
    for(int i=0;i<N;i++){
        cin>> s;
        s=s.substr(4,s.size()-8);
        words[i]=s;
    }
    
    if(K<5){
        cout<<0<<'\n';
        return 0;
    }
    teachWords(0,5);
    cout<<result<<'\n';

    return 0;
}