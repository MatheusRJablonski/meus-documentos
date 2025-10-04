#include <bits/stdc++.h>
 
using namespace std;
int acharPerdido(vector<int> vec){
    int n = vec.size();
    sort(vec.begin(),vec.end());
    for(int i = vec.size()-1;i>=0;i--){
        if(n == vec[i])n--;
        else return n;
    }
}
int main (){
    int t,n;
    cin >> t;
    while(t--){
        cin >> n;
        vector<vector<int>> quant(n, vector<int>(n));
        vector<int>encontrei(n*2);
        for(int j = 0;j<n;j++){
            for(int i = 0; i<n;i++){
                cin >> quant[i][j];
            }
        }
        for(int i = 1;i<n+1;i++){
            encontrei[i] = quant[i-1][0];
        }
        
        for(int i = n+1;i<n*2;i++){
            
            encontrei[i] = quant[n-1][i-n];
 
        }
        encontrei[0] = acharPerdido(encontrei);
        for(int i = 0;i<n*2;i++){
            cout << encontrei[i] << ' ';
        }
        
    }
}