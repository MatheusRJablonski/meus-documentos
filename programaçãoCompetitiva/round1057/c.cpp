#include <bits/stdc++.h>
using namespace std;
#define int long long
int ehtriangulo(int a,int c){
    if(a+a > c)return a+a+c;
    else return 0;
}
signed main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    int n;
    cin >> t;
    while (t--) {
        cin >> n;
        vector<int> v(n);
        map<int, int> freq;
        vector<int>pares;
        vector<int>sozz;
        int soma = 0;
        int somatermos = 0;
        bool logo = true;
        for (int i = 0; i < n; i++) {
            cin >> v[i];
            freq[v[i]]++; 
        }
        for (auto par : freq) {
            int comp = par.first;
            int quant = par.second; 
            int k = 1;
            if(quant > 1){
                while(quant >= k*2){
                    pares.emplace_back(comp);
                    k++;
                }
            } 
            if(quant % 2 == 1)sozz.emplace_back(comp);
        }
        sort(pares.begin(), pares.end());
        sort(sozz.begin(), sozz.end());
        if(sozz.size() != 0)soma += sozz[sozz.size()-1];
        bool valido = false;
        int dist = soma;
        for(int i = 0; i<pares.size();i++){
            dist -= pares[i]*2;
            soma += pares[i]*2;
            if(dist < 0) valido = true;
        }
        if(!valido){
            if(sozz.size() != 0)soma -= sozz[sozz.size()-1];
            int r = sozz.size()-1;
            int l = r-1;
            while(l >= 0){
                int dist2 = abs(sozz[r] - sozz[l]);
                if(dist2 < soma){
                    soma += sozz[r] + sozz[l];
                    valido = true;
                    break;
                }
                l--;
                r--;
            }
            if(!valido){
                for(int i = 0; i < sozz.size();i++)
                if(sozz[i] < soma){
                    soma += sozz[i];
                    valido = true;
                    break;
                }
            }

        }else{
            for(int i = sozz.size()-1;i>=0;i--){
                if(sozz[i] < soma && sozz[i] != sozz[sozz.size()-1]){
                    soma += sozz[i];
                    break;
                }
            }
        }
        if(pares.size() == 0 || pares[0]*2 == soma) soma = 0;
        cout << soma << endl;
    }
}

