#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
using namespace std;
int N;
long long B;
vector<vector<int> > multiple(vector<vector<int> > arr1, vector<vector<int> > arr2){
    vector<vector<int> > ret(N, vector<int>(N,0));

    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            long long num=0;
            for(int a=0;a<N;a++){
                num+=arr1[i][a]*arr2[a][j];
            }
            ret[i][j]=num%1000;
        }
    }
    return ret;
}
vector<vector<int> > square(vector<vector<int> > arr, long long b){
    if(b==1)    
        return arr;
    
    vector<vector<int> > nArr = multiple(arr,arr);
    nArr=square(nArr,b/2);    
    
    if(b%2==0)
        return nArr;
    
    else{//홀수일때
        vector<vector<int> > result = multiple(nArr,arr);
        return result;
    }
}
int main(){
    ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    cin>>N>>B;
    vector<vector<int> > arr(N, vector<int>(N,0));

    for(int i=0;i<N;i++)
        for(int j=0;j<N;j++)
            cin>>arr[i][j];
    
    vector<vector<int> > result=square(arr,B);

    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            cout<<result[i][j]%1000<<" ";
        }
        cout<<'\n';
    }

    return 0;
}