#include <string>
#include <vector>
#include <queue>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;
int result=0;
const int ALPHABET=26;
int toNumber(char ch){return ch-'a';}
struct TrieNode{
    TrieNode* children[ALPHABET];
    bool terminal;
    int cnt;

    TrieNode():terminal(false),cnt(1){
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
            else children[next]->cnt++;
            children[next]->insert(key+1);
        }
    }
    int find(const char* key){
        if(*key=='?'){
            int tmp=0;
            for(int i=0;i<ALPHABET;i++)
                if(children[i]!=NULL)
                    tmp+=children[i]->cnt;
            return tmp;
        }
        if(*key==0) return 1;
        int next=toNumber(*key);
        if(children[next]==NULL) return 0;
        if(*(key+1)=='?') return children[next]->cnt; 
        return children[next]->find(key+1);
    }
};
TrieNode* root[10001];
TrieNode* reversed[10001];

vector<int> solution(vector<string> words, vector<string> queries) {
    vector<int> answer;

    for(string elem: words){
        int len=elem.size();
        if(root[len]==NULL) root[len]=new TrieNode();
        root[len]->insert(elem.c_str());
        
        reverse(elem.begin(),elem.end());
        if(reversed[len]==NULL) reversed[len]=new TrieNode();
        reversed[len]->insert(elem.c_str());
    }
    
    for(string elem:queries){
        int len=elem.size();
        if(elem.at(0)=='?'){//접두사
            reverse(elem.begin(),elem.end());
            
            if(reversed[len]==NULL)   result=0;
            else
                result=reversed[len]->find(elem.c_str());
        }
        else{//접미사
            if(root[len]==NULL)   result=0;
            else
                result=root[len]->find(elem.c_str());
        }
        answer.push_back(result);
    }
    return answer;
}