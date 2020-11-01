//[14425] 문자열 집합 - 트라이 풀이

#include <iostream>
#include <string>
#include <cstring>
using namespace std;
int N,M;
const int ALPHABET=26;
int toNumber(char ch){return ch-'a';}
struct TrieNode{
    TrieNode* children[ALPHABET];
    bool terminal;
    
    TrieNode():terminal(false){
        memset(children,0,sizeof(children));
    }
    ~TrieNode(){
        for(int i=0;i<ALPHABET;i++)
            if(children[i])
                delete children[i];
    }
    void insert(const char* key){
        if(*key==0)
            terminal=true;
        else{
            int next=toNumber(*key);
            if(children[next]==NULL)
                children[next]=new TrieNode();
            children[next]->insert(key+1);
        }
    }
    TrieNode* find(const char* key){
        if(*key==0) return this;
        int next=toNumber(*key);
        if(children[next]==NULL) return NULL;
        return children[next]->find(key+1);
    }
};
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);    cout.tie(NULL);

    cin>>N>>M;
    int result=0;
    char s[500];
    TrieNode* root=new TrieNode();
    for(int i=0;i<N;i++){
        cin>>s;
        root->insert(s);
    }
    for(int i=0;i<M;i++){
        cin>>s;
        TrieNode* ans=root->find(s);
        if(ans!=NULL && ans->terminal==true)
            result++;
    }
    cout<<result<<'\n';
    return 0;
}