#include <iostream>
#include <cstring>
#include <utility>
#include <algorithm>
#include <vector>
using namespace std;
int N;
int A[21][21];
int zone[21][21];
int minResult=987654321;

void calculDiff(int x, int y, int d1, int d2){
    vector<int> pop(5,0);
    memset(zone,0,sizeof(zone));
    // 5번 선거구
	int left = 0, right = 0;
	bool turnLeft = true, turnRight = true;
	for(int row = x; row <= x + d1 + d2; row++) {
		for(int col= y + left; col<= y+ right; col++) {
			zone[row][col] = 5;
			pop[4] += A[row][col];
		}

		if(left == -d1) turnLeft = !turnLeft;
		if(right == d2) turnRight = !turnRight;
			
		if(turnLeft) left--;
		else left++;
			
		if(turnRight) right++;
        else right--;
	}

    for(int r=1;r<=N;r++){
        for(int c=1;c<=N;c++){
            if(r< x+d1 && c<=y ){//1번 선거구
                if(zone[r][c]!=5){ 
                    pop[0]+=A[r][c];
                    zone[r][c]=1;
                }
            }
            else if(r<= x+d2 && c>y){ //2번 선거구
                if(zone[r][c]!=5){
                    pop[1]+=A[r][c];
                    zone[r][c]=2;
                }
            }
            else if(r>=x+d1 && r<=N && c<y-d1+d2){ //3번 선거구
                if(zone[r][c]!=5){
                    pop[2]+=A[r][c];
                    zone[r][c]=3;
                }
            }
            else if(r> x+d2 && r<=N && c >=y-d1+d2 && c<=N){ //4번 선거구
                if(zone[r][c]!=5){
                    pop[3]+=A[r][c];
                    zone[r][c]=4;
                }
            }
        }
    }
    sort(pop.begin(), pop.end());
    minResult=min(minResult, pop[4]-pop[0]);
}

void solution(){

    for(int x=1;x<=N-2;x++)
        for(int y=2;y<=N-1;y++)
            for(int d1=1;d1<= y-1 && d1<=N-x-1 ; d1++)
                for(int d2=1; d2<=N-y && d2<=N-x-d1;d2++)
                    calculDiff(x,y,d1,d2);

}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin>>N;
    
    for(int i=1;i<=N;i++)
        for(int j=1;j<=N;j++)
            cin>>A[i][j];

    solution();
    cout<<minResult<<'\n';
    return 0;
}