#include <bits/stdc++.h>
using namespace std;
int main() {
    int t;
    cin >> t;
    while (t--) {
        int a;

        
        cin >> a;
        
        vector<vector<char>>vec(a, vector<char>(a));

        for(int i = 0;i<a;i++){
            for(int j = 0;j<a;j++){
                cin >> vec[i][j];
            }    
        }
        bool algo = false;
        for(int i = 0;i<a;i++){
            int conta = 0;
            int conta2 = 0;
            for(int j = 0;j<a;j++){
                if(vec[i][j] == '#'){
                    conta++;
                }else{
                    conta = 0;
                }
                if(j >= 2){
                    if(vec[i][j] == '#' && vec[i][j-1] == '.' && vec[i][j-2] == '#'){
                        algo = true;
                        break;
                    }
                }
                if(conta == 3){
                    algo = true;
                    break;
                }
            }
            for(int j = 0;j<a;j++){
                if(vec[j][i] == '#'){
                    conta2++;
                }else{
                    conta2 = 0;
                }
                if(j >= 2){
                    if(vec[j][i] == '#' && vec[j-1][i] == '.'){
                        algo = true;
                        break;
                    }
                }
                if(conta2 == 3){
                    algo = true;
                    break;
                }
            }
            if(algo) break;
        }
        if(algo)cout << "no";
        else cout << "yes";
        cout << endl;
        
    }
    
    return 0;
}
