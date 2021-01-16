#include <string>
#include <iostream>
#include <vector>
using namespace std;

bool possibleTree(string skill, string tree){
    int idx=0;
    string str="";
    
    for(int j=0;j<tree.size();j++){
        char s=tree[j];
        str=s;
      //  cout<<str<<" ";
        if(skill.find(str)!=string::npos) {
            if(s!=skill[idx]) return false;
            
            idx++;
            if(idx >= skill.size() || j==tree.size()-1)
                return true;
            else 
                continue;
        }
    }
    return true;
}
int solution(string skill, vector<string> skill_trees) {
    int answer = 0;
    
    
    for(int i=0;i<skill_trees.size();i++){
        if(possibleTree(skill,skill_trees[i])) 
            answer++;
   //     cout<<"\n";
    }
    return answer;

}