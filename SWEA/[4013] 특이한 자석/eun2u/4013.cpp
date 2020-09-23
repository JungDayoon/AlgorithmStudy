#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <string>

using namespace std;
int gear[4][8]; //0.2 - 1.6, 1.2 - 2.6, 2.2 - 3.6
int cnt[4] = {0, 0, 0, 0};
bool visited[4];

void rotating(){

    for(int idx=0;idx<4;idx++){
        int newgear[8];
        int s=cnt[idx];

        if(s>0){//시계방향으로 cnt[i]만큼
            for(int j=0;j<8;j++)
                newgear[(j+s)%8]=gear[idx][j];
        }
        else if(s<0){//반시계 방향으로 cnt[i]만큼
            for(int j=0;j<8;j++)
                newgear[(8+j+s)%8]=gear[idx][j]; 
        }
        else continue;

        for(int j=0;j<8;j++)
            gear[idx][j]=newgear[j];
    }
}
void rotateGear(int num, int dir){

    cnt[num] += dir;
    visited[num]=true;
    
    int rnum = num + 1;
    int lnum = num - 1;

    if (rnum >= 0 && rnum < 4)
        if (!visited[rnum] && (gear[num][2] != gear[rnum][6])) //다른 극 -> 반대방향으로 회전
            rotateGear(rnum, (dir*(-1)));
        
    if (lnum >= 0 && lnum < 4)
        if (!visited[lnum] && (gear[num][6] != gear[lnum][2]))//다른 극
            rotateGear(lnum, (dir*(-1)) );
        
}
void init(){
    for(int i=0;i<4;i++)
        cnt[i]=0;
    for(int i=0;i<4;i++)
        visited[i]=false;
        
}
int main(){

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int tc;
    cin>>tc;
    for(int c=0;c<tc;c++){
        int k;
        cin>>k;

        vector<string> input;
        for (int i = 0; i < 4; i++)
            for(int j=0;j<8;j++)
                cin >> gear[i][j];
        

      for (int i = 0; i < k; i++){
            int num, dir;
            cin >> num >> dir;

            init();
            rotateGear(num - 1, dir);
            rotating();
        }

        int result=gear[0][0]*1+ gear[1][0]*2 + gear[2][0]*4 + gear[3][0]*8;
        cout<<"#"<<c+1 <<" "<<result<<'\n';
    }
    return 0;
}