#include <iostream>
#include <utility>
#include <cstring>
#include <cstdlib>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;

const int NUM=10;
bool result=false;
int toNumber(char ch) { return ch-'0';}
struct TrieNode{
    TrieNode* children[NUM];
    bool terminal;
    TrieNode() : terminal(false){
	    memset(children, 0, sizeof(children));
    }
    ~TrieNode(){
		for(int i=0;i<NUM;i++)
		    if(children[i])
		        delete children[i];
    }
    void insert(const char* key){
	    if(*key == 0)
	        terminal=true;
        else{
	        int next=toNumber(*key);
	        if(children[next]==NULL)
		        children[next]=new TrieNode();
            if(children[next]->terminal ==true)
                result=true;
		    children[next]->insert(key+1);
        }
    }
};
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int TC;
    cin>>TC;
    for(int i=1;i<=TC;i++){
        int N;
        cin>>N;
        vector<string> numbers;
        string s;
        for(int i=0;i<N;i++){
            cin>>s;
            numbers.push_back(s);
        }
        sort(numbers.begin(),numbers.end());

        TrieNode* root= new TrieNode();
        for(int i=0;i<numbers.size();i++){
            result=false;
            const char* cstr=numbers[i].c_str();
            root->insert(cstr);

            if(result==true){
                cout<<"NO"<<'\n';
                break;
            }
        }
        if(result==false)
            cout<<"YES"<<'\n';
        
    }   

    return 0;
}