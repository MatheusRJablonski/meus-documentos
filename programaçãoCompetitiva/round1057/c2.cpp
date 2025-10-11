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
        for (int i = 0; i < n; i++) {
            cin >> v[i];
        }
        for (int x : v) {
            freq[x]++; 
        }
        for (auto par : freq) {
            int comp = par.first;
            int quant = par.second; 
            // *** CORREÇÃO: Lógica correta de pares ***
            if(quant >= 2){
                int num_pares = quant / 2;
                for(int i = 0; i < num_pares; i++){
                    pares.emplace_back(comp);
                }
            } 
            if(quant % 2 == 1)sozz.emplace_back(comp);
        }
        sort(pares.begin(), pares.end());
        sort(sozz.begin(), sozz.end());
        
        for (int i = 0;i<pares.size();i++){
            soma += pares[i]*2;
            somatermos += 2;
        }
        
        int s = sozz.size();
        int sozz_adicionado = -1;
        
        // *** CORREÇÃO: Removida a duplicação ***
        if(s >= 1 && pares.size() > 0){
            for(int i = s-1; i >= 0; i--){
                if(2 * pares[0] > sozz[i]){
                    sozz_adicionado = sozz[i];
                    soma += sozz[i];  // *** SÓ UMA VEZ ***
                    somatermos += 1;
                    break;
                }
            }
        }
        
        if(somatermos == 3){
            if(sozz_adicionado != -1){
                cout << ehtriangulo(pares[0], sozz_adicionado) << '\n';
            } else {
                cout << 0 << '\n';
            }
        }else if (pares.size() >= 2 && somatermos >= 4){
            int maior_lado = pares.back() * 2;
            int soma_outros = soma - maior_lado;
            if(maior_lado < soma_outros){
                cout << soma << '\n';
            } else {
                cout << 0 << '\n';
            }
        }else{
            cout << 0 << '\n';
        }
    }
}