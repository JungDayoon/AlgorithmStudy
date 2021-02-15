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
bool selected[51];
vector<string> words;
void copy(bool a[], bool b[]){
    for(int i=0;i<26;i++)
        b[i]=a[i];
}
int countWord(){
    int ret=0;
    for(int i=0;i<N;i++)
        if(selected[i])
            ret++;
    return ret;
}
void teachWords(int start,bool alpha[26],int cnt ){
    if(cnt>K){
        return;
    }

    for(int i=start; i<N;i++){
        if(selected[i]) continue;
        bool copied[26];
        copy(alpha, copied);
        
        int c=0;
        for(int j=0;j<words[i].size();j++){
            if(!copied[words[i][j]-'a']){
                copied[words[i][j]-'a']=true;
                c++;
            }
        }
        if(cnt+c>K) continue;
        
        cnt+=c;
        selected[i]=true;
        teachWords(i,copied,cnt);
        cnt-=c;
        selected[i]=false;
    }

    int ret=countWord();
    result=max(ret,result);
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
    teachWords(0,alpha,5);
    cout<<result<<'\n';

    return 0;
}