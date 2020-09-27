//4014. 활주로 건설

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;
int n,x;
int land[21][21];
int slope[20];

int investigate(int road[]){
    int check[20];
    for (int i = 0; i < 20; i++) 
        check[i] = -1;

    for (int i = 1; i < n; i++){
        int diff = road[i] - road[i - 1];

        if (abs(diff) >1)
             return -1;
        else if (diff == 1){// 올라가는 경우
            if (i - x < 0) 
                return -1;
            for (int j = 1; j <= x; j++){
                if (check[i - j] == 1) 
                    return -1;
                if (road[i - 1] != road[i - j])
                     return -1;
                check[i - j] = 1;        
            }
        }
        else if (diff == -1){// 내려가는 경우
            if (i + x - 1 >= n) return -1;
            for (int j = 1; j <= x; j++){
                if (check[i + j - 1] == 1) 
                    return 0;   
                if (road[i] != road[i + j - 1])
                    return 0;
                check[i + j - 1] = 1;
            }
        }
        
    }
    return 1;
}

// void print(int land[]){
//     cout<<"debug ";
//     for(int i=0;i<n;i++)
//         cout<<land[i]<<" ";

//     cout<<"\n";

// }
int main(){
    int tc;
    cin>>tc;
    for(int c=0;c<tc;c++){
        int result=0;
        cin>>n>>x;

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                cin>>land[i][j];

        
        for(int i=0;i<n;i++)
            if(investigate(land[i])==1){
              // print(land[i]);
                result++;
            }

        int temp[20];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
                temp[j]=land[j][i];
            
            if(investigate(temp)==1){
              //  print(temp);
                result++;
            }
        }
        cout<<"#"<<c+1<<" "<< result<<'\n';
    }

    return 0;
}
