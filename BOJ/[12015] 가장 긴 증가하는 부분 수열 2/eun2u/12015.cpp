#include <iostream>
#include <vector>
using namespace std;
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    int N,result=0;
    cin>>N;
    int num;
    vector<int> arr;
    arr.push_back(0);
    for(int i=0;i<N;i++){
        cin>>num;

        if(num > arr.back()){
            arr.push_back(num);
            result++;
        }
        else{
            auto iter=lower_bound(arr.begin(), arr.end(), num);
            * iter=num;
        }
    }  
    cout<<result<<'\n';
    return 0;
}