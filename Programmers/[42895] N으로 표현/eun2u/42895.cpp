#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <unordered_set>
using namespace std;
vector<unordered_set<int> > dp(9);

int concatnum(int N, int digit){
    string s="";
    for(int i=0;i<digit;i++)
        s+=to_string(N);

    return stoi(s);  
}
int solution(int N, int number) {
    
    dp[1].insert(N);
    if(N==number) return 1;
    for(int i=2;i<=8;i++){
        int concat=concatnum(N,i);
        dp[i].insert(concat);
        
        for(int j=1;j<i;j++){
            for(auto op1: dp[j]){
                for(auto op2: dp[i-j]){
                    dp[i].insert(op1+op2);
                    dp[i].insert(op1*op2);
                    dp[i].insert(op1-op2);
                    if(op2!=0)
                        dp[i].insert(op1/op2);
                }
            }
        }
        if(dp[i].find(number) != dp[i].end()){
            return i;
        }
    }
    return -1;
}
int main(){
    cout<< solution(2,11);
    return 0;
}