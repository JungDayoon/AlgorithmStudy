#include <iostream>
#include <cstring>
#include <string>
using namespace std;

const int MAX = 2501;
const int INF = 987654321;
string str;
char S[MAX];
int dp[MAX][MAX];
int minpal[MAX];
int palindrome(int start, int end){
    if(start>=end) return 1;
    int &ret = dp[start][end];
    if(ret != -1) return ret;
    
    if(S[start]!=S[end]) return ret = 0;
    
    ret = palindrome(start+1, end-1);
    return ret;
}
int getminpal(void){
    minpal[0] = 0;
    for(int i=1;i<=str.length();i++){
        minpal[i] = INF;
        for(int j=1;j<=i;j++){
            if(palindrome(j, i)) minpal[i] = min(minpal[i], minpal[j-1]+1);
        }
    }
    
    return minpal[str.length()];
    
}
int main(){
    cin >> str;
    for(int i=0;i<str.length();i++){
        S[i+1] = str[i];
    }
    memset(dp, -1, sizeof(dp));
    
    printf("%d", getminpal());
    return 0;
    
}

