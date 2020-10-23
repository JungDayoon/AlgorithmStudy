#include <iostream>
#include <utility>
#include <string>
#include <cstring>
#include <algorithm>
#include <vector>
#include <set>
using namespace std;

int N;
string s;
int snums[201];
int cnums[201];
set<string> answer;

void printAnswer(vector<int> picked){
    for(int i=0;i<s.size();i++)
        cnums[i]=snums[i];

    for(int i=0;i<s.size();i++)
        for(int j=0;j<picked.size();j++)
            if(cnums[i]==picked[j])
                cnums[i]=0;
        
    string ans="";

    for(int i=0;i<s.size();i++){
        if(s[i]=='(' || s[i]==')'){
            if(cnums[i]!=0)
                ans=ans+s[i];
        }
        else 
            ans=ans+s[i];
    }
    answer.insert(ans);
}
void pickParen(vector<int> picked,int toDel, int cnt){
    if(toDel==cnt){
        printAnswer(picked);
        return;
    }
    int smallest=picked.empty()? 1 : picked.back()+1;

    for(int next=smallest;next<=N;next++){
        picked.push_back(next);
        pickParen(picked,toDel, cnt+1);
        picked.pop_back();
    }
}
void makeSnums(string s){
    int idx=1;
    vector<int> parens;

    for(int i=0;i<s.size();i++){
        if(s[i]=='('){
            snums[i]=idx;
            parens.push_back(idx);
            idx++;
        }
        else if(s[i]==')'){
            snums[i]=parens.back();
            parens.pop_back();
        }
        else 
            snums[i]=0;
    }
}
int main(){
    
    cin>>s;

    for(int i=0;i<s.size();i++)
        if(s[i]=='(')
            N++;

    makeSnums(s);

    vector<int> picked;
    for(int i=1;i<=N;i++)
        pickParen(picked,i,0);

    for(string elem: answer)
        cout<<elem<<'\n';

    return 0;
}