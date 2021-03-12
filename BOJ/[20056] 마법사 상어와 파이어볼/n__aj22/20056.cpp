#include <iostream>
#include <vector>
#include <cstring>
using namespace std;
typedef struct{
    int r, c, m, s, d;
}fireball;
int N, M, K;
int mass;
vector<fireball> fireball_info;
int dc[8] = {0, 1, 1, 1, 0, -1, -1, -1};
int dr[8] = {-1, -1, 0, 1, 1, 1, 0, -1};
vector<fireball> arr[50][50];
int answer = 0;
void start_time(){
    
    for(int t=0; t<K; t++){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                arr[i][j].clear();
            }
        }
        
        for(fireball ball:fireball_info){
            
            fireball newball;
            memcpy(&newball, &ball, sizeof(fireball));
            newball.r = (ball.r + dr[ball.d]*ball.s)%N;
            newball.c = (ball.c + dc[ball.d]*ball.s)%N;
            if(newball.r<0) newball.r+=N;
            if(newball.c<0) newball.c+=N;
            arr[newball.r][newball.c].push_back(newball);
        }
        fireball_info.clear();
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++){
                if(arr[i][j].size()==1){
                    fireball_info.push_back(arr[i][j][0]);
                }
                else if(arr[i][j].size()>1){
                    bool sameflag = true;
                    int num_type = arr[i][j][0].d%2;
                    int mess_sum=0;
                    int speed_sum=0;
                    for(fireball ball:arr[i][j]){
                        mess_sum+=ball.m; speed_sum+=ball.s;
                        if(ball.d%2 != num_type) sameflag = false;
                    }
                    int direction_num=1;
                    if(sameflag) direction_num=0;
                    int divided_mess = mess_sum/5;
                    int divided_speed = speed_sum/arr[i][j].size();
                    if(mess_sum>=5){
                        for(int k=0;k<4;k++){
                            fireball newball;
                            newball.r=i; newball.c=j;
                            newball.m = divided_mess;
                            newball.s = divided_speed;
                            newball.d = direction_num;
                            direction_num+=2;
                            fireball_info.push_back(newball);
                        }
                    }
                    
                }
            }
        }
    }
    for(fireball ball:fireball_info){
        answer+=ball.m;
    }
    return;
}
int main(){
    scanf("%d %d %d", &N, &M, &K);
    
    for(int i=0;i<M;i++){
        fireball temp;
        scanf("%d %d %d %d %d", &temp.r, &temp.c, &temp.m, &temp.s, &temp.d);
        temp.r--; temp.c--;
        fireball_info.push_back(temp);
        mass+=temp.m;
    }
    start_time();
    printf("%d", answer);
    return 0;
}
