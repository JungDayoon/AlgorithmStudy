#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int solution(int n)
{
    int ans = 1;
    
    while(n > 1){
        if(n%2==0)
            n/=2;
        else{
            n-=1;
            ans++;
        }
    }
    
    return ans;
}