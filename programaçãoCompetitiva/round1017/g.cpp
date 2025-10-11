#include <bits/stdc++.h>
using namespace std;
#define int long long
signed main() {
    int t,n;
    cin >> t;
    while(t--) {
        int dp[200000];
        dp[0] = 0;                
        cin >> n;
        deque<int>vec;
        int soma = 0;
        bool reverse = false;
        for(int k = 1;k<=n;k++){
            int op,v;
            cin >> op;
            if(op == 1){
                int pagina = 0;
                int posicao = 0;
                if(reverse){
                    v = vec[0];
                    pagina = vec[posicao];
                    posicao = vec.size()-1;
                    vec.pop_front();
                    vec.push_back(v);
                }else{
                    posicao = vec.size()-1;
                    pagina = vec[posicao];
                    v = vec[vec.size()-1];
                    vec.pop_back();
                    vec.push_front(v);
                }
                dp[k] = dp[k-1]  - pagina*(posicao+1) + soma; 
            }else if(op == 2){
                int posicao;
                dp[k] = (vec.size()+1)*soma - dp[k-1];  
                reverse = !reverse;
            }else if (op == 3){
                cin >> v;
                if(reverse) {
                    vec.insert(vec.begin(), v);
                }else vec.emplace_back(v);
                soma += v;
                dp[k] = v*vec.size() + dp[k-1]; 
            }
        }
        for(int i = 1;i<=n;i++){
            cout << dp[i] << '\n';
        }
    }     
}