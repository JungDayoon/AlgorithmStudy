#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M, K;
int A[10][10];//추가될 영양
vector<int> arr[10][10];//나무가 심겨졌는지(나무의 나이)
int land[10][10];//남은 영양
int dc[8] = {0, 1, 1, 1, 0, -1, -1, -1};
int dr[8] = {-1, -1, 0, 1, 1, 1, 0, -1};
bool isin(int r, int c){
    if(0<=r && r<N && 0<=c && c<N) return true;
    return false;
}
int start_financial(){
    for(int t=0;t<K;t++){
        vector<pair<int, int>> new_tree;
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++){
                if(arr[i][j].size()!=0){
                    sort(arr[i][j].begin(), arr[i][j].end());
                    for(int tree=0;tree<arr[i][j].size();tree++)
                    {
                        if(land[i][j]>=arr[i][j][tree]){
                            land[i][j]-=arr[i][j][tree];
                            arr[i][j][tree]+=1;
                            if(arr[i][j][tree]%5 == 0){
                                for(int k=0;k<8;k++)
                                {
                                    int nextr = i+dr[k];
                                    int nextc = j+dc[k];
                                    if(isin(nextr, nextc)){
                                        new_tree.push_back(make_pair(nextr, nextc));
                                    }
                                }
                            }
                        }
                        else{
                            for(int diedtree=tree;diedtree<arr[i][j].size();){
                                land[i][j]+=(arr[i][j][diedtree]/2);
                                arr[i][j].erase(arr[i][j].begin()+diedtree);
                            }
                            break;
                        }
                    }
                }
            }
            
        }
        for(pair<int, int> t:new_tree){
            arr[t.first][t.second].push_back(1);
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                land[i][j]+=A[i][j];
            }
        }
        
    }
    int tree_num = 0;
    for(int i =0;i<N;i++){
        for(int j=0;j<N;j++){
            tree_num+=arr[i][j].size();
        }
    }
    return tree_num;
}
int main(){
    fill(&land[0][0], &land[9][10], 5);
    scanf("%d %d %d", &N, &M, &K);
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            cin>>A[i][j];
        }
    }
    for(int i=0;i<M;i++){
        int x, y, z;
        scanf("%d %d %d", &x, &y, &z);
        x--; y--;
        arr[x][y].push_back(z);
    }
    
    printf("%d",start_financial());
    return 0;
}

