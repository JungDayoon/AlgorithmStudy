#include <iostream>
#include <vector>
#include <string>
using namespace std;
string bombString(string s, string bomb){
    string newS="";
    int blen=bomb.size();

    for(int i=0;i<s.size();i++){
        newS+=s[i];

        if(newS.back() == bomb.back()){
            bool matched=true;

            for(int j=0;j<blen;j++){
                if(bomb[blen-j-1]!=newS[newS.size()-j-1]){
                    matched=false;
                    break;
                }
            }
            if(matched) {
                for(int j=0;j<blen;j++)
                    newS.pop_back();
            }
        }
    }
    return newS;
}
int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    string s, bomb;
    cin>>s>>bomb;

    string result=bombString(s,bomb);
    
    if(result.size()==0)
        cout<<"FRULA"<<'\n';
    else cout<<result<<'\n';

    return 0;
}