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
        }
        for (int x : v) {
            freq[x]++; 
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
        for (int i = 0;i<pares.size();i++){
            soma += pares[i]*2;
            somatermos += 2;
        }
        int s = sozz.size();
        bool apenas = false;
        vector<int>a(2);
        if(s > 1){
            for(int i = s-1; i >= 0;i--){
                a[apenas] = sozz[i];
                apenas = !apenas;
                if(!apenas){
                    if(abs(a[0]-a[1]) < soma){
                        soma+=a[0]+a[1];
                        somatermos += 2;
                        logo = true;
                        break;
                    }else{
                        logo = false;
                    }
                }
            }
        }else if(s==1 && soma > sozz[0]){
            soma+=sozz[0];
            somatermos++;
        }
        if(somatermos == 3){
            cout << ehtriangulo(pares[0],sozz[0])<< '\n';
        }else if (pares.size() >= 1 && somatermos > 3){
            cout << soma << '\n' ;
        }else{
            cout << 0 << '\n';
        }
    }
}
/*
5
3
5 5 7
3
4 5 7
3
5 5 10
7
4 3 5 1 5 3 3
4
2 3 5 7
 */
