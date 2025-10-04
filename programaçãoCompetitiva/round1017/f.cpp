#include <bits/stdc++.h>
using namespace std;
int melhor(vector<int>n,int maior ,int outro){
  
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
 
    int t,n,m,a;
    cin >> t;
    while(t--) {
        cin >> n  >> m >> a;
        vector<vector<int>>num(m,vector<int>(n,0));
        int valor = 1;
        for(int j = 0;j<n;j++){
            int i;
            for(i = 0;i<m;i++){
                num[i][j] = valor;
                valor++;
                if(valor > a) valor=1;
                if(m % a == 0) 
                    if(j%2 == 1 && i != 0)swap(num[i][j],num[i-1][j]);
            }
        }
        for(int j = 0;j<n;j++){
            for(int i = 0;i<m;i++){
                cout << num[i][j] << ' ';
            }
            cout << "\n";
        }
        
    }
}