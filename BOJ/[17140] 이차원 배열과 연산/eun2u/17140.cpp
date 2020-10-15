#include <iostream>
#include <cstring>
#include <utility>
#include <algorithm>
#include <vector>
using namespace std;
int r, c,k;
int A[100][100];
int row,col;

bool comp_pair(pair<int,int> a, pair<int,int> b){ //수, 등장횟수
    if(a.second == b.second) //수 등장횟수 같으면
        return a.first<b.first; //수 크기 오름차순
    else 
        return a.second < b.second;  //아니면, 수 등장횟수 오른차순
}
void rowOperation(){
    int maxCol=col;
    for(int i=0;i<row;i++){
        int cnt[101];
        memset(cnt,0,sizeof(cnt));
        vector<int> nums;

        for(int j=0;j<maxCol;j++){           
            int val=A[i][j];
            if(val==0) continue;

            if(cnt[val]==0){
                nums.push_back(val);
                cnt[val]++;
            }
            else 
                cnt[val]++;
        }

        vector< pair<int,int> > toSort; //수, 등장횟수
        for(int k=0;k<nums.size();k++)
            toSort.push_back(make_pair(nums[k],cnt[nums[k]]));
        
        sort(toSort.begin(),toSort.end(), comp_pair);
        memset(A[i],0,sizeof(A[i]));
        
        for(int k=0;k<toSort.size();k++){
            if(k > 49) break;
            A[i][k*2]=toSort[k].first;
            A[i][(k*2)+1]=toSort[k].second;
        }
        if(toSort.size() > 49) 
            maxCol=100;
        else
            maxCol=max(maxCol,int(toSort.size() * 2));
    }
    col=maxCol;
}
void colOperation(){
    int maxRow=row;

    for(int i=0;i<col;i++){
        int cnt[101];
        memset(cnt,0,sizeof(cnt));
        vector<int> nums;

        for(int j=0;j<maxRow;j++){           
            int val=A[j][i];
            if(val==0) continue;
            if(cnt[val]==0){
                nums.push_back(val);
                cnt[val]++;
            }
            else 
                cnt[val]++;
        }

        vector< pair<int,int> > toSort; //수, 등장횟수
        for(int k=0;k<nums.size();k++)
            toSort.push_back(make_pair(nums[k],cnt[nums[k]]));
        
        sort(toSort.begin(),toSort.end(), comp_pair);
        for(int k=0;k<col;k++)
            A[k][i]=0;

        for(int k=0;k<toSort.size();k++){
            if(k > 49) break;
            A[k*2][i]=toSort[k].first;
            A[(k*2)+1][i]=toSort[k].second;
        }
        if(toSort.size() > 49) 
            maxRow=100;
        else
            maxRow=max(maxRow,int(toSort.size() * 2));
    }
    row=maxRow;
}
int solution(){
    int time=0;
    row=3, col=3;
    while(time<=100){ //100을 초과 하는 거겟지?
        if(A[r][c]==k) break;
        if(row>= col)
            rowOperation();
        else 
            colOperation();
        time++;
    }

    if(time>100) return -1;
    return time;
}
int main(){

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin>>r>>c>>k;
    r--,c--;
    for(int i=0;i<3;i++)
        for(int j=0;j<3;j++)
            cin>>A[i][j];
    
    cout<<solution()<<'\n';

    return 0;
}